package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.UserDto;
import com.rafdev.prova.blog.api.request.UserRequest;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    UserDto saveUser(UserRequest userRequest);

    UserDto getUserById(Long id);

    void deleteUserById(Long id);

    UserDto updateUserById(Long id, UserRequest userRequest);
}
