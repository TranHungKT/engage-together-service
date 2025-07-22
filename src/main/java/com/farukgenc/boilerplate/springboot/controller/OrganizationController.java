package com.farukgenc.boilerplate.springboot.controller;

import com.farukgenc.boilerplate.springboot.security.dto.response.OpportunitySummaryOfOrganizationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.OrganizationDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationOrganizationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.service.OrganizationService;
import com.farukgenc.boilerplate.springboot.service.dtoValidators.CreateOrganizationDtoValidator;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @InitBinder({
            "registrationOrganizationRequest"
    })
    public void initBinders(WebDataBinder binder, WebRequest request) {
        var validators = List.of(
                new CreateOrganizationDtoValidator()
        );

        binder.addValidators(validators.stream()
                .filter(validator -> validator.supports(Objects.requireNonNull(binder.getTarget()).getClass()))
                .toList()
                .toArray(Validator[]::new)
        );
    }

    @PostMapping("/organization/register")
    @Operation(tags = "Organization Service", description = "You can register your organization to the system by sending information in the appropriate format.")
    public ResponseEntity<RegistrationResponse> registerOrganization(@Valid @RequestBody RegistrationOrganizationRequest request) {
        final RegistrationResponse registrationResponse = organizationService.registration(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

    @GetMapping("/organization/summary_opportunity/{id}")
    @Operation(tags = "Organization Service", description = "You can get your organization summary.")
    public ResponseEntity<OpportunitySummaryOfOrganizationResponse> getOpportunitySummaryOfOrganization(
            @PathVariable UUID id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getOpportunitySummaryOfOrganization(id));
    }

    @GetMapping("/organization/{id}")
    @Operation(tags = "Organization Service", description = "You can get your organization details.")
    public ResponseEntity<OrganizationDetailsResponse> getOrganizationDetails(
            @PathVariable UUID id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getOrganizationDetails(id));
    }
}
