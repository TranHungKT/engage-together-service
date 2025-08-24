package com.farukgenc.boilerplate.springboot.model;

import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInOrganization;
import com.farukgenc.boilerplate.springboot.model.ids.OrganizationMembersId;
import com.farukgenc.boilerplate.springboot.model.ids.OrganizationMembersId_;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrganizationMembersId.class)
@Builder
@Table(name = "organization_member")
public class OrganizationMember {
    @Id
    private UUID userId;

    @Id
    private UUID organizationId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false, referencedColumnName = Organization_.ID)
    @MapsId(value = OrganizationMembersId_.ORGANIZATION_ID)
    private Organization organization;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, nullable = false, updatable = false, referencedColumnName = User_.ID)
    @MapsId(value = OrganizationMembersId_.USER_ID)
    private User user;

    @Enumerated(EnumType.STRING)
    private UserRoleInOrganization userRole;

    @Length(max = 50)
    @Column(nullable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdDt;

    @Length(max = 50)
    private String updatedBy;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedDt;
}
