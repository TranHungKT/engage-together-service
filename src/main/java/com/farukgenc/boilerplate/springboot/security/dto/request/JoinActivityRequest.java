package com.farukgenc.boilerplate.springboot.security.dto.request;

import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInActivity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class JoinActivityRequest {
    private UUID activityId;

    private UserRoleInActivity role;
}
