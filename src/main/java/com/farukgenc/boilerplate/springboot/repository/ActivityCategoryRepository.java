package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.ActivityCategory;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ActivityCategoryRepository extends JpaRepository<ActivityCategory, ActivityCategoryId> {
    List<ActivityCategory> findAllByIdActivityId(UUID activityId);
    List<ActivityCategory> findAllByIdActivityIdIn(Collection<UUID> activityIds);
}
