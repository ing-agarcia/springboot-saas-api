package com.eduardo.springbootsaasapi.modules.ml.forecast.infrastructure.client;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.eduardo.springbootsaasapi.modules.ml.forecast.application.dto.ForecastRequestDTO;
import com.eduardo.springbootsaasapi.modules.ml.forecast.application.dto.ForecastResponseDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ForecastClient {

    private final WebClient webClient;

    public ForecastResponseDTO getForecast(List<Double> values) {
        ForecastRequestDTO request = new ForecastRequestDTO(values);

        return webClient.post()
                .uri("http://127.0.0.1:8000/forecast")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ForecastResponseDTO.class)
                .block();
    }

}
