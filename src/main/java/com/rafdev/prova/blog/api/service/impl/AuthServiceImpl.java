package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.UserDetailsDto;
import com.rafdev.prova.blog.api.dto.UserDto;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.repository.UserRepository;
import com.rafdev.prova.blog.api.request.SignInRequest;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.response.TokenResponse;
import com.rafdev.prova.blog.api.service.AuthService;
import com.rafdev.prova.blog.api.service.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserService userService,
                           UserRepository userRepository, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) throws Exception {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                            signInRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException exception) {
            throw  new Exception("INVALID_CREDENTIALS", exception);
        }

        final UserDetailsDto userDetails =
                (UserDetailsDto) userDetailsService.loadUserByUsername(signInRequest.getUsername());
        final String accessToken = generateToken();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        User user = userRepository.findById(userDetails.getId());

        if (user != null) {
            user.setToken(accessToken);
            userRepository.update(user);
        }

        return new TokenResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                userDetails.getFullName(), roles, accessToken);
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
