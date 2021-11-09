package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.UserDetailsDto;
import com.rafdev.prova.blog.api.entity.User;
import com.rafdev.prova.blog.api.exception.LoginBadCredentialsException;
import com.rafdev.prova.blog.api.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws LoginBadCredentialsException {
        User user = userRepository.findByUsernameIgnoreCase(username).orElseThrow(LoginBadCredentialsException::new);

        return UserDetailsDto.build(user);
    }
}
