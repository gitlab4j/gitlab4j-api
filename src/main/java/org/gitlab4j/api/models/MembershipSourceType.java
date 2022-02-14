package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum MembershipSourceType {
    PROJECT("Project"),

    /** Representing a group */
    NAMESPACE("Namespace");

    public final String name;

    MembershipSourceType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static MembershipSourceType forValue(String value) {
        if (value == null) {
            return null;
        } else {
            return MembershipSourceType.valueOf(value.toUpperCase(Locale.ROOT));
        }
    }

    @JsonValue
    public String toValue() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
