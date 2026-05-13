package com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto;

public record OpportunityCreateDTO(
                Integer id,
                Integer ownerId,
                String name,
                String stage,
                Integer probability) {

}
