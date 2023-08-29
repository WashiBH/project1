package com.project.clients.service;

import com.project.clients.entity.Client;
import com.project.clients.mapper.ClientDtoToEntity;
import com.project.clients.mapper.ClientEntityToDto;
import com.project.clients.model.ClientDTO;
import com.project.clients.repository.ClientRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientDtoToEntity mapperDtoToEntity;

    @Autowired
    private ClientEntityToDto mapperEntityToDto;

    public Observable<List<ClientDTO>> findAll(){
        return Observable.just(clientRepository.findAll()
                .stream()
                .map(x -> mapperEntityToDto.map(x))
                .collect(Collectors.toList())
        );
    }

    public Maybe<ClientDTO> findClientById(String id){
        Client client = clientRepository.findById(id).orElse(null);
        return Maybe.just(mapperEntityToDto.map(client));
    }

    public Maybe<ClientDTO> save(ClientDTO clientDto){
        Client client = mapperDtoToEntity.map(clientDto);
        client = clientRepository.save(client);
        return Maybe.just(mapperEntityToDto.map(client));
    }

    public Maybe<ClientDTO> update(String id, ClientDTO clientDto){

        if(clientRepository.existsById(id)){
            Client client = mapperDtoToEntity.map(clientDto);
            client = clientRepository.save(client);
        }

        return Maybe.just(clientDto);
    }
}
