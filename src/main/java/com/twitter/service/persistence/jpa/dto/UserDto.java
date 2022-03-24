package com.twitter.service.persistence.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String userName;
    private String name;
    private String avatar;
    private String bio;
    private Long followerCount;
    private Long followingCount;
    private Boolean verified;
}
