package com.eduardo.springbootsaasapi.modules.dashboard.application.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardKpis;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardOverviewDTO;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardStage;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardTrend;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardTrendDTO;
import com.eduardo.springbootsaasapi.modules.dashboard.application.mappers.DashboardMapper;
import com.eduardo.springbootsaasapi.modules.dashboard.domain.DashboardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardOverviewDTO getPipelineOverview(Integer userId) {
        DashboardKpis kpis = dashboardRepository.getKpis(userId);
        List<DashboardStage> byStage = dashboardRepository.getByStage(userId);
        List<DashboardTrend> trendProjection = dashboardRepository.getTrend(userId);
        List<DashboardTrendDTO> trend = DashboardMapper.toTrendDtoList(trendProjection);
        return new DashboardOverviewDTO(kpis, byStage, trend);
    }

}
