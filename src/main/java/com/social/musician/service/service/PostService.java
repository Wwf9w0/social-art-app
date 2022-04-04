package com.social.musician.service.service;

import com.social.musician.service.persistence.jpa.dto.PostDto;
import com.social.musician.service.persistence.jpa.request.PostRequest;
import com.social.musician.service.persistence.jpa.service.PostPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostPersistenceService postPersistenceService;

    public PostDto addPost(PostRequest postRequest){
        return postPersistenceService.addPost(postRequest);
    }

    public PostDto getPostById(Long postId){
        return postPersistenceService.getPost(postId);
    }

    public List<PostDto> getPosts(){
        return postPersistenceService.getAllPosts();
    }

    public boolean deletePost(Long postId){
        return postPersistenceService.deletePost(postId);
    }

    public long addLike(Long postId, Long userId){
        return postPersistenceService.addLike(postId, userId);
    }

    public long removeLike(Long postId, Long userId){
        return postPersistenceService.removeLike(postId, userId);
    }


}
