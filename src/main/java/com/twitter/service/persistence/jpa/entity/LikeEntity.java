package com.twitter.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {

    @Id
    private UUID id;

    private PostEntity post;

    private UserEntity user;
}
