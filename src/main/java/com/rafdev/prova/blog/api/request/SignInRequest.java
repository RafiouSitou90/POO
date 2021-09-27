package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInRequest {

    @NotBlank(message = "The username cannot be blank")
    @Size.List ({
            @Size(min = 6, message = "The username must be at least {min} characters"),
            @Size(max = 100, message = "The username must be less than {max} characters")
    })
    private String username;

    @NotBlank(message = "The password cannot be blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
