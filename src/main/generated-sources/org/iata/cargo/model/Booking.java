
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.cvut.kbss.jopa.model.annotations.Properties;
import cz.cvut.kbss.jopa.model.annotations.Types;
import cz.cvut.kbss.jopa.vocabulary.RDFS;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;


/**
 * Booking details
 * 
 * This class was generated by OWL2Java 0.14.1
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Booking)
public class Booking
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
     * Goods value declaration details
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_chargeDeclaration_A)
    protected Set<ChargeDeclaration> chargeDeclaration;
    /**
     * Charge summary details
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_chargeSummary_A)
    protected Set<ChargeSummary> chargeSummary;
    /**
     * Weight taken into account to calculate the charge
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_chargeableWeight)
    protected Set<VolumetricWeight> chargeableWeight;
    /**
     * Other charge
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_otherCharge_A)
    protected Set<Value> otherCharge;
    /**
     * Details of individual transport segments
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_segmentDetail)
    protected Set<TransportSegment> segmentDetail;
    /**
     * Air Waybill number
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_airWaybillNumber_A)
    protected Set<String> airWaybillNumber;
    /**
     * Booking unique identifier
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_bookingRequestNumber)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", min = 1, max = -1)
    })
    protected Set<String> bookingRequestNumber;
    /**
     * Type of booking - e.g: Freesale, Allocation, BlockSpace Agreement
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_bookingType)
    protected Set<String> bookingType;
    /**
     * Booking date
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_date_A)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#dateTime", min = 1, max = -1)
    })
    protected Set<Date> date;

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
        return ((((("Booking {"+ name)+"<")+ id)+">")+"}");
    }

    public void setChargeDeclaration(Set<ChargeDeclaration> chargeDeclaration) {
        this.chargeDeclaration = chargeDeclaration;
    }

    public Set<ChargeDeclaration> getChargeDeclaration() {
        return chargeDeclaration;
    }

    public void setChargeSummary(Set<ChargeSummary> chargeSummary) {
        this.chargeSummary = chargeSummary;
    }

    public Set<ChargeSummary> getChargeSummary() {
        return chargeSummary;
    }

    public void setChargeableWeight(Set<VolumetricWeight> chargeableWeight) {
        this.chargeableWeight = chargeableWeight;
    }

    public Set<VolumetricWeight> getChargeableWeight() {
        return chargeableWeight;
    }

    public void setOtherCharge(Set<Value> otherCharge) {
        this.otherCharge = otherCharge;
    }

    public Set<Value> getOtherCharge() {
        return otherCharge;
    }

    public void setSegmentDetail(Set<TransportSegment> segmentDetail) {
        this.segmentDetail = segmentDetail;
    }

    public Set<TransportSegment> getSegmentDetail() {
        return segmentDetail;
    }

    public void setAirWaybillNumber(Set<String> airWaybillNumber) {
        this.airWaybillNumber = airWaybillNumber;
    }

    public Set<String> getAirWaybillNumber() {
        return airWaybillNumber;
    }

    public void setBookingRequestNumber(Set<String> bookingRequestNumber) {
        this.bookingRequestNumber = bookingRequestNumber;
    }

    public Set<String> getBookingRequestNumber() {
        return bookingRequestNumber;
    }

    public void setBookingType(Set<String> bookingType) {
        this.bookingType = bookingType;
    }

    public Set<String> getBookingType() {
        return bookingType;
    }

    public void setDate(Set<Date> date) {
        this.date = date;
    }

    public Set<Date> getDate() {
        return date;
    }

}
