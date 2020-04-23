package org.iata.service.impl;

import org.iata.api.model.Subscription;
import org.iata.repository.SubscriptionsRepository;
import org.iata.service.SubscriptionsService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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

//  @Override
//  public CompanyInformation getCompanyIdentifier() {
//    CompanyInformation companyInformation = new CompanyInformation();
//    companyInformation.setId(env.getProperty("companyInformation.companyId"));
//    companyInformation.setServerEndpoint(env.getProperty("companyInformation.serverEndpoint"));
//    companyInformation.setSupportedContentTypes(Set.of(Objects.requireNonNull(env.getProperty("companyInformation.supportedContentTypes")).split(",")));
//    companyInformation.setSupportedLogisticsObjects(Set.of((Objects.requireNonNull(env.getProperty("companyInformation.supportedLogisticsObjects")).split(","))));
//    return companyInformation;
//  }

  @Override
  public void addSubscription(Subscription subscription) {
    // TODO companyId?
    subscriptionsRepository.save(subscription);
  }

  @Override
  public Subscription getSubscription(String companyId, String topic) {
    // TODO Get it from the database
    Subscription subscription = new Subscription();
    subscription.setCallbackUrl(env.getProperty("subscription.callbackUrl"));
    subscription.setSendLogisticsObjectBody(Boolean.valueOf(env.getProperty("subscription.sendLogisticsObjectBody")));
    subscription.setSubscribeToStatusUpdates(Boolean.valueOf(env.getProperty("subscription.subscribeToStatusUpdates")));
    subscription.setSecret(env.getProperty("subscription.secret"));
    subscription.setCacheFor(Objects.requireNonNull(env.getProperty("subscription.cacheFor")));
    return subscription;
  }

}
