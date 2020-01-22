package org.iata.service;

import org.iata.api.model.CompanyInformation;
import org.iata.api.model.Subscription;

public interface CompanyIdentifierService {
  CompanyInformation getCompanyIdentifier();
  Subscription getSubscriptionInformation(String topic);
}
