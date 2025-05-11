package com.farukgenc.boilerplate.springboot.security.dto;

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
public class OpportunitySummaryOfOrganizationResponse {
    private Integer numberOfActiveOpportunity;
    private Integer numberOfUpcomingOpportunity;
    private Integer totalVolunteers;
    private Integer totalOpportunities;
}
