package com.project.accounts.delegate;

import com.project.accounts.api.ClientsApiDelegate;
import com.project.accounts.model.AccountClientRes;
import com.project.accounts.service.ClientAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
