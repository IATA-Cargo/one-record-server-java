package org.iata.deserialization;

import com.github.jsonldjava.core.JsonLdProcessor;
import cz.cvut.kbss.jsonld.ConfigParam;
import cz.cvut.kbss.jsonld.Configuration;
import cz.cvut.kbss.jsonld.JsonLd;
import cz.cvut.kbss.jsonld.deserialization.DeserializationContext;
import cz.cvut.kbss.jsonld.deserialization.JsonLdDeserializer;
import cz.cvut.kbss.jsonld.deserialization.ValueDeserializer;
import org.iata.api.Vocabulary;
import org.iata.api.model.OperationObject;

import java.util.List;
import java.util.Map;

public class OperationObjectDeserializer implements ValueDeserializer<OperationObject> {

    @Override
    public OperationObject deserialize(Map<?, ?> jsonNode, DeserializationContext<OperationObject> ctx) {
        final OperationObject result = new OperationObject();

        if (jsonNode.containsKey(Vocabulary.s_p_datatype)) {
            final Map<?, ?> datatypeMap = (Map<?, ?>) ((List<?>) jsonNode.get(Vocabulary.s_p_datatype)).get(0);
            result.setDatatype(datatypeMap.get(JsonLd.VALUE).toString());
        }

        if (jsonNode.containsKey(Vocabulary.s_p_value)) {
            Map<?, ?> valueMap = (Map<?, ?>) ((List<?>) jsonNode.get(Vocabulary.s_p_value)).get(0);
            if (valueMap.containsKey(JsonLd.TYPE)) {
                final Configuration config = new Configuration();
                config.set(ConfigParam.SCAN_PACKAGE, "org.iata");
                final JsonLdDeserializer deserializer = JsonLdDeserializer.createExpandedDeserializer(config);
                if (valueMap.containsKey(JsonLd.VALUE)) {
                    result.setObject(valueMap.get(JsonLd.VALUE));
                    result.setValue((String) valueMap.get(JsonLd.VALUE));
                } else {
                    result.setObject(deserializer.deserialize(JsonLdProcessor.expand(valueMap), Object.class));
                }
            } else {
                if (valueMap.containsKey(JsonLd.VALUE)) {
                    result.setObject(valueMap.get(JsonLd.VALUE));
                    result.setValue((String) valueMap.get(JsonLd.VALUE));
                }
            }
        }
        return result;
    }
}