
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.Properties;
import cz.cvut.kbss.jopa.model.annotations.Types;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;


/**
 * Unit Load Device details
 * 
 * This class was generated by OWL2Java 0.14.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_ULD)
public class Uld
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
     * Event details e.g. DEP, ARR, FOH, RCS, security screening, etc
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_event_A_A_A_A)
    protected Set<Event> event;
    /**
     * Reference documents details 
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_externalReference_A_A_A)
    protected Set<ExternalReference> externalReference;
    /**
     * Owner company details and contacts for the ULD
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_ownerCompany)
    protected Set<Company> ownerCompany;
    /**
     * Tare weight of the empty ULD
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_tareWeight)
    protected Set<Value> tareWeight;
    /**
     * Segment related to the ULD movement
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_transportSegment_A_A)
    protected Set<TransportSegment> transportSegment;
    /**
     * Details of contained (virtual) piece(s)
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_upid)
    protected Set<Piece> upid;
    @OWLDataProperty(iri = Vocabulary.s_p_SerialNumber)
    protected Set<String> SerialNumber;
    /**
     * US / ATA Unit Load Device type code e.g. M2
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_ataDesignator)
    protected Set<String> ataDesignator;
    /**
     * ULD profile, dimensions & shape
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_contour)
    protected Set<String> contour;
    /**
     * Position of the shipment in the aircraft - e.g. lower or main deck 
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_deckPosition)
    protected Set<String> deckPosition;
    /**
     * ULD height or loading limitation code. Refer CXML Code List 1.47,  e.g. R - ULD Height above 244 centimetres
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_loadingIndicator)
    protected Set<String> loadingIndicator;
    /**
     * Owner code of the ULD in aa, an or na format - owner can be an airline or leasing company
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_ownerCode)
    protected Set<String> ownerCode;
    /**
     * Issuer of the ULD owner code 
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_ownerCodeIssuer)
    protected Set<String> ownerCodeIssuer;
    /**
     * Standard Unit Load Device type code e.g. AKE - Certified Container - Contoured
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_uldType)
    protected Set<String> uldType;

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
        return ((((("ULD {"+ name)+"<")+ id)+">")+"}");
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }

    public Set<Event> getEvent() {
        return event;
    }

    public void setExternalReference(Set<ExternalReference> externalReference) {
        this.externalReference = externalReference;
    }

    public Set<ExternalReference> getExternalReference() {
        return externalReference;
    }

    public void setOwnerCompany(Set<Company> ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public Set<Company> getOwnerCompany() {
        return ownerCompany;
    }

    public void setTareWeight(Set<Value> tareWeight) {
        this.tareWeight = tareWeight;
    }

    public Set<Value> getTareWeight() {
        return tareWeight;
    }

    public void setTransportSegment(Set<TransportSegment> transportSegment) {
        this.transportSegment = transportSegment;
    }

    public Set<TransportSegment> getTransportSegment() {
        return transportSegment;
    }

    public void setUpid(Set<Piece> upid) {
        this.upid = upid;
    }

    public Set<Piece> getUpid() {
        return upid;
    }

    public void setSerialNumber(Set<String> SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    public Set<String> getSerialNumber() {
        return SerialNumber;
    }

    public void setAtaDesignator(Set<String> ataDesignator) {
        this.ataDesignator = ataDesignator;
    }

    public Set<String> getAtaDesignator() {
        return ataDesignator;
    }

    public void setContour(Set<String> contour) {
        this.contour = contour;
    }

    public Set<String> getContour() {
        return contour;
    }

    public void setDeckPosition(Set<String> deckPosition) {
        this.deckPosition = deckPosition;
    }

    public Set<String> getDeckPosition() {
        return deckPosition;
    }

    public void setLoadingIndicator(Set<String> loadingIndicator) {
        this.loadingIndicator = loadingIndicator;
    }

    public Set<String> getLoadingIndicator() {
        return loadingIndicator;
    }

    public void setOwnerCode(Set<String> ownerCode) {
        this.ownerCode = ownerCode;
    }

    public Set<String> getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCodeIssuer(Set<String> ownerCodeIssuer) {
        this.ownerCodeIssuer = ownerCodeIssuer;
    }

    public Set<String> getOwnerCodeIssuer() {
        return ownerCodeIssuer;
    }

    public void setUldType(Set<String> uldType) {
        this.uldType = uldType;
    }

    public Set<String> getUldType() {
        return uldType;
    }

}
