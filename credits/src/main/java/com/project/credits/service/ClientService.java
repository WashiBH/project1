package com.project.credits.service;

import com.project.credits.mapper.CreditEntityToRes;
import com.project.credits.model.CreditRes;
import com.project.credits.model.ExistClientCreditCardRes;
import com.project.credits.repository.CreditRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Client service that response if a client has or not an credit card.
 */
@Service
public class ClientService {
  private final CreditRepository creditRepository;

  @Autowired
  public ClientService(CreditRepository creditRepository) {
    this.creditRepository = creditRepository;
  }

  /**
   * List of client credits.
   *
   * @param clientId Client id.
   * @return List of client credits.
   */
  public List<CreditRes> getClientCredits(String clientId) {
    return creditRepository.findByClientId(clientId)
      .stream()
      .map(CreditEntityToRes::map)
      .collect(Collectors.toList());
  }

  /**
   * Response method if client has or not an credit card.
   *
   * @param clientId Client id.
   * @return ExistClientCreditCardRes object.
   */
  public ExistClientCreditCardRes getIfClientHasCreditCard(String clientId) {
    ExistClientCreditCardRes existClientCreditCardRes = new ExistClientCreditCardRes();
    existClientCreditCardRes.setValue(getExistClientCreditCard(clientId));
    return existClientCreditCardRes;
  }

  private String getExistClientCreditCard(String clientId) {
    return creditRepository.findByClientIdAndCreditType(clientId).isEmpty() ? "NO" : "SI";
  }
}
