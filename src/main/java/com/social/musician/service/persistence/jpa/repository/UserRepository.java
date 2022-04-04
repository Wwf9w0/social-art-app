package com.social.musician.service.persistence.jpa.repository;

import com.social.musician.service.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);
}
