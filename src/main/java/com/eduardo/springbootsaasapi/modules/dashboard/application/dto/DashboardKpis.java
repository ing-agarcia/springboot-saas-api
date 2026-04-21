package com.eduardo.springbootsaasapi.modules.dashboard.application.dto;

public record DashboardKpis(
                Long totalOpps,
                Double totalValue,
                Double weightedValue,
                Double winRate) {
}