package com.farukgenc.boilerplate.springboot.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateOpportunityRequest {
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
}
