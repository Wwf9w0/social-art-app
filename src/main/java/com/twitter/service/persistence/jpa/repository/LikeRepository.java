package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    LikeEntity deleteByPostLikesIdAndUserId(Long postId, Long userId);
}
