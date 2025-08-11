package com.farukgenc.boilerplate.springboot.model.ids;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Embeddable
@Getter
@Setter
public class ActivityParticipantId implements Serializable {
    @Serial
    private static final long serialVersionUID = -5861180923335350323L;

    private UUID userId;
    private UUID activityId;
}
