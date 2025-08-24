package com.farukgenc.boilerplate.springboot.repository.projections;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ActivityProjection {
    UUID getId();

    String getTitle();

    String getDescription();

    LocalDateTime getStartDateTime();

    LocalDateTime getEndDateTime();

    Integer getMaxAttendees();

    List<ActivityCategoryProjection> getCategories();

    UUID getOrganizationId();

    OrganizationProjection getOrganization();
}
