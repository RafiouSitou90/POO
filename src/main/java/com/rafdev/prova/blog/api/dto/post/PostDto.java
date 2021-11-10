package com.rafdev.prova.blog.api.dto.post;

import com.rafdev.prova.blog.api.dto.AbstractBaseDto;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.util.UtilityFunctions;

import java.time.LocalDateTime;
import java.util.Set;

public class PostDto extends AbstractBaseDto {

    private String title;
    private String slug;
    private String content;
    private String imageUrl;
    private String user;
    private String category;
    private LocalDateTime publishedAt;
    private String status;
    private Set<String> tags;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.slug = post.getSlug();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.status = post.getStatus().name();
        this.category = getResourceUrl("categories", post.getCategory().getId());
        this.user = getResourceUrl("users", post.getUser().getId());
        this.publishedAt = post.getPublishedAt();
        this.tags = UtilityFunctions.tagsToListOfString(post.getTags());
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
