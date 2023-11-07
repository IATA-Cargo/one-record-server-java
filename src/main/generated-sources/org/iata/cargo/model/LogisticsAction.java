
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.*;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;


/**
 * Superclass: LogisticsAction is a specific task with a specific result performed on one or more physical LOs by one party in the context of an Activity
 * 
 * This class was generated by OWL2Java 0.22.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_LogisticsAction)
public class LogisticsAction
    extends LogisticsObject
    implements Serializable
{

    /**
     * Information about contactDetails
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_contactDetails)
    @JsonProperty(Vocabulary.s_p_contactDetails)
    protected Set<ContactDetail> contactDetails;
    /**
     * References to Actors (Person, NonHumanActor) acting as contacts
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_contactPersons)
    @JsonProperty(Vocabulary.s_p_contactPersons)
    protected Set<Person> contactPersons;
    /**
     * Reference to the Location the Action was performed at
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_locationOfPerformance)
    @JsonProperty(Vocabulary.s_p_locationOfPerformance)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected Location locationOfPerformance;
    /**
     * Reference to the Activity the Action was performed for
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_servedActivity)
    @JsonProperty(Vocabulary.s_p_servedActivity)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected LogisticsActivity servedActivity;
    /**
     * DateTime holding the end time of the Action; Type is indicated through ActionType property
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_actionEndTime)
    @JsonProperty(Vocabulary.s_p_actionEndTime)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#dateTime", max = 1)
    })
    protected OffsetDateTime actionEndTime;
    /**
     * DateTime holding the start time of the Action; Type is indicated through ActionType property
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_actionStartTime)
    @JsonProperty(Vocabulary.s_p_actionStartTime)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#dateTime", max = 1)
    })
    protected OffsetDateTime actionStartTime;
    /**
     * Enum stating the type of the Action
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_actionTimeType)
    @JsonProperty(Vocabulary.s_p_actionTimeType)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String actionTimeType;

    public void setContactDetails(Set<ContactDetail> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Set<ContactDetail> getContactDetails() {
        return contactDetails;
    }

    public void setContactPersons(Set<Person> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public Set<Person> getContactPersons() {
        return contactPersons;
    }

    public void setLocationOfPerformance(Location locationOfPerformance) {
        this.locationOfPerformance = locationOfPerformance;
    }

    public Location getLocationOfPerformance() {
        return locationOfPerformance;
    }

    public void setServedActivity(LogisticsActivity servedActivity) {
        this.servedActivity = servedActivity;
    }

    public LogisticsActivity getServedActivity() {
        return servedActivity;
    }

    public void setActionEndTime(OffsetDateTime actionEndTime) {
        this.actionEndTime = actionEndTime;
    }

    public OffsetDateTime getActionEndTime() {
        return actionEndTime;
    }

    public void setActionStartTime(OffsetDateTime actionStartTime) {
        this.actionStartTime = actionStartTime;
    }

    public OffsetDateTime getActionStartTime() {
        return actionStartTime;
    }

    public void setActionTimeType(String actionTimeType) {
        this.actionTimeType = actionTimeType;
    }

    public String getActionTimeType() {
        return actionTimeType;
    }

}
