package org.iata.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.Types;
import org.iata.model.enums.NotificationEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "lofromsubscriptions")
public class Notification implements Serializable {

  @Id(generated = true)
  protected String id;
  @Types
  protected String type;

  private NotificationEnum eventType;
  private String topic;
  private String logisticsObjectId;

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

  public NotificationEnum getEventType() {
    return eventType;
  }

  public void setEventType(NotificationEnum eventType) {
    this.eventType = eventType;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getLogisticsObjectId() {
    return logisticsObjectId;
  }

  public void setLogisticsObjectId(String logisticsObjectId) {
    this.logisticsObjectId = logisticsObjectId;
  }

}
