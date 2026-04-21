package com.eduardo.springbootsaasapi.modules.dashboard.application.mappers;

import java.util.List;

import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardTrend;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardTrendDTO;

public class DashboardMapper {

    private static final String[] MONTHS = {
            "", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    public static DashboardTrendDTO toTrendDto(DashboardTrend item) {
        return new DashboardTrendDTO(
                item.month(),
                MONTHS[item.month()],
                item.currentYear(),
                item.lastYear());
    }

    public static List<DashboardTrendDTO> toTrendDtoList(List<DashboardTrend> items) {
        return items.stream()
                .map(DashboardMapper::toTrendDto)
                .toList();
    }
}
