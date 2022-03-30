package com.twitter.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    private String id;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @CreatedBy
    private UserEntity userPosts;

    @ElementCollection
    private Map<String, Date> images = new HashMap<>(4); // maximum of 4 img

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
    private Map<String, Date> hashtags;

    @ElementCollection
    private Map<String, Date> mentions;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HashTagPostEntity> postHashtag;

    @OneToMany(mappedBy = "postLikes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeEntity> postLikes;

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
