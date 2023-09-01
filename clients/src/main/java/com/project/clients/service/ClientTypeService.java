package com.project.clients.service;

import com.project.clients.entity.Client;
import com.project.clients.exception.ClientNotFoundException;
import com.project.clients.model.ClientTypeRes;
import com.project.clients.repository.ClientRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientTypeService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientTypeService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientTypeRes getClientType(String clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            ClientTypeRes response = new ClientTypeRes();
            response.setValue(client.getClientType());
            return response;
        } else {
            return new ClientTypeRes();
        }
    }
}
