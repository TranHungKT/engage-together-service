package com.farukgenc.boilerplate.springboot.controller;

import com.farukgenc.boilerplate.springboot.security.dto.response.UserDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserDetailsResponse> getUserDetails() {
        final UserDetailsResponse userDetailsResponse = userService.getUserDetails();

        return ResponseEntity.status(HttpStatus.OK).body(userDetailsResponse);
    }
}
