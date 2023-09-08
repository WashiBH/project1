package com.project.reports.service;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * Credit response.
 */
@Getter
@Setter
public class CreditRes {
  private String creditId;
  private String type;
  private String client;
  private BigDecimal amount;
}
