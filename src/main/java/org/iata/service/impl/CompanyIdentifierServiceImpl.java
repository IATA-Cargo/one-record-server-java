package org.iata.service.impl;

import org.iata.model.CompanyInformation;
import org.iata.model.Subscription;
import org.iata.service.CompanyIdentifierService;
import org.springframework.stereotype.Service;

@Service
public class CompanyIdentifierServiceImpl implements CompanyIdentifierService {

  @Override
  public CompanyInformation getCompanyIdentifier() {
    return null;
  }

  @Override
  public Subscription getSubscriptionInformation(String topic) {
    return null;
  }

}
