package com.eduardo.springbootsaasapi.modules.product.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import com.eduardo.springbootsaasapi.modules.product.domain.entities.Product;

public interface ProductRepository {
    Page<Product> findProducts(@NonNull Pageable pageable);
}
