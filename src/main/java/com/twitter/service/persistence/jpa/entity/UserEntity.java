package com.twitter.service.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user",
        indexes = {@Index(columnList = "userName")})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

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

    @Column(columnDefinition = "BIGINT(20) default '0'", nullable = false)
    private Long followingCount;

    @Column(columnDefinition = "BIGINT(20) default '0'", nullable = false)
    private Long followerCount;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean verified;

    @CreatedDate
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
    private Map<UUID, Date> follower;

    @ElementCollection
    private Map<UUID, Date> following;

    public void setFollower(final UUID userId){
        follower.put(userId, new Date());
    }

    public void setFollowing(final UUID userId){
        following.put(userId, new Date());
    }

    public void removeFollower(final UUID userId){
        follower.remove(userId);
    }

    public void removeFollowing(final UUID userId){
        following.remove(userId);
    }
}
