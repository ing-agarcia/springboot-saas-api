package com.eduardo.springbootsaasapi.modules.opportunity.summary.infrastructure.persistence.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Immutable
@Table(name = "opportunity_summary")
@Data()
@NoArgsConstructor
@AllArgsConstructor

public class OpportunitySummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "owner_id")
    private Integer ownerId;

    private String stage;
    private Integer probability;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
