package com.project.clients.service;

import lombok.Data;

/**
 * Checking account response.
 */
@Data
public class CheckingAccountRes {
  private String accountNumber;
  private String type;
  private String client;
  private String balance;
}
