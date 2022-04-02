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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hashtag")
public class HashTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String tag;

    @Column(nullable = false)
    private Long recentPostCount = 1L;

    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HashTagPostEntity> hashtagPostEntities;

    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> posts;

    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Column
    private boolean isUsed = false;
}
