package com.farukgenc.boilerplate.springboot.security.dto.request;

import com.farukgenc.boilerplate.springboot.model.enums.ActivityParticipantStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ManageParticipantsRequest {
    private UUID activityId;
    private List<Participant> updatedParticipants;

    @Getter
    @Setter
    public static class Participant {
        private UUID userId;
        private ActivityParticipantStatus status;
    }
}
