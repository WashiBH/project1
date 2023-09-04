package com.project.credits.service;

import com.project.credits.entity.Credit;
import com.project.credits.mapper.CreditEntityToRes;
import com.project.credits.mapper.CreditReqToEntity;
import com.project.credits.model.CreditReq;
import com.project.credits.model.CreditRes;
import com.project.credits.repository.CreditRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Credit service.
 */
@Service
public class CreditService {
  @Autowired
  private CreditRepository creditRepository;

  /**
   * Find all credits.
   *
   * @return List of credits.
   */
  public List<CreditRes> findAll() {
    return creditRepository.findAll()
      .stream()
      .map(CreditEntityToRes::map)
      .collect(Collectors.toList());
  }

  /**
   * Find credit by client id.
   *
   * @param id Client id.
   * @return Credit response object.
   */
  public CreditRes findCreditById(String id) {
    Credit credit = creditRepository.findById(id).orElse(null);
    return CreditEntityToRes.map(credit);
  }

  /**
   * Save credit.
   *
   * @param creditReq Credit request object.
   * @return Credit response object.
   */
  public CreditRes save(CreditReq creditReq) {
    Credit credit = CreditReqToEntity.map(creditReq, null);
    credit = creditRepository.save(credit);
    return CreditEntityToRes.map(credit);
  }

  /**
   * Update credit.
   *
   * @param id Credit id.
   * @param creditReq Credit request object.
   * @return Credit response object.
   */
  public CreditRes update(String id, CreditReq creditReq) {

    if (creditRepository.existsById(id)) {
      Credit credit = CreditReqToEntity.map(creditReq, id);
      credit = creditRepository.save(credit);
      return CreditEntityToRes.map(credit);
    } else {
      return new CreditRes();
    }
  }
}
