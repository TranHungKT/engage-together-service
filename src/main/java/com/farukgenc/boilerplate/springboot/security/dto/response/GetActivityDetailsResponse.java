package com.farukgenc.boilerplate.springboot.security.dto.response;

import com.farukgenc.boilerplate.springboot.model.enums.ActivityParticipantStatus;
import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInActivity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class GetActivityDetailsResponse {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Integer maxAttendees;

    private List<String> categories;

    private UUID organizationId;

    private List<GetActivityDetailsResponse.UserResponse> users;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public static class UserResponse {
        private UUID userId;
        private String name;
        private String username;
        private String email;
        private UserRoleInActivity userRoleInActivity;
        private ActivityParticipantStatus status;
    }

    private String address;

    private String stateProvince;

    private String city;

    private String postalCode;

    private String country;
}
