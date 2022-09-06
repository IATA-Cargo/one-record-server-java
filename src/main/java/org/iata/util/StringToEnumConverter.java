package org.iata.util;

import org.iata.model.enums.TopicEnum;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Stream;

public class StringToEnumConverter implements Converter<String, TopicEnum> {
    @Override
    public TopicEnum convert(String source) {
        try {
            return Stream.of(TopicEnum.values()).filter(targetEnum -> targetEnum.getTopic().equals(source)).findFirst().orElse(null);
        } catch(Exception e) {
            return null; // or SortEnum.asc
        }
    }
}