package com.project.accounts.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Account entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {
  @Id
  private String id;
  private String accountNumber;
  private String accountType;
  private String clientId;
  private BigDecimal balance;
}
