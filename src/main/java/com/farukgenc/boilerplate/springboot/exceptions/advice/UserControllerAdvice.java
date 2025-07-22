package com.farukgenc.boilerplate.springboot.exceptions.advice;


import com.farukgenc.boilerplate.springboot.controller.UserController;
import com.farukgenc.boilerplate.springboot.exceptions.ApiExceptionResponse;
import com.farukgenc.boilerplate.springboot.exceptions.BadRequestException;
import com.farukgenc.boilerplate.springboot.exceptions.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerAdvice {

    @ExceptionHandler(RegistrationException.class)
    ResponseEntity<ApiExceptionResponse> handleRegistrationException(RegistrationException exception) {

        final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ApiExceptionResponse> handleBadRequestException(BadRequestException exception) {

        final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ApiExceptionResponse> handleRegistrationException(BadCredentialsException exception) {

        final ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
