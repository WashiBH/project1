package com.project.transactions.service;

import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign account withdrawal service.
 */
@FeignClient(name = "account-withdrawal-service", url = "http://localhost:8582")
public interface FeignWithdrawalService {
  @PatchMapping("/withdrawals/{accountId}")
  public WithdrawalRes getWithdrawalResponse(@PathVariable("accountId") String accountId,
                                             BigDecimal amount);
}
