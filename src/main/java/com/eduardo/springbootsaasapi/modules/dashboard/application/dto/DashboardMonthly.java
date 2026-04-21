package com.eduardo.springbootsaasapi.modules.dashboard.application.dto;

import java.time.LocalDateTime;

public record DashboardMonthly(
                LocalDateTime month,
                Double total) {
}