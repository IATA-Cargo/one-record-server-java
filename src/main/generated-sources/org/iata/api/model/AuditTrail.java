
package org.iata.api.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
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
import org.iata.api.Vocabulary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Audit trail of a Logistics Object
 * 
 * This class was generated by OWL2Java 0.20.2
 * 
 */
@OWLClass(iri = Vocabulary.s_c_AuditTrail)
public class AuditTrail
    implements Serializable
{

    @Id(generated = true)
@JsonProperty("@id")
    protected String id;
    @OWLAnnotationProperty(iri = RDFS.LABEL)
@JsonIgnore
    protected String name;
    @OWLAnnotationProperty(iri = cz.cvut.kbss.jopa.vocabulary.DC.Elements.DESCRIPTION)
@JsonIgnore
    protected String description;
    @Types
@JsonProperty("@type")
    protected Set<String> types;
    @Properties
@JsonIgnore
    protected Map<String, Set<String>> properties;
    /**
     * Recorded change requests in the Audit Trail of a Logistics Object
     * 
     */
    @OWLObjectProperty(iri = Vocabulary.s_p_hasChangeRequest)
@JsonProperty(Vocabulary.s_p_hasChangeRequest)
    protected Set<ChangeRequest> hasChangeRequest;
    /**
     * Latest revision of the Logistics Object. Starting with revision 0
     * 
     */
    @OWLDataProperty(iri = Vocabulary.s_p_hasLatestRevision)
@JsonProperty(Vocabulary.s_p_hasLatestRevision)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", min = 1, max = 1)
    })
    protected Set<String> hasLatestRevision;

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
        return ((((("AuditTrail {"+ name)+"<")+ id)+">")+"}");
    }

    public void setHasChangeRequest(Set<ChangeRequest> hasChangeRequest) {
        this.hasChangeRequest = hasChangeRequest;
    }

    public Set<ChangeRequest> getHasChangeRequest() {
        return hasChangeRequest;
    }

    public void setHasLatestRevision(Set<String> hasLatestRevision) {
        this.hasLatestRevision = hasLatestRevision;
    }

    public Set<String> getHasLatestRevision() {
        return hasLatestRevision;
    }

}
