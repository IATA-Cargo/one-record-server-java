package org.iata.util;

import org.iata.model.enums.Topic;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Stream;

public class StringToEnumConverter implements Converter<String, Topic> {
    @Override
    public Topic convert(String source) {
        try {
            return Stream.of(Topic.values()).filter(targetEnum -> targetEnum.getTopic().equals(source)).findFirst().orElse(null);
        } catch(Exception e) {
            return null; // or SortEnum.asc
        }
    }
}