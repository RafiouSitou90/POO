package com.rafdev.prova.blog.api.dto;

import java.time.LocalDateTime;

public abstract class AbstractBaseDto {

    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected String getResourceUrl(String resourceName, Long id) {
        return String.format("/api/v2/%s/%d", resourceName, id);
    }
}
