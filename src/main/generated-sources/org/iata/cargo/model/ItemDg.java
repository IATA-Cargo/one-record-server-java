
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;


/**
 * Dangerous Goods subtype of Item
 * 
 * This class was generated by OWL2Java 0.15.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_ItemDg)
public class ItemDg
    extends Item
    implements Serializable
{

    /**
     * Contains the Emergency contact name (e.g. the name of the agency) and phone number (min required)
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_emergencyContact)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1),
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, min = 1, max = -1)
    })
    @JsonProperty(Vocabulary.s_p_emergencyContact)
    protected Person emergencyContact;
    /**
     * The total net weight of dangerous goods transported of this line item. For air transport the value must be the volume or mass in each package.
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_netWeightMeasure)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1),
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, min = 1, max = -1)
    })
    @JsonProperty(Vocabulary.s_p_netWeightMeasure)
    protected Value netWeightMeasure;
    /**
     * Reportable quantities, To and from the USA only
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_reportableQuantity)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    @JsonProperty(Vocabulary.s_p_reportableQuantity)
    protected String reportableQuantity;
    /**
     * Additional information that may be added in addition to the proper shipping name to more fully describe the goods or to identify a particular condition
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_supplementaryInfoPrefix)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    @JsonProperty(Vocabulary.s_p_supplementaryInfoPrefix)
    protected String supplementaryInfoPrefix;
    /**
     * Additional information that may be added in addition to the proper shipping to more fully describe the goods or to identify a particular condition
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_supplementaryInfoSuffix)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    @JsonProperty(Vocabulary.s_p_supplementaryInfoSuffix)
    protected String supplementaryInfoSuffix;

    public void setEmergencyContact(Person emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public Person getEmergencyContact() {
        return emergencyContact;
    }

    public void setNetWeightMeasure(Value netWeightMeasure) {
        this.netWeightMeasure = netWeightMeasure;
    }

    public Value getNetWeightMeasure() {
        return netWeightMeasure;
    }

    public void setReportableQuantity(String reportableQuantity) {
        this.reportableQuantity = reportableQuantity;
    }

    public String getReportableQuantity() {
        return reportableQuantity;
    }

    public void setSupplementaryInfoPrefix(String supplementaryInfoPrefix) {
        this.supplementaryInfoPrefix = supplementaryInfoPrefix;
    }

    public String getSupplementaryInfoPrefix() {
        return supplementaryInfoPrefix;
    }

    public void setSupplementaryInfoSuffix(String supplementaryInfoSuffix) {
        this.supplementaryInfoSuffix = supplementaryInfoSuffix;
    }

    public String getSupplementaryInfoSuffix() {
        return supplementaryInfoSuffix;
    }

}
