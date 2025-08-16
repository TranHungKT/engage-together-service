package com.farukgenc.boilerplate.springboot.service;

import com.farukgenc.boilerplate.springboot.exceptions.RegistrationException;
import com.farukgenc.boilerplate.springboot.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityValidationService {
    public void validateActivity(Optional<Activity> activity) {
        if (activity.isEmpty()) {
            log.warn("Activity is not exist");

            throw new RegistrationException("Activity is not exist");
        }
    }
}
