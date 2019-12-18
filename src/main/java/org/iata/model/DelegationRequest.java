package org.iata.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.Types;
import org.iata.model.enums.DelegationActionEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

public class DelegationRequest implements Serializable {

  @Id(generated = true)
  protected String id;
  @Types
  protected String type;

  protected String targetLogisticsObject;
  protected String targetCompany;
  protected DelegationActionEnum action;

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

  public String getTargetLogisticsObject() {
    return targetLogisticsObject;
  }

  public void setTargetLogisticsObject(String targetLogisticsObject) {
    this.targetLogisticsObject = targetLogisticsObject;
  }

  public String getTargetCompany() {
    return targetCompany;
  }

  public void setTargetCompany(String targetCompany) {
    this.targetCompany = targetCompany;
  }

  public DelegationActionEnum getAction() {
    return action;
  }

  public void setAction(DelegationActionEnum action) {
    this.action = action;
  }

}
