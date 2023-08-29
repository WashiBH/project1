package com.project.clients.mapper;

import com.project.clients.entity.Client;
import com.project.clients.model.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientEntityToDto {
    public ClientDTO map(Client client) {
        Function<Client,ClientDTO> map = (in) -> {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setClientId(in.getId());
            clientDTO.setClientType(in.getClientType());
            clientDTO.setName(in.getName());
            clientDTO.setFatherLastName(in.getFatherLastName());
            clientDTO.setMotherLastName(in.getMotherLastName());
            clientDTO.setBusinessName(in.getBusinessName());
            clientDTO.setDocumentType(in.getDocumentType());
            clientDTO.setDocumentNumber(in.getDocumentNumber());
            clientDTO.setBirthdate(in.getBirthdate());
            clientDTO.setAddress(in.getAddress());
            clientDTO.setPhoneNumber(in.getPhoneNumber());
            clientDTO.setEmail(in.getEmail());
            return clientDTO;
        };
        return map.apply(client);
    }
}
