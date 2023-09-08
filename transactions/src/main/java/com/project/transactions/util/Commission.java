package com.project.transactions.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Util for get commission value.
 */
@Getter
@Component
public class Commission {
  private double value;

  public void setValue(@Value("${account.commission}") double value) {
    this.value = value;
  }

}
