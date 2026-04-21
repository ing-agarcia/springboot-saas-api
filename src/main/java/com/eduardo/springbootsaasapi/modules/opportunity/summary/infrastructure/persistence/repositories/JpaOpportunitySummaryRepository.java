package com.eduardo.springbootsaasapi.modules.opportunity.summary.infrastructure.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduardo.springbootsaasapi.modules.opportunity.summary.application.dto.OpportunitySummaryDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.summary.infrastructure.persistence.entities.OpportunitySummaryEntity;

public interface JpaOpportunitySummaryRepository extends JpaRepository<OpportunitySummaryEntity, Integer> {

    @Query(value = """
            SELECT
                os.id AS id,
                os.name AS name,
                os.stage AS stage,
                os.probability AS probability,
                os.amount AS amount,
                os.created_at AS createdAt,
                MAX(CASE WHEN uh.level = 0 THEN u2.name END) AS salesName,
                MAX(CASE WHEN uh.level = 1 THEN u2.name END) AS managerName,
                MAX(CASE WHEN uh.level = 2 THEN u2.name END) AS directorName,
                MAX(CASE WHEN uh.level = 3 THEN u2.name END) AS vpName,
                MAX(CASE WHEN uh.level = 4 THEN u2.name END) AS rootName
            FROM opportunity_summary os
                JOIN user_hierarchy uh
                    ON uh.child_user_id = os.owner_id
                JOIN users u2
                    ON u2.id = uh.parent_user_id
            WHERE os.owner_id IN (
                SELECT child_user_id
                FROM user_hierarchy
                WHERE parent_user_id = :userId
            )
            GROUP BY
                os.id,
                os.name,
                os.amount,
                os.stage,
                os.probability,
                os.created_at

            """, countQuery = """
            SELECT
                COUNT(distinct os.id)
            FROM opportunity_summary os
                JOIN user_hierarchy uh
                    ON uh.child_user_id = os.owner_id
                JOIN users u2
                    ON u2.id = uh.parent_user_id
            WHERE os.owner_id IN (
                SELECT child_user_id
                FROM user_hierarchy
                WHERE parent_user_id = :userId
            )
            """, nativeQuery = true)
    Page<OpportunitySummaryDTO> findOpportunitiesByUserHierarchy(Integer userId, Pageable pageable);
}
