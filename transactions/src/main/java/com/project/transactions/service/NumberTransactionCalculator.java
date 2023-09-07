package com.project.transactions.service;

import org.springframework.stereotype.Component;
@Component
public class NumberTransactionCalculator {
  public Integer calculate(AccountNumberTransaction accountNumberTransaction){
    return accountNumberTransaction.getNumberTransaction();
  }
}
