package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.LikeEntity;
import com.twitter.service.persistence.jpa.entity.PostEntity;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<LikeEntity, String> {

    LikeEntity deleteByPostLikesIdAndUserId(String postId, String userId);
}
