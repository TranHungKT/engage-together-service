package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.GetActivityDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.SearchActivityResponse;

import java.util.UUID;

public interface ActivityService {
    RegistrationResponse createNewActivity(CreateActivityRequest request);

    CustomPage<SearchActivityResponse> searchActivity(SearchActivityRequest request);

    GetActivityDetailsResponse getActivityDetails(UUID activityId);
}
