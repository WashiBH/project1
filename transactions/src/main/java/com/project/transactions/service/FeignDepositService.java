package com.project.transactions.service;

import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign account deposit service.
 */
@FeignClient(name = "account-service", url = "http://localhost:8582")
public interface FeignDepositService {
  @PatchMapping("/deposits/{accountId}")
  public DepositRes getDepositResponse(@PathVariable("accountId") String accountId,
                                       BigDecimal amount);
}
