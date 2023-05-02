
package org.iata.cargo.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.cvut.kbss.jopa.model.annotations.Properties;
import cz.cvut.kbss.jopa.model.annotations.Types;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import org.iata.cargo.Vocabulary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Ranges details
 * 
 * This class was generated by OWL2Java 0.20.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Ranges)
public class Ranges
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
     * Amount
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasAmount)
@JsonProperty(Vocabulary.s_p_hasAmount)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", max = 1)
    })
    protected Set<String> hasAmount;
    /**
     * Maximum quantity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasMaximumQuantity)
@JsonProperty(Vocabulary.s_p_hasMaximumQuantity)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#double", max = 1)
    })
    protected Double hasMaximumQuantity;
    /**
     * Minimum quantity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasMinimumQuantity)
@JsonProperty(Vocabulary.s_p_hasMinimumQuantity)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#double", max = 1)
    })
    protected Double hasMinimumQuantity;
    /**
     * Rate class code e.g. Q. Refer to CXML Code List 1.4 Rate Class Codes
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasRateClassCode)
@JsonProperty(Vocabulary.s_p_hasRateClassCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", max = 1)
    })
    protected Set<String> hasRateClassCode;
    /**
     * rating type - Refer to CXML Code List 1.44 ULD Charge Codes
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasRatingType)
@JsonProperty(Vocabulary.s_p_hasRatingType)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String hasRatingType;
    /**
     * Specific commodity code linked to commodity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasSpecificCommodityCode)
@JsonProperty(Vocabulary.s_p_hasSpecificCommodityCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", max = 1)
    })
    protected Set<String> hasSpecificCommodityCode;
    /**
     * Specific commodity rates linked to commodity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasSpecificCommodityRate)
@JsonProperty(Vocabulary.s_p_hasSpecificCommodityRate)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", max = 1)
    })
    protected Set<String> hasSpecificCommodityRate;

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
        return ((((("Ranges {"+ name)+"<")+ id)+">")+"}");
    }

    public void setHasAmount(Set<String> hasAmount) {
        this.hasAmount = hasAmount;
    }

    public Set<String> getHasAmount() {
        return hasAmount;
    }

    public void setHasMaximumQuantity(Double hasMaximumQuantity) {
        this.hasMaximumQuantity = hasMaximumQuantity;
    }

    public Double getHasMaximumQuantity() {
        return hasMaximumQuantity;
    }

    public void setHasMinimumQuantity(Double hasMinimumQuantity) {
        this.hasMinimumQuantity = hasMinimumQuantity;
    }

    public Double getHasMinimumQuantity() {
        return hasMinimumQuantity;
    }

    public void setHasRateClassCode(Set<String> hasRateClassCode) {
        this.hasRateClassCode = hasRateClassCode;
    }

    public Set<String> getHasRateClassCode() {
        return hasRateClassCode;
    }

    public void setHasRatingType(String hasRatingType) {
        this.hasRatingType = hasRatingType;
    }

    public String getHasRatingType() {
        return hasRatingType;
    }

    public void setHasSpecificCommodityCode(Set<String> hasSpecificCommodityCode) {
        this.hasSpecificCommodityCode = hasSpecificCommodityCode;
    }

    public Set<String> getHasSpecificCommodityCode() {
        return hasSpecificCommodityCode;
    }

    public void setHasSpecificCommodityRate(Set<String> hasSpecificCommodityRate) {
        this.hasSpecificCommodityRate = hasSpecificCommodityRate;
    }

    public Set<String> getHasSpecificCommodityRate() {
        return hasSpecificCommodityRate;
    }

}
