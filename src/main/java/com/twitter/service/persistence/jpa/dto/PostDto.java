package com.twitter.service.persistence.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private String id;
    private String text;
    private String userId;
    private Map<String, Date> images;
    private Long likeCount;
    private Long repostCount;
    private String originalPostId;
    private String replyToId;
    private Date timestemp;
    private Map<String, Date> hashtags;
    private Map<String, Date> mentions;
}