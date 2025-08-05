package com.farukgenc.boilerplate.springboot.model;

import com.farukgenc.boilerplate.springboot.model.ids.ActivityCategoryId;
import com.farukgenc.boilerplate.springboot.model.ids.ActivityId_;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "activity_category")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ActivityCategory extends BaseEntity {
    @EmbeddedId
    private ActivityCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "activity_id", referencedColumnName = ActivityId_.ID, insertable = false, updatable = false, nullable = false),
            @JoinColumn(name = "organization_id", referencedColumnName = ActivityId_.ORGANIZATION_ID, insertable = false, updatable = false, nullable = false)

    })
    private Activity activity;
}
