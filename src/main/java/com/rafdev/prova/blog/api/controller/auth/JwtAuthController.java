package com.rafdev.prova.blog.api.controller.auth;

import com.rafdev.prova.blog.api.dto.user.UserDto;
import com.rafdev.prova.blog.api.exception.LoginBadCredentialsException;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.request.SignInRequest;
import com.rafdev.prova.blog.api.request.UserRequest;
import com.rafdev.prova.blog.api.response.JwtResponse;
import com.rafdev.prova.blog.api.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v2/auth")
@Api(tags = "Authentication")
@Tags(value = @Tag(name = "Authentication", description = "JWT Authentication System"))
public class JwtAuthController {

    private final AuthService authService;

    public JwtAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in", description = "Sign In with Email or Username and Password")
    public ResponseEntity<JwtResponse> signIn(@RequestBody @Valid SignInRequest signInRequest)
            throws LoginBadCredentialsException {

        return new ResponseEntity<>(authService.signIn(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Sign Up", description = "Create new User Account")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid UserRequest signUpRequest)
            throws ResourceAlreadyExistsException {

        return new ResponseEntity<>(authService.signUp(signUpRequest), HttpStatus.CREATED);
    }
}
