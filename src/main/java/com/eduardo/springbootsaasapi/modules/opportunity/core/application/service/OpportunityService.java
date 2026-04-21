package com.eduardo.springbootsaasapi.modules.opportunity.core.application.service;

import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.CreateOpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.mappers.OpportunityMapper;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.repositories.OpportunityRepository;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.BadRequestException;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.NotFoundException;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public OpportunityDTO newOpportunity(CreateOpportunityDTO createOpportunityDTO) {

        if (opportunityRepository.findByName(createOpportunityDTO.name()).isPresent()) {
            throw new BadRequestException("Opportunity already exists");
        }

        Opportunity opportunity = OpportunityMapper.toOpportunity(createOpportunityDTO);
        Opportunity saved = opportunityRepository.save(opportunity);

        return opportunityRepository.findDTOById(saved.getId())
                .orElseThrow(() -> new NotFoundException("Opportunity not found"));

    }

}
