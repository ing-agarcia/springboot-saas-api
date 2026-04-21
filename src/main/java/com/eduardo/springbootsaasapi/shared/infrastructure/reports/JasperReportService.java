package com.eduardo.springbootsaasapi.shared.infrastructure.reports;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class JasperReportService {

    private static final String REPORT_PATH = "/reports/templates/";
    private static final String IMAGE_PATH = "/reports/images/";

    public byte[] generateReport(String reportName, List<UserDetailDTO> data) {
        try {

            // 1. Cargar archivo .jasper
            InputStream reportStream = getClass()
                    .getResourceAsStream(REPORT_PATH + reportName + ".jasper");

            if (reportStream == null) {
                throw new RuntimeException("Report not found: " + reportName);
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

            // 2. Datasource dinámico
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

            // 3. Parámetros del reporte
            Map<String, Object> parameters = new HashMap<>();

            // LOGO (opcional)
            InputStream logoStream = getClass().getResourceAsStream(IMAGE_PATH + "coffee.jpg");

            if (logoStream != null) {
                parameters.put("logo", logoStream);
            }

            // 4. Llenar reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource);

            // 5. Exportar a PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Error generating report", e);
        }

    }

}
