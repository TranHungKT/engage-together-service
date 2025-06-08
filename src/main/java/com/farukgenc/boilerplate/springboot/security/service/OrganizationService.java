package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.security.dto.OpportunitySummaryOfOrganizationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.OrganizationDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.RegistrationOrganizationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.RegistrationResponse;

import java.util.UUID;

public interface OrganizationService {
    RegistrationResponse registration(RegistrationOrganizationRequest registrationOrganizationRequest);
    OpportunitySummaryOfOrganizationResponse getOpportunitySummaryOfOrganization(UUID id);
    OrganizationDetailsResponse getOrganizationDetails(UUID id);
}
