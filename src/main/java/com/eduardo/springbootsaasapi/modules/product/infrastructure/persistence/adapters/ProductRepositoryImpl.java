package com.eduardo.springbootsaasapi.modules.product.infrastructure.persistence.adapters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.product.domain.entities.Product;
import com.eduardo.springbootsaasapi.modules.product.domain.repositories.ProductRepository;
import com.eduardo.springbootsaasapi.modules.product.infrastructure.domain.ProductEntiyMapper;
import com.eduardo.springbootsaasapi.modules.product.infrastructure.persistence.repositories.JpaProductRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Page<Product> findProducts(@NonNull Pageable pageable) {
        return jpaProductRepository.findAll(pageable).map(ProductEntiyMapper::toDomain);
    }

}
