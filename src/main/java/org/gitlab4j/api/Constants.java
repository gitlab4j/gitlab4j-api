package org.gitlab4j.api;

import java.util.HashMap;
import java.util.Map;

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

    /** Used to specify the type of authentication token. */
    public enum TokenType {
        ACCESS, OAUTH2_ACCESS, PRIVATE;
    }

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


    /** Enum to use for ordering the results of getEpics(). */
    public enum EpicOrderBy {

        CREATED_AT, UPDATED_AT;

        private static JacksonJsonEnumHelper<EpicOrderBy> enumHelper = new JacksonJsonEnumHelper<>(EpicOrderBy.class);

        @JsonCreator
        public static EpicOrderBy forValue(String value) {
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

    /** Enum to use for ordering the results of getIssues(). */
    public enum IssueOrderBy {

        CREATED_AT, UPDATED_AT;

        private static JacksonJsonEnumHelper<IssueOrderBy> enumHelper = new JacksonJsonEnumHelper<>(IssueOrderBy.class);

        @JsonCreator
        public static IssueOrderBy forValue(String value) {
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

        ID, NAME, PATH, CREATED_AT, UPDATED_AT, LAST_ACTIVITY_AT;
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

    /** Enum to use for ordering the results of getMergeRequests(). */
    public enum MergeRequestOrderBy {

        CREATED_AT, UPDATED_AT;

        private static JacksonJsonEnumHelper<MergeRequestOrderBy> enumHelper = new JacksonJsonEnumHelper<>(MergeRequestOrderBy.class);

        @JsonCreator
        public static MergeRequestOrderBy forValue(String value) {
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

    /** Enum to use for ordering the results of getGroups() and getSubGroups(). */
    public enum GroupOrderBy {

        NAME, PATH, ID;
        private static JacksonJsonEnumHelper<GroupOrderBy> enumHelper = new JacksonJsonEnumHelper<>(GroupOrderBy.class);

        @JsonCreator
        public static GroupOrderBy forValue(String value) {
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

    /** Enum to use for specifying the scope when calling the various get issue methods. */
    public enum IssueScope {

        CREATED_BY_ME, ASSIGNED_TO_ME, ALL;

        private static JacksonJsonEnumHelper<IssueScope> enumHelper = new JacksonJsonEnumHelper<>(IssueScope.class);

        @JsonCreator
        public static IssueScope forValue(String value) { return enumHelper.forValue(value); }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /** Enum to use for specifying the scope for getMergeRequests methods. */
    public enum MergeRequestScope {

        CREATED_BY_ME, ASSIGNED_TO_ME, ALL;

        private static JacksonJsonEnumHelper<MergeRequestScope> enumHelper = new JacksonJsonEnumHelper<>(MergeRequestScope.class);

        @JsonCreator
        public static MergeRequestScope forValue(String value) { return enumHelper.forValue(value); }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /** Enum to use for querying the state of a MergeRequest */
    public enum MergeRequestState {

        OPENED, CLOSED, MERGED, ALL;

        private static JacksonJsonEnumHelper<MergeRequestState> enumHelper = new JacksonJsonEnumHelper<>(MergeRequestState.class);

        @JsonCreator
        public static MergeRequestState forValue(String value) { return enumHelper.forValue(value); }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /** Enum to use for specifying the state of a merge request or issue update. */
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

    /** Enum to used to store the state of an issue. */
    public enum IssueState {

        OPENED, CLOSED, REOPENED;

        private static JacksonJsonEnumHelper<IssueState> enumHelper = new JacksonJsonEnumHelper<>(IssueState.class);

        @JsonCreator
        public static IssueState forValue(String value) {
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

    public enum MilestoneState {

        ACTIVE, CLOSED, ACTIVATE, CLOSE;

        private static JacksonJsonEnumHelper<MilestoneState> enumHelper = new JacksonJsonEnumHelper<>(MilestoneState.class);

        @JsonCreator
        public static MilestoneState forValue(String value) {
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

    /** Enum to use for specifying the event action_type. */
    public enum ActionType {

        CREATED, UPDATED, CLOSED, REOPENED, PUSHED, COMMENTED, MERGED, JOINED, LEFT, DESTROYED, EXPIRED, REMOVED;

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

    /** Enum to use for specifying the line type for a commit comment. */
    public enum LineType {

        OLD, NEW;

        private static JacksonJsonEnumHelper<LineType> enumHelper = new JacksonJsonEnumHelper<>(LineType.class);

        @JsonCreator
        public static LineType forValue(String value) {
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

    /** Enum to specify the state of an ImpersonationToken. */
    public enum ImpersonationState {

        ALL, ACTIVE, INACTIVE;

        private static JacksonJsonEnumHelper<ImpersonationState> enumHelper = new JacksonJsonEnumHelper<>(ImpersonationState.class);

        @JsonCreator
        public static ImpersonationState forValue(String value) {
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

    /** Enum to specify the format of a downloaded archive. */
    public enum ArchiveFormat {

        BZ2, TAR, TAR_BZ2, TAR_GZ, TB2, TBZ, TBZ2, ZIP;

        private final String value;

        ArchiveFormat() {
            this.value = name().toLowerCase().replace('_', '.');
        }

        private static Map<String, ArchiveFormat> valuesMap = new HashMap<String, ArchiveFormat>(8);
        static {
            for (ArchiveFormat archiveFormat : ArchiveFormat.values())
                valuesMap.put(archiveFormat.value, archiveFormat);
        }

        public static ArchiveFormat forValue(String value) throws GitLabApiException {

            if (value == null || value.trim().isEmpty()) {
                return (null);
            }

            ArchiveFormat archiveFormat = valuesMap.get(value);
            if (archiveFormat != null) {
                return (archiveFormat);
            }

            throw new GitLabApiException("Invalid format! Options are tar.gz, tar.bz2, tbz, tbz2, tb2, bz2, tar, and zip.");
        }

        @Override
        public String toString() {
            return (value);
        }
    }

    /**
     * Enum for the various Commit build status values.
     */
    public enum CommitBuildState {

        PENDING, RUNNING, SUCCESS, FAILED, CANCELED;

        private static JacksonJsonEnumHelper<CommitBuildState> enumHelper = new JacksonJsonEnumHelper<>(CommitBuildState.class);

        @JsonCreator
        public static CommitBuildState forValue(String value) {
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
