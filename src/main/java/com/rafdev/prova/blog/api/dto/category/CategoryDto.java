package com.rafdev.prova.blog.api.dto.category;

import com.rafdev.prova.blog.api.dto.AbstractBaseDto;
import com.rafdev.prova.blog.api.entity.Category;

public class CategoryDto extends AbstractBaseDto {

    private String name;
    private String slug;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.slug = category.getSlug();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
