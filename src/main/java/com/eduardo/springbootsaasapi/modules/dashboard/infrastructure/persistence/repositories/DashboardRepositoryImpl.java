package com.eduardo.springbootsaasapi.modules.dashboard.infrastructure.persistence.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardKpis;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardStage;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardTrend;
import com.eduardo.springbootsaasapi.modules.dashboard.application.dto.DashboardMonthly;
import com.eduardo.springbootsaasapi.modules.dashboard.domain.DashboardRepository;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DashboardRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("null")
    public DashboardKpis getKpis(Integer userId) {
        String sql = """
                SELECT
                    COUNT(*)::INTEGER AS total_opps,
                    COALESCE(SUM(amount)::DOUBLE PRECISION, 0) AS total_value,
                    COALESCE(SUM(amount * probability / 100)::DOUBLE PRECISION, 0) AS weighted_value,
                    COALESCE(COUNT(CASE WHEN stage = 'Won' THEN 1 END) * 1.0 / NULLIF(COUNT(*), 0), 0) AS win_rate
                FROM
                    opportunity_summary os
                    JOIN user_hierarchy uh ON os.owner_id = uh.child_user_id
                WHERE
                    uh.parent_user_id = :userId
                    AND EXTRACT(YEAR FROM os.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                """;

        Map<String, Object> params = Map.of("userId", userId);

        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> new DashboardKpis(
                rs.getLong("total_opps"),
                rs.getDouble("total_value"),
                rs.getDouble("weighted_value"),
                rs.getDouble("win_rate")));
    }

    @SuppressWarnings("null")
    public List<DashboardStage> getByStage(Integer userId) {
        String sql = """
                SELECT
                    stage,
                    count(*)::INTEGER AS totalOpps,
                    -- Porcentaje basado en el número de oportunidades
                    round(count(*) * 100.0 / sum(count(*)) over(), 2)::DOUBLE PRECISION AS totalOppsPct,
                    sum(amount)::DOUBLE PRECISION AS totalValue,
                    -- Porcentaje basado en el valor monetario
                    round(sum(amount) * 100.0 / sum(sum(amount)) over(), 2)::DOUBLE PRECISION AS totalValuePct
                FROM
                    opportunity_summary os
                    JOIN user_hierarchy uh ON os.owner_id = uh.child_user_id
                WHERE
                    uh.parent_user_id = :userId
                    AND EXTRACT(YEAR FROM os.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                GROUP BY
                    stage
                ORDER BY
                    totalValue DESC
                """;

        Map<String, Object> params = Map.of("userId", userId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new DashboardStage(
                rs.getString("stage"),
                rs.getInt("totalOpps"),
                rs.getDouble("totalOppsPct"),
                rs.getDouble("totalValue"),
                rs.getDouble("totalValuePct")));
    }

    @SuppressWarnings("null")
    public List<DashboardTrend> getTrend(Integer userId) {
        String sql = """
                SELECT
                EXTRACT(MONTH FROM os.created_at)::INTEGER AS month,
                COALESCE(
                    SUM(
                        CASE
                            WHEN EXTRACT(YEAR FROM os.created_at) = EXTRACT(YEAR FROM CURRENT_DATE) - 1
                            THEN os.amount
                        END
                    ), 0) AS "lastYear",

                COALESCE(
                    SUM(
                        CASE
                            WHEN EXTRACT(YEAR FROM os.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                            THEN os.amount
                        END
                    ), 0) AS "currentYear"
                FROM
                    opportunity_summary os
                    JOIN user_hierarchy uh ON os.owner_id = uh.child_user_id
                WHERE
                    uh.parent_user_id = :userId AND
                    os.created_at >= DATE_TRUNC('year', CURRENT_DATE) - INTERVAL '1 year'
                GROUP BY
                    EXTRACT(MONTH FROM os.created_at)
                ORDER BY
                    month
                """;

        Map<String, Object> params = Map.of("userId", userId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new DashboardTrend(
                rs.getInt("month"),
                rs.getDouble("lastYear"),
                rs.getDouble("currentYear")));
    }

    @SuppressWarnings("null")
    public List<DashboardMonthly> getMonthlyTotals(Integer userId) {
        String sql = """
                    SELECT
                    DATE_TRUNC('month', created_at) AS month,
                    COALESCE(SUM(amount), 0) AS total
                FROM
                    opportunity_summary os
                    JOIN user_hierarchy uh ON os.owner_id = uh.child_user_id
                WHERE
                    uh.parent_user_id = :userId
                    AND EXTRACT(YEAR FROM os.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                GROUP BY month
                ORDER BY month
                    """;

        Map<String, Object> params = Map.of("userId", userId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new DashboardMonthly(
                rs.getTimestamp("month").toLocalDateTime(),
                rs.getDouble("total")));
    }

}
