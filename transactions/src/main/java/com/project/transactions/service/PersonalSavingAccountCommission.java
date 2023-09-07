package com.project.transactions.service;

import com.project.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
@Component
public class PersonalSavingAccountCommission implements AccountCommission{
  @Override
  public BigDecimal getCommission() {
    return null;
  }
}
