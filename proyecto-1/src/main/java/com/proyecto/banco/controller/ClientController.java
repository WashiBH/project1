package com.proyecto.banco.controller;

import com.proyecto.banco.entity.Client;
import com.proyecto.banco.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public List<Client> findAll(){
        return clientService.findAll();
    }

    @PostMapping("")
    public void save(@RequestBody Client client){
        clientService.save(client);
    }
}
