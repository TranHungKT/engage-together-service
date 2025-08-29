package com.farukgenc.boilerplate.springboot.security.dto.request;

import com.farukgenc.boilerplate.springboot.common.model.dto.request.CustomPagingRequest;
import com.farukgenc.boilerplate.springboot.model.enums.ActivityStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SearchActivityRequest extends CustomPagingRequest {
    private UUID organizationId;

    private UUID userId;
    private String title;

    private List<ActivityStatus> statuses;
}
