package com.eduardo.springbootsaasapi.modules.product.infrastructure.persistence.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data()
@NoArgsConstructor
@AllArgsConstructor

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}
