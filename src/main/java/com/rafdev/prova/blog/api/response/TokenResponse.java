package com.rafdev.prova.blog.api.response;

import java.util.List;

public class TokenResponse {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private List<String> roles;
    private String token;

    public TokenResponse(Long id, String username, String email, String fullName, List<String> roles, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }
}
