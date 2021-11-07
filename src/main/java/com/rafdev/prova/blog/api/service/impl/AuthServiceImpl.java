package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.UserDto;
import com.rafdev.prova.blog.api.entity.Role;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.LoginBadCredentialsException;
import com.rafdev.prova.blog.api.repository.UserRepository;
import com.rafdev.prova.blog.api.request.SignInRequest;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.response.TokenResponse;
import com.rafdev.prova.blog.api.service.AuthService;
import com.rafdev.prova.blog.api.service.UserService;

import com.rafdev.prova.blog.api.util.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserService userService,
                           UserRepository userRepository, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) throws LoginBadCredentialsException {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                            signInRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException exception) {
            throw new LoginBadCredentialsException();
        }

        final User user = (User) userDetailsService.loadUserByUsername(signInRequest.getUsername());
        final String accessToken = jwtUtil.generateToken(user);

        return new TokenResponse(user.getId(), user.getUsername(), user.getEmail(),
                user.getFullName(), changeRolesToString(user.getRoles()), accessToken);
    }

    @Override
    public UserDto signUp(UserRequest signUpRequest) {
        signUpRequest.setRoles(null);
        return userService.saveUser(signUpRequest);
    }

    private List<String> changeRolesToString(List<Role> roles) {
        List<String> strRoles = new ArrayList<>();
        roles.forEach(role -> strRoles.add(role.getName().toString()));

        return strRoles;
    }
}
