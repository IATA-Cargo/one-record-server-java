package org.iata.model.enums;

import org.iata.cargo.Vocabulary;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Enum containing all the existing types of LogisticsObjects
 */
public enum LogisticsObjectType {

    WAYBILL(Vocabulary.s_c_Waybill),
    SHIPMENT(Vocabulary.s_c_Shipment),
    BOOKING(Vocabulary.s_c_Booking),
//    ULD(Vocabulary.s_c_ULD),
    ITEM(Vocabulary.s_c_Item),
    INSURANCE(Vocabulary.s_c_Insurance),
    PRODUCT(Vocabulary.s_c_Product),
//    CHARACTERISTICS(Vocabulary.s_c_Characteristics),
    CARRIER_PRODUCT(Vocabulary.s_c_CarrierProduct),
    PIECE(Vocabulary.s_c_Piece),
    SENSOR(Vocabulary.s_c_Sensor),
    IOTDEVICE(Vocabulary.s_c_IotDevice),
    TRANSPORTMOVEMENT(Vocabulary.s_c_TransportMovement),
    TRANSPORTMEANS(Vocabulary.s_c_TransportMeans)
    ;

    private final String iri;

    LogisticsObjectType(String iri) {
        this.iri = iri;
    }

    public String getLogisticsObjectTypeIRI() {
        return this.iri;
    }
}
