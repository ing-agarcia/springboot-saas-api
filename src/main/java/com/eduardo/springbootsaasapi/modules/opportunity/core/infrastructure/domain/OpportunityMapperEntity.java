package com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.domain;

import org.springframework.stereotype.Component;

import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.entities.OpportunityEntity;
import com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class OpportunityMapperEntity {

    @PersistenceContext
    private EntityManager entityManager;

    public OpportunityEntity toEntity(Opportunity opportunity) {
        OpportunityEntity entity = new OpportunityEntity();
        entity.setId(opportunity.getId());
        if (opportunity.getOwnerId() != null) {
            entity.setOwner(entityManager.getReference(UserEntity.class, opportunity.getOwnerId()));
        }
        entity.setName(opportunity.getName());
        entity.setStage(opportunity.getStage());
        entity.setProbability(opportunity.getProbability());
        entity.setCreatedAt(opportunity.getCreatedAt());
        return entity;
    }

    public Opportunity toDomain(OpportunityEntity entity) {
        Opportunity opportunity = new Opportunity();
        opportunity.setId(entity.getId());
        opportunity.setOwnerId(entity.getOwner() != null ? entity.getOwner().getId() : null);
        opportunity.setName(entity.getName());
        opportunity.setStage(entity.getStage());
        opportunity.setProbability(entity.getProbability());
        opportunity.setCreatedAt(opportunity.getCreatedAt());

        return opportunity;
    }
}
