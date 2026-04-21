package com.eduardo.springbootsaasapi.modules.product.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.springbootsaasapi.modules.product.infrastructure.persistence.entities.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {

}
