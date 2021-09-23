package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    public String getName() {
        return name;
    }
}
