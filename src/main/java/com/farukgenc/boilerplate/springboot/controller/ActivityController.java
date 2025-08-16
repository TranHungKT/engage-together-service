package com.farukgenc.boilerplate.springboot.controller;

import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.GetActivityDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.SearchActivityResponse;
import com.farukgenc.boilerplate.springboot.security.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<CustomPage<SearchActivityResponse>> searchActivity(@Valid @RequestBody SearchActivityRequest request) {
        CustomPage<SearchActivityResponse> activityCustomPage = activityService.searchActivity(request);
        return ResponseEntity.status(HttpStatus.OK).body(activityCustomPage);
    }

    @GetMapping("/{id}")
    @Operation(tags = "Get Activity details", description = "You can get activity details")
    public ResponseEntity<GetActivityDetailsResponse> getActivityDetails(
            @PathVariable UUID id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(activityService.getActivityDetails(id));
    }
}
