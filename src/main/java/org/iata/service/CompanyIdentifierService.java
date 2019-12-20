package org.iata.service;

import org.iata.model.CompanyInformation;
import org.iata.model.Subscription;

public interface CompanyIdentifierService {
  CompanyInformation getCompanyIdentifier();
  Subscription getSubscriptionInformation(String topic);
}
