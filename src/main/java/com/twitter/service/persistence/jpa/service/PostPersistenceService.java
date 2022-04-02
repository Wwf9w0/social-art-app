package com.twitter.service.persistence.jpa.service;

import com.twitter.service.persistence.jpa.converter.PostEntityConverter;
import com.twitter.service.persistence.jpa.converter.UserEntityConverter;
import com.twitter.service.persistence.jpa.dto.PostDto;
import com.twitter.service.persistence.jpa.entity.*;
import com.twitter.service.persistence.jpa.repository.HashTagPostRepository;
import com.twitter.service.persistence.jpa.repository.LikeRepository;
import com.twitter.service.persistence.jpa.repository.PostRepository;
import com.twitter.service.persistence.jpa.repository.UserRepository;
import com.twitter.service.persistence.jpa.request.PostRequest;
import com.twitter.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostPersistenceService {

    private final PostRepository postRepository;
    private final HashTagPostRepository hashTagPostRepository;
    private final UserService userService;
    private final HashTagPersistenceService hashTagPersistenceService;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostEntityConverter postEntityConverter;
    private final UserEntityConverter userEntityConverter;


    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        posts.stream().forEach(post ->
                postDtos.add(postEntityConverter.toPostDto(post)));
        return postDtos;
    }

    public PostDto getPost(String postId) {
        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return postEntityConverter.toPostDto(post.get());
        }
        return null;
    }

    @Transactional
    public PostDto addPost(PostRequest request) {
        List<HashTagPostEntity> hashTagPostEntityArrayList = new ArrayList<>();
        PostEntity postEntity = postEntityConverter.toDto(request);
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        postEntity.setUserPosts(user.get());
        List<HashTagEntity> hashTagEntities = hashTagPersistenceService.getHashTagsByTags(request.getHashtags());
        hashTagEntities.stream()
                .forEach(hashPost -> {
                    HashTagPostEntity hashTagPostEntity = new HashTagPostEntity();
                    hashTagPostEntity.setHashTag(hashPost);
                    hashTagPostEntity.setPost(postEntity);
                    hashTagPostEntityArrayList.add(hashTagPostEntity);

                });
        postEntity.setPostHashtag(hashTagPostEntityArrayList);
        hashTagPostRepository.saveAll(hashTagPostEntityArrayList);
        postRepository.save(postEntity);
        return postEntityConverter.toPostDto(postEntity);
    }

    @Transactional
    public boolean deletePost(String postId, String userId) {
        if (Optional.ofNullable(getPost(postId)).isPresent()) {
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }

    @Transactional
    public long addLike(String postId, String userId){
        PostEntity post = postEntityConverter.toPostEntity(getPost(postId));
        post.incrementLikeCount();
        UserEntity user = userEntityConverter.toEntityOfDto(userService.getUserByUserId(userId));
        LikeEntity likeMapping = new LikeEntity();
        likeMapping.setUser(user);
        likeMapping.setPostLikes(post);


        try {
            likeRepository.save(likeMapping);
            postRepository.save(post);
            return post.getLikeCount();

        }catch (Exception e){
            log.error("Cannot Save Like Entity");
            // TODO exception handler add
            throw  new RuntimeException();
        }
    }

    @Transactional
    public long removeLike(String postId, String userId){
        PostEntity post = postEntityConverter.toPostEntity(getPost(postId));
        post.decrementLikeCount();
        UserEntity user = userEntityConverter.toEntityOfDto(userService.getUserByUserId(userId));

        try{
            likeRepository.deleteByPostsAndUsers(post, user);
            postRepository.save(post);
            return post.getLikeCount();

        }catch (Exception e){
            log.error("Cannot Save Like Entity");
            // TODO exception handler add
            throw  new RuntimeException();        }
    }


}
