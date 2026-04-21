package com.eduardo.springbootsaasapi.modules.opportunity.summary.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.opportunity.summary.application.dto.OpportunitySummaryDTO;
import com.eduardo.springbootsaasapi.modules.opportunity.summary.application.service.OpportunitySummaryService;
import com.eduardo.springbootsaasapi.shared.application.dto.PaginatedResultDTO;
import com.eduardo.springbootsaasapi.shared.application.mappers.TableResponseMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/opportunities/summary")
public class OpportunitySummaryController {

    private final OpportunitySummaryService opportunitySummaryService;

    public OpportunitySummaryController(OpportunitySummaryService opportunitySummaryService) {
        this.opportunitySummaryService = opportunitySummaryService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResultDTO<OpportunitySummaryDTO>> getGroups(
            @AuthenticationPrincipal(expression = "id") Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<OpportunitySummaryDTO> opportunities = opportunitySummaryService.findOpportunitiesByUserHierarchy(
                userId,
                pageable);
        return ResponseEntity.ok(TableResponseMapper.buildResponse(opportunities));
    }

}
