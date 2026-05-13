package com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto;

import java.time.LocalDateTime;

public record OpportunityDTO(
        Integer id,
        Integer ownerId,
        String ownerName,
        String name,
        String stage,
        Integer probability,
        LocalDateTime createdAt

) {

}
