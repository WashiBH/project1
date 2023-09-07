package com.project.transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
@Component
public class CommissionCalculator {
  public BigDecimal calculate(AccountCommission accountCommission){
    return accountCommission.getCommission();
  }
}
