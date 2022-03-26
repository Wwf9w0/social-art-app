package com.twitter.service.persistence.jpa.service;

import com.twitter.service.persistence.jpa.converter.UserEntityConverter;
import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import com.twitter.service.persistence.jpa.repository.UserRepository;
import com.twitter.service.persistence.jpa.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPersistenceService {

    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;

    public UserDto getUserByUserName(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        return userEntityConverter.toDto(userEntity);
    }

    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.getById(userId);

        return userEntityConverter.toDto(userEntity);
    }

    public void addUser(UserRequest userRequest) {
        try {
            UserEntity userEntity = userEntityConverter.toEntity(userRequest);
            log.info("user added- {}", userEntity.getUserName());
            userEntityConverter.toDto(userEntity);
        } catch (Exception e) {
            log.error("Exception : {}", e);
        }
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

    public List<UserDto> getFollowings(String userId) {
        List<UserDto> followings = new ArrayList<>();
        UserEntity user = userRepository.getById(userId);
        List<UserEntity> users = userRepository.findAllById(user.getFollower().keySet());
        Optional.ofNullable(users)
                .ifPresent(userList -> userList.forEach(eachUser -> followings.add(userEntityConverter.toDto(eachUser))));
        log.info("list followings - {}", user.getFollower());
        return followings;
    }
}
