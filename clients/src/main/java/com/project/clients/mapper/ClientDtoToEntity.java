package com.project.clients.mapper;

import com.project.clients.entity.Client;
import com.project.clients.model.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoToEntity implements Mapper<ClientDTO, Client>{
    @Override
    public Client map(ClientDTO in) {
        return new Client(
                in.getClientId(),
                in.getClientType(),
                in.getName(),
                in.getFatherLastName(),
                in.getMotherLastName(),
                in.getBusinessName(),
                in.getDocumentType(),
                in.getDocumentNumber(),
                in.getBirthdate(),
                in.getAddress(),
                in.getPhoneNumber(),
                in.getEmail()
        );
    }

}
