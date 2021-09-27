package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryRequest {

    @NotBlank(message = "The name cannot be blank")
    @Size.List ({
            @Size(min = 3, message = "The name must be at least {min} characters"),
            @Size(max = 100, message = "The name must be less than {max} characters")
    })
    private String name;

    public String getName() {
        return name;
    }
}
