package com.project.accounts.delegate;

import com.project.accounts.api.DepositsApiDelegate;
import com.project.accounts.model.DepositRes;
import com.project.accounts.service.DepositService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Class implements DepositsApiDelegate interface generate by OpenAPI generator.
 */
public class DepositDelegate implements DepositsApiDelegate {
  private final DepositService depositService;

  @Autowired
  public DepositDelegate(DepositService depositService) {
    this.depositService = depositService;
  }

  @Override
  public ResponseEntity<DepositRes> saveDeposit(String accountId, BigDecimal amount) {
    return ResponseEntity.ok(depositService.saveDeposit(accountId, amount));
  }

}
