package com.farukgenc.boilerplate.springboot.repository.projections;

import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInActivity;

import java.util.UUID;

public interface ParticipantProjection {
    UUID getUserId();
    UUID getActivityId();
    UserProjection getUser();
    UserRoleInActivity getUserRole();
}
