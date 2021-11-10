package com.rafdev.prova.blog.api.dto.comment;

import com.rafdev.prova.blog.api.dto.AbstractBaseDto;
import com.rafdev.prova.blog.api.entity.Comment;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.User;

import java.time.LocalDateTime;

public class CommentDto extends AbstractBaseDto {

    private String content;
    private String user;
    private String post;
    private LocalDateTime publishedAt;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.user = getResourceUrl("users", comment.getUser().getId());
        this.post = getResourceUrl("posts", comment.getPost().getId());
        this.publishedAt = comment.getPublishedAt();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
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
}
