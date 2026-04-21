package com.eduardo.springbootsaasapi.modules.ml.forecast.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.ml.forecast.application.dto.ForecastChartDTO;
import com.eduardo.springbootsaasapi.modules.ml.forecast.application.services.ForecastService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/forecast/trend")
@AllArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping
    public ResponseEntity<List<ForecastChartDTO>> forecast(
            @AuthenticationPrincipal(expression = "id") Integer userId) {
        return ResponseEntity.ok(
                forecastService.getTrendForecast(userId));
    }

}
