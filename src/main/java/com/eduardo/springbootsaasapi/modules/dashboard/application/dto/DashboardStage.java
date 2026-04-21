package com.eduardo.springbootsaasapi.modules.dashboard.application.dto;

public record DashboardStage(
                String stage,
                Integer totalOpps,
                Double totalOppsPct,
                Double totalValue,
                Double totalValuePct) {
}
