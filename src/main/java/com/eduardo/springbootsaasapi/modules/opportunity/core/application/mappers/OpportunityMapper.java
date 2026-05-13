package com.eduardo.springbootsaasapi.modules.opportunity.core.application.mappers;

import org.springframework.stereotype.Component;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityCreateDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;
import com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class OpportunityMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public OpportunityDTO toDto(Opportunity opportunity) {
        return new OpportunityDTO(
                opportunity.getId(),
                opportunity.getOwnerId(),
                entityManager.getReference(UserEntity.class, opportunity.getOwnerId()).getName(),
                opportunity.getName(),
                opportunity.getStage(),
                opportunity.getProbability(),
                opportunity.getCreatedAt());
    }

    public Opportunity toEntity(OpportunityDTO opportunityDTO) {
        return new Opportunity(
                opportunityDTO.id(),
                opportunityDTO.ownerId(),
                opportunityDTO.name(),
                opportunityDTO.stage(),
                opportunityDTO.probability(),
                opportunityDTO.createdAt());
    }

    public Opportunity toCreateEntity(OpportunityCreateDTO createOpportunityDTO) {
        return new Opportunity(
                createOpportunityDTO.id(),
                createOpportunityDTO.ownerId(),
                createOpportunityDTO.name(),
                createOpportunityDTO.stage(),
                createOpportunityDTO.probability(),
                null);

    }
}
