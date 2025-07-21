package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.model.Opportunity;
import com.farukgenc.boilerplate.springboot.security.dto.CreateOpportunityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.SearchOpportunityRequest;

public interface OpportunityService {
    RegistrationResponse createNewOpportunity(CreateOpportunityRequest request);
    CustomPage<Opportunity> searchOpportunity(SearchOpportunityRequest request);
}
