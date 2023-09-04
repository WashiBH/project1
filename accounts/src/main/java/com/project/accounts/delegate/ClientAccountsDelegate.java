package com.project.accounts.delegate;

import com.project.accounts.api.ClientsApiDelegate;
import com.project.accounts.model.AccountClientRes;
import com.project.accounts.service.ClientAccountsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Class implements ClientsApiDelegate interface generate by OpenAPI generator.
 */
public class ClientAccountsDelegate implements ClientsApiDelegate {
  private final ClientAccountsService clientAccountsService;

  @Autowired
  public ClientAccountsDelegate(ClientAccountsService clientAccountsService) {
    this.clientAccountsService = clientAccountsService;
  }

  @Override
  public ResponseEntity<List<AccountClientRes>> getAccountsByClient(String clientId) {
    return ResponseEntity.ok(clientAccountsService.getAccountsByClient(clientId));
  }
}
