package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class CommitRef implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The type of the reference (e.g., branch, tag).
     */
    @JsonProperty("type")
    private RefType type;

    /**
     * The name of the reference (e.g., branch name, tag name).
     */
    @JsonProperty("name")
    private String name;

    public enum RefType {
        BRANCH,
        TAG,
        ALL;

        private static JacksonJsonEnumHelper<RefType> enumHelper = new JacksonJsonEnumHelper<>(RefType.class);

        @JsonCreator
        public static RefType forValue(String value) {
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

    public RefType getType() {
        return type;
    }

    public void setType(RefType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
