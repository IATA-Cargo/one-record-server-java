
package org.iata.cargo.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import io.swagger.v3.oas.annotations.media.Schema;
import org.iata.cargo.Vocabulary;

import java.io.Serializable;


/**
 * Superclass: LogisticsAgents describe acting entities in the logistics supply chain such as persons and organizations
 * <p>
 * This class was generated by OWL2Java 0.22.0
 */
@OWLClass(iri = Vocabulary.s_c_LogisticsAgent)
@Schema(
        type = "object",
        title = "LogisticsAgent",
        allOf = {
                LogisticsObject.class
        }
)
public class LogisticsAgent
        extends LogisticsObject
        implements Serializable {


}
