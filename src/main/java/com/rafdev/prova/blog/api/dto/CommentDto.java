package com.rafdev.prova.blog.api.dto;

import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.User;

import java.util.Date;

public class CommentDto {

    private Long id;
    private String content;
    private String user;
    private String post;
    private Date publishedAt;

    public CommentDto(Long id, String content, User user, Post post, Date publishedAt) {
        this.id = id;
        this.content = content;

        if (user != null) {
            this.user = getResourcePath("users", user.getId());
        }

        if (post != null) {
            this.post = getResourcePath("posts", post.getId());
        }

        this.publishedAt = publishedAt;
    }

    public CommentDto(Long id, String content, User user, Date publishedAt) {
        this.id = id;
        this.content = content;

        if (user != null) {
            this.user = getResourcePath("users", user.getId());
        }

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

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    private String getResourcePath(String resourceName, Long resourceId) {
        return String.format("/api/v1/%s/%s", resourceName, resourceId);
    }
}
