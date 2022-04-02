package com.twitter.service.persistence.jpa.service;

import com.twitter.service.persistence.jpa.converter.UserEntityConverter;
import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import com.twitter.service.persistence.jpa.repository.UserRepository;
import com.twitter.service.persistence.jpa.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPersistenceService {

    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;

    public UserDto getUserByUserName(String name) {
        UserEntity user =  userRepository.findByUserName(name);
        return userEntityConverter.toDto(user);
    }

    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.getById(userId);
        return userEntityConverter.toDto(userEntity);
    }

    public void addUser(UserDto user) {
        userEntityConverter.toEntityOfDto(user);
    }

    public UserDto editUser(UserRequest request) {
        UserEntity userEntity = userEntityConverter.toEntity(request);
        log.info("edited user - {}", userEntity.toString());
        return userEntityConverter.toDto(userRepository.save(userEntity));
    }

    public boolean addFollower(String userId, String followerId) {
        UserEntity user = userRepository.getById(userId);
        user.setFollower(followerId);
        userRepository.save(user);
        log.info("added follower - {}", user.getFollower());
        return true;
    }

    public boolean addFollowing(String userId, String followingId) {
        UserEntity user = userRepository.getById(userId);
        user.setFollowing(followingId);
        userRepository.save(user);

        log.info("added following : {}", user.getFollowing());
        return true;
    }

    public boolean removeFollower(String followerId, String userId) {
        UserEntity user = userRepository.getById(userId);
        user.removeFollower(followerId);
        userRepository.save(user);
        log.info("removed follower - {}", user.getFollower());
        return true;
    }

    public Map<String, Date> getFollowers(String userId) {
        Optional<UserEntity> users = userRepository.findById(userId);
        log.info("list followers - {}", users.get().getFollower());
        return users.get().getFollower();
    }

    public Map<String, Date> getFollowings(String userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        log.info("list followings - {}", user.get().getFollowing());
        return user.get().getFollowing();
    }

    public Optional<UserEntity> findByUserId(String userId){
        return userRepository.findById(userId);
    }
}
