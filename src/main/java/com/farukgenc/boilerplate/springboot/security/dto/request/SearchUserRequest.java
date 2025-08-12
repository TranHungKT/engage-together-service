package com.farukgenc.boilerplate.springboot.security.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SearchUserRequest {
    @NotNull
    private UUID organizationId;

    private String username;
}
