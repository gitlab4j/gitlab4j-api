package org.gitlab4j.api.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum for the various Pipeline status values.
 */
public enum PipelineStatus {

    RUNNING, PENDING, SUCCESS, FAILED, CANCELED, SKIPPED, MANUAL;

    private static Map<String, PipelineStatus> valuesMap = new HashMap<>(6);
    static {
        for (PipelineStatus status : PipelineStatus.values())
            valuesMap.put(status.toValue(), status);
    }

    @JsonCreator
    public static PipelineStatus forValue(String value) {
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