package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.Opportunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OpportunityRepository extends JpaRepository<Opportunity, UUID> {
    List<Opportunity> findByOrganizationId(UUID organizationId);

    @Query("select o from Opportunity o where o.title like %:title%")
    Page<Opportunity> searchOpportunity(String title, Pageable pageable);
}
