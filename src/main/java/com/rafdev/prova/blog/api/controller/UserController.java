package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.user.UserDetailsDto;
import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.UserPagination;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v2/users")
@Api(tags = "Users")
@Tags(value = @Tag(name = "Users", description = "Users Resources"))
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create new User", description = "Add a single user resource in the API.")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserRequest userRequest)
            throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User by Id", description = "Update a single user resource in the API.")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long id, @Valid @RequestBody UserRequest userRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(userService.updateUserById(id, userRequest), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get Users list", description = "Get the full list paginated of users provided by the API.")
    public ResponseEntity<Page<UserDto>> getUsers(UserPagination pagination) {
        return new ResponseEntity<>(userService.getUsers(pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by Id", description = "Get single user resource provided by the API.")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User by Id", description = "Delete a single user resource in the API.")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        userService.deleteUserById(id);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
