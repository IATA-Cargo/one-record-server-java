package org.iata.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import org.iata.cargo.model.Piece;
import org.iata.cargo.model.Value;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationObjectTest {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

//    @Test
//    public void testCreateOperationObjectWithValue() throws JsonProcessingException {
//        OperationObject o = new OperationObject();
//        o.setDatatype("https://onerecord.iata.org/Value");
//
//        Value v = new Value();
//        v.setUnit("KGM");
//        v.setValue(2.0);
//
//        o.setObject(v);
//        o.setValue(jacksonObjectMapper.writeValueAsString(v));
//
//        String jsonInputString = jacksonObjectMapper.writeValueAsString(o);
//
//        assertThat(jsonInputString, containsString("KGM"));
//    }
//
//    @Test
//    public void testCreateOperationObjectWithPiece() throws JsonProcessingException {
//        OperationObject o = new OperationObject();
//        o.setDatatype("https://onerecord.iata.org/Piece");
//
//        Piece p = new Piece();
//        p.setCoload(true);
//        p.setGoodsDescription("SPARE PARTS");
//
//        o.setObject(p);
//        o.setValue(jacksonObjectMapper.writeValueAsString(p));
//
//        String jsonInputString = jacksonObjectMapper.writeValueAsString(o);
//
//        assertThat(jsonInputString, containsString("SPARE"));
//    }
//
//    @Test
//    public void testDeserializeOperationObjectWithValue() throws JsonProcessingException {
//        String jsonInputString = "{\"@id\":\"_:1890993962\",\"@type\":[\"https://onerecord.iata.org/api/OperationObject\"],\"https://onerecord.iata.org/api/OperationObject#datatype\":\"https://onerecord.iata.org/Value\",\"https://onerecord.iata.org/api/OperationObject#value\":\"{\\\"@id\\\":\\\"_:2129581654\\\",\\\"@type\\\":[\\\"https://onerecord.iata.org/Value\\\"],\\\"https://onerecord.iata.org/Value#value\\\":2.0,\\\"https://onerecord.iata.org/Value#unit\\\":\\\"KGM\\\"}\"}\n";
//        OperationObject o = jacksonObjectMapper.readValue(jsonInputString, OperationObject.class);
//        if (o.getValue() != null) {
//            o.setObject(jacksonObjectMapper.readValue(o.getValue(), Value.class));
//        }
//
//        assertEquals(o.getObject().getClass().getSimpleName(),"Value");
//        assertEquals(Optional.ofNullable(((Value) o.getObject()).getValue()),Optional.ofNullable(2.0));
//    }
//    @Test
//    public void testDeserializeOperationObjectWithPiece() throws JsonProcessingException {
//        String jsonInputString = "{\"@id\":\"_:1081151379\",\"@type\":[\"https://onerecord.iata.org/api/OperationObject\"],\"https://onerecord.iata.org/api/OperationObject#datatype\":\"https://onerecord.iata.org/Piece\",\"https://onerecord.iata.org/api/OperationObject#value\":\"{\\\"@id\\\":\\\"_:2031215079\\\",\\\"@type\\\":[\\\"https://onerecord.iata.org/Piece\\\",\\\"https://onerecord.iata.org/LogisticsObject\\\"],\\\"https://onerecord.iata.org/Piece#coload\\\":true,\\\"https://onerecord.iata.org/Piece#goodsDescription\\\":\\\"SPARE PARTS\\\"}\"}";
//        OperationObject o = jacksonObjectMapper.readValue(jsonInputString, OperationObject.class);
//        if (o.getValue() != null) {
//            Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages("org.iata"));
//
//            Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
//            Class valueClass = Piece.class;
//            for (Class c : classes) {
//                System.out.println(c.getSimpleName());
//                Annotation logisticsObjectClassAnnotation = Arrays.stream(c.getDeclaredAnnotations()).filter(a -> a.annotationType() == OWLClass.class).findAny().orElse(null);
//                if (logisticsObjectClassAnnotation != null && logisticsObjectClassAnnotation.annotationType() == OWLClass.class && ((OWLClass) logisticsObjectClassAnnotation).iri().equals(o.getDatatype())) {
//                    valueClass =  c;
//                }
//            }
//            if (valueClass != null) {
//                o.setObject(jacksonObjectMapper.readValue(o.getValue(), valueClass));
//            }
//        }
//
//        assertEquals(o.getObject().getClass().getSimpleName(),"Piece");
//        assertEquals(((Piece) o.getObject()).getGoodsDescription(), "SPARE PARTS");
//    }
}
