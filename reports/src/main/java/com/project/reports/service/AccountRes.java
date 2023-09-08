package com.project.reports.service;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Account response.
 */
@Getter
@Setter
public class AccountRes {
  private String accountId;
  private String accountNumber;
  private String accountType;
  private String clientId;
  private BigDecimal balance;
}
