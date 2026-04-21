package com.eduardo.springbootsaasapi.modules.product.application.mappers;

import com.eduardo.springbootsaasapi.modules.product.application.dto.ProductDTO;
import com.eduardo.springbootsaasapi.modules.product.domain.entities.Product;

public class ProductMapper {

    public static Product toDomain(ProductDTO productDTO) {
        if (productDTO == null)
            return null;

        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getCategory(),
                productDTO.getPrice());
    }

    public static ProductDTO toDto(Product product) {
        if (product == null)
            return null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice());
    }

}
