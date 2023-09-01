package com.project.accounts.delegate;

import com.project.accounts.api.AccountsApiDelegate;
import com.project.accounts.api.DepositsApiDelegate;
import com.project.accounts.model.AccountRes;
import com.project.accounts.model.DepositRes;
import com.project.accounts.service.AccountService;
import com.project.accounts.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

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
