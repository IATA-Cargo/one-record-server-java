
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;


/**
 * Unit Load Device details
 * 
 * This class was generated by OWL2Java 0.22.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Uld)
public class Uld
    extends LoadingUnit
    implements Serializable
{

    /**
     * US / ATA Unit Load Device type code e.g. M2
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_ataDesignator)
    @JsonProperty(Vocabulary.s_p_ataDesignator)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String ataDesignator;
    /**
     * Indicates if the ULD is Damaged
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_damageFlag)
    @JsonProperty(Vocabulary.s_p_damageFlag)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#boolean", max = 1)
    })
    protected Boolean damageFlag;
    /**
     * Contains three designator of demurrage code, refer to RP 1654 (BCC, HHH, XXX, ZZZ)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_demurrageCode)
    @JsonProperty(Vocabulary.s_p_demurrageCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String demurrageCode;
    /**
     * ULD height or loading limitation code. Refer CXML Code List 1.47,  e.g. R - ULD Height above 244 centimetres
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_loadingIndicator)
    @JsonProperty(Vocabulary.s_p_loadingIndicator)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String loadingIndicator;
    /**
     * Number of doors
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_numberOfDoors)
    @JsonProperty(Vocabulary.s_p_numberOfDoors)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#integer", max = 1)
    })
    protected Integer numberOfDoors;
    /**
     * Number of fittings
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_numberOfFittings)
    @JsonProperty(Vocabulary.s_p_numberOfFittings)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#integer", max = 1)
    })
    protected Integer numberOfFittings;
    /**
     * Number of nets
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_numberOfNets)
    @JsonProperty(Vocabulary.s_p_numberOfNets)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#integer", max = 1)
    })
    protected Integer numberOfNets;
    /**
     * Number of straps
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_numberOfStraps)
    @JsonProperty(Vocabulary.s_p_numberOfStraps)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#integer", max = 1)
    })
    protected Integer numberOfStraps;
    /**
     * Contains two designator codes of ODLN or Operational Damage Limit Notices. ODLN code is used to define type of damage after visually check the serviceability of ULDs section 7, Standard Specifications 40/3 or 40/4 in ULD Regulations
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_odlnCode)
    @JsonProperty(Vocabulary.s_p_odlnCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String odlnCode;
    /**
     * Owner code of the ULD in aa, an or na format - owner can be an airline or leasing company
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_ownerCode)
    @JsonProperty(Vocabulary.s_p_ownerCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String ownerCode;
    /**
     * ULD seal number if applicable
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_sealNumber)
    @JsonProperty(Vocabulary.s_p_sealNumber)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String sealNumber;
    /**
     * Designator of serviceablity condition e.g. SER or DAM 
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_serviceabilityCode)
    @JsonProperty(Vocabulary.s_p_serviceabilityCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String serviceabilityCode;
    /**
     * Serial number that allows to uniquely identify the ULD
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_uldSerialNumber)
    @JsonProperty(Vocabulary.s_p_uldSerialNumber)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String uldSerialNumber;
    /**
     * Standard Unit Load Device type code e.g. AKE - Certified Container - Contoured. Refer to IATA ULD Technical Manual
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_uldTypeCode)
    @JsonProperty(Vocabulary.s_p_uldTypeCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String uldTypeCode;

    public void setAtaDesignator(String ataDesignator) {
        this.ataDesignator = ataDesignator;
    }

    public String getAtaDesignator() {
        return ataDesignator;
    }

    public void setDamageFlag(Boolean damageFlag) {
        this.damageFlag = damageFlag;
    }

    public Boolean getDamageFlag() {
        return damageFlag;
    }

    public void setDemurrageCode(String demurrageCode) {
        this.demurrageCode = demurrageCode;
    }

    public String getDemurrageCode() {
        return demurrageCode;
    }

    public void setLoadingIndicator(String loadingIndicator) {
        this.loadingIndicator = loadingIndicator;
    }

    public String getLoadingIndicator() {
        return loadingIndicator;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfFittings(Integer numberOfFittings) {
        this.numberOfFittings = numberOfFittings;
    }

    public Integer getNumberOfFittings() {
        return numberOfFittings;
    }

    public void setNumberOfNets(Integer numberOfNets) {
        this.numberOfNets = numberOfNets;
    }

    public Integer getNumberOfNets() {
        return numberOfNets;
    }

    public void setNumberOfStraps(Integer numberOfStraps) {
        this.numberOfStraps = numberOfStraps;
    }

    public Integer getNumberOfStraps() {
        return numberOfStraps;
    }

    public void setOdlnCode(String odlnCode) {
        this.odlnCode = odlnCode;
    }

    public String getOdlnCode() {
        return odlnCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setSealNumber(String sealNumber) {
        this.sealNumber = sealNumber;
    }

    public String getSealNumber() {
        return sealNumber;
    }

    public void setServiceabilityCode(String serviceabilityCode) {
        this.serviceabilityCode = serviceabilityCode;
    }

    public String getServiceabilityCode() {
        return serviceabilityCode;
    }

    public void setUldSerialNumber(String uldSerialNumber) {
        this.uldSerialNumber = uldSerialNumber;
    }

    public String getUldSerialNumber() {
        return uldSerialNumber;
    }

    public void setUldTypeCode(String uldTypeCode) {
        this.uldTypeCode = uldTypeCode;
    }

    public String getUldTypeCode() {
        return uldTypeCode;
    }

}
