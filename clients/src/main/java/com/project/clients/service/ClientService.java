package com.project.clients.service;

import com.project.clients.entity.Client;
import com.project.clients.mapper.ClientEntityToRes;
import com.project.clients.mapper.ClientReqToEntity;
import com.project.clients.model.ClientReq;
import com.project.clients.model.ClientRes;
import com.project.clients.repository.ClientRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Client service.
 */
@Service
public class ClientService {
  private final ClientRepository clientRepository;
  private final FeignExistClientCreditCardService existClientCreditCardService;

  @Autowired
  public ClientService(ClientRepository clientRepository,
                       FeignExistClientCreditCardService existClientCreditCardService) {
    this.clientRepository = clientRepository;
    this.existClientCreditCardService = existClientCreditCardService;
  }

  /**
   * Find all clients.
   *
   * @return List of clients.
   */
  public List<ClientRes> findAll() {
    return clientRepository.findAll()
      .stream()
      .map(ClientEntityToRes::map)
      .collect(Collectors.toList());
  }

  /**
   * Find client by client id.
   *
   * @param id Client id.
   * @return Client response object.
   */
  public ClientRes findClientById(String id) {
    Client client = clientRepository.findById(id).orElse(null);
    return ClientEntityToRes.map(client);
  }

  /**
   * Save client.
   *
   * @param clientReq Client request object.
   * @return Client response object.
   */
  public ClientRes save(ClientReq clientReq) {
    Client client = clientRepository.findByDocumentNumber(clientReq.getDocumentNumber());
    if (existClient(client)) {
      if (clientReq.getClientType().equals(ClientReq.ClientTypeEnum.PERSONA_VIP)
          && isClientWithCreditCard(client.getId())) {
        client.setClientType(ClientReq.ClientTypeEnum.PERSONA_VIP.getValue());
      } else if (clientReq.getClientType().equals(ClientReq.ClientTypeEnum.PYME)
          && isClientWithCreditCard(client.getId())) {
        client.setClientType(ClientReq.ClientTypeEnum.PYME.getValue());
      }
    } else {
      client = ClientReqToEntity.map(clientReq, null);
      if (clientReq.getClientType().equals(ClientReq.ClientTypeEnum.PERSONA_VIP)) {
        client.setClientType(ClientReq.ClientTypeEnum.PERSONA.getValue());
      }
      if (clientReq.getClientType().equals(ClientReq.ClientTypeEnum.PYME)) {
        client.setClientType(ClientReq.ClientTypeEnum.EMPRESA.getValue());
      }
    }
    client = clientRepository.save(client);
    return ClientEntityToRes.map(client);
  }

  /**
   * Update client.
   *
   * @param id        Client id.
   * @param clientReq Client request object.
   * @return Client response object.
   */
  public ClientRes update(String id, ClientReq clientReq) {
    if (clientRepository.existsById(id)) {
      Client client = ClientReqToEntity.map(clientReq, id);
      if (clientReq.getClientType().equals(ClientReq.ClientTypeEnum.PERSONA_VIP)
          && isClientWithCreditCard(client.getId())) {
        client.setClientType(ClientReq.ClientTypeEnum.PERSONA_VIP.getValue());
      } else if (clientReq.getClientType().equals(ClientReq.ClientTypeEnum.PYME)
          && isClientWithCreditCard(client.getId())) {
        client.setClientType(ClientReq.ClientTypeEnum.PYME.getValue());
      }
      client = clientRepository.save(client);
      return ClientEntityToRes.map(client);
    } else {
      return new ClientRes();
    }
  }

  private boolean existClient(Client client) {
    return client != null;
  }

  private boolean isClientWithCreditCard(String clientId) {
    return existClientCreditCardService.getIfClientHasCreditCard(clientId).getValue().equals("SI");
  }

}
