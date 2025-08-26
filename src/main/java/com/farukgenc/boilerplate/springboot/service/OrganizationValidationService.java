package com.farukgenc.boilerplate.springboot.service;

import com.farukgenc.boilerplate.springboot.exceptions.DataException;
import com.farukgenc.boilerplate.springboot.exceptions.RegistrationException;
import com.farukgenc.boilerplate.springboot.model.Organization;
import com.farukgenc.boilerplate.springboot.model.OrganizationMember;
import com.farukgenc.boilerplate.springboot.repository.OrganizationMemberRepository;
import com.farukgenc.boilerplate.springboot.repository.OrganizationRepository;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationOrganizationRequest;
import com.farukgenc.boilerplate.springboot.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationValidationService {
    private static final String EMAIL_ALREADY_EXISTS = "organization_email_exists";
    private static final String NAME_ALREADY_EXISTS = "organization_name_exists";

    private final OrganizationRepository organizationRepository;
    private final OrganizationMemberRepository organizationMemberRepository;
    private final ExceptionMessageAccessor exceptionMessageAccessor;

    public void validateOrganization(RegistrationOrganizationRequest request) {
        final String email = request.getEmail();
        final String name = request.getName();

        checkEmail(email);
        checkUsername(name);
    }

    public void validateOrganizationId(UUID id) {
        final boolean existByOrganizationId = organizationRepository.existsById(id);

        if (!existByOrganizationId) {
            throwOrganizationDoNotExistException(id);
        }
    }

    public void validateAdminUser(List<UUID> adminUsers, UUID organizationId) {
        var organizationMembers = organizationMemberRepository.findAllByOrganizationIdAndUserIdIn(organizationId, adminUsers).stream().map(OrganizationMember::getUserId).toList();

        var cloneAdminUsers = new ArrayList<>(adminUsers);
        cloneAdminUsers.removeAll(organizationMembers);
        if (!cloneAdminUsers.isEmpty()) {
            log.warn("Some user id {} is not in organization", cloneAdminUsers);

            throw new RegistrationException("Some user do not belong to organization");
        }
    }

    public void validateOrganizationIsNotExist(Optional<Organization> organizationOpt) {

        if (organizationOpt.isEmpty()) {
            log.warn("Organization is not exist");

            throw new RegistrationException("Organization is not exist");
        }
    }

    public void throwOrganizationDoNotExistException(UUID id) {
        log.warn("Organization id {} is not exist", id);

        throw new DataException("Organization id {} is not exist");
    }

    private void checkEmail(String email) {
        final boolean existsByEmail = organizationRepository.existsByEmail(email);

        if (existsByEmail) {
            log.warn("{} is already being used!", email);

            final String existsEmail = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
            throw new RegistrationException(existsEmail);
        }
    }

    private void checkUsername(String username) {

        final boolean existsByUsername = organizationRepository.existsByName(username);

        if (existsByUsername) {

            log.warn("{} is already being used!", username);

            final String existsUsername = exceptionMessageAccessor.getMessage(null, NAME_ALREADY_EXISTS);
            throw new RegistrationException(existsUsername);
        }
    }
}
