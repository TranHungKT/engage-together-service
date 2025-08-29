package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.Activity;
import com.farukgenc.boilerplate.springboot.model.enums.ActivityStatus;
import com.farukgenc.boilerplate.springboot.repository.projections.ActivityProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    List<Activity> findByOrganizationId(UUID organizationId);

    @Query("SELECT DISTINCT a.id as id, a.title as title, a.description as description, a.startDateTime as startDateTime, a.endDateTime as endDateTime, a.maxAttendees as maxAttendees, a.status as status ,a.organization.id as organizationId, o as organization "
            + "FROM Activity a "
            + "LEFT JOIN Organization o on a.organization.id = o.id "
            + "LEFT JOIN ActivityParticipant ap on a.id = ap.activityId "
            + "WHERE (a.title LIKE %:title% OR :title IS NULL OR :title ='') "
            + "AND (ap.userId = :userId OR :userId IS NULL) "
            + "AND (o.id = :organizationId OR :organizationId IS NULL) "
            + "AND (a.status IN :activityStatuses or :activityStatuses IS NULL)"
    )
    Page<ActivityProjection> searchActivity(String title, UUID userId, UUID organizationId, Collection<ActivityStatus> activityStatuses , Pageable pageable);
}
