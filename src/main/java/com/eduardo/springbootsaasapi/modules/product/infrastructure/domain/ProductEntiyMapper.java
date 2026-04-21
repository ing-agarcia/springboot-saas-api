package com.eduardo.springbootsaasapi.modules.product.infrastructure.domain;

import com.eduardo.springbootsaasapi.modules.product.domain.entities.Product;
import com.eduardo.springbootsaasapi.modules.product.infrastructure.persistence.entities.ProductEntity;

public class ProductEntiyMapper {

    public static Product toDomain(ProductEntity productEntity) {
        if (productEntity == null)
            return null;

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCategory(),
                productEntity.getPrice());
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null)
            return null;

        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice());
    }
}
