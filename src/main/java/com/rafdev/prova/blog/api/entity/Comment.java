package com.rafdev.prova.blog.api.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Comment extends BaseEntity {

    private Long id;
    private String content;
    private User user;
    private Post post;
    private LocalDateTime publishedAt;

    public Comment(Long id, String content, User user, Post post, LocalDateTime publishedAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.post = post;
        this.publishedAt = publishedAt;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
