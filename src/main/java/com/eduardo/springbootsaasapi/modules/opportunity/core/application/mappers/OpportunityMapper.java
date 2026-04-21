package com.eduardo.springbootsaasapi.modules.opportunity.core.application.mappers;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.CreateOpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;

public class OpportunityMapper {

    public static OpportunityDTO toDto(Opportunity opportunity) {
        return new OpportunityDTO(
                opportunity.getId(),
                opportunity.getOwnerId(),
                opportunity.getName(),
                opportunity.getStage(),
                opportunity.getProbability(),
                opportunity.getCreatedAt());
    }

    public static Opportunity toEntity(OpportunityDTO opportunityDTO) {
        return new Opportunity(
                opportunityDTO.id(),
                opportunityDTO.ownerId(),
                opportunityDTO.name(),
                opportunityDTO.stage(),
                opportunityDTO.probability(),
                opportunityDTO.createdAt());
    }

    public static Opportunity toOpportunity(CreateOpportunityDTO createOpportunityDTO) {
        return new Opportunity(
                null,
                createOpportunityDTO.ownerId(),
                createOpportunityDTO.name(),
                createOpportunityDTO.stage(),
                createOpportunityDTO.probability(),
                null);

    }
}
