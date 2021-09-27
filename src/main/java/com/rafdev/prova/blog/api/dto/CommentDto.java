package com.rafdev.prova.blog.api.dto;

import com.rafdev.prova.blog.api.entity.Comment;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.User;

import java.time.LocalDateTime;

public class CommentDto {

    private Long id;
    private String content;
    private String user;
    private String post;
    private LocalDateTime publishedAt;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();

        if (comment.getUser() != null) {
            this.user = getResourcePath("users", comment.getUser().getId());
        }

        if (comment.getPost() != null) {
            this.post = getResourcePath("posts", comment.getPost().getId());
        }

        this.publishedAt = comment.getPublishedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    private String getResourcePath(String resourceName, Long resourceId) {
        return String.format("/api/v1/%s/%s", resourceName, resourceId);
    }
}
