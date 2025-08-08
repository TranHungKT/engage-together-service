package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.repository.projections.ActivityProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    List<Activity> findByOrganizationId(UUID organizationId);

    @Query("select a.id as id, a.title as title, a.description as description, a.startDateTime as startDateTime, a.endDateTime as endDateTime, a.maxAttendees as maxAttendees, a.organization.id as organizationId, ac as categories " +
            "from Activity a JOIN ActivityCategory ac ON a.id = ac.id.activityId " +
            "where a.title like %:title% or :title is null or :title =''")
    Page<ActivityProjection> searchActivity(String title, Pageable pageable);
}
