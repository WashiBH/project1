package com.project.clients.service;

import com.project.clients.entity.Client;
import com.project.clients.exception.ClientNotFoundException;
import com.project.clients.mapper.ClientDtoToEntity;
import com.project.clients.mapper.ClientEntityToDto;
import com.project.clients.model.ClientDTO;
import com.project.clients.model.ClientUpdateDTO;
import com.project.clients.repository.ClientRepository;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientDtoToEntity mapperToEntity;

    @Autowired
    private ClientEntityToDto mapperToDto;

    public Maybe<ClientDTO> findClientById(String id){
        Client client = clientRepository.findById(id).orElse(null);
        return Maybe.just(mapperToDto.map(client));
    }

    public Maybe<ClientDTO> save(ClientDTO clientDto){
        Client client = mapperToEntity.map(clientDto);
        client = clientRepository.save(client);
        return Maybe.just(mapperToDto.map(client));
    }

    public ClientDTO update(String id, ClientUpdateDTO clientUpdateDto){

        Client client = clientRepository.findById(id).orElse(null);
        //client.setClientType(clientUpdateDto.getClientType());
        client.setName(clientUpdateDto.getName());
        client.setFatherLastName(clientUpdateDto.getFatherLastName());
        client.setMotherLastName(clientUpdateDto.getMotherLastName());
        client.setBusinessName(clientUpdateDto.getBusinessName());
        client.setDocumentType(clientUpdateDto.getDocumentType());
        client.setDocumentNumber(clientUpdateDto.getDocumentNumber());
        client.setBirthdate(clientUpdateDto.getBirthdate());
        client.setAddress(clientUpdateDto.getAddress());
        client.setPhoneNumber(clientUpdateDto.getPhoneNumber());
        client.setEmail(clientUpdateDto.getEmail());

        client = clientRepository.save(client);

        ClientDTO clientDto = new ClientDTO();
        clientDto.setClientId(client.getId());
        //clientDto.setClientType(client.getClientType());
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
                .orElseThrow(() -> new ClientNotFoundException("Not update client"));
    }
}
