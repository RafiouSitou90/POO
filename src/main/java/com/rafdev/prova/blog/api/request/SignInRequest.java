package com.rafdev.prova.blog.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInRequest {

    @NotBlank
    @Size(min = 6, max = 100)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
