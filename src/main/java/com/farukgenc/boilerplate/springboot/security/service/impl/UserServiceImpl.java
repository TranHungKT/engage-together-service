package com.farukgenc.boilerplate.springboot.security.service.impl;

import com.farukgenc.boilerplate.springboot.exceptions.DataException;
import com.farukgenc.boilerplate.springboot.model.CustomUserDetails;
import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.model.enums.UserRole;
import com.farukgenc.boilerplate.springboot.repository.UserRepository;
import com.farukgenc.boilerplate.springboot.security.dto.AuthenticatedUserDto;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.UserDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.mapper.UserMapper;
import com.farukgenc.boilerplate.springboot.security.service.UserService;
import com.farukgenc.boilerplate.springboot.security.utils.UserDetailUtils;
import com.farukgenc.boilerplate.springboot.service.UserValidationService;
import com.farukgenc.boilerplate.springboot.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserValidationService userValidationService;

    private final GeneralMessageAccessor generalMessageAccessor;

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

        if(userOptional.isEmpty()){
            throw new DataException("User not found");
        }

        var user = userOptional.get();

        return UserDetailsResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
