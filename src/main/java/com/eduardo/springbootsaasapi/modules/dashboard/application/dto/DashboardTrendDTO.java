package com.eduardo.springbootsaasapi.modules.dashboard.application.dto;

public record DashboardTrendDTO(
                Integer month,
                String nameMonth,
                Double currentYear,
                Double lastYear) {
}
