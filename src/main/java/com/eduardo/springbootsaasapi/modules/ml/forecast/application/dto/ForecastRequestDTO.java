package com.eduardo.springbootsaasapi.modules.ml.forecast.application.dto;

import java.util.List;

public record ForecastRequestDTO(
                List<Double> values) {

}
