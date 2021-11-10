package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.user.UserDetailsDto;
import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v2/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserRequest userRequest)
            throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long id, @Valid @RequestBody UserRequest userRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(userService.updateUserById(id, userRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        userService.deleteUserById(id);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
