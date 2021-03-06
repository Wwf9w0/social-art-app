package com.social.musician.service.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user",
        indexes = {@Index(columnList = "userName")})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String avatar;

    @Column(unique = true)
    private String email;

    @Column
    private String bio;

    @Column(nullable = false)
    private Long followingCount;

    @Column(nullable = false)
    private Long followerCount;

    @Column(nullable = false)
    private Boolean verified;

    @CreationTimestamp
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    @UpdateTimestamp
    private Date updatedDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LikeEntity> userLikes;

    @OneToMany(mappedBy = "userPosts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PostEntity> userPosts;

    @ElementCollection
    private Map<String, Date> follower;

    @ElementCollection
    private Map<String, Date> following;

    public UserEntity(UUID fromString) {
    }

    public void setFollower(final String userId){
        follower.put(userId, new Date());
    }

    public void setFollowing(final String userId){
        following.put(userId, new Date());
    }

    public void removeFollower(final String userId){
        follower.remove(userId);
    }

    public void removeFollowing(final String userId){
        following.remove(userId);
    }
}
