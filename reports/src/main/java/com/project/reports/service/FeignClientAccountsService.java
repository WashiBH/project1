package com.project.reports.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign account service for get client accounts.
 */
@FeignClient(name = "account-service", url = "http://localhost:8582")
public interface FeignClientAccountsService {
  @GetMapping("/clients/{clientId}/accounts")
  public List<AccountRes> getClientAccounts(@PathVariable("clientId") String clientId);
}
