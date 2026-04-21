package com.eduardo.springbootsaasapi.modules.opportunity.summary.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OpportunitySummaryDTO {
        Integer getId();

        String getName();

        String getStage();

        Integer getProbability();

        BigDecimal getAmount();

        LocalDateTime getCreatedAt();

        String getSalesName();

        String getManagerName();

        String getDirectorName();

        String getVpName();

        String getRootName();
}