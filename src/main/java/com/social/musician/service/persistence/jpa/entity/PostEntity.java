package com.social.musician.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @CreatedBy
    private UserEntity userPosts;

    @ElementCollection
    private List<String> images = new ArrayList<>(4);

    @Column(nullable = false)
    private Long likeCount = 0L;

    @Column(nullable = false)
    private Long repostCount = 0L;

    @Column
    private String originalPostId;

    @Column
    private String replyToId;

    @CreatedDate
    private Date timestamp;

    @UpdateTimestamp
    private Date updatedAt;

    @ElementCollection
    private List<String> hashtags = new ArrayList<>();

    @ElementCollection
    private List<String> mentions = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HashTagPostEntity> postHashtag;

    @OneToMany(mappedBy = "postLikes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeEntity> postLikes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private HashTagEntity hashTag;

    public long incrementLikeCount(){
        return ++likeCount;
    }

    public long decrementLikeCount(){
        return (likeCount < 1) ? 0 : --likeCount;
    }

    public long incrementRepostCount(){
        return ++repostCount;
    }

    public long decrementRepostCount(){
        return (repostCount < 1) ? 0 : --repostCount;
    }
}
