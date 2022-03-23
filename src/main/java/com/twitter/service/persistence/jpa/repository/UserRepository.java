package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUserName(String userName);
}
