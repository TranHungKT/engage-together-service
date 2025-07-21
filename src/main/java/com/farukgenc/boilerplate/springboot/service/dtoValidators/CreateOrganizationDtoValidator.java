package com.farukgenc.boilerplate.springboot.service.dtoValidators;

import com.farukgenc.boilerplate.springboot.exceptions.BadRequestException;
import com.farukgenc.boilerplate.springboot.model.enums.OrganizationType;
import com.farukgenc.boilerplate.springboot.security.dto.request.RegistrationOrganizationRequest;
import lombok.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class CreateOrganizationDtoValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return RegistrationOrganizationRequest.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        RegistrationOrganizationRequest request = (RegistrationOrganizationRequest) target;
        if (request.getType() == OrganizationType.OTHER && Objects.isNull(request.getOtherType())) {
            throw new BadRequestException("Other type must not be null when organization type is OTHER");
        }

        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new BadRequestException("Password miss match");
        }
    }
}
