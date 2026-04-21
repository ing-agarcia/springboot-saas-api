package com.eduardo.springbootsaasapi.modules.product.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.product.application.dto.ProductDTO;
import com.eduardo.springbootsaasapi.modules.product.application.service.ProductService;
import com.eduardo.springbootsaasapi.shared.application.dto.PaginatedResultDTO;
import com.eduardo.springbootsaasapi.shared.application.mappers.TableResponseMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResultDTO<ProductDTO>> getGroups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> products = productService.getProducts(pageable);
        return ResponseEntity.ok(TableResponseMapper.buildResponse(products));
    }

}
