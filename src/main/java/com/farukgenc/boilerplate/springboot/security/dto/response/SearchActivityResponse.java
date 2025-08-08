package com.farukgenc.boilerplate.springboot.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class SearchActivityResponse {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Integer maxAttendees;

    private List<ActivityCategoryResponse> categories;

    private UUID organizationId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ActivityCategoryResponse {
        private ActivityCategoryIdResponse id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ActivityCategoryIdResponse {
        private UUID activityId;
        private String categoryKey;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class OrganizationResponse {
        private UUID organizationId;
    }
}
