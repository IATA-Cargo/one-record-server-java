package org.iata.util;

import org.iata.model.enums.LogisticsObjectType;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Stream;

public class StringToEnumConverter implements Converter<String, LogisticsObjectType> {
    @Override
    public LogisticsObjectType convert(String source) {
        try {
            return Stream.of(LogisticsObjectType.values()).filter(targetEnum -> targetEnum.getLogisticsObjectTypeIRI().equals(source)).findFirst().orElse(null);
        } catch(Exception e) {
            return null; // or SortEnum.asc
        }
    }
}