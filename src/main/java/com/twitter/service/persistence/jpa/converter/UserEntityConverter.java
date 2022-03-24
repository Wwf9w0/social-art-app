package com.twitter.service.persistence.jpa.converter;

import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;


public class UserEntityConverter {

    public UserDto toDto(UserEntity userEntity){
       return UserDto.builder()
               .id(userEntity.getId())
               .avatar(userEntity.getAvatar())
               .followerCount(userEntity.getFollowerCount())
               .followingCount(userEntity.getFollowingCount())
               .bio(userEntity.getBio())
               .userName(userEntity.getUserName())
               .name(userEntity.getName())
               .verified(userEntity.getVerified())
               .build();
    }

    public List<UserDto> toDtoList(List<UserEntity> userEntities){
        return userEntities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
