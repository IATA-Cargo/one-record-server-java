package org.iata.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.Types;

import java.io.Serializable;
import java.util.List;

public class Subscription implements Serializable {

  @Id(generated = true)
  protected String id;
  @Types
  protected String type;

  protected String subscribedTo;
  protected String topic;
  protected String callbackUrl;
  protected List<String> contentType;
  protected String secret;
  protected Boolean subscribeToStatusUpdates;
  protected Boolean sendLogisticsObjectBody;
  protected Long cacheFor;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubscribedTo() {
    return subscribedTo;
  }

  public void setSubscribedTo(String subscribedTo) {
    this.subscribedTo = subscribedTo;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public List<String> getContentType() {
    return contentType;
  }

  public void setContentType(List<String> contentType) {
    this.contentType = contentType;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public Boolean getSubscribeToStatusUpdates() {
    return subscribeToStatusUpdates;
  }

  public void setSubscribeToStatusUpdates(Boolean subscribeToStatusUpdates) {
    this.subscribeToStatusUpdates = subscribeToStatusUpdates;
  }

  public Boolean getSendLogisticsObjectBody() {
    return sendLogisticsObjectBody;
  }

  public void setSendLogisticsObjectBody(Boolean sendLogisticsObjectBody) {
    this.sendLogisticsObjectBody = sendLogisticsObjectBody;
  }

  public Long getCacheFor() {
    return cacheFor;
  }

  public void setCacheFor(Long cacheFor) {
    this.cacheFor = cacheFor;
  }
}
