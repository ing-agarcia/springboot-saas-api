package com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.entities.OpportunityEntity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;

public interface JpaOpportunityRepository extends JpaRepository<OpportunityEntity, Integer> {

    @Query(value = """
            SELECT o
            FROM OpportunityEntity o
            WHERE o.name = :name
            """)
    Optional<OpportunityEntity> findByName(@Param("name") String name);

    @Query(value = """
            SELECT new com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO(
                o.id,
                o.owner.id,
                o.name,
                o.stage,
                o.probability,
                o.createdAt
            )
            FROM OpportunityEntity o
            WHERE o.id = :id
            """)
    Optional<OpportunityDTO> findDTOById(@Param("id") Integer id);
}
