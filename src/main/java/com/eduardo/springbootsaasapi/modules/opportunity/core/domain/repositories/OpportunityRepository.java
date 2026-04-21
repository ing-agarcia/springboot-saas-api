package com.eduardo.springbootsaasapi.modules.opportunity.core.domain.repositories;

import java.util.Optional;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities.Opportunity;

public interface OpportunityRepository {
    Opportunity save(Opportunity opportunity);

    Optional<Opportunity> findByName(String name);

    Optional<OpportunityDTO> findDTOById(Integer id);

}
