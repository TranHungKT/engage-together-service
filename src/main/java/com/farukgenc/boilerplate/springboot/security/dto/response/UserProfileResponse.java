package com.farukgenc.boilerplate.springboot.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserProfileResponse {

    private UUID id;

    private String username;

    private String email;

    private String name;

    private String avatar;

    private LocalDateTime createdDt;

    private Integer numberOfActivityRegistered;

    private Integer numberOfActivityCompleted;

    private Integer numberOfDidNotJoinActivity;
}
