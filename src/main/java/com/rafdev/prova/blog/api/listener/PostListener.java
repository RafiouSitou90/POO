package com.rafdev.prova.blog.api.listener;

import com.rafdev.prova.blog.api.entity.Post;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PostListener {

    @PrePersist
    @PreUpdate
    public void onPrePersistAndUpdate(final Post post) {
        post.computeSlug();
    }
}
