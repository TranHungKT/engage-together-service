package com.farukgenc.boilerplate.springboot.security.service.impl;

import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.model.ActivityCategory;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityCategoryId;
import com.farukgenc.boilerplate.springboot.repository.ActivityRepository;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchActivityRequest;
import com.farukgenc.boilerplate.springboot.security.mapper.ActivityMapper;
import com.farukgenc.boilerplate.springboot.security.service.ActivityService;
import com.farukgenc.boilerplate.springboot.service.ActivityValidationService;
import com.farukgenc.boilerplate.springboot.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";
    private static final String DEFAULT_SORT_FIELD = "title";

    private final ActivityValidationService activityValidationService;
    private final ActivityRepository activityRepository;
    private final GeneralMessageAccessor generalMessageAccessor;

    public RegistrationResponse createNewActivity(CreateActivityRequest request) {
        activityValidationService.validateOrganization(UUID.fromString(request.getOrganizationId()));

        final Activity activity = ActivityMapper.MAPPER.toActivity(request);
        List<ActivityCategory> activityTypeList = request.getActivityCategories()
                .stream()
                .map(activityTypeKey -> ActivityCategory.builder()
                        .activity(activity)
                        .id(ActivityCategoryId.builder()
                                .activityId(activity.getId())
                                .organizationId(activity.getOrganizationId())
                                .categoryKey(activityTypeKey)
                                .build())
                        .createdBy("SYSTEM")
                        .build())
                .collect(Collectors.toUnmodifiableList());

        activity.setCategories(activityTypeList);
        activityRepository.saveAndFlush(activity);

        final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, request.getTitle());

        log.info("{} registered successful!", request.getTitle());

        return new RegistrationResponse(registrationSuccessMessage);
    }

    @Override
    public CustomPage<Activity> searchActivity(SearchActivityRequest request) {
        Pageable pageable = request.toPageable(DEFAULT_SORT_FIELD);
        Page<Activity> opportunities = activityRepository.searchActivity(request.getTitle(), pageable);
        return CustomPage.of(opportunities.getContent(), opportunities);
    }
}
