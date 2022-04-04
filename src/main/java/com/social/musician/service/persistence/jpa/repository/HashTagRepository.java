package com.social.musician.service.persistence.jpa.repository;

import com.social.musician.service.persistence.jpa.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long> {

    HashTagEntity findByTag(String tag);
    List<HashTagEntity> findByTagIn(List<String> tags);
}