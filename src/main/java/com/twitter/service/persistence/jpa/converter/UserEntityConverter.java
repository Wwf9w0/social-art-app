package com.twitter.service.persistence.jpa.converter;

import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import com.twitter.service.persistence.jpa.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class UserEntityConverter {

    public UserDto toDto(UserEntity userEntity){
       return UserDto.builder()
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

    public UserEntity toEntity(UserRequest request){
        UserEntity user = new UserEntity();
        String id = UUID.randomUUID().toString();
        user.setId(id);
        user.setAvatar(request.getAvatar());
        user.setBio(request.getBio());
        user.setEmail(request.getEmail());
        user.setVerified(request.getVerified());
        user.setName(request.getName());
        user.setUserName(request.getUserName());
        return user;
    }
}
