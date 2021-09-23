package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentRequest {

    @NotBlank
    @Size(min =10)
    private String content;

    private Long userId;
    private Long postId;

    public CommentRequest(String content, Long userId, Long postId) {
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }
}
