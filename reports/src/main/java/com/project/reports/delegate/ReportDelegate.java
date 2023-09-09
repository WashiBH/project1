package com.project.reports.delegate;

import com.project.reports.api.ReportsApiDelegate;
import com.project.reports.model.ReportProductSummaryRes;
import com.project.reports.service.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Implements ReportsApiDelegate.
 */
public class ReportDelegate implements ReportsApiDelegate {

  private final ReportService reportService;

  @Autowired
  public ReportDelegate(ReportService reportService) {
    this.reportService = reportService;
  }

  @Override
  public ResponseEntity<List<ReportProductSummaryRes>> getClientProductsSummary(
      String clientId,
      Integer year,
      Integer month
  ) {
    return ResponseEntity.ok(reportService.getClientProductsSummary(clientId, year, month));
  }
}
