package com.farukgenc.boilerplate.springboot.controller;

import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchActivityRequest;
import com.farukgenc.boilerplate.springboot.security.service.ActivityService;
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
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping("/register")
    @Operation(tags = "Activity Service", description = "You can register new activity by sending information in the appropriate format.")
    public ResponseEntity<RegistrationResponse> createNewActivity(@Valid @RequestBody CreateActivityRequest request) {
        final RegistrationResponse createActivityResponse = activityService.createNewActivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createActivityResponse);
    }

    @PostMapping("/search")
    @Operation(tags = "Activity Service", description = "You can search opportunities.")
    public ResponseEntity<CustomPage<Activity>> searchActivity(@Valid @RequestBody SearchActivityRequest request) {
        CustomPage<Activity> activityCustomPage = activityService.searchActivity(request);
        return ResponseEntity.status(HttpStatus.OK).body(activityCustomPage);
    }
}
