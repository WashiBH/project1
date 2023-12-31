package com.project.clients.mapper;

import com.project.clients.entity.Client;
import com.project.clients.model.ClientRes;
import java.util.function.Function;
import org.springframework.stereotype.Component;

/**
 * Mapper class.
 */
@Component
public class ClientEntityToRes {

  /**
   * Mapper method: Client class to Client response class.
   *
   * @param client Client object.
   * @return Client response object.
   */
  public static ClientRes map(Client client) {
    Function<Client, ClientRes> map = (in) -> {
      ClientRes clientRes = new ClientRes();
      clientRes.setClientId(in.getId());
      clientRes.setClientType(in.getClientType());
      clientRes.setName(in.getName());
      clientRes.setFatherLastName(in.getFatherLastName());
      clientRes.setMotherLastName(in.getMotherLastName());
      clientRes.setBusinessName(in.getBusinessName());
      clientRes.setDocumentType(in.getDocumentType());
      clientRes.setDocumentNumber(in.getDocumentNumber());
      clientRes.setBirthdate(in.getBirthdate());
      clientRes.setAddress(in.getAddress());
      clientRes.setPhoneNumber(in.getPhoneNumber());
      clientRes.setEmail(in.getEmail());
      return clientRes;
    };
    return map.apply(client);
  }
}
