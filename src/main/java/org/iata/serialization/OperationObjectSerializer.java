//package org.iata.serialization;
//
//import cz.cvut.kbss.jsonld.serialization.model.JsonNode;
//import cz.cvut.kbss.jsonld.serialization.serializer.ValueSerializer;
//import cz.cvut.kbss.jsonld.serialization.serializer.compact.ObjectPropertyValueSerializer;
//import cz.cvut.kbss.jsonld.serialization.traversal.ObjectGraphTraverser;
//import cz.cvut.kbss.jsonld.serialization.traversal.SerializationContext;
//import org.iata.api.model.OperationObject;
//import org.iata.serialization.util.BufferedJsonGenerator;
//
//public class OperationObjectSerializer implements ValueSerializer<OperationObject> {
//
////    private BufferedJsonGenerator jsonWriter;
////
////    public OperationObjectSerializer() {
////        this.jsonWriter = new BufferedJsonGenerator();
////    }
//
////    @Override
////    public JsonNode serialize(OperationObject value, SerializationContext<OperationObject> ctx) {
////
////        ObjectGraphTraverser traverser = new ObjectGraphTraverser(ctx.getJsonLdContext());
////        ObjectPropertyValueSerializer serializer = new ObjectPropertyValueSerializer(traverser);
////
////        JsonNode j = serializer.serialize(value, ctx);
////
////
////        //JsonNode j = ObjectPropertyValueSerializer.class(jsonWriter.getResult());
////        //JsonNode j = JsonNodeFactory.createLiteralNode(ctx.getAttributeId(), "hello");
////        return j;
////    }
//
////    @Override
////    public JsonNode serialize(OperationObject value, SerializationContext<OperationObject> ctx) {
////
////        final Configuration config = new Configuration();
////        config.set(ConfigParam.SCAN_PACKAGE, "org.iata");
////
////        final JsonLdSerializer serializer = JsonLdSerializer.createCompactedJsonLdSerializer(this.jsonWriter, config);
////        serializer.serialize(value.getObject());
////        String result = this.jsonWriter.getResult();
////        value.setValue(result);
////
////        return JsonNodeFactory.createLiteralNode(ctx.getAttributeId(), value);
////    }
//}
