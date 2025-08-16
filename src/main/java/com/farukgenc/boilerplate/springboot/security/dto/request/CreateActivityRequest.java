package com.farukgenc.boilerplate.springboot.security.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateActivityRequest {
    private UUID organizationId;

    @NotEmpty(message = "not empty")
    private String title;

    private String description;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String[] requirements;

    private List<String> activityCategories;

    private Integer maxAttendees;

    private List<UUID> adminUsers;

    private String address;

    private String stateProvince;

    private String city;

    private String postalCode;

    private String country;
}
