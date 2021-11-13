package com.rafdev.prova.blog.api.request;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

public class PostRequest {

    @NotBlank(message = "The title cannot be blank")
    @Size.List ({
            @Size(min = 3, message = "The title must be at least {min} characters"),
            @Size(max = 100, message = "The title must be less than {max} characters")
    })
    private String title;

    @NotBlank(message = "The content cannot be blank")
    @Size.List ({
            @Size(min = 10, message = "The content must be at least {min} characters")
    })
    private String content;

    @URL(message = "Invalid image url")
    private String imageUrl;

    @PositiveOrZero(message = "The userId must be greater than or equal to zero")
    private Long userId;

    @PositiveOrZero(message = "The categoryId must be greater than or equal to zero")
    private Long categoryId;

    private LocalDateTime publishedAt;

    @Size.List ({
            @Size(max = 5, message = "The tag must be less than or equals {max}")
    })
    private Set<String> tags;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public Set<String> getTags() {
        return tags;
    }
}
