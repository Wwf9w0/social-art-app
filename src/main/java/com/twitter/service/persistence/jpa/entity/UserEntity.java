package com.twitter.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private UUID id;

    @Column
    private String userName;

    @Column
    private String name;

    @Column
    private String avatar;

    @Column
    private String bio;

    @Column
    private Long followingCount;

    @Column
    private Long followerCount;

    @Column
    private Boolean verified;
}
