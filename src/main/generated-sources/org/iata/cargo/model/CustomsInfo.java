
package org.iata.cargo.model;

import java.io.Serializable;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import org.iata.cargo.Vocabulary;


/**
 * Customs information details
 * 
 * This class was generated by OWL2Java 0.17.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_CustomsInfo)
public class CustomsInfo
    extends LogisticsObject
    implements Serializable
{

    /**
     * Piece on which the Customs Info is applicable
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_piece)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected Piece piece;
    /**
     * Customs, Security and Regulatory Control Information Identifier. Coded indicator qualifying Customs related information: Item Number "I", Exemption Legend "L", System Downtime Reference "S", Unique Consignment Reference Number "U", Movement Reference Number "M" .
     * Refers to Code List 1.100
     * Condition: At least one of the three elements (Country Code, Information Identifier or Customs, Security and Regulatory Control Information Identifier) must be completed
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_customsInfoContentCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String customsInfoContentCode;
    /**
     * Customs country code. Refer ISO 3166-2
     * Condition:  At least one of the three elements (Country Code, Information Identifier or Customs, Security and Regulatory Control Information Identifier) must be completed
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_customsInfoCountryCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String customsInfoCountryCode;
    /**
     * Free text for customs remarks, not used in OCI Composition Rules Table
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_customsInfoNote)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String customsInfoNote;
    /**
     * Information Identifier. Code identifying a piece of information/entity e.g. "IMP" for import, "EXP" for export, "AGT" for Agent, "ISS" for The Regulated Agent Issuing the Security Status for a Consignment etc. 
     * Condition: At least one of the three elements (Country Code, Information Identifier or Customs, Security and Regulatory Control Information Identifier) must be completed
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_customsInfoSubjectCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String customsInfoSubjectCode;
    /**
     * Supplementary Customs, Security and Regulatory Control Information
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_customsInformation)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String customsInformation;

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setCustomsInfoContentCode(String customsInfoContentCode) {
        this.customsInfoContentCode = customsInfoContentCode;
    }

    public String getCustomsInfoContentCode() {
        return customsInfoContentCode;
    }

    public void setCustomsInfoCountryCode(String customsInfoCountryCode) {
        this.customsInfoCountryCode = customsInfoCountryCode;
    }

    public String getCustomsInfoCountryCode() {
        return customsInfoCountryCode;
    }

    public void setCustomsInfoNote(String customsInfoNote) {
        this.customsInfoNote = customsInfoNote;
    }

    public String getCustomsInfoNote() {
        return customsInfoNote;
    }

    public void setCustomsInfoSubjectCode(String customsInfoSubjectCode) {
        this.customsInfoSubjectCode = customsInfoSubjectCode;
    }

    public String getCustomsInfoSubjectCode() {
        return customsInfoSubjectCode;
    }

    public void setCustomsInformation(String customsInformation) {
        this.customsInformation = customsInformation;
    }

    public String getCustomsInformation() {
        return customsInformation;
    }

}
