package com.rafdev.prova.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoginBadCredentialsException extends RuntimeException {

    public LoginBadCredentialsException() {
        super("Username or password incorrect");
    }
}
