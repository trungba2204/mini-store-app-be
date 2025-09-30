package com.example.Mini_store_app.admin.news.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Mini_store_app.admin.news.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    
}
