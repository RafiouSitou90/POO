package com.rafdev.prova.blog.api.builder;

import com.rafdev.prova.blog.api.entity.Comment;
import com.rafdev.prova.blog.api.entity.Post;
import com.rafdev.prova.blog.api.entity.User;

import java.time.LocalDateTime;

public class CommentBuilder {
    private final Comment comment;

    public CommentBuilder() {
        comment = new Comment();
    }

    public CommentBuilder withContent(final String content) {
        comment.setContent(content);

        return this;
    }

    public CommentBuilder withUser(final User user) {
        comment.setUser(user);

        return this;
    }

    public CommentBuilder withPost(final Post post) {
        comment.setPost(post);

        return this;
    }

    public CommentBuilder withPublishedAt(final LocalDateTime publishedAt) {
        comment.setPublishedAt(publishedAt);

        return this;
    }

    public Comment build() {
        return comment;
    }
}
