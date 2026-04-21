package com.eduardo.springbootsaasapi.modules.ml.forecast.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardMonthly;
import com.eduardo.springbootsaasapi.modules.dashboard.domain.DashboardRepository;
import com.eduardo.springbootsaasapi.modules.ml.forecast.application.dto.ForecastChartDTO;
import com.eduardo.springbootsaasapi.modules.ml.forecast.application.dto.ForecastResponseDTO;
import com.eduardo.springbootsaasapi.modules.ml.forecast.infrastructure.client.ForecastClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ForecastService {

        private final DashboardRepository dashboardRepository;
        private final ForecastClient forecastClient;

        public List<ForecastChartDTO> getTrendForecast(Integer userId) {

                List<DashboardMonthly> trend = dashboardRepository.getMonthlyTotals(userId);

                List<Double> values = trend.stream()
                                .map(DashboardMonthly::total)
                                .toList();

                ForecastResponseDTO response = forecastClient.getForecast(values);

                Double prediction = response.prediction();

                if (trend.isEmpty())
                        return List.of();

                // meses
                String[] months = {
                                "", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                };

                // 2. construir base
                List<ForecastChartDTO> base = trend.stream()
                                .map(item -> new ForecastChartDTO(
                                                months[item.month().getMonthValue()],
                                                item.total(),
                                                null))
                                .toList();

                // 3. último elemento
                ForecastChartDTO last = base.get(base.size() - 1);

                List<ForecastChartDTO> result = new ArrayList<>(base.subList(0, base.size() - 1));

                // copiar último valor real al forecast
                result.add(new ForecastChartDTO(
                                last.nameMonth(),
                                last.currentYear(),
                                last.currentYear()));

                // 4. agregar predicción
                if (prediction != null) {
                        result.add(new ForecastChartDTO(
                                        "Next",
                                        null,
                                        prediction));
                }

                return result;
        }

}
