package com.eduardo.springbootsaasapi.modules.opportunity.core.domain.entities;

import java.time.LocalDateTime;

import lombok.*;

@Data()
@NoArgsConstructor
@AllArgsConstructor

public class Opportunity {
    private Integer id;
    private Integer ownerId;
    private String name;
    private String stage;
    private Integer probability;
    private LocalDateTime createdAt;
}
