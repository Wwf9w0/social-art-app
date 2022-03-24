package com.twitter.service.service;

import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.request.UserRequest;
import com.twitter.service.persistence.jpa.service.UserPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserPersistenceService userPersistenceService;

    public UserDto saveUser (UserRequest request){
        return userPersistenceService.addUser(request);
    }

    public UserDto getUserByName(String name){
        return userPersistenceService.getUserByUserName(name);
    }

    public UserDto getUserByUserId(UUID userId){
        return  userPersistenceService.getUserByUserId(userId);
    }

    public UserDto updateUser(UserRequest request){
        return userPersistenceService.editUser(request);
    }

    public boolean addFollower(UUID followerId, UUID userId){
        return userPersistenceService.addFollower(followerId, userId);
    }

    public boolean removeFollower(UUID followerId, UUID userId){
        return userPersistenceService.removeFollower(followerId, userId);
    }

    public List<UserDto> getFollowers(UUID userId){
        return userPersistenceService.getFollowers(userId);
    }

    public List<UserDto> getFollowings(UUID userId){
        return userPersistenceService.getFollowings(userId);
    }
}
