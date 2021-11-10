package com.rafdev.prova.blog.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @Column
    protected LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
