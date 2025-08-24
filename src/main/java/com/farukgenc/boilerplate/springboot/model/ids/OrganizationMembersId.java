package com.farukgenc.boilerplate.springboot.model.ids;

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

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationMembersId implements Serializable {
    @Serial
    private static final long serialVersionUID = 761503947455170507L;

    private UUID userId;
    private UUID organizationId;
}
