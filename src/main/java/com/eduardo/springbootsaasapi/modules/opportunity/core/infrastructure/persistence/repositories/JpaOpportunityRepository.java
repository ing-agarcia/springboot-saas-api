package com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.entities.OpportunityEntity;

public interface JpaOpportunityRepository extends JpaRepository<OpportunityEntity, Integer> {

    @Query(value = """
            SELECT o
            FROM OpportunityEntity o
            WHERE o.name = :name
            """)
    Optional<OpportunityEntity> findByName(@Param("name") String name);

    @Query(value = """
            SELECT
                op
            FROM
                UserHierarchy uh
                JOIN OpportunityEntity op ON op.owner.id = uh.childUserId
            WHERE
                uh.parentUserId = :userId
            ORDER BY
                op.owner.id, op.name
            """, countQuery = """
                    SELECT
                        COUNT(op)
                    FROM
                        UserHierarchy uh
                        JOIN OpportunityEntity op ON op.owner.id = uh.childUserId
                    WHERE
                        uh.parentUserId = :userId
            """)
    Page<OpportunityEntity> findAllByUserId(Integer userId, Pageable pageable);

}
