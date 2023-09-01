package com.project.accounts.delegate;

import com.project.accounts.api.HoldersApiDelegate;
import com.project.accounts.model.HolderReq;
import com.project.accounts.model.HolderRes;
import com.project.accounts.service.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HolderDelegate implements HoldersApiDelegate {
    private final HolderService holderService;
    @Autowired
    public HolderDelegate(HolderService holderService) {
        this.holderService = holderService;
    }

    @Override
    public ResponseEntity<HolderRes> saveHolder(HolderReq holderReq) {
        return ResponseEntity.ok(holderService.save(holderReq));
    }

    @Override
    public ResponseEntity<HolderRes> updateHolder(String id, HolderReq holderReq) {
        return ResponseEntity.ok(holderService.update(id, holderReq));
    }
}
