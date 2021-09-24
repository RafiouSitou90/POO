package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.UserDto;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.repository.UserRepository;
import com.rafdev.prova.blog.api.request.SignInRequest;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.response.TokenResponse;

import com.rafdev.prova.blog.api.service.AuthService;
import com.rafdev.prova.blog.api.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) {

        User user = userRepository.findByUsername(signInRequest.getUsername());

        if (user == null) {
            throw new ResourceNotFoundException("User");
        }

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("User");
        }

        return new TokenResponse(new UserDto(user), generateToken());
    }

    @Override
    public UserDto signUp(UserRequest signUpRequest) {
        signUpRequest.setRoles(null);
        return userService.saveUser(signUpRequest);
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
