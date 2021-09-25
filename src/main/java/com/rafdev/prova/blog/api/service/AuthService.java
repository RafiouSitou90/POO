package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.UserDto;
import com.rafdev.prova.blog.api.request.SignInRequest;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.response.TokenResponse;

public interface AuthService {

    TokenResponse signIn(SignInRequest signInRequest) throws Exception;

    UserDto signUp(UserRequest signUpRequest);
}
