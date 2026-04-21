package com.eduardo.springbootsaasapi.modules.ml.forecast.application.dto;

public record ForecastChartDTO(
                String nameMonth,
                Double currentYear,
                Double forecast) {
}
