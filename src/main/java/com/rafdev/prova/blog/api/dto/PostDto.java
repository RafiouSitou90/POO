package com.rafdev.prova.blog.api.dto;

import com.rafdev.prova.blog.api.entity.Category;
import com.rafdev.prova.blog.api.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDto {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String user = null;
    private String category = null;
    private LocalDateTime publishedAt;
    private List<CommentDto> comments = new ArrayList<>();

    public PostDto(Long id, String title, String content, String imageUrl, User user, Category category,
                   LocalDateTime publishedAt, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;

        if (user != null) {
            this.user = getResourcePath("users", user.getId());
        }

        if (category != null) {
            this.category = getResourcePath("categories", category.getId());
        }

        this.publishedAt = publishedAt;
        this.comments = comments;
    }

    public PostDto(Long id, String title, String content, String imageUrl, User user, Category category,
                   LocalDateTime publishedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;

        if (user != null) {
            this.user = getResourcePath("users", user.getId());
        }

        if (category != null) {
            this.category = getResourcePath("categories", category.getId());
        }

        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    private String getResourcePath(String resourceName, Long resourceId) {
        return String.format("/api/v1/%s/%s", resourceName, resourceId);
    }
}
