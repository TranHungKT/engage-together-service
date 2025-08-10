package com.farukgenc.boilerplate.springboot.model;

import com.farukgenc.boilerplate.springboot.model.enums.UserRoleInOrganization;
import com.farukgenc.boilerplate.springboot.model.ids.OrganizationId_;
import com.farukgenc.boilerplate.springboot.model.ids.OrganizationMembersId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrganizationMembersId.class)
@Table(name = "organization_member")
public class OrganizationMember extends BaseEntity {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, nullable = false, updatable = false, referencedColumnName = User_.ID)
    private User user;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false, referencedColumnName = OrganizationId_.ID)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    private UserRoleInOrganization userRole;
}
