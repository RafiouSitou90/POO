package com.rafdev.prova.blog.api.entity;

import com.github.slugify.Slugify;
import com.rafdev.prova.blog.api.listener.CategoryListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(CategoryListener.class)
@Entity
@Table(name = "tab_categories")
public class Category extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @OneToMany(targetEntity = Post.class, mappedBy = "category", orphanRemoval = true)
    private Set<Post> posts;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.posts = new HashSet<>();
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void computeSlug() {
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(name);
    }
}
