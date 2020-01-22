package org.iata.service.impl;

import org.iata.api.model.CompanyInformation;
import org.iata.api.model.Subscription;
import org.iata.service.CompanyIdentifierService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@Service
public class CompanyIdentifierServiceImpl implements CompanyIdentifierService {

  private final Environment env;

  @Inject
  public CompanyIdentifierServiceImpl(Environment env) {
    this.env = env;
  }

  @Override
  public CompanyInformation getCompanyIdentifier() {
    CompanyInformation companyInformation = new CompanyInformation();
    companyInformation.setId(env.getProperty("companyInformation.companyId"));
    companyInformation.setServerEndpoint(env.getProperty("companyInformation.serverEndpoint"));
    companyInformation.getSupportedContentTypes().addAll(Arrays.asList(Objects.requireNonNull(env.getProperty("companyInformation.supportedContentTypes")).split(",")));
    companyInformation.getSupportedLogisticsObjects().addAll(Arrays.asList((Objects.requireNonNull(env.getProperty("companyInformation.supportedLogisticsObjects")).split(","))));
    companyInformation.getTypes().addAll(Collections.singletonList(CompanyInformation.class.toString()));
    return companyInformation;
  }

  @Override
  public Subscription getSubscriptionInformation(String topic) {
    Subscription subscription = new Subscription();
    subscription.setCallbackUrl(env.getProperty("subscription.callbackUrl"));
    subscription.setSendLogisticsObjectBody(Boolean.valueOf(env.getProperty("subscription.sendLogisticsObjectBody")));
    subscription.setSubscribeToStatusUpdates(Boolean.valueOf(env.getProperty("subscription.subscribeToStatusUpdates")));
    subscription.setSecret(env.getProperty("subscription.secret"));
    subscription.setCacheFor(Objects.requireNonNull(env.getProperty("subscription.cacheFor")));
    return subscription;
  }

}
