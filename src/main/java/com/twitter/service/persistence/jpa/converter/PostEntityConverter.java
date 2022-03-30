package com.twitter.service.persistence.jpa.converter;


import com.twitter.service.persistence.jpa.dto.PostDto;
import com.twitter.service.persistence.jpa.entity.PostEntity;
import javafx.geometry.Pos;
import org.springframework.stereotype.Component;

@Component
public class PostEntityConverter {

    public PostDto toPostDto(PostEntity postEntity){
        return PostDto.builder()
                .id(postEntity.getId())
                .userId(postEntity.getUserPosts().getId())
                .hashtags(postEntity.getHashtags())
                .images(postEntity.getImages())
                .likeCount(postEntity.getLikeCount())
                .originalPostId(postEntity.getOriginalPostId())
                .replyToId(postEntity.getReplyToId())
                .repostCount(postEntity.getRepostCount())
                .mentions(postEntity.getMentions())
                .text(postEntity.getText())
                .timestemp(postEntity.getTimestamp())
                .build();
    }
}
