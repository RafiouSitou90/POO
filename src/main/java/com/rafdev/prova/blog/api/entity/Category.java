package com.rafdev.prova.blog.api.entity;

import com.github.slugify.Slugify;
import com.rafdev.prova.blog.api.listener.CategoryListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@EntityListeners(CategoryListener.class)
@Entity
@Table(name = "tab_categories")
public class Category extends AbstractBaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void computeSlug() {
        Slugify slugify = new Slugify();

        this.slug = slugify.slugify(name);
    }


}
