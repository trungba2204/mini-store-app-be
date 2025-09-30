package com.example.Mini_store_app.admin.news.service;

import java.util.List;

import com.example.Mini_store_app.admin.news.dto.NewsDto;

public interface NewService {
    
    NewsDto createNews(NewsDto newsDto);

    List<NewsDto> getAllNews();

    NewsDto updateNews(NewsDto newsDto);

    NewsDto getNewsById(Long newsId);

    NewsDto deleteNews(Long newsId);
}
