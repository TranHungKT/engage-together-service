package com.farukgenc.boilerplate.springboot.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySummaryOfOrganizationResponse {
    private Integer numberOfActiveActivity;
    private Integer numberOfUpcomingActivity;
    private Integer totalVolunteers;
    private Integer totalActivities;
}
