package com.social.musician.service.persistence.jpa.repository;

import com.social.musician.service.persistence.jpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<PostEntity, Long> {
   // List<PostEntity> findByPostId(String postId);
}
