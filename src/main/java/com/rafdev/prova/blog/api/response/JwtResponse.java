package com.rafdev.prova.blog.api.response;

import com.rafdev.prova.blog.api.dto.user.UserDto;

import java.util.Set;

public class JwtResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final String fullName;
    private final Set<String> roles;
    private final String token;

    public JwtResponse(UserDto user, String token) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.roles = user.getRoles();
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

    public Set<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }
}
