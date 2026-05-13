package com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.adapters;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.repositories.OpportunityRepository;
import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.entities.OpportunityEntity;
import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.mappers.OpportunityEntityMapper;
import com.eduardo.springbootsaasapi.modules.opportunity.core.infrastructure.persistence.repositories.JpaOpportunityRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OpportunityRepositoryImpl implements OpportunityRepository {

    private final JpaOpportunityRepository jpaOpportunityRepository;
    private final OpportunityEntityMapper opportunityMapperEntity;

    @SuppressWarnings("null")
    @Override
    public Opportunity save(Opportunity opportunity) {
        OpportunityEntity entity = opportunityMapperEntity.toEntity(opportunity);
        System.out.println(entity);
        OpportunityEntity saved = jpaOpportunityRepository.save(entity);
        return opportunityMapperEntity.toDomain(saved);
    }

    @Override
    public Optional<Opportunity> findByName(String name) {
        return jpaOpportunityRepository.findByName(name)
                .map(opportunityMapperEntity::toDomain);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Opportunity> findById(Integer id) {
        return jpaOpportunityRepository.findById(id)
                .map(opportunityMapperEntity::toDomain);
    }

    @Override
    public Page<Opportunity> findAllByUserId(Integer userId, Pageable pageable) {
        return jpaOpportunityRepository.findAllByUserId(userId, pageable)
                .map(opportunityMapperEntity::toDomain);
    }

}
