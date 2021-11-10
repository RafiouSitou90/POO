package com.rafdev.prova.blog.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_comments")
public class Comment extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 10)
    @Column
    private String content;

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Post.class, optional = false)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column
    private LocalDateTime publishedAt;

    public Comment() {
    }

    public Comment(String content, User user, Post post, LocalDateTime publishedAt) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
