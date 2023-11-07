
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.*;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;


/**
 * Other identifiers
 * 
 * This class was generated by OWL2Java 0.22.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_OtherIdentifier)
public class OtherIdentifier
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
    /**
     * Identifier type or description
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_otherIdentifierType)
    @JsonProperty(Vocabulary.s_p_otherIdentifierType)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String otherIdentifierType;
    /**
     * Textual value filled on use context (eg. characteristic colour, contactDetail mail adress, etc.)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_textualValue)
    @JsonProperty(Vocabulary.s_p_textualValue)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String textualValue;

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
        return ((((("OtherIdentifier {"+ name)+"<")+ id)+">")+"}");
    }

    public void setOtherIdentifierType(String otherIdentifierType) {
        this.otherIdentifierType = otherIdentifierType;
    }

    public String getOtherIdentifierType() {
        return otherIdentifierType;
    }

    public void setTextualValue(String textualValue) {
        this.textualValue = textualValue;
    }

    public String getTextualValue() {
        return textualValue;
    }

}
