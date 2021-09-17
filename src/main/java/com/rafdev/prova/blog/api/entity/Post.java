package com.rafdev.prova.blog.api.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post extends BaseEntity {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private User user;
    private Category category;
    private Date publishedAt;
    private List<Comment> comments;

    public Post(Long id, String title, String content, String imageUrl, User user, Category category, Date publishedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.user = user;
        this.category = category;
        this.publishedAt = publishedAt;
        this.comments = new ArrayList<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
