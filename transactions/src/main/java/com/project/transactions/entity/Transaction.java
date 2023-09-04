package com.project.transactions.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Transaction entity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class Transaction {
  @Id
  private String id;
  private String clientId;
  private String transactionType;
  private BigDecimal amount;
  private String originAccount;
  private String destinyAccount;
}
