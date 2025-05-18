package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.exceptions.RegistrationException;
import com.farukgenc.boilerplate.springboot.model.Organization;
import com.farukgenc.boilerplate.springboot.model.OrganizationMembers;
import com.farukgenc.boilerplate.springboot.repository.OpportunityRepository;
import com.farukgenc.boilerplate.springboot.repository.OrganizationMembersRepository;
import com.farukgenc.boilerplate.springboot.repository.OrganizationRepository;
import com.farukgenc.boilerplate.springboot.repository.UserRepository;
import com.farukgenc.boilerplate.springboot.security.dto.OpportunitySummaryOfOrganizationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.RegistrationOrganizationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.mapper.OrganizationMapper;
import com.farukgenc.boilerplate.springboot.security.utils.UserDetailUtils;
import com.farukgenc.boilerplate.springboot.service.OrganizationValidationService;
import com.farukgenc.boilerplate.springboot.utils.GeneralMessageAccessor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

    private final OrganizationValidationService organizationValidationService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final OrganizationRepository organizationRepository;
    private final OpportunityRepository opportunityRepository;

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
        var member = OrganizationMembers.builder().user(user.get()).organization(savedOrganization).createdBy(user.get().getUsername()).userRole("MASTER").build();
        organizationMembersRepository.save(member);
        final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, registrationOrganizationRequest.getName());

        log.info("{} registered successful!", registrationOrganizationRequest.getName());

        return new RegistrationResponse(registrationSuccessMessage);
    }

    @Override
    public OpportunitySummaryOfOrganizationResponse getOpportunitySummaryOfOrganization(UUID id) {
        organizationValidationService.validateOrganizationId(id);

        var allOpportunitiesOfOrganization = opportunityRepository.findByOrganizationId(id);
        return OpportunitySummaryOfOrganizationResponse.builder()
                .totalOpportunities(allOpportunitiesOfOrganization.size())
                .numberOfUpcomingOpportunity(
                        allOpportunitiesOfOrganization.stream()
                                .filter(opportunity -> opportunity.getStartDateTime().isAfter(LocalDateTime.now()))
                                .toList().size())
                .numberOfActiveOpportunity(
                        allOpportunitiesOfOrganization.stream()
                                .filter(opportunity -> opportunity.getStartDateTime().toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))
                                .toList().size())
                .totalVolunteers(0)
                .build();
    }
}
