
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.Types;
import io.swagger.annotations.ApiModelProperty;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.util.Set;


/**
 * Connected Device details
 * 
 * This class was generated by OWL2Java 0.15.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_ConnectedDevice)
public class ConnectedDevice
    extends LogisticsObject
    implements Serializable
{

  @Types
  @JsonProperty("@type")
  @ApiModelProperty(allowableValues = Vocabulary.s_c_ConnectedDevice)
  protected Set<String> types;

  public Set<String> getTypes() {
    return types;
  }

  public void setTypes(Set<String> types) {
    this.types = types;
  }

}
