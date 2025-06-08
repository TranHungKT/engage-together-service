package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.OrganizationMembers;
import com.farukgenc.boilerplate.springboot.model.ids.OrganizationMembersId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrganizationMembersRepository extends JpaRepository<OrganizationMembers, OrganizationMembersId> {
    List<OrganizationMembers> findAllByOrganizationId(UUID organizationId);
}
