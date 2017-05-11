package org.gitlab4j.api.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Visibility {

    PUBLIC, PRIVATE, INTERNAL;

    private static Map<String, Visibility> valuesMap = new HashMap<>(3);
    static {
        for (Visibility visibility : Visibility.values())
            valuesMap.put(visibility.toValue(), visibility);
    }

    @JsonCreator
    public static Visibility forValue(String value) {
        return valuesMap.get(value);
    }

    @JsonValue
    public String toValue() {
        return (name().toLowerCase());
    }

    @Override
    public String toString() {
        return (name().toLowerCase());
    }
}
