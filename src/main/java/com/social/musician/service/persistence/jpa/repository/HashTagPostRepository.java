package com.social.musician.service.persistence.jpa.repository;

import com.social.musician.service.persistence.jpa.entity.HashTagPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface HashTagPostRepository extends JpaRepository<HashTagPostEntity, Long> {
}