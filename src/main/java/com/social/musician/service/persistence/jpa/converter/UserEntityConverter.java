package com.social.musician.service.persistence.jpa.converter;

import com.social.musician.service.persistence.jpa.repository.UserRepository;
import com.social.musician.service.persistence.jpa.entity.UserEntity;
import com.social.musician.service.persistence.jpa.dto.UserDto;
import com.social.musician.service.persistence.jpa.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
               .email(userEntity.getEmail())
               .userName(userEntity.getUserName())
               .name(userEntity.getName())
               .verified(userEntity.getVerified())
               .build();
    }

    public UserDto toDtoOpt(Optional<UserEntity> userEntity){
        return UserDto.builder()
                .id(userEntity.get().getId())
                .avatar(userEntity.get().getAvatar())
                .followerCount(userEntity.get().getFollowerCount())
                .followingCount(userEntity.get().getFollowingCount())
                .bio(userEntity.get().getBio())
                .email(userEntity.get().getEmail())
                .userName(userEntity.get().getUserName())
                .name(userEntity.get().getName())
                .verified(userEntity.get().getVerified())
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
        user.setId(userDto.getId());
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

    public UserEntity toEntity(UserDto userDto){
        return UserEntity.builder()
                .avatar(userDto.getAvatar())
                .bio(userDto.getBio())
                .email(userDto.getEmail())
                .verified(userDto.getVerified())
                .name(userDto.getName())
                .userName(userDto.getUserName())
                .followerCount(userDto.getFollowerCount())
                .followingCount(userDto.getFollowingCount())
                .build();
    }

}
