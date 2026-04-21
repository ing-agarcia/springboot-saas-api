package com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto;

public record CreateOpportunityDTO(
                Integer ownerId,
                String name,
                String stage,
                Integer probability) {

}
