package com.farukgenc.boilerplate.springboot.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDetailsResponse {

    private UUID id;

    private String username;

    private String email;

    private List<OrganizationResponse> organizations;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class OrganizationResponse {
        private UUID organizationId;
        private String organizationName;
    }
}
