package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTagEntity, String> {
}