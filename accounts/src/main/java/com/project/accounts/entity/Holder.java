package com.project.accounts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Holder entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "holders")
public class Holder {
  @Id
  private String id;
  private String accountId;
  private String authorizedSignatory;
  private String holderName;
}
