package com.twitter.service.persistence.jpa.service;

import com.twitter.service.persistence.jpa.converter.UserEntityConverter;
import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import com.twitter.service.persistence.jpa.repository.UserRepository;
import com.twitter.service.persistence.jpa.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPersistenceService {

    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;

    public UserDto getUserByUserName(String userName){
        return userEntityConverter.toDto(userRepository.findByUserName(userName));
    }

    public UserDto getUserByUserId(UUID userId){
        return userEntityConverter.toDto(userRepository.getById(userId));
    }

    public UserDto addUser(UserRequest userRequest){
        UserEntity userEntity = userEntityConverter.toEntity(userRequest);
        return userEntityConverter.toDto(userRepository.save(userEntity));
    }

    public UserDto editUser(UserRequest request){
        if (Objects.isNull(request.getId())){
            return null;
        }
        UserEntity userEntity = userEntityConverter.toEntity(request);
        return userEntityConverter.toDto(userRepository.save(userEntity));
    }

    public boolean addFollower(UUID followerId, UUID userId){
        UserEntity user = userRepository.getById(userId);
        user.setFollower(followerId);
        userRepository.save(user);
        return true;
    }

    public boolean removeFollower(UUID followerId, UUID userId){
        UserEntity user = userRepository.getById(userId);
        user.removeFollower(followerId);
        userRepository.save(user);
        return true;
    }

    public List<UserDto> getFollowers(UUID userId){
        List<UserDto> followers = new ArrayList<>();
        UserEntity user = userRepository.getById(userId);
        List<UserEntity> users = userRepository.findAllById(user.getFollower().keySet());
        Optional.ofNullable(users)
                .ifPresent(userList -> userList.forEach(eachUser -> followers.add(userEntityConverter.toDto(eachUser))));
        return followers;
    }

    public List<UserDto> getFollowings(UUID userId){
        List<UserDto> followings = new ArrayList<>();
        UserEntity user = userRepository.getById(userId);
        List<UserEntity> users = userRepository.findAllById(user.getFollower().keySet());
        Optional.ofNullable(users)
                .ifPresent(userList -> userList.forEach(eachUser -> followings.add(userEntityConverter.toDto(eachUser))));
        return followings;
    }
}
