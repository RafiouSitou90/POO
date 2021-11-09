package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class CommentRequest {

    @NotBlank(message = "The content cannot be blank")
    @Size(min = 10, message = "The content must be at least {min} characters")
    private String content;

    @PositiveOrZero(message = "The userId must be greater than or equal to zero")
    private Long userId;

    @PositiveOrZero(message = "The postId must be greater than or equal to zero")
    private Long postId;

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
