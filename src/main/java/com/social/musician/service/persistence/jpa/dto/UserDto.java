package com.social.musician.service.persistence.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String userName;
    private String name;
    private String avatar;
    private String email;
    private String bio;
    private Long followerCount;
    private Long followingCount;
    private Boolean verified;
}
