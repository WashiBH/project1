package com.project.transactions.service;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
@Component
public class PersonalVipSavingAccountCommission implements AccountCommission{
  @Override
  public BigDecimal getCommission() {
    return null;
  }
}
