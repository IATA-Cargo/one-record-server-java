
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
 * Superclass: Organizations represent a kind of Agent corresponding to social instititutions such as companies, societies, etc
 * 
 * This class was generated by OWL2Java 0.20.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Organization)
public class Organization
    extends LogisticsAgent
    implements Serializable
{

    /**
     * References to Actors (Person, NonHumanActor) acting as contacts
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_hasContactPerson)
@JsonProperty(Vocabulary.s_p_hasContactPerson)
    protected Set<Actor> hasContactPerson;
    @OWLObjectProperty(iri = Vocabulary.s_p_hasOtherIdentifier)
@JsonProperty(Vocabulary.s_p_hasOtherIdentifier)
    protected Set<OtherIdentifier> hasOtherIdentifier;
    /**
     * Reference to the parent Organization
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_hasParentOrganization)
@JsonProperty(Vocabulary.s_p_hasParentOrganization)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Organization, max = 1)
    })
    protected Organization hasParentOrganization;
    /**
     * References to all sub-Organizations
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_hasSubOrganization)
@JsonProperty(Vocabulary.s_p_hasSubOrganization)
    protected Set<Organization> hasSubOrganization;
    /**
     * Reference to the Location where the Organization is based at or headquartered
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_isBasedAt)
@JsonProperty(Vocabulary.s_p_isBasedAt)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = Vocabulary.s_c_Location, max = 1)
    })
    protected Set<Thing> isBasedAt;
    /**
     * Human-understandable name of object depending on the context
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasName)
@JsonProperty(Vocabulary.s_p_hasName)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", max = 1)
    })
    protected Set<String> hasName;
    /**
     * Short name of the Organization if any
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasShortName)
@JsonProperty(Vocabulary.s_p_hasShortName)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", max = 1)
    })
    protected Set<String> hasShortName;

    public void setHasContactPerson(Set<Actor> hasContactPerson) {
        this.hasContactPerson = hasContactPerson;
    }

    public Set<Actor> getHasContactPerson() {
        return hasContactPerson;
    }

    public void setHasOtherIdentifier(Set<OtherIdentifier> hasOtherIdentifier) {
        this.hasOtherIdentifier = hasOtherIdentifier;
    }

    public Set<OtherIdentifier> getHasOtherIdentifier() {
        return hasOtherIdentifier;
    }

    public void setHasParentOrganization(Organization hasParentOrganization) {
        this.hasParentOrganization = hasParentOrganization;
    }

    public Organization getHasParentOrganization() {
        return hasParentOrganization;
    }

    public void setHasSubOrganization(Set<Organization> hasSubOrganization) {
        this.hasSubOrganization = hasSubOrganization;
    }

    public Set<Organization> getHasSubOrganization() {
        return hasSubOrganization;
    }

    public void setIsBasedAt(Set<Thing> isBasedAt) {
        this.isBasedAt = isBasedAt;
    }

    public Set<Thing> getIsBasedAt() {
        return isBasedAt;
    }

    public void setHasName(Set<String> hasName) {
        this.hasName = hasName;
    }

    public Set<String> getHasName() {
        return hasName;
    }

    public void setHasShortName(Set<String> hasShortName) {
        this.hasShortName = hasShortName;
    }

    public Set<String> getHasShortName() {
        return hasShortName;
    }

}
