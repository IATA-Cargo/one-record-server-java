
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.Properties;
import cz.cvut.kbss.jopa.model.annotations.Types;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


/**
 * Ranges
 * 
 * This class was generated by OWL2Java 0.14.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Ranges)
public class Ranges
    implements Serializable
{

    @Id(generated = true)
    @JsonProperty("@id")
    protected String id;
    @JsonIgnore
    @OWLAnnotationProperty(iri = RDFS.LABEL)
    protected String name;
    @JsonIgnore
    @OWLAnnotationProperty(iri = cz.cvut.kbss.jopa.vocabulary.DC.Elements.DESCRIPTION)
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
    @OWLDataProperty(iri = Vocabulary.s_p_amount)
    protected Set<BigDecimal> amount;
    /**
     * Maximum quantity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_maximumQuantity)
    protected Set<BigDecimal> maximumQuantity;
    /**
     * Minimum quantity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_minimumQuantity)
    protected Set<BigDecimal> minimumQuantity;
    /**
     * rate class code e.g. Q
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_rateClassCode)
    protected Set<String> rateClassCode;
    /**
     * rating type - list uldRatingType
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_ratingType)
    protected Set<String> ratingType;
    /**
     * Specific commodity code linked to commodity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_scr)
    protected Set<String> scr;
    /**
     * Specific commodity code linked to commodity
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_unitBasis)
    protected Set<String> unitBasis;

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

    public void setAmount(Set<BigDecimal> amount) {
        this.amount = amount;
    }

    public Set<BigDecimal> getAmount() {
        return amount;
    }

    public void setMaximumQuantity(Set<BigDecimal> maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }

    public Set<BigDecimal> getMaximumQuantity() {
        return maximumQuantity;
    }

    public void setMinimumQuantity(Set<BigDecimal> minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Set<BigDecimal> getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setRateClassCode(Set<String> rateClassCode) {
        this.rateClassCode = rateClassCode;
    }

    public Set<String> getRateClassCode() {
        return rateClassCode;
    }

    public void setRatingType(Set<String> ratingType) {
        this.ratingType = ratingType;
    }

    public Set<String> getRatingType() {
        return ratingType;
    }

    public void setScr(Set<String> scr) {
        this.scr = scr;
    }

    public Set<String> getScr() {
        return scr;
    }

    public void setUnitBasis(Set<String> unitBasis) {
        this.unitBasis = unitBasis;
    }

    public Set<String> getUnitBasis() {
        return unitBasis;
    }

}
