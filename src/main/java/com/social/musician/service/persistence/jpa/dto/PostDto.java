package com.social.musician.service.persistence.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String text;
    private Long userId;
    private List<String> images = new ArrayList<>(4);
    private Long likeCount;
    private Long repostCount;
    private String originalPostId;
    private String replyToId;
    private Date timestemp;
    private List<String> hashtags = new ArrayList<>();
    private List<String> mentions = new ArrayList<>();
}