package com.farukgenc.boilerplate.springboot.service;

import com.farukgenc.boilerplate.springboot.exceptions.RegistrationException;
import com.farukgenc.boilerplate.springboot.model.ActivityParticipant;
import com.farukgenc.boilerplate.springboot.repository.projections.ParticipantProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityParticipantValidationService {
    public void validateParticipantAlreadyJoin(UUID userId, List<ParticipantProjection> activityParticipants){
        var activityParticipantIds = activityParticipants.stream().map(ParticipantProjection::getUserId).toList();
        if(activityParticipantIds.contains(userId)){
            log.warn("User has already join this activity");

            throw new RegistrationException("User has already join this activity");
        }
    }
}
