package com.rafdev.prova.blog.api.builder;

import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.Tag;
import com.rafdev.prova.blog.api.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

public class PostBuilder {
    private final Post post;

    public PostBuilder() {
        post = new Post();
    }

    public PostBuilder withTitle(final String title) {
        post.setTitle(title);

        return this;
    }

    public PostBuilder withContent(final String content) {
        post.setContent(content);

        return this;
    }

    public PostBuilder withImageUrl(final String imageUrl) {
        post.setImageUrl(imageUrl);

        return this;
    }

    public PostBuilder withPublishedAt(final LocalDateTime publishedAt) {
        post.setPublishedAt(publishedAt);

        return this;
    }

    public PostBuilder withUser(final User user) {
        post.setUser(user);

        return this;
    }

    public PostBuilder withTags(final Set<Tag> tags) {
        post.setTags(tags);

        return this;
    }

    public PostBuilder withTags(final Tag tag) {
        if (post.getTags().contains(tag)) {
            return this;
        }

        post.getTags().add(tag);

        return this;
    }

    public Post build() {
        return post;
    }
}
