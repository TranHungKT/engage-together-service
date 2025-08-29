package com.farukgenc.boilerplate.springboot.repository.projections;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ActivityProjection {
    UUID getId();

    String getTitle();

    String getDescription();

    LocalDateTime getStartDateTime();

    LocalDateTime getEndDateTime();

    Integer getMaxAttendees();

    UUID getOrganizationId();

    OrganizationProjection getOrganization();
}
