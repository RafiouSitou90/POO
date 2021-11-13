package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.exception.LoginBadCredentialsException;
import com.rafdev.prova.blog.api.request.SignInRequest;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.response.JwtResponse;

public interface AuthService {

    JwtResponse signIn(SignInRequest signInRequest) throws LoginBadCredentialsException;

    UserDto signUp(UserRequest signUpRequest);
}
