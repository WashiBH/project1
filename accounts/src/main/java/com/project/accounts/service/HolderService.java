package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.entity.Holder;
import com.project.accounts.mapper.HolderEntityToRes;
import com.project.accounts.mapper.HolderReqToEntity;
import com.project.accounts.model.HolderReq;
import com.project.accounts.model.HolderRes;
import com.project.accounts.repository.AccountRepository;
import com.project.accounts.repository.HolderRepository;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolderService {
    private final AccountRepository accountRepository;
    private final HolderRepository holderRepository;
    private final FeignClientService clientService;
    @Autowired
    public HolderService(HolderRepository holderRepository, AccountRepository accountRepository, FeignClientService clientService) {
        this.holderRepository = holderRepository;
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    public HolderRes save(HolderReq holderReq) {
        if(isValidToSaveHolder(holderReq.getAccount())){
            Holder holder = getHolderValidatingAuthorizationSignature(holderReq, null);
            return HolderEntityToRes.map(holderRepository.save(holder));
        } else {
            return new HolderRes();
        }
    }

    public HolderRes update(String id, HolderReq holderReq) {
        if(isValidToSaveHolder(holderReq.getAccount())){
            Holder holder = getHolderValidatingAuthorizationSignature(holderReq, id);
            return HolderEntityToRes.map(holderRepository.save(holder));
        } else {
            return new HolderRes();
        }
    }

    private boolean isValidToSaveHolder(String accountId){
        String clientId = getClientId(accountId);
        return clientId != null && isCompanyClientType(clientId);
    }

    private String getClientId(String accountId){
        Account account = accountRepository.findById(accountId).orElse(null);
        return account != null ? account.getClientId() : null;
    }

    private boolean isCompanyClientType(String clientId){
        ClientTypeRes clientType = clientService.getClientType(clientId);
        return clientType.getValue().equals("EMPRESA");
    }

    private Holder getHolderValidatingAuthorizationSignature(HolderReq holderReq, String holderId){
        Holder holder = HolderReqToEntity.map(holderReq, holderId);
        if(holder.getAuthorizedSignatory().equals("SI")){
            if(isNotValidAuthorizationSignature(holderReq.getAccount())){
                holder.setAuthorizedSignatory("NO");
            }
        }
        return holder;
    }

    private boolean isNotValidAuthorizationSignature(String accountId){
        long numberHoldersAuthorizedSignatory = holderRepository.findByAccountId(accountId)
                .stream()
                .filter(h -> h.getAuthorizedSignatory().equals("SI"))
                .count();
        return numberHoldersAuthorizedSignatory >= 4;
    }

}
