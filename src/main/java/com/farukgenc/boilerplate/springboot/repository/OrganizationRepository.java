package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.Organization;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsById(@NonNull UUID id);

    Optional<Organization> findById(UUID id);

    @Query(value = """
        select org from Organization org join OrganizationMember om on org.id = om.organizationId
        where om.userId = :userId
    """)
    List<Organization> findByUserId(UUID userId);
}
