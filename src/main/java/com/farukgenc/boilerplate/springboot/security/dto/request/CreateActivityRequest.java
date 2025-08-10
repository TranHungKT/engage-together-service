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
    private String organizationId;

    @NotEmpty(message = "not empty")
    private String title;

    private String description;

    private String address;

    private String city;

    private String zipCode;

//    @NotEmpty(message = "not empty")
    private LocalDateTime startDateTime;

//    @NotEmpty(message = "not empty")
    private LocalDateTime endDateTime;

    private String[] requirements;

    private String status;

    private List<String> activityCategories;

    private Integer maxAttendees;

    private List<UUID> adminUsers;
}
