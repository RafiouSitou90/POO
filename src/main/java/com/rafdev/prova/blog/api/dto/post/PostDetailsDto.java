package com.rafdev.prova.blog.api.dto.post;

import com.rafdev.prova.blog.api.dto.AbstractBaseDto;
import com.rafdev.prova.blog.api.dto.category.CategoryDto;
import com.rafdev.prova.blog.api.dto.comment.CommentDto;
import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.util.UtilityFunctions;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PostDetailsDto extends AbstractBaseDto {

    private String title;
    private String slug;
    private String content;
    private String imageUrl;
    private String status;
    private UserDto user;
    private CategoryDto category;
    private LocalDateTime publishedAt;
    private Set<String> tags;
    private Set<CommentDto> comments;

    public PostDetailsDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.slug = post.getSlug();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.status = post.getStatus().name();
        this.user = new UserDto(post.getUser());
        this.category = new CategoryDto(post.getCategory());
        this.publishedAt = post.getPublishedAt();
        this.tags = UtilityFunctions.tagsToListOfString(post.getTags());
        this.comments = new HashSet<>();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }

    public PostDetailsDto(Post post, Set<CommentDto> comments) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.slug = post.getSlug();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.status = post.getStatus().name();
        this.user = new UserDto(post.getUser());
        this.category = new CategoryDto(post.getCategory());
        this.publishedAt = post.getPublishedAt();
        this.tags = UtilityFunctions.tagsToListOfString(post.getTags());
        this.comments = comments;
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
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

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
