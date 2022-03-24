package com.twitter.service.persistence.jpa.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private UUID id;
    private String userName;
    private String name;
    private String avatar;
    private String email;
    private String bio;
    private Long followerCount;
    private Long followingCount;
    private Boolean verified;
}
