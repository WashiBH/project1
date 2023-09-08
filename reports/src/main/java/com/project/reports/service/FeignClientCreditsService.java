package com.project.reports.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign credit service for get client credits.
 */
@FeignClient(name = "account-service", url = "http://localhost:8583")
public interface FeignClientCreditsService {
  @GetMapping("/clients/{clientId}/credits")
  public List<CreditRes> getClientCredits(@PathVariable("clientId") String clientId);
}
