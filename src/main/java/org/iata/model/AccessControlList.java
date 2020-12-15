package org.iata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.cvut.kbss.jopa.model.annotations.Properties;
import cz.cvut.kbss.jopa.model.annotations.Types;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.iata.api.Vocabulary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.wc.acl.model.Authorization;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@OWLClass(iri = "https://onerecord.iata.org/AccessControlList")
@Document(collection = "accessControlLists")
@ApiModel
public class AccessControlList implements Serializable {

  @Id(generated = true)
  @ApiModelProperty(readOnly = true)
  protected String id;
  @JsonIgnore
  @OWLAnnotationProperty(iri = RDFS.LABEL)
  protected String name;
  @JsonIgnore
  @OWLAnnotationProperty(iri = cz.cvut.kbss.jopa.vocabulary.DC.Elements.DESCRIPTION)
  protected String description;
  @Types
  @JsonProperty("@type")
  @ApiModelProperty(allowableValues = "https://onerecord.iata.org/AccessControlList")
  protected Set<String> types;
  @Properties
  @JsonIgnore
  protected Map<String, Set<String>> properties;

  @OWLObjectProperty(iri = org.wc.acl.Vocabulary.s_p_accessTo)
  @ParticipationConstraints({
      @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1),
      @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", min = 1, max = -1)
  })
  @JsonProperty(org.wc.acl.Vocabulary.s_p_accessTo)
  protected String logisticsObjectRef;

  @OWLObjectProperty(iri = "https://onerecord.iata.org/AccessControlList#authorizations")
  @ParticipationConstraints({
      @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = -1)
  })
  @JsonProperty("https://onerecord.iata.org/AccessControlList#authorizations")
  protected Set<Authorization> authorizations;

  public String getLogisticsObjectRef() {
    return logisticsObjectRef;
  }

  public void setLogisticsObjectRef(String logisticsObjectRef) {
    this.logisticsObjectRef = logisticsObjectRef;
  }

  public Set<Authorization> getAuthorizations() {
    return authorizations;
  }

  public void setAuthorizations(Set<Authorization> authorizations) {
    this.authorizations = authorizations;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<String> getTypes() {
    return types;
  }

  public void setTypes(Set<String> types) {
    this.types = types;
  }

  public Map<String, Set<String>> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Set<String>> properties) {
    this.properties = properties;
  }

}
