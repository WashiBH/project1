package com.project.clients.mapper;

import com.project.clients.entity.Client;
import com.project.clients.model.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientDtoToEntity {

    public Client map(ClientDTO clientDTO) {
        Function<ClientDTO, Client> map = (in) -> {
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
        };
        return map.apply(clientDTO);
    }

}
