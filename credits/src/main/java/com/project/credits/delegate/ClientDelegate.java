package com.project.credits.delegate;

import com.project.credits.api.ClientsApiDelegate;
import com.project.credits.model.CreditRes;
import com.project.credits.model.ExistClientCreditCardRes;
import com.project.credits.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Implements class for ClientsApiDelegate interface.
 */
public class ClientDelegate implements ClientsApiDelegate {

  private final ClientService clientService;

  @Autowired
  public ClientDelegate(ClientService clientService) {
    this.clientService = clientService;
  }

  @Override
  public ResponseEntity<List<CreditRes>> getClientCredits(String clientId) {
    return ResponseEntity.ok(clientService.getClientCredits(clientId));
  }

  @Override
  public ResponseEntity<ExistClientCreditCardRes> getIfClientHasCreditCard(String clientId) {
    return ResponseEntity.ok(clientService.getIfClientHasCreditCard(clientId));
  }
}
