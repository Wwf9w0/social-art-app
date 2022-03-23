package com.twitter.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashtagPostEntity {

    @Id
    private UUID id;

    private HashtagEntity hashtag;

    private PostEntity post;
}
