package org.iata.model;

import org.iata.cargo.model.Thing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.w3.acl.model.Authorization;

import java.util.List;

@Document(collection = "accessControlList")
public class AccessControlList extends Thing {

  protected String logisticsObjectRef;
  protected List<Authorization> authorizations;

  public String getLogisticsObjectRef() {
    return logisticsObjectRef;
  }

  public void setLogisticsObjectRef(String logisticsObjectRef) {
    this.logisticsObjectRef = logisticsObjectRef;
  }

  public List<Authorization> getAuthorizations() {
    return authorizations;
  }

  public void setAuthorizations(List<Authorization> authorizations) {
    this.authorizations = authorizations;
  }
}
