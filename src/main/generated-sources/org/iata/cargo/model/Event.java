
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
 * Event details
 * 
 * This class was generated by OWL2Java 0.14.1
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Event)
public class Event
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
     * Handling details  
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_handlingDetails)
    protected Set<Handling> handlingDetails;
    /**
     * Location of event
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_location_A_A)
    protected Set<Location> location;
    /**
     * Party performing the event
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_performedBy)
    protected Set<Company> performedBy;
    /**
     * Date and time of the event
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_dateTime_A)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#dateTime", min = 1, max = -1)
    })
    protected Set<Date> dateTime;
    /**
     * Movement or milestone code. Refer CXML Code List 1.18, e.g. DEP, ARR, FOH, RCS
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_eventCode)
    protected Set<String> eventCode;
    /**
     * If no EventCode provided, event name - e.g. Security clearance
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_eventName)
    protected Set<String> eventName;
    /**
     * Indicates type of event e.g.  Scheduled, Estimated, Actual
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_eventTypeIndicator)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", min = 1, max = -1)
    })
    protected Set<String> eventTypeIndicator;

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
        return ((((("Event {"+ name)+"<")+ id)+">")+"}");
    }

    public void setHandlingDetails(Set<Handling> handlingDetails) {
        this.handlingDetails = handlingDetails;
    }

    public Set<Handling> getHandlingDetails() {
        return handlingDetails;
    }

    public void setLocation(Set<Location> location) {
        this.location = location;
    }

    public Set<Location> getLocation() {
        return location;
    }

    public void setPerformedBy(Set<Company> performedBy) {
        this.performedBy = performedBy;
    }

    public Set<Company> getPerformedBy() {
        return performedBy;
    }

    public void setDateTime(Set<Date> dateTime) {
        this.dateTime = dateTime;
    }

    public Set<Date> getDateTime() {
        return dateTime;
    }

    public void setEventCode(Set<String> eventCode) {
        this.eventCode = eventCode;
    }

    public Set<String> getEventCode() {
        return eventCode;
    }

    public void setEventName(Set<String> eventName) {
        this.eventName = eventName;
    }

    public Set<String> getEventName() {
        return eventName;
    }

    public void setEventTypeIndicator(Set<String> eventTypeIndicator) {
        this.eventTypeIndicator = eventTypeIndicator;
    }

    public Set<String> getEventTypeIndicator() {
        return eventTypeIndicator;
    }

}
