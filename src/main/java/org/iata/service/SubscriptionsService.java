package org.iata.service;

import org.iata.api.model.Subscription;
import org.iata.model.enums.EventType;
import org.iata.model.enums.Topic;

import java.util.List;

public interface SubscriptionsService {

  void addSubscription(Subscription subscription);
  Subscription getSubscription(String companyUrl, String companyId, Topic topic);
  List<Subscription> getSubscribers(String companyId);

  void notifySubscribers(EventType objectUpdated, String logisticsObjectType, String logisticsObjectId);
}
