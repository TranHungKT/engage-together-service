package com.farukgenc.boilerplate.springboot.model;

import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInActivity;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityParticipantId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder(toBuilder = true)
@Table(name = "activity_member")
@NoArgsConstructor
@IdClass(ActivityParticipantId.class)
public class ActivityParticipant extends BaseEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = User_.ID, insertable = false, updatable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", referencedColumnName = Activity_.ID, insertable = false, updatable = false)
    private Activity activity;

    @Enumerated(EnumType.STRING)
    private UserRoleInActivity userRole;

}
