package com.farukgenc.boilerplate.springboot.model;

import com.farukgenc.boilerplate.springboot.model.ids.ActivityId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
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
@IdClass(ActivityId.class)
public class Activity extends BaseEntity {
    @Id
    @Column(unique = true)
    private UUID id;

    @Id
    private UUID organizationId;

    private String title;

    private String description;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityCategory> categories = new ArrayList<>();
}
