package org.gitlab4j.api.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum for the various Job status values.
 */
public enum JobStatus {

    RUNNING, PENDING, SUCCESS, FAILED, CANCELED, SKIPPED;

    private static Map<String, JobStatus> valuesMap = new HashMap<>(6);
    static {
        for (JobStatus status : JobStatus.values())
            valuesMap.put(status.toValue(), status);
    }

    @JsonCreator
    public static JobStatus forValue(String value) {
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