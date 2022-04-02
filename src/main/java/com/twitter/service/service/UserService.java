package com.twitter.service.service;

import com.twitter.service.persistence.jpa.converter.UserEntityConverter;
import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import com.twitter.service.persistence.jpa.repository.UserRepository;
import com.twitter.service.persistence.jpa.request.UserRequest;
import com.twitter.service.persistence.jpa.service.UserPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserPersistenceService userPersistenceService;
    private final UserEntityConverter userEntityConverter;

    public void saveUser(UserRequest request) {
        try{
            UserEntity user = userEntityConverter.toEntity(request);
            userPersistenceService.addUser(userEntityConverter.toDto(user));
        }catch (Exception e){
            //TODO exception handler added
            log.error("Save User Exception : {}", e);
        }
    }

    public UserDto getUserByName(String name) {
        return userPersistenceService.getUserByUserName(name);
    }

    public UserDto getUserByUserId(String userId) {
        return userPersistenceService.getUserByUserId(userId);
    }

    public UserDto updateUser(UserRequest request) {
        return userPersistenceService.editUser(request);
    }

    public boolean addFollower(String userId, String followerId) {
        return userPersistenceService.addFollower(userId, followerId);
    }

    public boolean addFollowing(String userId, String followingId) {
        return userPersistenceService.addFollowing(userId, followingId);
    }

    public boolean removeFollower(String followerId, String userId) {
        return userPersistenceService.removeFollower(followerId, userId);
    }

    public Map<String, Date> getFollowers(String userId) {
        return userPersistenceService.getFollowers(userId);
    }

    public Map<String, Date> getFollowings(String userId) {
        return userPersistenceService.getFollowings(userId);
    }
}
