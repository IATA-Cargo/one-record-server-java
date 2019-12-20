package org.iata.service.impl;

import org.iata.model.CompanyInformation;
import org.iata.model.Subscription;
import org.iata.service.CompanyIdentifierService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
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
    companyInformation.setCompanyId(env.getProperty("companyInformation.companyId"));
    companyInformation.setServerEndpoint(env.getProperty("companyInformation.serverEndpoint"));
    companyInformation.setSupportedContentTypes(Arrays.asList((Objects.requireNonNull(env.getProperty("companyInformation.supportedContentTypes")).split(","))));
    companyInformation.setSupportedLogisticsObjects(Arrays.asList((Objects.requireNonNull(env.getProperty("companyInformation.supportedLogisticsObjects")).split(","))));
    companyInformation.setType(CompanyInformation.class.toString()); // TODO change it to Vocabulary - CompanyInformation when new ontology ready
    return companyInformation;
  }

  @Override
  public Subscription getSubscriptionInformation(String topic) {
    Subscription subscription = new Subscription();
    subscription.setCallbackUrl(env.getProperty("subscription.callbackUrl"));
    subscription.setSendLogisticsObjectBody(Boolean.valueOf(env.getProperty("subscription.sendLogisticsObjectBody")));
    subscription.setSubscribeToStatusUpdates(Boolean.valueOf(env.getProperty("subscription.subscribeToStatusUpdates")));
    subscription.setSecret(env.getProperty("subscription.secret"));
    subscription.setCacheFor(Long.valueOf(Objects.requireNonNull(env.getProperty("subscription.cacheFor"))));
    return subscription;
  }

}
