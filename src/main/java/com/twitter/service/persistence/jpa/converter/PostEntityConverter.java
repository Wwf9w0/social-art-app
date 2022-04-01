package com.twitter.service.persistence.jpa.converter;


import com.twitter.service.persistence.jpa.dto.PostDto;
import com.twitter.service.persistence.jpa.entity.PostEntity;
import com.twitter.service.persistence.jpa.request.PostRequest;
import org.springframework.stereotype.Component;


@Component
public class PostEntityConverter {

    public PostDto toPostDto(PostEntity postEntity) {
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

    public PostDto toDto(PostRequest request) {
        return PostDto.builder()
                .id(request.getId())
                .hashtags(request.getHashtags())
                .images(request.getImages())
                .likeCount(request.getLikeCount())
                .originalPostId(request.getOriginalPostId())
                .replyToId(request.getReplyToId())
                .repostCount(request.getRepostCount())
                .mentions(request.getMentions())
                .text(request.getText())
                .build();
    }


    public PostEntity toPostEntity(PostDto postDto){
        PostEntity postEntity = new PostEntity();
        postEntity.setHashtags(postDto.getHashtags());
        postEntity.setId(postEntity.getId());
        postEntity.setOriginalPostId(postDto.getOriginalPostId());
        postEntity.setHashtags(postDto.getHashtags());
        postEntity.setImages(postDto.getImages());
        postEntity.setReplyToId(postEntity.getReplyToId());
        postEntity.setRepostCount(postDto.getRepostCount());
        postEntity.setMentions(postDto.getMentions());
        postEntity.setText(postDto.getText());
        postEntity.setLikeCount(postDto.getLikeCount());
        return postEntity;
    }
}
