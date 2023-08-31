package com.project.clients.service;

import com.project.clients.entity.Client;
import com.project.clients.mapper.ClientEntityToRes;
import com.project.clients.mapper.ClientReqToEntity;
import com.project.clients.model.ClientReq;
import com.project.clients.model.ClientRes;
import com.project.clients.repository.ClientRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService( ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Observable<List<ClientRes>> findAll(){
        return Observable.just(clientRepository.findAll()
                .stream()
                .map(ClientEntityToRes::map)
                .collect(Collectors.toList())
        );
    }

    public Maybe<ClientRes> findClientById(String id) {
        return Maybe.fromCallable(() -> clientRepository.findById(id).orElse(null))
                .map(ClientEntityToRes::map);
    }

    public Maybe<ClientRes> save(ClientReq clientReq) {
        return Maybe.fromCallable(() -> {
            Client client = ClientReqToEntity.map(clientReq,null);
            client = clientRepository.save(client);
            return ClientEntityToRes.map(client);
        });
    }

    public Maybe<ClientRes> update(String id, ClientReq clientReq) {
        return Maybe.defer(() -> {
            if (clientRepository.existsById(id)) {
                Client client = ClientReqToEntity.map(clientReq,id);
                client = clientRepository.save(client);
                return Maybe.just(ClientEntityToRes.map(client));
            } else {
                return Maybe.empty();
            }
        });
    }

}
