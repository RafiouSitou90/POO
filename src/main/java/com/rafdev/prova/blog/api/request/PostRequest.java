package com.rafdev.prova.blog.api.request;

import java.time.LocalDateTime;

public class PostRequest {

    private String title;
    private String content;
    private String imageUrl;
    private Long userId;
    private Long categoryId;
    private LocalDateTime publishedAt;

    public PostRequest(String title, String content, String imageUrl, Long userId, Long categoryId, LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.categoryId = categoryId;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
}
