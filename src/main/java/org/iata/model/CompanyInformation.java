package org.iata.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.Types;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "companies")
public class CompanyInformation implements Serializable {

  @Id(generated = true)
  protected String id;
  @Types
  protected String type;

  private String companyId;
  private Company company;
  private List<String> supportedLogisticsObjects;
  private List<String> supportedContentTypes;
  private String serverEndpoint;

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

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public List<String> getSupportedLogisticsObjects() {
    return supportedLogisticsObjects;
  }

  public void setSupportedLogisticsObjects(List<String> supportedLogisticsObjects) {
    this.supportedLogisticsObjects = supportedLogisticsObjects;
  }

  public List<String> getSupportedContentTypes() {
    return supportedContentTypes;
  }

  public void setSupportedContentTypes(List<String> supportedContentTypes) {
    this.supportedContentTypes = supportedContentTypes;
  }

  public String getServerEndpoint() {
    return serverEndpoint;
  }

  public void setServerEndpoint(String serverEndpoint) {
    this.serverEndpoint = serverEndpoint;
  }

}
