
package org.iata.cargo.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.cvut.kbss.jopa.model.annotations.Properties;
import cz.cvut.kbss.jopa.model.annotations.Types;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import org.iata.cargo.Vocabulary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Volumetric weight details
 * 
 * This class was generated by OWL2Java 0.20.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_VolumetricWeight)
public class VolumetricWeight
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
     * Chargeable weight
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_hasChargeableWeight)
@JsonProperty(Vocabulary.s_p_hasChargeableWeight)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Value, max = 1)
    })
    protected Value hasChargeableWeight;
    /**
     * Volume to weight conversion factor
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_hasConversionFactor)
@JsonProperty(Vocabulary.s_p_hasConversionFactor)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Value, max = 1)
    })
    protected Value hasConversionFactor;

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
        return ((((("VolumetricWeight {"+ name)+"<")+ id)+">")+"}");
    }

    public void setHasChargeableWeight(Value hasChargeableWeight) {
        this.hasChargeableWeight = hasChargeableWeight;
    }

    public Value getHasChargeableWeight() {
        return hasChargeableWeight;
    }

    public void setHasConversionFactor(Value hasConversionFactor) {
        this.hasConversionFactor = hasConversionFactor;
    }

    public Value getHasConversionFactor() {
        return hasConversionFactor;
    }

}
