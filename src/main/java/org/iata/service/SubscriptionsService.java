package org.iata.service;

import org.iata.api.model.Subscription;

public interface SubscriptionsService {

  void addSubscription(Subscription subscription);
  Subscription getSubscription(String companyId, String topic);

}
