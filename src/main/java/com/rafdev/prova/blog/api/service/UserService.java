package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.user.UserDetailsDto;
import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.UserPagination;
import com.rafdev.prova.blog.api.request.UserRequest;

import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserDto> getUsers(UserPagination pagination);

    UserDto saveUser(UserRequest userRequest) throws ResourceAlreadyExistsException;

    UserDetailsDto getUserById(Long id) throws ResourceNotFoundException;

    void deleteUserById(Long id) throws ResourceNotFoundException;

    UserDto updateUserById(Long id, UserRequest userRequest) throws ResourceNotFoundException, ResourceAlreadyExistsException;
}
