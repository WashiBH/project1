package com.project.clients.mapper;

import com.project.clients.entity.Client;
import com.project.clients.model.ClientReq;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class ClientReqToEntity {

    public static Client map(ClientReq clientReq, String id) {
        BiFunction<ClientReq, String, Client> map = (t, u) -> {
            return new Client(
                    u,
                    t.getClientType().getValue(),
                    t.getName(),
                    t.getFatherLastName(),
                    t.getMotherLastName(),
                    t.getBusinessName(),
                    t.getDocumentType(),
                    t.getDocumentNumber(),
                    t.getBirthdate(),
                    t.getAddress(),
                    t.getPhoneNumber(),
                    t.getEmail()
            );
        };
        return map.apply(clientReq, id);
    }

}
