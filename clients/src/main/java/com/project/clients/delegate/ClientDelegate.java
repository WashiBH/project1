package com.project.clients.delegate;

import com.project.clients.api.ClientsApiDelegate;
import com.project.clients.model.ClientDTO;
import com.project.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientDelegate implements ClientsApiDelegate {

    @Autowired
    private ClientService clientService;

    @Override
    public ResponseEntity<ClientDTO> getClientById(String id) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @Override
    public ResponseEntity<ClientDTO> saveClient(ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.save(clientDTO));
    }
}
