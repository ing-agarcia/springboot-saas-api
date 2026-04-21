package com.eduardo.springbootsaasapi.modules.product.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.product.application.dto.ProductDTO;
import com.eduardo.springbootsaasapi.modules.product.application.mappers.ProductMapper;
import com.eduardo.springbootsaasapi.modules.product.domain.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDTO> getProducts(@NonNull Pageable pageable) {
        return productRepository.findProducts(pageable).map(ProductMapper::toDto);
    }

}
