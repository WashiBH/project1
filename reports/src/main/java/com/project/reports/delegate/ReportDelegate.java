package com.project.reports.delegate;

import com.project.reports.api.ReportsApiDelegate;
import com.project.reports.model.ReportProductSummaryRes;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Implements ReportsApiDelegate.
 */
public class ReportDelegate implements ReportsApiDelegate {

  @Override
  public ResponseEntity<List<ReportProductSummaryRes>> getClientProductsSummary(
      String clientId,
      Integer year,
      Integer month
  ) {
    return ReportsApiDelegate.super.getClientProductsSummary(clientId, year, month);
  }
}
