package com.eduardo.springbootsaasapi.modules.product.domain.entities;

import java.math.BigDecimal;

import lombok.*;

@Data()
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private Integer id;
    private String name;
    private String category;
    private BigDecimal price;
}
