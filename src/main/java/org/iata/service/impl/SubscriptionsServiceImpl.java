package org.iata.service.impl;

import org.iata.api.model.Subscription;
import org.iata.model.enums.EventType;
import org.iata.model.enums.LogisticsObjectType;
import org.iata.repository.SubscriptionsRepository;
import org.iata.service.NotificationService;
import org.iata.service.SubscriptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

  private final SubscriptionsRepository subscriptionsRepository;
  private final Environment env;

  private final NotificationService notificationService;

  private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionsServiceImpl.class);

  @Inject
  public SubscriptionsServiceImpl(SubscriptionsRepository subscriptionsRepository, Environment env, NotificationService notificationService) {
    this.subscriptionsRepository = subscriptionsRepository;
    this.env = env;
    this.notificationService = notificationService;
  }

  @Override
  public void addSubscription(Subscription subscription) {
    subscriptionsRepository.save(subscription);
  }

  @Override
  public Subscription getSubscription(String companyUrl, String companyId, LogisticsObjectType logisticsObjectType) {
    Subscription subscription = new Subscription();
    subscription.setId(companyUrl + "/subscription");
    subscription.setCallbackUrl(companyUrl + "/callback?topic=" + logisticsObjectType);
    subscription.setSendLogisticsObjectBody(Boolean.valueOf(env.getProperty("subscription.sendLogisticsObjectBody")));
    subscription.setSubscribeToStatusUpdates(Boolean.valueOf(env.getProperty("subscription.subscribeToStatusUpdates")));
    subscription.setSecret(env.getProperty("subscription.secret"));
    subscription.setCacheFor(Objects.requireNonNull(env.getProperty("subscription.cacheFor")));
    subscription.setContentTypes(new HashSet<>(Collections.singleton(Objects.requireNonNull(env.getProperty("subscription.contentType")))));
    subscription.setTopic(logisticsObjectType.getLogisticsObjectTypeIRI());
    subscription.setMyCompanyIdentifier(companyUrl);
    return subscription;
  }

  @Override
  public List<Subscription> getSubscribers(String companyId) {
    return subscriptionsRepository.findByMyCompanyIdentifier(companyId);
  }

  /**
   * Search for subscribers and send notifiations to subscribers
   * @param eventType
   * @param logisticsObjectType
   * @param logisticsObjectId
   */
  @Override
  public void notifySubscribers(EventType eventType, String logisticsObjectType, String logisticsObjectId) {
    List<Subscription> subscriptions = subscriptionsRepository.findByTopic(logisticsObjectType);
    LOGGER.info("Found {} subscribers to notify", subscriptions.size());
    if (subscriptions != null && !subscriptions.isEmpty()) {
        subscriptions.parallelStream().forEach(subscription -> {
          notificationService.sendNotification(subscription.getCallbackUrl(), eventType, logisticsObjectType, logisticsObjectId);
        });
    }

  }

}
