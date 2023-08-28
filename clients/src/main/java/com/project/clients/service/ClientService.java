package com.project.clients.service;

import com.project.clients.entity.Client;
import com.project.clients.exception.ClientNotFoundException;
import com.project.clients.model.ClientDTO;
import com.project.clients.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public ClientDTO findClientById(String id){
        Client client = clientRepository.findById(id).orElse(null);
        ClientDTO clientDto = new ClientDTO();
        clientDto.setClientId(client.getId());
        clientDto.setClientType(client.getClientType());
        clientDto.setName(client.getName());
        clientDto.setFatherLastName(client.getFatherLastName());
        clientDto.setMotherLastName(client.getMotherLastName());
        clientDto.setBusinessName(client.getBusinessName());
        clientDto.setDocumentType(client.getDocumentType());
        clientDto.setDocumentNumber(client.getDocumentNumber());
        clientDto.setBirthdate(client.getBirthdate());
        clientDto.setAddress(client.getAddress());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setEmail(client.getEmail());

        return Optional.ofNullable(clientDto)
                .orElseThrow(() -> new ClientNotFoundException("Client not found for Id: " + id));
    }

    public ClientDTO save(ClientDTO clientDto){

        Client client = new Client();
        client.setClientType(clientDto.getClientType());
        client.setName(clientDto.getName());
        client.setFatherLastName(clientDto.getFatherLastName());
        client.setMotherLastName(clientDto.getMotherLastName());
        client.setBusinessName(clientDto.getBusinessName());
        client.setDocumentType(clientDto.getDocumentType());
        client.setDocumentNumber(clientDto.getDocumentNumber());
        client.setBirthdate(clientDto.getBirthdate());
        client.setAddress(clientDto.getAddress());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setEmail(clientDto.getEmail());

        client = clientRepository.save(client);

        clientDto.setClientId(client.getId());
        clientDto.setClientType(client.getClientType());
        clientDto.setName(client.getName());
        clientDto.setFatherLastName(client.getFatherLastName());
        clientDto.setMotherLastName(client.getMotherLastName());
        clientDto.setBusinessName(client.getBusinessName());
        clientDto.setDocumentType(client.getDocumentType());
        clientDto.setDocumentNumber(client.getDocumentNumber());
        clientDto.setBirthdate(client.getBirthdate());
        clientDto.setAddress(client.getAddress());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setEmail(client.getEmail());

        return Optional.ofNullable(clientDto)
                .orElseThrow(() -> new ClientNotFoundException("Not save client"));
    }
}
