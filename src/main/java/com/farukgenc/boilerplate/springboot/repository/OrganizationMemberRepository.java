package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.OrganizationMember;
import com.farukgenc.boilerplate.springboot.model.ids.OrganizationMembersId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember, OrganizationMembersId> {
    List<OrganizationMember> findAllByOrganizationId(UUID organizationId);

    List<OrganizationMember> findAllByOrganizationIdAndUserIdIn(UUID organizationId, Collection<UUID> userIds);
}
