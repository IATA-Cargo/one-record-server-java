package org.iata.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.Types;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "audittrails")
public class AuditTrail implements Serializable {

  @Id(generated = true)
  protected String id;
  @Types
  protected String type;

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

  public String getLogisticsObjectId() {
    return logisticsObjectId;
  }

  public void setLogisticsObjectId(String logisticsObjectId) {
    this.logisticsObjectId = logisticsObjectId;
  }
}
