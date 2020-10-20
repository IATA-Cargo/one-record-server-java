package org.iata.model.enums;

import org.iata.cargo.Vocabulary;

/**
 * Enum containing all the existing types of Logistics Objects
 */
public enum TopicEnum {

  WAYBILL (Vocabulary.s_c_Waybill),
  SHIPMENT (Vocabulary.s_c_Shipment),
  BOOKING (Vocabulary.s_c_Booking),
  ULD (Vocabulary.s_c_ULD),
  ITEM (Vocabulary.s_c_Item),
  INSURANCE (Vocabulary.s_c_Insurance),
  PRODUCT (Vocabulary.s_c_Product),
  CHARACTERISTICS (Vocabulary.s_c_Characteristics),
  CARRIER_PRODUCT (Vocabulary.s_c_CarrierProduct),
  PIECE (Vocabulary.s_c_Piece);

  private final String className;

  TopicEnum(String className) {
    this.className = className;
  }

  public String getTopic() {
    return this.className;
  }

}
