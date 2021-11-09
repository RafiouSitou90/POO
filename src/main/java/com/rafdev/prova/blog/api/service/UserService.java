package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.user.UserDetailsDto;
import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.request.UserRequest;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    UserDto saveUser(UserRequest userRequest);

    UserDetailsDto getUserById(Long id);

    void deleteUserById(Long id);

    UserDto updateUserById(Long id, UserRequest userRequest);
}
