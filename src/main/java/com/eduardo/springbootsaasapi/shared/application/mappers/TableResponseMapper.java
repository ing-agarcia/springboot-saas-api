package com.eduardo.springbootsaasapi.shared.application.mappers;

import org.springframework.data.domain.Page;

import com.eduardo.springbootsaasapi.shared.application.dto.PaginatedResultDTO;

public class TableResponseMapper {

    public static <T> PaginatedResultDTO<T> buildResponse(Page<T> pageData) {
        PaginatedResultDTO<T> dto = new PaginatedResultDTO<>();
        dto.setData(pageData.getContent());
        dto.setTotal(pageData.getTotalElements());
        dto.setPage(pageData.getNumber());
        dto.setPageSize(pageData.getSize());
        return dto;
    }
}
