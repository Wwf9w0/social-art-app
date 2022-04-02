package com.twitter.service.persistence.jpa.converter;

import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.entity.UserEntity;
import com.twitter.service.persistence.jpa.repository.UserRepository;
import com.twitter.service.persistence.jpa.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class UserEntityConverter {

    private final UserRepository userRepository;

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

    public UserEntity toEntity(UserRequest request){
        return UserEntity.builder()
                .avatar(request.getAvatar())
                .bio(request.getBio())
                .email(request.getEmail())
                .verified(request.getVerified())
                .name(request.getName())
                .userName(request.getUserName())
                .followerCount(request.getFollowerCount())
                .followingCount(request.getFollowingCount())
                .build();
    }

    public UserEntity toEntityOfDto(UserDto userDto){
        UserEntity user = new UserEntity();
        String id = UUID.randomUUID().toString();
        user.setId(id);
        user.setAvatar(userDto.getAvatar());
        user.setBio(userDto.getBio());
        user.setEmail(userDto.getEmail());
        user.setVerified(userDto.getVerified());
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setFollowingCount(userDto.getFollowingCount());
        user.setFollowerCount(userDto.getFollowerCount());
        return userRepository.save(user);
    }

}
