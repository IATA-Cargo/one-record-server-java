
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.*;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.util.Set;


/**
 * Sensor details and measurements, linked to Connected Devices
 * 
 * This class was generated by OWL2Java 0.22.0
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
    @OWLObjectProperty(iri = Vocabulary.s_p_measurements)
    @JsonProperty(Vocabulary.s_p_measurements)
    protected Set<Measurement> measurements;
    /**
     * Reference to the IoT Device to which the sensor is linked
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_partOfIotDevice)
    @JsonProperty(Vocabulary.s_p_partOfIotDevice)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected IotDevice partOfIotDevice;
    /**
     * Natural language description if required
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_description)
    @JsonProperty(Vocabulary.s_p_description)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String description;
    /**
     * Human-understandable name of object depending on the context
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_name)
    @JsonProperty(Vocabulary.s_p_name)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String name;
    /**
     * Type of sensor as described in Interactive Cargo Recommended Practice
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_sensorType)
    @JsonProperty(Vocabulary.s_p_sensorType)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String sensorType;
    /**
     * Serial number that allows to uniquely identify the object
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_serialNumber)
    @JsonProperty(Vocabulary.s_p_serialNumber)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String serialNumber;

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setPartOfIotDevice(IotDevice partOfIotDevice) {
        this.partOfIotDevice = partOfIotDevice;
    }

    public IotDevice getPartOfIotDevice() {
        return partOfIotDevice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

}
