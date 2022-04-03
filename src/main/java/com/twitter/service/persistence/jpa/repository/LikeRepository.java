package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.LikeEntity;
import com.twitter.service.persistence.jpa.entity.PostEntity;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    LikeEntity deleteByPostLikesIdAndUserId(Long postId, Long userId);
}
