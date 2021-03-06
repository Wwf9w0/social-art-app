package com.social.musician.service.controller;

import com.social.musician.service.persistence.jpa.dto.PostDto;
import com.social.musician.service.persistence.jpa.request.PostRequest;
import com.social.musician.service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.addPost(request));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping()
    public ResponseEntity<List<PostDto>> fetchAllPost() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @PostMapping("/add-like")
    public ResponseEntity<Long> addLike(@RequestParam(value = "postId") Long postId,
                                        @RequestParam(value = "userId") Long userId) {
        return ResponseEntity.ok(postService.addLike(postId, userId));
    }

    @DeleteMapping("/remove-like")
    public ResponseEntity<Long> removeLike(@RequestParam(value = "postId") Long postId,
                                           @RequestParam(value = "userId") Long userId) {
        return ResponseEntity.ok(postService.removeLike(postId, userId));
    }

    //TODO getPostByUserId add
}
