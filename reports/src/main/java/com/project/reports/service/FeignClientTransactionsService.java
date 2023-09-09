package com.project.reports.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign transaction service for get client transactions.
 */
@FeignClient(name = "transaction-service", url = "http://localhost:8584")
public interface FeignClientTransactionsService {
  @GetMapping("/transactions/{clientId}/{year}/{month}")
  public List<TransactionRes> getClientTransactions(
      @PathVariable("clientId") String clientId,
      @PathVariable("year") Integer year,
      @PathVariable("month") Integer month
  );
}