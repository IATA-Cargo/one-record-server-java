
package org.iata.cargo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.kbss.jopa.model.annotations.*;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;


/**
 * LiveAnimals subclass of Piece
 * 
 * This class was generated by OWL2Java 0.22.0
 * 
 */
@OWLClass(iri = Vocabulary.s_c_PieceLiveAnimals)
public class PieceLiveAnimals
    extends Piece
    implements Serializable
{

    /**
     * Reference to the permits associated with the Live Animals
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_associatedEpermit)
    @JsonProperty(Vocabulary.s_p_associatedEpermit)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected EpermitConsignment associatedEpermit;
    /**
     * Country of last re-export (box 12a)
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_exportTradeCountry)
    @JsonProperty(Vocabulary.s_p_exportTradeCountry)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected Country exportTradeCountry;
    /**
     * country of origin (box 12)
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_originTradeCountry)
    @JsonProperty(Vocabulary.s_p_originTradeCountry)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Thing, max = 1)
    })
    protected Country originTradeCountry;
    /**
     * Defined in Resolution Conf. 13.6 and is required for pre-Convention specimens (box 12b)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_acquisitionDateTime)
    @JsonProperty(Vocabulary.s_p_acquisitionDateTime)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#dateTime", max = 1)
    })
    protected OffsetDateTime acquisitionDateTime;
    /**
     * total number of specimens exported in the current calendar year and the current annuela quota for the species concerned (box 11a)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_annualQuotaQuantity)
    @JsonProperty(Vocabulary.s_p_annualQuotaQuantity)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#integer", max = 1)
    })
    protected Integer annualQuotaQuantity;
    /**
     * Operations code ID. Refers to the number of the registered captive-breeding or artifical propagation operation (box 12b)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_categoryCode)
    @JsonProperty(Vocabulary.s_p_categoryCode)
    protected Set<String> categoryCode;
    /**
     * Appendix number of the convention (I, II or III) (box 10)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_goodsTypeCode)
    @JsonProperty(Vocabulary.s_p_goodsTypeCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String goodsTypeCode;
    /**
     * Appendix number of the convention (I, II or III) (box 10)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_goodsTypeExtensionCode)
    @JsonProperty(Vocabulary.s_p_goodsTypeExtensionCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String goodsTypeExtensionCode;
    /**
     * Issuing date for Origin reference permit or re-export reference Certificate (box 12)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_originReferencePermitDateTime)
    @JsonProperty(Vocabulary.s_p_originReferencePermitDateTime)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#dateTime", max = 1)
    })
    protected OffsetDateTime originReferencePermitDateTime;
    /**
     * identifier of Origin reference permit or re-export reference Certificate (box 12/12a)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_originReferencePermitId)
    @JsonProperty(Vocabulary.s_p_originReferencePermitId)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String originReferencePermitId;
    /**
     * Document type code of origin reference permit or re-export reference Certificate (box 12/12a)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_originReferencePermitTypeCode)
    @JsonProperty(Vocabulary.s_p_originReferencePermitTypeCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String originReferencePermitTypeCode;
    /**
     * Quantity including units (box 11)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_quantityAnimals)
    @JsonProperty(Vocabulary.s_p_quantityAnimals)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#integer", max = 1)
    })
    protected Integer quantityAnimals;
    /**
     * Species common name (box 8)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_speciesCommonName)
    @JsonProperty(Vocabulary.s_p_speciesCommonName)
    protected Set<String> speciesCommonName;
    /**
     * Species scientific name (box 7)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_speciesScientificName)
    @JsonProperty(Vocabulary.s_p_speciesScientificName)
    protected Set<String> speciesScientificName;
    /**
     * Description of specimens, including age and sex if LA (box 9)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_specimenDescription)
    @JsonProperty(Vocabulary.s_p_specimenDescription)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String specimenDescription;
    /**
     * Description of specimens, CITES type code (box 9)
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_specimenTypeCode)
    @JsonProperty(Vocabulary.s_p_specimenTypeCode)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", max = 1)
    })
    protected String specimenTypeCode;

    public void setAssociatedEpermit(EpermitConsignment associatedEpermit) {
        this.associatedEpermit = associatedEpermit;
    }

    public EpermitConsignment getAssociatedEpermit() {
        return associatedEpermit;
    }

    public void setExportTradeCountry(Country exportTradeCountry) {
        this.exportTradeCountry = exportTradeCountry;
    }

    public Country getExportTradeCountry() {
        return exportTradeCountry;
    }

    public void setOriginTradeCountry(Country originTradeCountry) {
        this.originTradeCountry = originTradeCountry;
    }

    public Country getOriginTradeCountry() {
        return originTradeCountry;
    }

    public void setAcquisitionDateTime(OffsetDateTime acquisitionDateTime) {
        this.acquisitionDateTime = acquisitionDateTime;
    }

    public OffsetDateTime getAcquisitionDateTime() {
        return acquisitionDateTime;
    }

    public void setAnnualQuotaQuantity(Integer annualQuotaQuantity) {
        this.annualQuotaQuantity = annualQuotaQuantity;
    }

    public Integer getAnnualQuotaQuantity() {
        return annualQuotaQuantity;
    }

    public void setCategoryCode(Set<String> categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Set<String> getCategoryCode() {
        return categoryCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeExtensionCode(String goodsTypeExtensionCode) {
        this.goodsTypeExtensionCode = goodsTypeExtensionCode;
    }

    public String getGoodsTypeExtensionCode() {
        return goodsTypeExtensionCode;
    }

    public void setOriginReferencePermitDateTime(OffsetDateTime originReferencePermitDateTime) {
        this.originReferencePermitDateTime = originReferencePermitDateTime;
    }

    public OffsetDateTime getOriginReferencePermitDateTime() {
        return originReferencePermitDateTime;
    }

    public void setOriginReferencePermitId(String originReferencePermitId) {
        this.originReferencePermitId = originReferencePermitId;
    }

    public String getOriginReferencePermitId() {
        return originReferencePermitId;
    }

    public void setOriginReferencePermitTypeCode(String originReferencePermitTypeCode) {
        this.originReferencePermitTypeCode = originReferencePermitTypeCode;
    }

    public String getOriginReferencePermitTypeCode() {
        return originReferencePermitTypeCode;
    }

    public void setQuantityAnimals(Integer quantityAnimals) {
        this.quantityAnimals = quantityAnimals;
    }

    public Integer getQuantityAnimals() {
        return quantityAnimals;
    }

    public void setSpeciesCommonName(Set<String> speciesCommonName) {
        this.speciesCommonName = speciesCommonName;
    }

    public Set<String> getSpeciesCommonName() {
        return speciesCommonName;
    }

    public void setSpeciesScientificName(Set<String> speciesScientificName) {
        this.speciesScientificName = speciesScientificName;
    }

    public Set<String> getSpeciesScientificName() {
        return speciesScientificName;
    }

    public void setSpecimenDescription(String specimenDescription) {
        this.specimenDescription = specimenDescription;
    }

    public String getSpecimenDescription() {
        return specimenDescription;
    }

    public void setSpecimenTypeCode(String specimenTypeCode) {
        this.specimenTypeCode = specimenTypeCode;
    }

    public String getSpecimenTypeCode() {
        return specimenTypeCode;
    }

}
