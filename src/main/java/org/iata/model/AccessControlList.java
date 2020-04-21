package org.iata.model;

import org.iata.cargo.model.Thing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.w3.acl.model.Authorization;

import java.util.List;

@Document(collection = "accessControlList")
public class AccessControlList extends Thing {

  protected List<Authorization> authorizations;

  public List<Authorization> getAuthorizations() {
    return authorizations;
  }

  public void setAuthorizations(List<Authorization> authorizations) {
    this.authorizations = authorizations;
  }
}
