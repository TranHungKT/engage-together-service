package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.security.dto.response.ActivitySummaryOfOrganizationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.OrganizationDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationOrganizationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;

import java.util.UUID;

public interface OrganizationService {
    RegistrationResponse registration(RegistrationOrganizationRequest registrationOrganizationRequest);
    ActivitySummaryOfOrganizationResponse getActivitySummaryOfOrganization(UUID id);
    OrganizationDetailsResponse getOrganizationDetails(UUID id);
}
