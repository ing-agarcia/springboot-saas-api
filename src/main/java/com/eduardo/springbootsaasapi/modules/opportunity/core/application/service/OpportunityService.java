package com.eduardo.springbootsaasapi.modules.opportunity.core.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityCreateDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.mappers.OpportunityMapper;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.repositories.OpportunityRepository;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.BadRequestException;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final OpportunityMapper opportunityMapper;

    public OpportunityService(
            OpportunityRepository opportunityRepository,
            OpportunityMapper opportunityMapper) {
        this.opportunityRepository = opportunityRepository;
        this.opportunityMapper = opportunityMapper;
    }

    public OpportunityDTO newOpportunity(OpportunityCreateDTO createOpportunityDTO) {

        Opportunity opportunity = opportunityMapper.toCreateEntity(createOpportunityDTO);
        Optional<Opportunity> existingOpportunity = opportunityRepository.findByName(opportunity.getName());
        if (existingOpportunity.isPresent()
                &&
                !existingOpportunity.get()
                        .getId()
                        .equals(opportunity.getId())) {
            throw new BadRequestException(
                    "Opportunity already exists");
        }

        Opportunity saved = opportunityRepository.save(opportunity);

        return opportunityMapper.toDto(saved);

    }

    public Page<OpportunityDTO> getOpportunitiesByUserId(Integer userId, Pageable pageable) {

        return opportunityRepository.findAllByUserId(userId, pageable)
                .map(opportunityMapper::toDto);
    }
}
