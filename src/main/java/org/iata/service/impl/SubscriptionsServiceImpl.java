package org.iata.service.impl;

import org.iata.api.model.Subscription;
import org.iata.model.enums.TopicEnum;
import org.iata.repository.SubscriptionsRepository;
import org.iata.service.SubscriptionsService;
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

  @Inject
  public SubscriptionsServiceImpl(SubscriptionsRepository subscriptionsRepository, Environment env) {
    this.subscriptionsRepository = subscriptionsRepository;
    this.env = env;
  }

  @Override
  public void addSubscription(Subscription subscription) {
    subscriptionsRepository.save(subscription);
  }

  @Override
  public Subscription getSubscription(String companyUrl, String companyId, TopicEnum topic) {
    Subscription subscription = new Subscription();
    subscription.setId(companyUrl + "/subscription");
    subscription.setCallbackUrl(companyUrl + "/callback?topic=" + topic);
    subscription.setSendLogisticsObjectBody(Boolean.valueOf(env.getProperty("subscription.sendLogisticsObjectBody")));
    subscription.setSubscribeToStatusUpdates(Boolean.valueOf(env.getProperty("subscription.subscribeToStatusUpdates")));
    subscription.setSecret(env.getProperty("subscription.secret"));
    subscription.setCacheFor(Objects.requireNonNull(env.getProperty("subscription.cacheFor")));
    subscription.setContentTypes(new HashSet<>(Collections.singleton(Objects.requireNonNull(env.getProperty("subscription.contentType")))));
    subscription.setTopic(topic.getTopic());
    subscription.setMyCompanyIdentifier(companyUrl);
    return subscription;
  }

  @Override
  public List<Subscription> getSubscribers(String companyId) {
    return subscriptionsRepository.findByMyCompanyIdentifier(companyId);
  }

}
