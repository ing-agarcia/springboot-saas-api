package com.eduardo.springbootsaasapi.modules.opportunity.core.domain.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;

public interface OpportunityRepository {
    Opportunity save(Opportunity opportunity);

    Optional<Opportunity> findByName(String name);

    Optional<Opportunity> findById(Integer id);

    Page<Opportunity> findAllByUserId(Integer userId, Pageable pageable);

}
