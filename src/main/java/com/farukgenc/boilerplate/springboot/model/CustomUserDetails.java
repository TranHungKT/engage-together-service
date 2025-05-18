package com.farukgenc.boilerplate.springboot.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface CustomUserDetails extends
        UserDetails {
    UUID getUserId();

    void setUserId(UUID userId);
}
