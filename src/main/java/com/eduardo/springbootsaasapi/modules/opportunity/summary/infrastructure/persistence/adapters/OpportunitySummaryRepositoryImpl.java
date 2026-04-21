package com.eduardo.springbootsaasapi.modules.opportunity.summary.infrastructure.persistence.adapters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.opportunity.summary.application.dto.OpportunitySummaryDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.summary.domain.repositories.OpportunitySummaryRepository;
import com.eduardo.springbootsaasapi.modules.opportunity.summary.infrastructure.persistence.repositories.JpaOpportunitySummaryRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OpportunitySummaryRepositoryImpl implements OpportunitySummaryRepository {

    private final JpaOpportunitySummaryRepository jpaOpportunitySummaryRepository;

    @Override
    public Page<OpportunitySummaryDTO> findOpportunitiesByUserHierarchy(Integer userId, Pageable pageable) {
        return jpaOpportunitySummaryRepository.findOpportunitiesByUserHierarchy(userId, pageable);
    }

}
