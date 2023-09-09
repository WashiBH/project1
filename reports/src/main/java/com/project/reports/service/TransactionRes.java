package com.project.reports.service;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * Transaction response.
 */
@Getter
@Setter
public class TransactionRes {
  private String transactionId;
  private String client;
  private String type;
  private BigDecimal amount;
  private String origin;
  private String destiny;
  private String createAt;
}
