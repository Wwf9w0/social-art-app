package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.HashTagPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface HashTagPostRepository extends JpaRepository<HashTagPostEntity, Long> {
}