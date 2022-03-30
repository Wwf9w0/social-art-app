package com.twitter.service.persistence.jpa.converter;

import com.twitter.service.persistence.jpa.dto.HashTagDto;
import com.twitter.service.persistence.jpa.entity.HashTagEntity;
import org.springframework.stereotype.Component;

@Component
public class HashTagEntityConverter {

    public HashTagDto toHashTagDto(HashTagEntity hashTagEntity) {
        return HashTagDto.builder()
                .id(hashTagEntity.getId())
                .recentPostCount(hashTagEntity.getRecentPostCount())
                .tag(hashTagEntity.getTag())
                .build();
    }

    public HashTagEntity toHashTagEntity(HashTagDto hashTagDto) {
        HashTagEntity hashTagEntity = new HashTagEntity();
        hashTagEntity.setId(hashTagDto.getId());
        hashTagEntity.setTag(hashTagDto.getTag());
        hashTagEntity.setRecentPostCount(hashTagDto.getRecentPostCount());
        hashTagEntity.setId(hashTagDto.getId());
        return hashTagEntity;
    }
}
