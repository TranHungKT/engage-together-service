package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.security.dto.AuthenticatedUserDto;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.security.dto.request.SearchUserRequest;
import com.farukgenc.boilerplate.springboot.security.dto.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.SearchUserResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.UserDetailsResponse;
import com.farukgenc.boilerplate.springboot.security.dto.response.UserProfileResponse;

import java.util.List;
import java.util.UUID;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserService {

    User findByUsername(String username);

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

    UserDetailsResponse getUserDetails();

    List<SearchUserResponse> searchUser(SearchUserRequest request);

    UserProfileResponse getUserProfile(UUID userId);

}
