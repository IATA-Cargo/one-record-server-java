
package org.iata.cargo.model;

import java.io.Serializable;
import java.util.Set;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import org.iata.cargo.Vocabulary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Sensor details and measurements, linked to Connected Devices
 * 
 * This class was generated by OWL2Java 0.20.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Sensor)
public class Sensor
    extends PhysicalLogisticsObject
    implements Serializable
{

    /**
     * Reference to the Measurements recorded by the Sensor
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_hasRecordedMeasurement)
@JsonProperty(Vocabulary.s_p_hasRecordedMeasurement)
    protected Set<Measurement> hasRecordedMeasurement;
    /**
     * Reference to the IoT Device to which the sensor is linked
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_isPartOfIotDevice)
@JsonProperty(Vocabulary.s_p_isPartOfIotDevice)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_IotDevice, max = 1)
    })
    protected Set<Thing> isPartOfIotDevice;
    /**
     * Natural language description if required
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasDescription)
@JsonProperty(Vocabulary.s_p_hasDescription)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String hasDescription;
    /**
     * Human-understandable name of object depending on the context
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasName)
@JsonProperty(Vocabulary.s_p_hasName)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String hasName;
    /**
     * Serial number that allows to uniquely identify the object
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasSerialNumber)
@JsonProperty(Vocabulary.s_p_hasSerialNumber)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String hasSerialNumber;
    /**
     * Type of sensor as described in Interactive Cargo Recommended Practice
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_isOfSensorType)
@JsonProperty(Vocabulary.s_p_isOfSensorType)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", max = 1)
    })
    protected Set<String> isOfSensorType;

    public void setHasRecordedMeasurement(Set<Measurement> hasRecordedMeasurement) {
        this.hasRecordedMeasurement = hasRecordedMeasurement;
    }

    public Set<Measurement> getHasRecordedMeasurement() {
        return hasRecordedMeasurement;
    }

    public void setIsPartOfIotDevice(Set<Thing> isPartOfIotDevice) {
        this.isPartOfIotDevice = isPartOfIotDevice;
    }

    public Set<Thing> getIsPartOfIotDevice() {
        return isPartOfIotDevice;
    }

    public void setHasDescription(String hasDescription) {
        this.hasDescription = hasDescription;
    }

    public String getHasDescription() {
        return hasDescription;
    }

    public void setHasName(String hasName) {
        this.hasName = hasName;
    }

    public String getHasName() {
        return hasName;
    }

    public void setHasSerialNumber(String hasSerialNumber) {
        this.hasSerialNumber = hasSerialNumber;
    }

    public String getHasSerialNumber() {
        return hasSerialNumber;
    }

    public void setIsOfSensorType(Set<String> isOfSensorType) {
        this.isOfSensorType = isOfSensorType;
    }

    public Set<String> getIsOfSensorType() {
        return isOfSensorType;
    }

}
