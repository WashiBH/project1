package com.project.clients.service;

import com.project.clients.entity.Client;
import com.project.clients.mapper.ClientEntityToRes;
import com.project.clients.mapper.ClientReqToEntity;
import com.project.clients.model.ClientReq;
import com.project.clients.model.ClientRes;
import com.project.clients.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService( ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientRes> findAll(){
        return clientRepository.findAll()
                .stream()
                .map(ClientEntityToRes::map)
                .collect(Collectors.toList());
    }

    public ClientRes findClientById(String id) {
        Client client = clientRepository.findById(id).orElse(null);
        return ClientEntityToRes.map(client);
    }

    public ClientRes save(ClientReq clientReq) {
        Client client = ClientReqToEntity.map(clientReq,null);
        client = clientRepository.save(client);
        return ClientEntityToRes.map(client);
    }

    public ClientRes update(String id, ClientReq clientReq) {
        if (clientRepository.existsById(id)) {
            Client client = ClientReqToEntity.map(clientReq,id);
            client = clientRepository.save(client);
            return ClientEntityToRes.map(client);
        } else {
            return new ClientRes();
        }
    }

}
