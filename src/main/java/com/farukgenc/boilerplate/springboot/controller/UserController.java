package com.farukgenc.boilerplate.springboot.controller;

import com.farukgenc.boilerplate.springboot.security.dto.request.LoginRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchUserRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.LoginResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.SearchUserResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.UserDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.UserProfileResponse;
import com.farukgenc.boilerplate.springboot.security.jwt.JwtTokenService;
import com.farukgenc.boilerplate.springboot.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @GetMapping()
    @Operation(tags = "User Service", description = "You can get the basic user information base on token")
    public ResponseEntity<UserDetailsResponse> getUserDetails() {
        final UserDetailsResponse userDetailsResponse = userService.getUserDetails();
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsResponse);
    }

    @GetMapping("/profile/{userId}")
    @Operation(tags = "User Profile", description = "Get all information related to user")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    @Operation(tags = "User Service", description = "You can register to the system by sending information in the appropriate format.")
    public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

        final RegistrationResponse registrationResponse = userService.registration(registrationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

    @PostMapping("/login")
    @Operation(tags = "User Service", description = "You must log in with the correct information to successfully obtain the token information.")
    public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {
        final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchUserResponse>> searchUser(@Valid @RequestBody SearchUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchUser(request));
    }
}
