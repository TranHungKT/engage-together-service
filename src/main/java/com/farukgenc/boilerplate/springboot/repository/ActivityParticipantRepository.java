package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.ActivityParticipant;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityParticipantId;
import com.farukgenc.boilerplate.springboot.repository.projections.ParticipantProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ActivityParticipantRepository extends JpaRepository<ActivityParticipant, ActivityParticipantId> {
    @Query("select ap from ActivityParticipant ap join User u on ap.userId = u.id where ap.activityId = :activityId")
    List<ParticipantProjection> findByActivityId(UUID activityId);
}
