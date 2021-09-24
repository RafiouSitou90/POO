package com.rafdev.prova.blog.api.response;

import com.rafdev.prova.blog.api.dto.UserDto;

public class TokenResponse {

    private UserDto user;
    private String token;

    public TokenResponse(UserDto userDto, String token) {
        this.token = token;
        this.user = userDto;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
