package org.gitlab4j.api;

import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface Constants {

    /** Enum to use for ordering the results of various API calls. */
    public enum SortOrder {

        ASC, DESC;

        private static JacksonJsonEnumHelper<SortOrder> enumHelper = new JacksonJsonEnumHelper<>(SortOrder.class);

        @JsonCreator
        public static SortOrder forValue(String value) {
            return enumHelper.forValue(value);
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

    /** Enum to use for ordering the results of getProjects(). */
    public enum ProjectOrderBy {

        ID, NAME, PATH, CREATED_AT, UPDATED_AT, LAST_ACTIVITY;
        private static JacksonJsonEnumHelper<ProjectOrderBy> enumHelper = new JacksonJsonEnumHelper<>(ProjectOrderBy.class);

        @JsonCreator
        public static ProjectOrderBy forValue(String value) {
            return enumHelper.forValue(value);
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

    /** Enum to use for ordering the results of getPipelines(). */
    public enum PipelineOrderBy {

        ID, STATUS, REF, USER_ID;

        private static JacksonJsonEnumHelper<PipelineOrderBy> enumHelper = new JacksonJsonEnumHelper<>(PipelineOrderBy.class);

        @JsonCreator
        public static PipelineOrderBy forValue(String value) {
            return enumHelper.forValue(value);
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

    /** Enum to use for specifying the scope when calling getPipelines(). */
    public enum PipelineScope {

        RUNNING, PENDING, FINISHED, BRANCHES, TAGS;

        private static JacksonJsonEnumHelper<PipelineScope> enumHelper = new JacksonJsonEnumHelper<>(PipelineScope.class);

        @JsonCreator
        public static PipelineScope forValue(String value) {
            return enumHelper.forValue(value);
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
}
