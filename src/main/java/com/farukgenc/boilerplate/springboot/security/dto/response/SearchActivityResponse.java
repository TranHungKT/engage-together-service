package com.farukgenc.boilerplate.springboot.security.dto.response;

import com.farukgenc.boilerplate.springboot.model.enums.ActivityStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchActivityResponse {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Integer maxAttendees;

    private ActivityStatus status;

    private List<String> categories;

    private UUID organizationId;

    private String address;

    private String stateProvince;

    private String city;

    private String postalCode;

    private String country;

    private OrganizationResponse organization;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class OrganizationResponse {
        private UUID id;
        private String name;
        private String email;
        private String phoneNumber;
        private String type;
        private String otherType;
        private String missionStatement;
        private String address;
        private String city;
        private String stateProvince;
        private String zipPostalCode;
        private String country;
    }
}
