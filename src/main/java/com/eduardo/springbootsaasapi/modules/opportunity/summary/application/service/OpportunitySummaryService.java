package com.eduardo.springbootsaasapi.modules.opportunity.summary.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.opportunity.summary.application.dto.OpportunitySummaryDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.summary.domain.repositories.OpportunitySummaryRepository;

@Service
public class OpportunitySummaryService {

    private final OpportunitySummaryRepository opportunitySummaryRepository;

    public OpportunitySummaryService(OpportunitySummaryRepository opportunitySummaryRepository) {
        this.opportunitySummaryRepository = opportunitySummaryRepository;
    }

    public Page<OpportunitySummaryDTO> findOpportunitiesByUserHierarchy(Integer userId, Pageable pageable) {
        return opportunitySummaryRepository.findOpportunitiesByUserHierarchy(userId, pageable);
    }

}
