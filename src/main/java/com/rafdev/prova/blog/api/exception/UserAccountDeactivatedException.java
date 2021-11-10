package com.rafdev.prova.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserAccountDeactivatedException extends RuntimeException {

    public UserAccountDeactivatedException() {
        super("Your account is deactivated.");
    }
}
