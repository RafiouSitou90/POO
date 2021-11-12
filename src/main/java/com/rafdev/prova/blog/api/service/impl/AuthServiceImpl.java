package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.entity.Role;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.LoginBadCredentialsException;
import com.rafdev.prova.blog.api.exception.UserAccountDeactivatedException;
import com.rafdev.prova.blog.api.request.SignInRequest;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.response.JwtResponse;
import com.rafdev.prova.blog.api.service.AuthService;
import com.rafdev.prova.blog.api.service.UserService;

import com.rafdev.prova.blog.api.util.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserService userService,
                           UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse signIn(SignInRequest signInRequest) throws LoginBadCredentialsException {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                            signInRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException exception) {
            if (exception instanceof BadCredentialsException) {
                throw new LoginBadCredentialsException();
            } else if (exception instanceof DisabledException) {
                throw new UserAccountDeactivatedException();
            }
        }

        final User user = (User) userDetailsService.loadUserByUsername(signInRequest.getUsername());
        final String token = jwtUtil.generateToken(user);

        return new JwtResponse(new UserDto(user), token);
    }

    @Override
    public UserDto signUp(UserRequest signUpRequest) {

        signUpRequest.setRoles(null);

        return userService.saveUser(signUpRequest);
    }
}
