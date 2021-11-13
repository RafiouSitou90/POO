package com.rafdev.prova.blog.api.exception;

import com.rafdev.prova.blog.api.response.ApiErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Resource Not Found Exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            HttpServletRequest request) {

        ApiErrorResponse errorDetails = getErrorDetails(HttpStatus.NOT_FOUND, exception.getMessage(),
                null, request);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handle Bad Requests Exceptions
    @ExceptionHandler({
            ResourceAlreadyExistsException.class, ResourceBadRequestException.class,
            LoginBadCredentialsException.class, ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(
            RuntimeException exception, HttpServletRequest request) {

        ApiErrorResponse errorDetails = getErrorDetails(HttpStatus.BAD_REQUEST, exception.getMessage(),
                null, request);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Handle User Account Deactivated Exception Exceptions
    @ExceptionHandler(UserAccountDeactivatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiErrorResponse> handleUserAccountDeactivatedException(
            UserAccountDeactivatedException exception, HttpServletRequest request) {

        ApiErrorResponse errorDetails = getErrorDetails(HttpStatus.UNAUTHORIZED, exception.getMessage(),
                null, request);

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    // Handle Resource Method Argument Not Valid Exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiErrorResponse> handleConstraintViolationException(MethodArgumentNotValidException exception,
                                                                        HttpServletRequest request) {
        List<String> details = new ArrayList<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            details.add(String.format("%s => %s", error.getField(), error.getDefaultMessage()));
        }

        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            details.add(String.format("%s => %s", error.getObjectName(), error.getDefaultMessage()));
        }

        ApiErrorResponse errorDetails = getErrorDetails(HttpStatus.BAD_REQUEST, "Fields validation errors",
                details, request);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Handle Access Denied Exceptions
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException exception,
                                                                        HttpServletRequest request) {

        ApiErrorResponse errorDetails = getErrorDetails(HttpStatus.FORBIDDEN, exception.getMessage(), null, request);

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    private ApiErrorResponse getErrorDetails(HttpStatus status, String message, @Nullable Object details,
                                             HttpServletRequest request) {
        ApiErrorResponse errorDetails = new ApiErrorResponse();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setStatus(status.value());
        errorDetails.setError(status.getReasonPhrase());
        errorDetails.setMessage(message);
        errorDetails.setPath(request.getRequestURI());
        errorDetails.setDetails(Objects.requireNonNullElseGet(details, ArrayList::new));

        return errorDetails;
    }
}
