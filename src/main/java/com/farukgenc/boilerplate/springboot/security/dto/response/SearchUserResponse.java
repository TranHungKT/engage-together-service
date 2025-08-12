package com.farukgenc.boilerplate.springboot.security.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchUserResponse {
    private UUID id;
    private String email;
    private String username;
    private String name;
    private String userRole;
}
