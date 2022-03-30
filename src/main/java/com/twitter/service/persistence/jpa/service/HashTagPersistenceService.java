package com.twitter.service.persistence.jpa.service;

import com.twitter.service.persistence.jpa.converter.HashTagEntityConverter;
import com.twitter.service.persistence.jpa.converter.PostEntityConverter;
import com.twitter.service.persistence.jpa.dto.HashTagDto;
import com.twitter.service.persistence.jpa.dto.PostDto;
import com.twitter.service.persistence.jpa.entity.HashTagEntity;
import com.twitter.service.persistence.jpa.entity.PostEntity;
import com.twitter.service.persistence.jpa.repository.HashTagPostRepository;
import com.twitter.service.persistence.jpa.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class HashTagPersistenceService {

    private final HashTagRepository hashTagRepository;
    private final HashTagEntityConverter hashTagEntityConverter;
    private final PostEntityConverter postEntityConverter;
    private final HashTagPostRepository hashTagPostRepository;

    public List<HashTagDto> getHasTags(){
        List<HashTagEntity> hashTagEntities = hashTagRepository.findAll();
        List<HashTagDto> hashTagDtos = new ArrayList<>();
        hashTagEntities.stream().forEach(hashTagEntity -> {
            hashTagDtos.add(hashTagEntityConverter.toHashTagDto(hashTagEntity));
        });
        return hashTagDtos;
    }

    public List<PostDto> getPosts(String tag){
        HashTagEntity hashTag = hashTagRepository.findByTag(tag);
        List<PostEntity> postList= new ArrayList<>();
        if (Objects.nonNull(hashTag)){
            postList = hashTagPostRepository.findByHashTags(hashTag);
        }
        List<PostDto> postDtos = new ArrayList<>();
        postList.stream().forEach(post -> {
            postDtos.add(postEntityConverter.toPostDto(post));
        });
        return postDtos;
    }

}
