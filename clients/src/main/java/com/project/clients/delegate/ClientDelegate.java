package com.project.clients.delegate;

import com.project.clients.api.ClientsApiDelegate;
import com.project.clients.model.ClientDTO;
import com.project.clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDelegate implements ClientsApiDelegate {
    private final ClientService clientService;

    @Autowired
    public ClientDelegate(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientDTO> getClientById(String id) {
        return ResponseEntity.ok(clientService.findClientById(id).blockingGet());
    }

    @Override
    public ResponseEntity<List<ClientDTO>> getClients() {
        return ResponseEntity.ok(clientService.findAll().blockingSingle());
    }

    @Override
    public ResponseEntity<ClientDTO> saveClient(ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.save(clientDTO).blockingGet());
    }

    @Override
    public ResponseEntity<ClientDTO> updateClient(String id, ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.update(id, clientDTO).blockingGet());
    }

}
