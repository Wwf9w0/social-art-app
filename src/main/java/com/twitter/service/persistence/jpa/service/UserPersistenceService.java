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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPersistenceService {

    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;

    public UserDto getUserByUserName(String userName){
        return userEntityConverter.toDto(userRepository.findByUserName(userName));
    }

    public UserDto getUserByUserId(String userId){
        return userEntityConverter.toDto(userRepository.getById(userId));
    }

    public UserDto addUser(UserRequest userRequest){
        UserEntity userEntity = userEntityConverter.toEntity(userRequest);
        UserEntity user = userRepository.save(userEntity);
        log.info("user added- {}", user.getUserName());
        return userEntityConverter.toDto(user);
    }

    public UserDto editUser(UserRequest request){

        UserEntity userEntity = userEntityConverter.toEntity(request);
        log.info("edited user - {}" , userEntity.toString());
        return userEntityConverter.toDto(userRepository.save(userEntity));
    }

    public boolean addFollower(String followerId, String userId){
        UserEntity user = userRepository.getById(userId);
        user.setFollower(followerId);
        userRepository.save(user);
        log.info("added follower - {}", user.getFollower());
        return true;
    }

    public boolean removeFollower(String  followerId, String userId){
        UserEntity user = userRepository.getById(userId);
        user.removeFollower(followerId);
        userRepository.save(user);
        log.info("removed follower - {}", user.getFollower());
        return true;
    }

    public List<UserDto> getFollowers(String userId){
        List<UserDto> followers = new ArrayList<>();
        UserEntity user = userRepository.getById(userId);
        List<UserEntity> users = userRepository.findAllById(user.getFollower().keySet());
        Optional.ofNullable(users)
                .ifPresent(userList -> userList.forEach(eachUser -> followers.add(userEntityConverter.toDto(eachUser))));
        log.info("list followers - {}", user.getFollower());
        return followers;
    }

    public List<UserDto> getFollowings(String userId){
        List<UserDto> followings = new ArrayList<>();
        UserEntity user = userRepository.getById(userId);
        List<UserEntity> users = userRepository.findAllById(user.getFollower().keySet());
        Optional.ofNullable(users)
                .ifPresent(userList -> userList.forEach(eachUser -> followings.add(userEntityConverter.toDto(eachUser))));
        log.info("list followings - {}", user.getFollower());
        return followings;
    }
}
