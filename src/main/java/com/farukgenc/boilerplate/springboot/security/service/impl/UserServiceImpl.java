package com.farukgenc.boilerplate.springboot.security.service.impl;

import com.farukgenc.boilerplate.springboot.exceptions.DataException;
import com.farukgenc.boilerplate.springboot.model.ActivityParticipant;
import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.model.enums.ActivityParticipantStatus;
import com.farukgenc.boilerplate.springboot.model.enums.UserRole;
import com.farukgenc.boilerplate.springboot.repository.ActivityParticipantRepository;
import com.farukgenc.boilerplate.springboot.repository.OrganizationRepository;
import com.farukgenc.boilerplate.springboot.repository.UserRepository;
import com.farukgenc.boilerplate.springboot.security.dto.AuthenticatedUserDto;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchUserRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.SearchUserResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.UserDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.UserProfileResponse;
import com.farukgenc.boilerplate.springboot.security.mapper.BasicMapper;
import com.farukgenc.boilerplate.springboot.security.mapper.UserMapper;
import com.farukgenc.boilerplate.springboot.security.service.UserService;
import com.farukgenc.boilerplate.springboot.security.utils.UserDetailUtils;
import com.farukgenc.boilerplate.springboot.service.UserValidationService;
import com.farukgenc.boilerplate.springboot.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

    private final UserRepository userRepository;
    private final ActivityParticipantRepository activityParticipantRepository;
    private final OrganizationRepository organizationRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserValidationService userValidationService;

    private final GeneralMessageAccessor generalMessageAccessor;
    private final BasicMapper basicMapper;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {

        userValidationService.validateUser(registrationRequest);

        final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER);

        userRepository.save(user);

        final String username = registrationRequest.getUsername();
        final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

        log.info("{} registered successfully!", username);

        return new RegistrationResponse(registrationSuccessMessage);
    }

    @Override
    public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

        final User user = findByUsername(username);

        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }

    @Override
    public UserDetailsResponse getUserDetails() {
        var userOptional = userRepository.findById(UserDetailUtils.getUserDetailsByToken().getUserId());

        if (userOptional.isEmpty()) {
            throw new DataException("User not found");
        }

        var user = userOptional.get();
        var organizations = organizationRepository.findByUserId(user.getId());

        return UserDetailsResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .organizations(organizations.stream().map(organization -> UserDetailsResponse.OrganizationResponse.builder()
                                .organizationId(organization.getId())
                                .organizationName(organization.getName())
                                .build())
                        .toList())
                .build();
    }

    @Override
    public List<SearchUserResponse> searchUser(SearchUserRequest request) {
        return basicMapper.convertToResponseList(userRepository.searchUser(request.getOrganizationId(), request.getUsername()), SearchUserResponse.class);
    }

    @Override
    public UserProfileResponse getUserProfile(UUID userId) {
        var userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new DataException("User not found");
        }

        var user = userOptional.get();

        var activityParticipants = activityParticipantRepository.findByUserId(user.getId());
        var numberOfActivityMap = activityParticipants.stream().collect(Collectors.groupingBy(ActivityParticipant::getStatus));
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .name(user.getName())
                .createdDt(user.getCreatedDt())
                .numberOfActivityRegistered(numberOfActivityMap.getOrDefault(ActivityParticipantStatus.REGISTERED, List.of()).size())
                .numberOfActivityCompleted(numberOfActivityMap.getOrDefault(ActivityParticipantStatus.COMPLETED, List.of()).size())
                .numberOfDidNotJoinActivity(numberOfActivityMap.getOrDefault(ActivityParticipantStatus.DID_NOT_JOIN, List.of()).size())
                .build();
    }
}
