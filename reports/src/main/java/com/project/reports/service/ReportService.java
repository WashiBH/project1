package com.project.reports.service;

import com.project.reports.mapper.AccountResToProduct;
import com.project.reports.mapper.CreditResToProduct;
import com.project.reports.model.ReportProductSummaryRes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Report service.
 */
@Service
public class ReportService {
  private final FeignClientAccountsService clientAccountsService;
  private final FeignClientCreditsService clientCreditsService;
  private final FeignClientTransactionsService clientTransactionsService;

  /**
   * Constructor.
   *
   * @param clientAccountsService clientAccountsService.
   * @param clientCreditsService clientCreditsService.
   * @param clientTransactionsService clientTransactionsService.
   */
  @Autowired
  public ReportService(
      FeignClientAccountsService clientAccountsService,
      FeignClientCreditsService clientCreditsService,
      FeignClientTransactionsService clientTransactionsService
  ) {
    this.clientAccountsService = clientAccountsService;
    this.clientCreditsService = clientCreditsService;
    this.clientTransactionsService = clientTransactionsService;
  }

  /**
   * Get report of clients products summary.
   *
   * @param clientId Client id.
   * @param year year.
   * @param month month.
   * @return List of clients products summary reports.
   */
  public List<ReportProductSummaryRes> getClientProductsSummary(
      String clientId,
      Integer year,
      Integer month
  ) {
    List<Product> products = getClientProducts(clientId);
    List<TransactionRes> transactions = clientTransactionsService
                                        .getClientTransactions(clientId, year, month);

    return products.stream()
      .flatMap(product -> {
        List<TransactionRes> transactionsParams = transactions.stream()
            .filter(transaction -> transaction.getType().equals(product.getProductName()))
            .collect(Collectors.toList());
        return findSummarySavingAccountTransactions(product, transactionsParams).stream();
      })
      .collect(Collectors.toList());
  }

  private List<Product> getClientProducts(String clientId) {
    return Stream.concat(
        clientAccountsService.getClientAccounts(clientId).stream().map(AccountResToProduct::map),
        clientCreditsService.getClientCredits(clientId).stream().map(CreditResToProduct::map))
      .collect(Collectors.toList());
  }

  private List<ReportProductSummaryRes> findSummarySavingAccountTransactions(
      Product product,
      List<TransactionRes> transactions
  ) {
    return transactions.stream()
      .collect(Collectors.groupingBy(TransactionRes::getCreateAt,
        Collectors.summingDouble(transaction -> transaction.getAmount().doubleValue())))
      .entrySet()
      .stream()
      .map(entry -> {
        ReportProductSummaryRes reportProductSummaryRes = new ReportProductSummaryRes();
        reportProductSummaryRes.setProduct(product.getProductName());
        reportProductSummaryRes.setBalance(BigDecimal.valueOf(entry.getValue()));
        reportProductSummaryRes.setDate(entry.getKey());
        return reportProductSummaryRes;
      })
      .collect(Collectors.toList());
  }
}
