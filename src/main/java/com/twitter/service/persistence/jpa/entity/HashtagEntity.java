package com.twitter.service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashtagEntity {

    @Id
    private UUID id;

    @Column
    private String tag;

    @Column
    private Long recentPostCount;
}
