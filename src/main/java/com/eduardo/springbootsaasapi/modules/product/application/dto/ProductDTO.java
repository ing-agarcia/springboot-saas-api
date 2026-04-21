package com.eduardo.springbootsaasapi.modules.product.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private Integer id;
    private String name;
    private String category;
    private BigDecimal price;
}
