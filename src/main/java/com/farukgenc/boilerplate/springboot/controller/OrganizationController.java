package com.farukgenc.boilerplate.springboot.controller;

import com.farukgenc.boilerplate.springboot.security.dto.OpportunitySummaryOfOrganizationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.RegistrationOrganizationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> registerOrganization(@Valid @RequestBody RegistrationOrganizationRequest request) {
        final RegistrationResponse registrationResponse = organizationService.registration(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

    @GetMapping("/summary_opportunity/{id}")
    public ResponseEntity<OpportunitySummaryOfOrganizationResponse> getOpportunitySummaryOfOrganization(
            @PathVariable UUID id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getOpportunitySummaryOfOrganization(id));
    }
}
