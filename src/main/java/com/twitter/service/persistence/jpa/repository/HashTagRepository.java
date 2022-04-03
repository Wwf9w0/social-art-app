package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long> {

    HashTagEntity findByTag(String tag);
    List<HashTagEntity> findByTagIn(List<String> tags);
}