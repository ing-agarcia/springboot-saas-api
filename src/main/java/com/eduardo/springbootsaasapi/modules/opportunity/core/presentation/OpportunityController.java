package com.eduardo.springbootsaasapi.modules.opportunity.core.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.CreateOpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.service.OpportunityService;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    public ResponseEntity<OpportunityDTO> newOpportunity(CreateOpportunityDTO createOpportunityDTO) {
        OpportunityDTO opportunity = opportunityService.newOpportunity(createOpportunityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(opportunity);
    }

}
