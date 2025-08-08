package com.farukgenc.boilerplate.springboot.repository.projections;

import java.util.UUID;

public interface ActivityCategoryIdProjection {
    UUID getActivityId();

    String getCategoryKey();
}
