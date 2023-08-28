package com.proyecto.banco.service;

import com.proyecto.banco.entity.Client;
import com.proyecto.banco.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public void save(Client client){
        clientRepository.save(client);
    }
}
