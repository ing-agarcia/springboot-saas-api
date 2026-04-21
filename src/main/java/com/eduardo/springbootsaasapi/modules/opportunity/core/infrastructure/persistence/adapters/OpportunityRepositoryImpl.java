package com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.adapters;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.repositories.OpportunityRepository;
import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.domain.OpportunityMapperEntity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.entities.OpportunityEntity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.repositories.JpaOpportunityRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OpportunityRepositoryImpl implements OpportunityRepository {

    private final JpaOpportunityRepository jpaOpportunityRepository;
    private final OpportunityMapperEntity opportunityMapperEntity;

    @SuppressWarnings("null")
    @Override
    public Opportunity save(Opportunity opportunity) {
        OpportunityEntity entity = opportunityMapperEntity.toEntity(opportunity);
        OpportunityEntity saved = jpaOpportunityRepository.save(entity);
        return opportunityMapperEntity.toDomain(saved);
    }

    @Override
    public Optional<Opportunity> findByName(String name) {
        return jpaOpportunityRepository.findByName(name)
                .map(opportunityMapperEntity::toDomain);
    }

    @Override
    public Optional<OpportunityDTO> findDTOById(Integer id) {
        return jpaOpportunityRepository.findDTOById(id);
    }

}
