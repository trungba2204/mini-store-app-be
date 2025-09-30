package com.example.Mini_store_app.admin.news.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "news_title")
    private String title;

    @Column(name = "news_description")
    private String description;
    
}
