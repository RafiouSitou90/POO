package com.rafdev.prova.blog.api.listener;

import com.rafdev.prova.blog.api.entity.Category;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class CategoryListener {

    @PrePersist
    @PreUpdate
    public void onPrePersistAndUpdate(Category category) {
        category.computeSlug();
    }
}
