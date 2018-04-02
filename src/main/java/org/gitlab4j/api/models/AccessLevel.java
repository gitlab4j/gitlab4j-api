package org.gitlab4j.api.models;

import java.util.HashMap;
import java.util.Map;

import org.gitlab4j.api.GitLabApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccessLevel {

    INVALID(-1), NONE(0), GUEST(10), REPORTER(20), DEVELOPER(30), MASTER(40), OWNER(50);

    public final Integer value;

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

        AccessLevel level = valuesMap.get(value);
        if (level != null) {
            return (level);
        }

        GitLabApi.getLogger().warning(String.format("[%d] is not a valid GitLab access level.", value));
        return (value == null ? null : INVALID);
    }

    @JsonValue
    public Integer toValue() {
        return (value);
    }

    @Override
    public String toString() {
        return (value.toString());
    }
}
