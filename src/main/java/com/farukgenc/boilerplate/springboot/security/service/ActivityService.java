package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchActivityRequest;

public interface ActivityService {
    RegistrationResponse createNewActivity(CreateActivityRequest request);

    CustomPage<Activity> searchActivity(SearchActivityRequest request);
}
