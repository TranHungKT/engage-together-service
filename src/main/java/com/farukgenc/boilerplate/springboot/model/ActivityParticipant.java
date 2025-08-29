package com.farukgenc.boilerplate.springboot.model;

import com.farukgenc.boilerplate.springboot.model.enums.ActivityParticipantStatus;
import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInActivity;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityParticipantId;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityParticipantId_;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@Table(name = "activity_participant")
@NoArgsConstructor
@IdClass(ActivityParticipantId.class)
public class ActivityParticipant extends BaseEntity {
    @Id
    private UUID userId;

    @Id
    private UUID activityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = User_.ID, insertable = false, updatable = false)
    @MapsId(value = ActivityParticipantId_.USER_ID)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", referencedColumnName = Activity_.ID, insertable = false, updatable = false)
    @MapsId(value = ActivityParticipantId_.ACTIVITY_ID)
    private Activity activity;

    @Enumerated(EnumType.STRING)
    private UserRoleInActivity userRole;

    @Enumerated(EnumType.STRING)
    private ActivityParticipantStatus status;
}
