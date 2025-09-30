package com.example.Mini_store_app.admin.news.mapper;

import org.mapstruct.Mapper;

import com.example.Mini_store_app.admin.news.dto.NewsDto;
import com.example.Mini_store_app.admin.news.entity.News;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDto toDTO(News news);

    News toEntity(NewsDto newsDto);
}
