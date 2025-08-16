package com.farukgenc.boilerplate.springboot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@Table(name = "Activity")
@Entity
@NoArgsConstructor
public class Activity extends BaseEntity {
    @Id
    @Column
    private UUID id;

    private String title;

    private String description;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Integer maxAttendees;

    private String address;

    private String stateProvince;

    private String city;

    private String postalCode;

    private String country;

    @OneToMany(mappedBy = ActivityCategory_.ACTIVITY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityCategory> categories = new ArrayList<>();

    @OneToMany(mappedBy = ActivityParticipant_.ACTIVITY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityParticipant> activityParticipants = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;
}
