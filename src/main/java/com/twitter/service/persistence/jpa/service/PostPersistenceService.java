package com.twitter.service.persistence.jpa.service;

import com.twitter.service.persistence.jpa.converter.PostEntityConverter;
import com.twitter.service.persistence.jpa.converter.UserEntityConverter;
import com.twitter.service.persistence.jpa.dto.PostDto;
import com.twitter.service.persistence.jpa.entity.*;
import com.twitter.service.persistence.jpa.repository.*;
import com.twitter.service.persistence.jpa.request.PostRequest;
import com.twitter.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    public PostDto getPost(Long postId) {
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
        UserEntity user = userRepository.findById(request.getUserId()).orElse(null);
        postEntity.setUserPosts(user);
      /*  if (Objects.nonNull(request.getTag())){
            hashTagPost.get().setUsed(true);
        }*/
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
    public boolean deletePost(Long postId) {
        if (Optional.ofNullable(getPost(postId)).isPresent()) {
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }

    @Transactional
    public long addLike(Long postId, Long userId) {
        PostEntity post = getPostById(postId);
        post.incrementLikeCount();
        UserEntity user = getUser(userId);
        LikeEntity likeMapping = new LikeEntity();
        likeMapping.setUser(user);
        likeMapping.setPostLikes(post);
        try {
            likeRepository.save(likeMapping);
            postRepository.save(post);
            return post.getLikeCount();

        } catch (Exception e) {
            log.error("Cannot Save Like Entity");
            // TODO exception handler add
            throw new RuntimeException();
        }
    }

    @Transactional
    public long removeLike(Long postId, Long userId) {
        PostEntity post = getPostById(postId);
        post.decrementLikeCount();
        UserEntity user = getUser(userId);

        try {
            likeRepository.deleteByPostLikesIdAndUserId(post.getId(), user.getId());
            postRepository.save(post);
            return post.getLikeCount();

        } catch (Exception e) {
            log.error("Cannot Save Like Entity");
            // TODO exception handler add
            throw new RuntimeException();
        }
    }

    private UserEntity getUser(Long userId) {
        return userEntityConverter
                .toEntityOfDto(userService.getUserByUserId(userId));
    }

    private PostEntity getPostById(Long postId) {
        return postEntityConverter
                .toPostEntity(getPost(postId));
    }


}
