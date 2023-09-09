package com.project.reports.service;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
  private String id;
  private String productType;
  private String productName;
  private BigDecimal amount;
}
