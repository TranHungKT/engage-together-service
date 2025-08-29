package com.farukgenc.boilerplate.springboot.security.service.impl;

import com.farukgenc.boilerplate.springboot.common.model.dto.CustomPage;
import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.model.ActivityCategory;
import com.farukgenc.boilerplate.springboot.model.ActivityParticipant;
import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInActivity;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityCategoryId;
import com.farukgenc.boilerplate.springboot.repository.ActivityCategoryRepository;
import com.farukgenc.boilerplate.springboot.repository.ActivityParticipantRepository;
import com.farukgenc.boilerplate.springboot.repository.ActivityRepository;
import com.farukgenc.boilerplate.springboot.repository.OrganizationRepository;
import com.farukgenc.boilerplate.springboot.repository.UserRepository;
import com.farukgenc.boilerplate.springboot.repository.projections.ActivityProjection;
import com.farukgenc.boilerplate.springboot.security.dto.request.CreateActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.JoinActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchActivityRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.GetActivityDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.JoinActivityResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.SearchActivityResponse;
import com.farukgenc.boilerplate.springboot.security.mapper.ActivityMapper;
import com.farukgenc.boilerplate.springboot.security.mapper.BasicMapper;
import com.farukgenc.boilerplate.springboot.security.service.ActivityService;
import com.farukgenc.boilerplate.springboot.security.utils.UserDetailUtils;
import com.farukgenc.boilerplate.springboot.service.ActivityParticipantValidationService;
import com.farukgenc.boilerplate.springboot.service.ActivityValidationService;
import com.farukgenc.boilerplate.springboot.service.OrganizationValidationService;
import com.farukgenc.boilerplate.springboot.utils.GeneralMessageAccessor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ActivityServiceImpl implements ActivityService {
    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";
    private static final String DEFAULT_SORT_FIELD = "title";

    private final OrganizationValidationService organizationValidationService;
    private final ActivityValidationService activityValidationService;
    private final ActivityParticipantValidationService activityParticipantValidationService;
    private final ActivityRepository activityRepository;
    private final GeneralMessageAccessor generalMessageAccessor;
    private final BasicMapper basicMapper;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final ActivityParticipantRepository activityParticipantRepository;
    private final ActivityCategoryRepository activityCategoryRepository;

    public RegistrationResponse createNewActivity(CreateActivityRequest request) {
        var organization = organizationRepository.findById(request.getOrganizationId());

        organizationValidationService.validateOrganizationIsNotExist(organization);
        organizationValidationService.validateAdminUser(request.getAdminUsers(), request.getOrganizationId());

        var userMap = userRepository.findAllByIdIn(request.getAdminUsers()).stream().collect(Collectors.toMap(User::getId, Function.identity()));

        assert organization.isPresent();

        final Activity activity = ActivityMapper.MAPPER.toActivity(request);
        List<ActivityCategory> activityTypeList = request.getActivityCategories()
                .stream()
                .map(activityTypeKey -> ActivityCategory.builder()
                        .activity(activity)
                        .id(ActivityCategoryId.builder()
                                .activityId(activity.getId())
                                .categoryKey(activityTypeKey)
                                .build())
                        .createdBy("SYSTEM")
                        .build())
                .collect(Collectors.toUnmodifiableList());

        List<ActivityParticipant> adminsOfActivity = request.getAdminUsers().stream().map(
                userId -> ActivityParticipant.builder()
                        .activityId(activity.getId())
                        .userId(userId)
                        .userRole(UserRoleInActivity.ADMIN)
                        .activity(activity)
                        .user(userMap.get(userId))
                        .createdBy("SYSTEM")
                        .build()).collect(Collectors.toUnmodifiableList());

        activity.setActivityParticipants(adminsOfActivity);
        activity.setCategories(activityTypeList);
        activity.setOrganization(organization.get());

        activityRepository.saveAndFlush(activity);

        final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, request.getTitle());

        log.info("{} registered successful!", request.getTitle());

        return new RegistrationResponse(registrationSuccessMessage);
    }

    @Override
    public CustomPage<SearchActivityResponse> searchActivity(SearchActivityRequest request) {
        Pageable pageable = request.toPageable(DEFAULT_SORT_FIELD);
        Page<ActivityProjection> activities = activityRepository.searchActivity(request.getTitle(), request.getUserId(), pageable);

        Page<SearchActivityResponse> listActivity = new PageImpl<>(activities.getContent().stream().map(activity -> basicMapper.convertToResponse(activity, SearchActivityResponse.class)).toList());

        return CustomPage.of(listActivity.getContent(), activities);
    }

    @Override
    public GetActivityDetailsResponse getActivityDetails(UUID activityId) {
        var activityOptional = activityRepository.findById(activityId);
        activityValidationService.validateActivity(activityOptional);
        assert activityOptional.isPresent();

        var activityParticipants = activityParticipantRepository.findByActivityId(activityId).stream().map(
                activityParticipant -> GetActivityDetailsResponse.UserResponse.builder()
                        .userId(activityParticipant.getUserId())
                        .username(activityParticipant.getUser().getUsername())
                        .name(activityParticipant.getUser().getName())
                        .email(activityParticipant.getUser().getName())
                        .userRoleInActivity(activityParticipant.getUserRole())
                        .build()
        ).toList();

        var activityCategories = activityCategoryRepository.findAllByIdActivityId(activityId).stream()
                .map(category -> category.getId().getCategoryKey()).toList();

        var activity = activityOptional.get();

        return GetActivityDetailsResponse.builder()
                .id(activityId)
                .title(activity.getTitle())
                .description(activity.getDescription())
                .startDateTime(activity.getStartDateTime())
                .endDateTime(activity.getEndDateTime())
                .address(activity.getAddress())
                .stateProvince(activity.getStateProvince())
                .city(activity.getCity())
                .postalCode(activity.getPostalCode())
                .country(activity.getCountry())
                .users(activityParticipants)
                .categories(activityCategories)
                .build();
    }

    @Override
    public JoinActivityResponse joinActivity(JoinActivityRequest request) {
        var userOptional = userRepository.findById(UserDetailUtils.getUserDetailsByToken().getUserId());

        var activityOptional = activityRepository.findById(request.getActivityId());
        activityValidationService.validateActivity(activityOptional);

        assert activityOptional.isPresent();
        assert userOptional.isPresent();

        var currentActivityParticipants = activityParticipantRepository.findByActivityId(activityOptional.get().getId());
        activityParticipantValidationService.validateParticipantAlreadyJoin(userOptional.get().getId(), currentActivityParticipants);

        var newActivityParticipant = ActivityParticipant.builder()
                .userId(userOptional.get().getId())
                .activityId(activityOptional.get().getId())
                .user(userOptional.get())
                .activity(activityOptional.get())
                .userRole(request.getRole())
                .createdBy("SYSTEM")
                .build();
        activityParticipantRepository.save(newActivityParticipant);
        return new JoinActivityResponse("Join activity success");
    }
}
