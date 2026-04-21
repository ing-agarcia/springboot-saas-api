package com.eduardo.springbootsaasapi.modules.dashboard.application.dto;

import java.util.List;

public record DashboardOverviewDTO(
                DashboardKpis kpis,
                List<DashboardStage> byStage,
                List<DashboardTrendDTO> trend) {
}
