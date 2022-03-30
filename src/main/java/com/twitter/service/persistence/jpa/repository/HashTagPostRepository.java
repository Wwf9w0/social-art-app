package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.HashTagEntity;
import com.twitter.service.persistence.jpa.entity.HashTagPostEntity;
import com.twitter.service.persistence.jpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagPostRepository extends JpaRepository<HashTagPostEntity, String> {

    List<PostEntity> findByHashTags(HashTagEntity hashTags);
}