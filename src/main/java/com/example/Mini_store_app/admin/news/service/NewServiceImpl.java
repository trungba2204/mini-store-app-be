package com.example.Mini_store_app.admin.news.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.Mini_store_app.admin.news.dto.NewsDto;
import com.example.Mini_store_app.admin.news.entity.News;
import com.example.Mini_store_app.admin.news.mapper.NewsMapper;
import com.example.Mini_store_app.admin.news.repo.NewsRepository;
import com.example.Mini_store_app.user.User;
import com.example.Mini_store_app.user.UserRepository;

@Service
public class NewServiceImpl implements NewService {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    private final UserRepository userRepository;

    public NewServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper,UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.userRepository = userRepository;
    }

    @Override
    public NewsDto createNews(NewsDto newsDto){
        News news = newsMapper.toEntity(newsDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);

        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            news.setCreatedBy(String.valueOf(currentUser));
        }
        Instant currentDate = Instant.now();
        news.setCreatedDate(currentDate);
        News savedNews = newsRepository.save(news);
        return newsMapper.toDTO(savedNews);
    }

    @Override
    public List<NewsDto> getAllNews() {
       List<News> news = newsRepository.findAll();
       return news.stream().map(newsMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public NewsDto updateNews(NewsDto newsDto) {
        News news = newsMapper.toEntity(newsDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);

        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            news.setModifiedBy(String.valueOf(currentUser));
        }
        Instant currentDate = Instant.now();
        news.setModifiedDate(currentDate);
        News savedNews = newsRepository.save(news);
        return newsMapper.toDTO(savedNews);
    }

    @Override
    public NewsDto getNewsById(Long newsId) {
        News news = newsRepository.findById(newsId).orElse(null);
        return newsMapper.toDTO(news);
    }

    @Override
    public NewsDto deleteNews(Long newsId) {
        News news = newsRepository.findById(newsId).orElse(null);
        news.setDeleted(true);
        News savedNews = newsRepository.save(news);
        return newsMapper.toDTO(savedNews);
    }

    

}
