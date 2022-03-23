package com.twitter.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

    @Id
    private UUID id;

    @Column
    private String text;

    @Column
    private UUID userId;

    @Column
    private List<String> images = new ArrayList<>(4);

    @Column
    private Long likeCount;

    @Column
    private Long repostCount;

    @Column
    private UUID originalPostId;

    @Column
    private UUID replyToId;

    @Column
    private Date timestamp;

    @Column
    private List<String> hashtags = new ArrayList<>();

    @Column
    private List<String> mentions = new ArrayList<>();
}
