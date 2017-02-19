package org.gitlab4j.api.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccessLevel {

    NONE(0), GUEST(10), REPORTER(20), DEVELOPER(30), MASTER(40), OWNER(50);

    public final int value;

    AccessLevel(int value) {
        this.value = value;
    }

    private static Map<Integer, AccessLevel> valuesMap = new HashMap<Integer, AccessLevel>(6);
    static {
        for (AccessLevel accessLevel : AccessLevel.values())
            valuesMap.put(accessLevel.value, accessLevel);
    }

    @JsonCreator
    public static AccessLevel forValue(Integer value) {
        return valuesMap.get(value);
    }

    @JsonValue
    public Integer toValue() {
        return (value);
    }
}
