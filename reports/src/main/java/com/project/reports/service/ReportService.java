package com.project.reports.service;

import com.project.reports.model.ReportProductSummaryRes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
  private FeignClientAccountsService clientAccountsService;
  private FeignClientCreditsService clientCreditsService;

  @Autowired
  public ReportService (
    FeignClientAccountsService clientAccountsService,
    FeignClientCreditsService clientCreditsService
  ) {
    this.clientAccountsService = clientAccountsService;
    this.clientCreditsService = clientCreditsService;
  }

  public List<ReportProductSummaryRes> getClientProductsSummary(
    String clientId,
    Integer year,
    Integer month) {
    List<AccountRes> accounts = clientAccountsService.getClientAccounts(clientId);
    List<CreditRes> credits = clientCreditsService.getClientCredits(clientId);

    return null;
  }
}
