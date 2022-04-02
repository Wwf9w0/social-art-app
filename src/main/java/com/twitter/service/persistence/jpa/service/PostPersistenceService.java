package com.twitter.service.persistence.jpa.service;

import com.twitter.service.persistence.jpa.converter.PostEntityConverter;
import com.twitter.service.persistence.jpa.dto.PostDto;
import com.twitter.service.persistence.jpa.entity.HashTagEntity;
import com.twitter.service.persistence.jpa.entity.HashTagPostEntity;
import com.twitter.service.persistence.jpa.entity.PostEntity;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import com.twitter.service.persistence.jpa.repository.HashTagPostRepository;
import com.twitter.service.persistence.jpa.repository.LikeRepository;
import com.twitter.service.persistence.jpa.repository.PostRepository;
import com.twitter.service.persistence.jpa.repository.UserRepository;
import com.twitter.service.persistence.jpa.request.PostRequest;
import com.twitter.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        posts.stream().forEach(post ->
                        postDtos.add(postEntityConverter.toPostDto(post)));
        return postDtos;
    }

    public PostDto getPost(String postId){
        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isPresent()){
            return postEntityConverter.toPostDto(post.get());
        }
        return null;
    }

    public PostDto addPost(PostRequest request){
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

                } );
        postEntity.setPostHashtag(hashTagPostEntityArrayList);
        hashTagPostRepository.saveAll(hashTagPostEntityArrayList);
        postRepository.save(postEntity);
        return postEntityConverter.toPostDto(postEntity);
    }

}
