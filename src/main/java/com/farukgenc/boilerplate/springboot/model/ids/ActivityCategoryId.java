package com.farukgenc.boilerplate.springboot.model.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCategoryId implements Serializable {
    @Serial
    private static final long serialVersionUID = -769650532677995343L;

    @Column(name = "activity_id")
    private UUID activityId;

    @Column(name = "category_key")
    private String categoryKey;
}
