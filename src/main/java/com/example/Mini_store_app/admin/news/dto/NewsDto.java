package com.example.Mini_store_app.admin.news.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class NewsDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Instant createdDate;
    private Instant modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private Boolean deleted;
}
