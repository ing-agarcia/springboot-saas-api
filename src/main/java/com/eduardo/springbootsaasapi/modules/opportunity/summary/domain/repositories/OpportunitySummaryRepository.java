package com.eduardo.springbootsaasapi.modules.opportunity.summary.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eduardo.springbootsaasapi.modules.opportunity.summary.application.dto.OpportunitySummaryDTO;

public interface OpportunitySummaryRepository {
    Page<OpportunitySummaryDTO> findOpportunitiesByUserHierarchy(
            Integer userId,
            Pageable pageable);
}
