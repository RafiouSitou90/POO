package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class PostRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @Size(min = 10)
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
