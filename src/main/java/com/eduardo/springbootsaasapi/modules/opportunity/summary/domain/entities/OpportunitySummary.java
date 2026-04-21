package com.eduardo.springbootsaasapi.modules.opportunity.summary.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Data()
@NoArgsConstructor
@AllArgsConstructor

public class OpportunitySummary {
    private Integer id;
    private String name;
    private Integer ownerId;
    private String stage;
    private Integer probability;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
