package org.iata.service;

import org.iata.api.model.Subscription;
import org.iata.model.enums.TopicEnum;

import java.util.List;

public interface SubscriptionsService {

  void addSubscription(Subscription subscription);
  Subscription getSubscription(String companyUrl, String companyId, TopicEnum topic);
  List<Subscription> getSubscribers(String companyId);

}
