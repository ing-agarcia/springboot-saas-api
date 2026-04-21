package com.eduardo.springbootsaasapi.modules.dashboard.application.dto;

public record DashboardTrend(
                Integer month,
                Double lastYear,
                Double currentYear) {
}