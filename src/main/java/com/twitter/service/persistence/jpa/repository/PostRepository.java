package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<PostEntity, String> {
    List<PostEntity> findByPostId(String postId);
}
