package org.gitlab4j.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Milestone;
import org.gitlab4j.api.models.Note;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.SearchBlob;
import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.User;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

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
        ACCESS,
        OAUTH2_ACCESS,
        JOB_TOKEN,
        PRIVATE;
    }

    /** Enum to specify encoding of file contents. */
    public enum Encoding {
        TEXT,
        BASE64;

        private static JacksonJsonEnumHelper<Encoding> enumHelper = new JacksonJsonEnumHelper<>(Encoding.class);

        @JsonCreator
        public static Encoding forValue(String value) {
            return enumHelper.forValue((value != null ? value.toLowerCase() : value));
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

    /** Enum to use for ordering the results of various API calls. */
    public enum SortOrder {
        ASC,
        DESC;

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
        CREATED_AT,
        UPDATED_AT;

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
        CREATED_AT,
        UPDATED_AT;

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

    /** Enum to use for ordering the results of getPackages(). */
    public enum PackageOrderBy {
        NAME,
        CREATED_AT,
        VERSION,
        TYPE,
        PROJECT_PATH;

        private static JacksonJsonEnumHelper<PackageOrderBy> enumHelper =
                new JacksonJsonEnumHelper<>(PackageOrderBy.class);

        @JsonCreator
        public static PackageOrderBy forValue(String value) {
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

    /** Enum to use for filtering the results of getPackages(). */
    public enum PackageStatus {
        DEFAULT,
        HIDDEN,
        PROCESSING;

        private static JacksonJsonEnumHelper<PackageStatus> enumHelper =
                new JacksonJsonEnumHelper<>(PackageStatus.class);

        @JsonCreator
        public static PackageStatus forValue(String value) {
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
        ID,
        NAME,
        PATH,
        CREATED_AT,
        UPDATED_AT,
        LAST_ACTIVITY_AT;
        private static JacksonJsonEnumHelper<ProjectOrderBy> enumHelper =
                new JacksonJsonEnumHelper<>(ProjectOrderBy.class);

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
        ID,
        STATUS,
        REF,
        UPDATED_AT,
        USER_ID;

        private static JacksonJsonEnumHelper<PipelineOrderBy> enumHelper =
                new JacksonJsonEnumHelper<>(PipelineOrderBy.class);

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
        CREATED_AT,
        UPDATED_AT;

        private static JacksonJsonEnumHelper<MergeRequestOrderBy> enumHelper =
                new JacksonJsonEnumHelper<>(MergeRequestOrderBy.class);

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
        NAME,
        PATH,
        ID,
        SIMILARITY;
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

    /** Enum to use for ordering the results of getTags(). */
    public enum TagOrderBy {
        NAME,
        UPDATED;
        private static JacksonJsonEnumHelper<TagOrderBy> enumHelper = new JacksonJsonEnumHelper<>(TagOrderBy.class);

        @JsonCreator
        public static TagOrderBy forValue(String value) {
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

    /** Enum to use for ordering the results of getDeployments. */
    public static enum DeploymentOrderBy {
        ID,
        IID,
        CREATED_AT,
        UPDATED_AT,
        REF;
        private static JacksonJsonEnumHelper<DeploymentOrderBy> enumHelper =
                new JacksonJsonEnumHelper<>(DeploymentOrderBy.class);

        @JsonCreator
        public static DeploymentOrderBy forValue(String value) {
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

    /** Enum to use for ordering the results of getContibutors(). */
    public enum ContributorOrderBy {
        NAME,
        EMAIL,
        COMMITS;

        private static JacksonJsonEnumHelper<ContributorOrderBy> enumHelper =
                new JacksonJsonEnumHelper<>(ContributorOrderBy.class);

        @JsonCreator
        public static ContributorOrderBy forValue(String value) {
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
        RUNNING,
        PENDING,
        FINISHED,
        BRANCHES,
        TAGS;

        private static JacksonJsonEnumHelper<PipelineScope> enumHelper =
                new JacksonJsonEnumHelper<>(PipelineScope.class);

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

    /** Enum to use for specifying the source when calling getPipelines(). */
    public enum PipelineSource {
        PUSH,
        WEB,
        TRIGGER,
        SCHEDULE,
        API,
        EXTERNAL,
        PIPELINE,
        CHAT,
        WEBIDE,
        MERGE_REQUEST_EVENT,
        EXTERNAL_PULL_REQUEST_EVENT;

        private static JacksonJsonEnumHelper<PipelineSource> enumHelper =
                new JacksonJsonEnumHelper<>(PipelineSource.class);

        @JsonCreator
        public static PipelineSource forValue(String value) {
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
        CREATED,
        PENDING,
        RUNNING,
        FAILED,
        SUCCESS,
        CANCELED,
        SKIPPED,
        MANUAL;

        private static JacksonJsonEnumHelper<JobScope> enumHelper = new JacksonJsonEnumHelper<>(JobScope.class);

        @JsonCreator
        public static JobScope forValue(String value) {
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

    /** Enum to use for specifying the scope when calling the various get issue methods. */
    public enum IssueScope {
        CREATED_BY_ME,
        ASSIGNED_TO_ME,
        ALL;

        private static JacksonJsonEnumHelper<IssueScope> enumHelper = new JacksonJsonEnumHelper<>(IssueScope.class);

        @JsonCreator
        public static IssueScope forValue(String value) {
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

    /** Enum to use for specifying the scope for getMergeRequests methods. */
    public enum MergeRequestScope {
        CREATED_BY_ME,
        ASSIGNED_TO_ME,
        ALL;

        private static JacksonJsonEnumHelper<MergeRequestScope> enumHelper =
                new JacksonJsonEnumHelper<>(MergeRequestScope.class);

        @JsonCreator
        public static MergeRequestScope forValue(String value) {
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

    /** Enum to use for querying the state of a MergeRequest */
    public enum MergeRequestState {
        OPENED,
        CLOSED,
        LOCKED,
        MERGED,
        ALL;

        private static JacksonJsonEnumHelper<MergeRequestState> enumHelper =
                new JacksonJsonEnumHelper<>(MergeRequestState.class);

        @JsonCreator
        public static MergeRequestState forValue(String value) {
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

    /** Enum to use for specifying the scope of the search attribute when calling getMergeRequests(). */
    public enum MergeRequestSearchIn {
        TITLE,
        DESCRIPTION;

        private static JacksonJsonEnumHelper<MergeRequestSearchIn> enumHelper =
                new JacksonJsonEnumHelper<>(MergeRequestSearchIn.class);

        @JsonCreator
        public static MergeRequestSearchIn forValue(String value) {
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

    /** Enum to use for specifying the state of a merge request or issue update. */
    public enum StateEvent {
        CLOSE,
        REOPEN;

        private static JacksonJsonEnumHelper<StateEvent> enumHelper = new JacksonJsonEnumHelper<>(StateEvent.class);

        @JsonCreator
        public static StateEvent forValue(String value) {
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

    /** Enum to used to store the state of an issue. */
    public enum IssueState {
        OPENED,
        CLOSED,
        REOPENED;

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
        ACTIVE,
        CLOSED,
        ACTIVATE,
        CLOSE;

        private static JacksonJsonEnumHelper<MilestoneState> enumHelper =
                new JacksonJsonEnumHelper<>(MilestoneState.class);

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
        CREATED,
        UPDATED,
        OPENED,
        CLOSED,
        REOPENED,
        PUSHED,
        COMMENTED,
        MERGED,
        JOINED,
        LEFT,
        DESTROYED,
        EXPIRED,
        REMOVED,
        DELETED,
        APPROVED,
        ACCEPTED,
        IMPORTED;

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
        ISSUE,
        MILESTONE,
        MERGE_REQUEST,
        NOTE,
        PROJECT,
        SNIPPET,
        USER;

        private static JacksonJsonEnumHelper<TargetType> enumHelper =
                new JacksonJsonEnumHelper<>(TargetType.class, true, false, true);

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
        OLD,
        NEW;

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
        ALL,
        ACTIVE,
        INACTIVE;

        private static JacksonJsonEnumHelper<ImpersonationState> enumHelper =
                new JacksonJsonEnumHelper<>(ImpersonationState.class);

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
        BZ2,
        TAR,
        TAR_BZ2,
        TAR_GZ,
        TB2,
        TBZ,
        TBZ2,
        ZIP;

        private final String value;

        ArchiveFormat() {
            this.value = name().toLowerCase().replace('_', '.');
        }

        private static Map<String, ArchiveFormat> valuesMap = new HashMap<String, ArchiveFormat>(8);

        static {
            for (ArchiveFormat archiveFormat : ArchiveFormat.values())
                valuesMap.put(archiveFormat.value, archiveFormat);
        }

        public static ArchiveFormat forValue(String value) {

            if (value == null || value.trim().isEmpty()) {
                return (null);
            }

            ArchiveFormat archiveFormat = valuesMap.get(value);
            if (archiveFormat != null) {
                return (archiveFormat);
            }

            throw new IllegalArgumentException(
                    "Invalid format! Options are tar.gz, tar.bz2, tbz, tbz2, tb2, bz2, tar, and zip.");
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
        PENDING,
        RUNNING,
        SUCCESS,
        FAILED,
        CANCELED,
        SKIPPED;

        private static JacksonJsonEnumHelper<CommitBuildState> enumHelper =
                new JacksonJsonEnumHelper<>(CommitBuildState.class);

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

    /**
     * Enum for the various Application scope values.
     */
    public enum ApplicationScope {

        /**  Access the authenticated user's API */
        API,

        /** Read the authenticated user's personal information */
        READ_USER,

        /**  Perform API actions as any user in the system */
        SUDO,

        /** Allows read-access to the repository */
        READ_REPOSITORY,

        /** Authenticate using OpenID Connect */
        OPENID,

        /** Allows read-only access to the user's personal information using OpenID Connect */
        PROFILE,

        /** Allows read-only access to the user's primary email address using OpenID Connect */
        EMAIL;

        private static JacksonJsonEnumHelper<ApplicationScope> enumHelper =
                new JacksonJsonEnumHelper<>(ApplicationScope.class);

        @JsonCreator
        public static ApplicationScope forValue(String value) {
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

    /**
     * Enum for the search scope when doing a globalSearch() with the SearchApi.
     */
    public static class SearchScope<T> {

        private final String jsonName;
        private final Class<T> resultType;

        private SearchScope(String jsonName, Class<T> resultType) {
            this.jsonName = jsonName;
            this.resultType = resultType;
        }

        public Class<T> getResultType() {
            return resultType;
        }

        public static final SearchScope<Project> PROJECTS = new SearchScope<>("projects", Project.class);
        public static final SearchScope<Issue> ISSUES = new SearchScope<>("issues", Issue.class);
        public static final SearchScope<MergeRequest> MERGE_REQUESTS =
                new SearchScope<>("merge_requests", MergeRequest.class);
        public static final SearchScope<Milestone> MILESTONES = new SearchScope<>("milestones", Milestone.class);
        public static final SearchScope<Snippet> SNIPPET_TITLES = new SearchScope<>("snippet_titles", Snippet.class);
        public static final SearchScope<Snippet> SNIPPET_BLOBS = new SearchScope<>("snippet_blobs", Snippet.class);
        public static final SearchScope<User> USERS = new SearchScope<>("users", User.class);
        public static final SearchScope<SearchBlob> BLOBS = new SearchScope<>("blobs", SearchBlob.class);
        public static final SearchScope<Commit> COMMITS = new SearchScope<>("commits", Commit.class);
        public static final SearchScope<SearchBlob> WIKI_BLOBS = new SearchScope<>("wiki_blobs", SearchBlob.class);

        private static final Map<String, SearchScope<?>> jsonLookup = Arrays.stream(new SearchScope[] {
                    PROJECTS,
                    ISSUES,
                    MERGE_REQUESTS,
                    MILESTONES,
                    SNIPPET_TITLES,
                    SNIPPET_BLOBS,
                    USERS,
                    BLOBS,
                    COMMITS,
                    WIKI_BLOBS
                })
                .collect(Collectors.toMap(searchScope -> searchScope.jsonName, Function.identity()));

        @JsonCreator
        @SuppressWarnings("unchecked")
        public static <T> SearchScope<T> forValue(String value) {
            return (SearchScope<T>) jsonLookup.get(value);
        }

        public static Set<String> values() {
            return jsonLookup.keySet();
        }

        @JsonValue
        public String toValue() {
            return jsonName;
        }

        @Override
        public String toString() {
            return jsonName;
        }
    }

    /**
     * Enum for the search scope when doing a groupSearch() with the SearchApi.
     */
    public static class GroupSearchScope<T> {

        private final String jsonName;
        private final Class<T> resultType;

        public GroupSearchScope(String jsonName, Class<T> resultType) {
            this.jsonName = jsonName;
            this.resultType = resultType;
        }

        public Class<T> getResultType() {
            return resultType;
        }

        public static final GroupSearchScope<Project> PROJECTS = new GroupSearchScope<>("projects", Project.class);
        public static final GroupSearchScope<Issue> ISSUES = new GroupSearchScope<>("issues", Issue.class);
        public static final GroupSearchScope<MergeRequest> MERGE_REQUESTS =
                new GroupSearchScope<>("merge_requests", MergeRequest.class);
        public static final GroupSearchScope<Milestone> MILESTONES =
                new GroupSearchScope<>("milestones", Milestone.class);
        public static final GroupSearchScope<SearchBlob> WIKI_BLOBS =
                new GroupSearchScope<>("wiki_blobs", SearchBlob.class);
        public static final GroupSearchScope<Commit> COMMITS = new GroupSearchScope<>("commits", Commit.class);
        public static final GroupSearchScope<SearchBlob> BLOBS = new GroupSearchScope<>("blobs", SearchBlob.class);
        public static final GroupSearchScope<Note> NOTES = new GroupSearchScope<>("notes", Note.class);
        public static final GroupSearchScope<User> USERS = new GroupSearchScope<>("users", User.class);

        private static final Map<String, GroupSearchScope<?>> jsonLookup = Arrays.stream(new GroupSearchScope[] {
                    PROJECTS, ISSUES, MERGE_REQUESTS, MILESTONES, WIKI_BLOBS, COMMITS, BLOBS, NOTES, USERS,
                })
                .collect(Collectors.toMap(searchScope -> searchScope.jsonName, Function.identity()));

        @JsonCreator
        @SuppressWarnings("unchecked")
        public static <T> GroupSearchScope<T> forValue(String value) {
            return (GroupSearchScope<T>) jsonLookup.get(value);
        }

        public static Set<String> values() {
            return jsonLookup.keySet();
        }

        @JsonValue
        public String toValue() {
            return jsonName;
        }

        @Override
        public String toString() {
            return jsonName;
        }
    }

    /**
     * Enum for the search scope when doing a projectSearch() with the SearchApi.
     */
    public static class ProjectSearchScope<T> {

        private final String jsonName;
        private final Class<T> resultType;

        public ProjectSearchScope(String jsonName, Class<T> resultType) {
            this.jsonName = jsonName;
            this.resultType = resultType;
        }

        public Class<T> getResultType() {
            return resultType;
        }

        public static final ProjectSearchScope<SearchBlob> BLOBS = new ProjectSearchScope<>("blobs", SearchBlob.class);
        public static final ProjectSearchScope<Commit> COMMITS = new ProjectSearchScope<>("commits", Commit.class);
        public static final ProjectSearchScope<Issue> ISSUES = new ProjectSearchScope<>("issues", Issue.class);
        public static final ProjectSearchScope<MergeRequest> MERGE_REQUESTS =
                new ProjectSearchScope<>("merge_requests", MergeRequest.class);
        public static final ProjectSearchScope<Milestone> MILESTONES =
                new ProjectSearchScope<>("milestones", Milestone.class);
        public static final ProjectSearchScope<Note> NOTES = new ProjectSearchScope<>("notes", Note.class);
        public static final ProjectSearchScope<SearchBlob> WIKI_BLOBS =
                new ProjectSearchScope<>("wiki_blobs", SearchBlob.class);
        public static final ProjectSearchScope<User> USERS = new ProjectSearchScope<>("users", User.class);

        private static final Map<String, ProjectSearchScope<?>> jsonLookup = Arrays.stream(new ProjectSearchScope[] {
                    BLOBS, COMMITS, ISSUES, MERGE_REQUESTS, MILESTONES, NOTES, WIKI_BLOBS, USERS,
                })
                .collect(Collectors.toMap(searchScope -> searchScope.jsonName, Function.identity()));

        @JsonCreator
        @SuppressWarnings("unchecked")
        public static <T> ProjectSearchScope<T> forValue(String value) {
            return (ProjectSearchScope<T>) jsonLookup.get(value);
        }

        public static Set<String> values() {
            return jsonLookup.keySet();
        }

        @JsonValue
        public String toValue() {
            return jsonName;
        }

        @Override
        public String toString() {
            return jsonName;
        }
    }

    /** Enum to use for specifying the action when doing a getTodos() with the TodosApi. */
    public enum TodoAction {
        ASSIGNED,
        MENTIONED,
        BUILD_FAILED,
        MARKED,
        APPROVAL_REQUIRED,
        UNMERGEABLE,
        DIRECTLY_ADDRESSED;

        private static JacksonJsonEnumHelper<TodoAction> enumHelper = new JacksonJsonEnumHelper<>(TodoAction.class);

        @JsonCreator
        public static TodoAction forValue(String value) {
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

    /** Enum to use for specifying the state when doing a getTodos() with the TodosApi. */
    public enum TodoState {
        PENDING,
        DONE;

        private static JacksonJsonEnumHelper<TodoState> enumHelper = new JacksonJsonEnumHelper<>(TodoState.class);

        @JsonCreator
        public static TodoState forValue(String value) {
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

    /** Enum to use for specifying the type when doing a getTodos() with the TodosApi. */
    public enum TodoType {
        ISSUE,
        MERGE_REQUEST;

        private static JacksonJsonEnumHelper<TodoType> enumHelper =
                new JacksonJsonEnumHelper<>(TodoType.class, true, true);

        @JsonCreator
        public static TodoType forValue(String value) {
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

    /** Enum to use for specifying the status of a deployment. */
    public enum DeploymentStatus {
        /**
         * After some tests, {@link #CREATED} value is not a valid value.
         */
        CREATED,
        RUNNING,
        SUCCESS,
        FAILED,
        CANCELED;

        private static JacksonJsonEnumHelper<DeploymentStatus> enumHelper =
                new JacksonJsonEnumHelper<>(DeploymentStatus.class);

        @JsonCreator
        public static DeploymentStatus forValue(String value) {
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

    /** Enum to use for specifying the deploy token scope. */
    public enum DeployTokenScope {
        READ_REPOSITORY,
        READ_REGISTRY;

        private static JacksonJsonEnumHelper<DeployTokenScope> enumHelper =
                new JacksonJsonEnumHelper<>(DeployTokenScope.class);

        @JsonCreator
        public static DeployTokenScope forValue(String value) {
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

    /** Enum to use for specifying the project token scope. */
    public enum ProjectAccessTokenScope {
        API,
        READ_API,
        READ_REGISTRY,
        WRITE_REGISTRY,
        READ_REPOSITORY,
        WRITE_REPOSITORY,
        CREATE_RUNNER;

        private static JacksonJsonEnumHelper<ProjectAccessTokenScope> enumHelper =
                new JacksonJsonEnumHelper<>(ProjectAccessTokenScope.class);

        @JsonCreator
        public static ProjectAccessTokenScope forValue(String value) {
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

    /** Enum for the build_git_strategy of the project instance. */
    enum SquashOption {
        NEVER,
        ALWAYS,
        DEFAULT_ON,
        DEFAULT_OFF;

        private static JacksonJsonEnumHelper<SquashOption> enumHelper = new JacksonJsonEnumHelper<>(SquashOption.class);

        @JsonCreator
        public static SquashOption forValue(String value) {
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

    /** Enum for the build_git_strategy of the project instance. */
    enum BuildGitStrategy {
        FETCH,
        CLONE;

        private static JacksonJsonEnumHelper<BuildGitStrategy> enumHelper =
                new JacksonJsonEnumHelper<>(BuildGitStrategy.class);

        @JsonCreator
        public static BuildGitStrategy forValue(String value) {
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

    enum AutoDevopsDeployStrategy {
        CONTINUOUS,
        MANUAL,
        TIMED_INCREMENTAL;

        private static JacksonJsonEnumHelper<AutoDevopsDeployStrategy> enumHelper =
                new JacksonJsonEnumHelper<>(AutoDevopsDeployStrategy.class);

        @JsonCreator
        public static AutoDevopsDeployStrategy forValue(String value) {
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

    /** Enum to use for specifying the Event scope. */
    public enum EventScope {
        ALL;

        private static JacksonJsonEnumHelper<EventScope> enumHelper = new JacksonJsonEnumHelper<>(EventScope.class);

        @JsonCreator
        public static EventScope forValue(String value) {
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

    /**
     * Constant to specify the project_creation_level for the group.
     */
    public enum ProjectCreationLevel {
        NOONE,
        DEVELOPER,
        MAINTAINER;

        private static JacksonJsonEnumHelper<ProjectCreationLevel> enumHelper =
                new JacksonJsonEnumHelper<>(ProjectCreationLevel.class);

        @JsonCreator
        public static ProjectCreationLevel forValue(String value) {
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

    /**
     * Constant to specify the subgroup_creation_level for the group.
     */
    public enum SubgroupCreationLevel {
        OWNER,
        MAINTAINER;

        private static JacksonJsonEnumHelper<SubgroupCreationLevel> enumHelper =
                new JacksonJsonEnumHelper<>(SubgroupCreationLevel.class);

        @JsonCreator
        public static SubgroupCreationLevel forValue(String value) {
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

    public enum DefaultBranchProtectionLevel {
        NOT_PROTECTED(0),
        PARTIALLY_PROTECTED(1),
        FULLY_PROTECTED(2),
        PROTECTED_AGAINST_PUSHES(3),
        FULL_PROTECTION_AFTER_INITIAL_PUSH(4);

        @JsonValue
        private final int value;

        private DefaultBranchProtectionLevel(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }
}
