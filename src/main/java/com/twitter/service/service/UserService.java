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

    public void saveUser(UserRequest request) {
        userPersistenceService.addUser(request);
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

    public boolean addFollower(String userId, String followerId ) {
        return userPersistenceService.addFollower(userId,followerId);
    }

    public boolean removeFollower(String followerId, String userId) {
        return userPersistenceService.removeFollower(followerId, userId);
    }

    public List<UserDto> getFollowers(String userId) {
        return userPersistenceService.getFollowers(userId);
    }

    public List<UserDto> getFollowings(String userId) {
        return userPersistenceService.getFollowings(userId);
    }
}
