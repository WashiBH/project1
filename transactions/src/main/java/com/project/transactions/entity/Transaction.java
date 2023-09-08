package com.project.transactions.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
  private String originAccount;
  private String destinyAccount;
  private BigDecimal amount;
  private BigDecimal commission;
  private LocalDateTime createdAt;

  /**
   * Specific constructor.
   *
   * @param id              accountId
   * @param clientId        clientId
   * @param transactionType transactionType
   * @param originAccount   originAccount
   * @param destinyAccount  destinyAccount
   * @param amount          amount
   */
  public Transaction(
      String id,
      String clientId,
      String transactionType,
      String originAccount,
      String destinyAccount,
      BigDecimal amount
  ) {
    this.id = id;
    this.clientId = clientId;
    this.transactionType = transactionType;
    this.originAccount = originAccount;
    this.destinyAccount = destinyAccount;
    this.amount = amount;
  }
}
