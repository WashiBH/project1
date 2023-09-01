package com.project.accounts.delegate;

import com.project.accounts.api.WithdrawalsApiDelegate;
import com.project.accounts.model.WithdrawalRes;
import com.project.accounts.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithdrawalDelegate implements WithdrawalsApiDelegate {
    private final WithdrawalService withdrawalService;
    @Autowired
    public WithdrawalDelegate(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @Override
    public ResponseEntity<WithdrawalRes> saveWithdrawal(String accountId, BigDecimal amount) {
        return ResponseEntity.ok(withdrawalService.saveWithdrawal(accountId, amount));
    }
}
