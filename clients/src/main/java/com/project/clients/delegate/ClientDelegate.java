package com.project.clients.delegate;

import com.project.clients.api.ClientsApiDelegate;
import com.project.clients.model.ClientReq;
import com.project.clients.model.ClientRes;
import com.project.clients.model.ClientTypeRes;
import com.project.clients.service.ClientService;
import com.project.clients.service.ClientTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Class implements ClientsApiDelegate interface.
 */
@Service
public class ClientDelegate implements ClientsApiDelegate {
  private final ClientService clientService;
  private final ClientTypeService clientTypeService;

  @Autowired
  public ClientDelegate(ClientService clientService, ClientTypeService clientTypeService) {
    this.clientService = clientService;
    this.clientTypeService = clientTypeService;
  }

  @Override
  public ResponseEntity<ClientRes> getClientById(String id) {
    return ResponseEntity.ok(clientService.findClientById(id));
  }

  @Override
  public ResponseEntity<ClientTypeRes> getClientType(String id) {
    return ResponseEntity.ok(clientTypeService.getClientType(id));
  }

  @Override
  public ResponseEntity<List<ClientRes>> getClients() {
    return ResponseEntity.ok(clientService.findAll());
  }

  @Override
  public ResponseEntity<ClientRes> saveClient(ClientReq clientReq) {
    return ResponseEntity.ok(clientService.save(clientReq));
  }

  @Override
  public ResponseEntity<ClientRes> updateClient(String id, ClientReq clientReq) {
    return ResponseEntity.ok(clientService.update(id, clientReq));
  }

}
