package com.project.transactions.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Util for get transaction limit value.
 */
@Getter
@Component
public class TransactionLimit {
  private long value;

  public void setValue(@Value("${account.limit}") long value) {
    this.value = value;
  }

}
