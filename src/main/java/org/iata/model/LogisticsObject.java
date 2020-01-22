package org.iata.model;

import org.iata.cargo.model.Thing;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "los")
public class LogisticsObject extends Thing {

  protected String companyId;

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

}
