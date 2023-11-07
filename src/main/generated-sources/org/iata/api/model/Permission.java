
package org.iata.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.*;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import org.iata.api.Vocabulary;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;


/**
 * This class was generated by OWL2Java 0.22.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Permission)
public class Permission
    implements Serializable
{

    @Id(generated = true)
    @JsonProperty("@id")
    protected String id;
    @OWLAnnotationProperty(iri = RDFS.LABEL)
    @JsonIgnore
    protected String name;
    @OWLAnnotationProperty(iri = cz.cvut.kbss.jopa.vocabulary.DC.Elements.DESCRIPTION)
    @JsonIgnore
    protected String description;
    @Types
    @JsonProperty("@type")
    protected Set<String> types;
    @Properties
    @JsonIgnore
    protected Map<String, Set<String>> properties;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }

    public Set<String> getTypes() {
        return types;
    }

    public void setProperties(Map<String, Set<String>> properties) {
        this.properties = properties;
    }

    public Map<String, Set<String>> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return ((((("Permission {"+ name)+"<")+ id)+">")+"}");
    }

}
