package com.farukgenc.boilerplate.springboot.security.dto.request;

import com.farukgenc.boilerplate.springboot.common.model.dto.request.CustomPagingRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SearchActivityRequest extends CustomPagingRequest {
    @NotNull
    private String organizationId;

    private String title;
}
