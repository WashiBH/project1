package com.project.credits.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Credit entity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credits")
public class Credit {
  @Id
  private String id;
  private String creditType;
  private String clientId;
  private BigDecimal amount;
}
