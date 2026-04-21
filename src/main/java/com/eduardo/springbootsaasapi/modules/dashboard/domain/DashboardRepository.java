package com.eduardo.springbootsaasapi.modules.dashboard.domain;

import java.util.List;

import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardKpis;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardStage;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardTrend;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardMonthly;

public interface DashboardRepository {

    DashboardKpis getKpis(Integer userId);

    List<DashboardStage> getByStage(Integer userId);

    List<DashboardTrend> getTrend(Integer userId);

    List<DashboardMonthly> getMonthlyTotals(Integer userId);
}
