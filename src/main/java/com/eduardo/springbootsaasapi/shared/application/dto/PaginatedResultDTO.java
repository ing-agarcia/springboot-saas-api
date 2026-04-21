package com.eduardo.springbootsaasapi.shared.application.dto;

import java.util.List;

import lombok.Data;

@Data

public class PaginatedResultDTO<T> {
    private List<T> data;
    private long total;
    private int page;
    private int pageSize;
}
