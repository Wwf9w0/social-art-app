package com.twitter.service.persistence.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HashtagDto {

    private UUID id;
    private String tag;
    private Long recentPostCount;
}
