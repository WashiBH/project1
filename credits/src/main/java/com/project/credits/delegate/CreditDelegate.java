package com.project.credits.delegate;

import com.project.credits.api.CreditsApiDelegate;
import com.project.credits.model.CreditReq;
import com.project.credits.model.CreditRes;
import com.project.credits.service.CreditService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Class implements CreditsApiDelegate interface.
 */
@Service
public class CreditDelegate implements CreditsApiDelegate {
  private final CreditService creditService;

  @Autowired
  public CreditDelegate(CreditService creditService) {
    this.creditService = creditService;
  }

  @Override
  public ResponseEntity<CreditRes> getCreditById(String id) {
    return ResponseEntity.ok(creditService.findCreditById(id));
  }

  @Override
  public ResponseEntity<List<CreditRes>> getCredits() {
    return ResponseEntity.ok(creditService.findAll());
  }

  @Override
  public ResponseEntity<CreditRes> saveCredit(CreditReq creditReq) {
    return ResponseEntity.ok(creditService.save(creditReq));
  }

  @Override
  public ResponseEntity<CreditRes> updateCredit(String id, CreditReq creditReq) {
    return ResponseEntity.ok(creditService.update(id, creditReq));
  }
}
