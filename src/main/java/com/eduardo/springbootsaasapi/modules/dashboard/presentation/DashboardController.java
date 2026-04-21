package com.eduardo.springbootsaasapi.modules.dashboard.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardOverviewDTO;
import com.eduardo.springbootsaasapi.modules.dashboard.application.service.DashboardService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardOverviewDTO> getPipelineOverview(
            @AuthenticationPrincipal(expression = "id") Integer userId) {
        return ResponseEntity.ok(dashboardService.getPipelineOverview(userId));
    }

}
