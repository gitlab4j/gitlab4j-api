package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MembershipSourceType {

    PROJECT,

    /** Representing a group */
    NAMESPACE;

    private static JacksonJsonEnumHelper<MembershipSourceType> enumHelper = new JacksonJsonEnumHelper<>(MembershipSourceType.class);

    @JsonCreator
    public static MembershipSourceType forValue(String value) {
        return enumHelper.forValue(value);
    }

    @JsonValue
    public String toValue() {
        return (enumHelper.toString(this));
    }

    @Override
    public String toString() {
        return (enumHelper.toString(this));
    }
}
