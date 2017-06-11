package org.gitlab4j.api.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

public class JacksonJsonEnumHelper<E extends Enum<E>> {

    private Map<String, E> valuesMap;

    public JacksonJsonEnumHelper(Class<E> enumType) {
        valuesMap = new HashMap<>();
        for (E e : enumType.getEnumConstants())
            valuesMap.put(e.name().toLowerCase(), e);
    }

    @JsonCreator
    public E forValue(String value) {
        return valuesMap.get(value);
    }
}