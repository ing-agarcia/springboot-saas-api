package com.eduardo.springbootsaasapi.modules.opportunity.core.presentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityCreateDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.dto.OpportunityDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.core.application.service.OpportunityService;
import com.eduardo.springbootsaasapi.shared.application.dto.PaginatedResultDTO;
import com.eduardo.springbootsaasapi.shared.application.mappers.TableResponseMapper;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @PostMapping
    public ResponseEntity<OpportunityDTO> newOpportunity(@RequestBody OpportunityCreateDTO createOpportunityDTO) {

        OpportunityDTO opportunity = opportunityService.newOpportunity(createOpportunityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(opportunity);
    }

    @GetMapping
    public ResponseEntity<PaginatedResultDTO<OpportunityDTO>> getOpportunities(
            @AuthenticationPrincipal(expression = "id") Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<OpportunityDTO> opportunities = opportunityService.getOpportunitiesByUserId(userId, pageable);
        return ResponseEntity.ok(TableResponseMapper.buildResponse(opportunities));
    }

}
