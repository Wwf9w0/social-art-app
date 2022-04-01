package com.twitter.service.persistence.jpa.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private String id;
    private String text;
    private String userId;
    private List<String> images = new ArrayList<>(4);
    private Long likeCount;
    private Long repostCount;
    private String originalPostId;
    private String replyToId;
    private Date timestemp;
    private List<String> hashtags = new ArrayList<>();
    private List<String> mentions = new ArrayList<>();

}