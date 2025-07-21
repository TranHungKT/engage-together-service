package com.farukgenc.boilerplate.springboot.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDetailsResponse {

    private UUID id;

    private String username;

    private String email;
}
