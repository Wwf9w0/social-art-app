package com.twitter.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hashtag")
public class HashtagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String tag;

    @Column(columnDefinition = "BIGINT(20) default '1'", nullable = false)
    private Long recentPostCount;

    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HashtagPostEntity> hashtagPostEntities;

    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
