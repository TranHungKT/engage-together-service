package com.farukgenc.boilerplate.springboot.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDetailsResponse {
    private String name;
    private String type;
    private String otherType;
    private Integer numberOfOpportunity;
    private Integer numberOfFollowers;
    private List<OrganizationAdmin> admins;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrganizationAdmin {
        private UUID adminId;
    }
}
