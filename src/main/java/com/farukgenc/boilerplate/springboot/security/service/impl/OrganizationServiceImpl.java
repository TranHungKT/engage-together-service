package com.farukgenc.boilerplate.springboot.security.service.impl;

import com.farukgenc.boilerplate.springboot.exceptions.RegistrationException;
import com.farukgenc.boilerplate.springboot.model.Organization;
import com.farukgenc.boilerplate.springboot.model.OrganizationMembers;
import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInOrganization;
import com.farukgenc.boilerplate.springboot.repository.ActivityRepository;
import com.farukgenc.boilerplate.springboot.repository.OrganizationMembersRepository;
import com.farukgenc.boilerplate.springboot.repository.OrganizationRepository;
import com.farukgenc.boilerplate.springboot.repository.UserRepository;
import com.farukgenc.boilerplate.springboot.security.dto.response.ActivitySummaryOfOrganizationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.OrganizationDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationOrganizationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.mapper.OrganizationMapper;
import com.farukgenc.boilerplate.springboot.security.service.OrganizationService;
import com.farukgenc.boilerplate.springboot.security.utils.UserDetailUtils;
import com.farukgenc.boilerplate.springboot.service.OrganizationValidationService;
import com.farukgenc.boilerplate.springboot.utils.GeneralMessageAccessor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

    private final OrganizationValidationService organizationValidationService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final OrganizationRepository organizationRepository;
    private final ActivityRepository activityRepository;

    private final GeneralMessageAccessor generalMessageAccessor;
    private final OrganizationMembersRepository organizationMembersRepository;
    private final UserRepository userRepository;

    @Transactional
    public RegistrationResponse registration(RegistrationOrganizationRequest registrationOrganizationRequest) {
        organizationValidationService.validateOrganization(registrationOrganizationRequest);

        final Organization organization = OrganizationMapper.MAPPER.toOrganization(registrationOrganizationRequest);
        organization.setPassword(bCryptPasswordEncoder.encode(organization.getPassword()));
        Organization savedOrganization = organizationRepository.saveAndFlush(organization);
        var user = userRepository.findById(UserDetailUtils.getUserDetailsByToken().getUserId());

        if (user.isEmpty()) {
            throw new RegistrationException("User not exist");
        }
        var member = OrganizationMembers.builder().user(user.get()).organization(savedOrganization).createdBy(user.get().getUsername()).userRole(UserRoleInOrganization.ADMIN).build();
        organizationMembersRepository.save(member);
        final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, registrationOrganizationRequest.getName());

        log.info("{} registered successful!", registrationOrganizationRequest.getName());

        return new RegistrationResponse(registrationSuccessMessage);
    }

    @Override
    public ActivitySummaryOfOrganizationResponse getActivitySummaryOfOrganization(UUID id) {
        organizationValidationService.validateOrganizationId(id);

        var allActivitiesOfOrganization = activityRepository.findByOrganizationId(id);
        return ActivitySummaryOfOrganizationResponse.builder()
                .totalActivities(allActivitiesOfOrganization.size())
                .numberOfUpcomingActivity(
                        allActivitiesOfOrganization.stream()
                                .filter(activity -> activity.getStartDateTime().isAfter(LocalDateTime.now()))
                                .toList().size())
                .numberOfActiveActivity(
                        allActivitiesOfOrganization.stream()
                                .filter(activity -> activity.getStartDateTime().toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))
                                .toList().size())
                .totalVolunteers(0)
                .build();
    }

    @Override
    public OrganizationDetailsResponse getOrganizationDetails(UUID id) {
        var organizationOpt = organizationRepository.findById(id);

        if (organizationOpt.isEmpty()) {
            organizationValidationService.throwOrganizationDoNotExistException(id);
        }

        var organization = organizationOpt.get();

        var organizationMembers = organizationMembersRepository.findAllByOrganizationId(id).stream()
                .collect(Collectors.groupingBy(OrganizationMembers::getUserRole));
        var allActivitiesOfOrganization = activityRepository.findByOrganizationId(id);

        return OrganizationDetailsResponse.builder()
                .name(organization.getName())
                .type(organization.getType().name())
                .otherType(
                        organization.getOtherType()
                )
                .admins(
                        organizationMembers.get(UserRoleInOrganization.ADMIN).stream()
                                .map(member -> member.getUser().getId())
                                .map(userId -> OrganizationDetailsResponse.OrganizationAdmin.builder().adminId(userId).build())
                                .toList()
                )
                .numberOfFollowers(organizationMembers.getOrDefault(UserRoleInOrganization.FOLLOWER, List.of()).size())
                .numberOfActivity(allActivitiesOfOrganization.stream()
                        .filter(activity -> activity.getStartDateTime().toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))
                        .toList().size())
                .build();
    }
}
