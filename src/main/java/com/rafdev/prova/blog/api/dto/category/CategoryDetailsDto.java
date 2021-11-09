package com.rafdev.prova.blog.api.dto.category;

import com.rafdev.prova.blog.api.dto.AbstractBaseDto;
import com.rafdev.prova.blog.api.dto.post.PostDto;
import com.rafdev.prova.blog.api.entity.Category;

import java.util.HashSet;
import java.util.Set;

public class CategoryDetailsDto extends AbstractBaseDto {

    private Long id;
    private String name;
    private String slug;
    private Set<PostDto> posts;

    public CategoryDetailsDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.slug = category.getSlug();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
        this.posts = new HashSet<>();
    }

    public CategoryDetailsDto(Category category, Set<PostDto> posts) {
        this.id = category.getId();
        this.name = category.getName();
        this.slug = category.getSlug();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostDto> posts) {
        this.posts = posts;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
