
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.*;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;


/**
 * Carrier product details
 * 
 * This class was generated by OWL2Java 0.22.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_CarrierProduct)
public class CarrierProduct
    extends LogisticsObject
    implements Serializable
{

    /**
     * Reference to the BookingOptionRequest the LogisticsObject is detailling
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_forBookingOptions)
    @JsonProperty(Vocabulary.s_p_forBookingOptions)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected BookingOption forBookingOptions;
    /**
     * Carrier's product code
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_productCode)
    @JsonProperty(Vocabulary.s_p_productCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String productCode;
    /**
     * Carrier's product description
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_productDescription)
    @JsonProperty(Vocabulary.s_p_productDescription)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String productDescription;

    public void setForBookingOptions(BookingOption forBookingOptions) {
        this.forBookingOptions = forBookingOptions;
    }

    public BookingOption getForBookingOptions() {
        return forBookingOptions;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

}
