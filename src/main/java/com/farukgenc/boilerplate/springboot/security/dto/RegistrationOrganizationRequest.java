package com.farukgenc.boilerplate.springboot.security.dto;

import com.farukgenc.boilerplate.springboot.model.enums.OrganizationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationOrganizationRequest {
    @NotEmpty(message = "name not empty")
    private String name;

    @NotEmpty(message = "email not empty")
    @Email
    private String email;

    @NotEmpty(message = "password not empty")
    private String password;

    @NotEmpty(message = "confirmPassword not empty")
    private String confirmPassword;

    @NotEmpty(message = "phoneNumber not empty")
    private String phoneNumber;

    @NotNull(message = "type not empty")
    private OrganizationType type;

    private String otherType;

    private String missionStatement;

    @NotEmpty(message = "address not empty")
    private String address;

    @NotEmpty(message = "city not empty")
    private String city;

    @NotEmpty(message = "stateProvince not empty")
    private String stateProvince;

    @NotEmpty(message = "zipPostalCode not empty")
    private String zipPostalCode;

    @NotEmpty(message = "country not empty")
    private String country;

}
