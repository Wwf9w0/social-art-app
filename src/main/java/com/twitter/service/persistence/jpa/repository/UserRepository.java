package com.twitter.service.persistence.jpa.repository;

import com.twitter.service.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUserName(String userName);
}
