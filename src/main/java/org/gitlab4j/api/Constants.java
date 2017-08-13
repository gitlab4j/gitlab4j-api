package org.gitlab4j.api;

import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface Constants {

    /** The total number of items HTTP header key. */
    public static final String TOTAL_HEADER = "X-Total";

    /** The total number of pages HTTP header key. */
    public static final String TOTAL_PAGES_HEADER = "X-Total-Pages";

    /** The number of items per page HTTP header key. */
    public static final String PER_PAGE = "X-Per-Page";

    /** The index of the current page (starting at 1) HTTP header key. */
    public static final String PAGE_HEADER = "X-Page";

    /** The index of the next page HTTP header key. */
    public static final String NEXT_PAGE_HEADER = "X-Next-Page";

    /** The index of the previous page HTTP header key. */
    public static final String PREV_PAGE_HEADER = "X-Prev-Page";

    /** Items per page param HTTP header key. */
    public static final String PER_PAGE_PARAM = "per_page";

    /** Page param HTTP header key. */
    public static final String PAGE_PARAM = "page";

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
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
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
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
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
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
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
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /** Enum to use for specifying the scope when calling getJobs(). */
    public enum JobScope {

        CREATED, PENDING, RUNNING, FAILED, SUCCESS, CANCELED, SKIPPED, MANUAL;

        private static JacksonJsonEnumHelper<JobScope> enumHelper = new JacksonJsonEnumHelper<>(JobScope.class);

        @JsonCreator
        public static JobScope forValue(String value) { return enumHelper.forValue(value); }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /** Enum to use for specifying the state of a merge request update. */
    public enum StateEvent {

        CLOSE, REOPEN;

        private static JacksonJsonEnumHelper<StateEvent> enumHelper = new JacksonJsonEnumHelper<>(StateEvent.class);

        @JsonCreator
        public static StateEvent forValue(String value) { return enumHelper.forValue(value); }


        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /** Enum to use for specifying the event action_type. */
    public enum ActionType {

        CREATED, UPDATED, CLOSED, REOPENED, PUSHED, COMMENTED, MERGED, JOINED, LEFT, DESTROYED, EXPIRED;

        private static JacksonJsonEnumHelper<ActionType> enumHelper = new JacksonJsonEnumHelper<>(ActionType.class);

        @JsonCreator
        public static ActionType forValue(String value) {
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

    /** Enum to use for specifying the event target_type. */
    public enum TargetType {

        ISSUE, MILESTONE, MERGE_REQUEST, NOTE, PROJECT, SNIPPET, USER;

        private static JacksonJsonEnumHelper<TargetType> enumHelper = new JacksonJsonEnumHelper<>(TargetType.class, true);

        @JsonCreator
        public static TargetType forValue(String value) {
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
}
