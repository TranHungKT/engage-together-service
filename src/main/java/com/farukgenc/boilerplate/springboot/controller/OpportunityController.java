package com.farukgenc.boilerplate.springboot.controller;


import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.model.Opportunity;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateOpportunityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchOpportunityRequest;
import com.farukgenc.boilerplate.springboot.security.service.OpportunityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/opportunity")
public class OpportunityController {
    private final OpportunityService opportunityService;

    @PostMapping("/register")
    @Operation(tags = "Opportunity Service", description = "You can register new opportunity by sending information in the appropriate format.")
    public ResponseEntity<RegistrationResponse> createNewOpportunity(@Valid @RequestBody CreateOpportunityRequest request) {
        final RegistrationResponse createOpportunityResponse = opportunityService.createNewOpportunity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOpportunityResponse);
    }

    @PostMapping("/search")
    @Operation(tags = "Opportunity Service", description = "You can search opportunities.")
    public ResponseEntity<CustomPage<Opportunity>> searchOpportunity(@Valid @RequestBody SearchOpportunityRequest request) {
        CustomPage<Opportunity> opportunityCustomPage = opportunityService.searchOpportunity(request);
        return ResponseEntity.status(HttpStatus.OK).body(opportunityCustomPage);
    }
}
