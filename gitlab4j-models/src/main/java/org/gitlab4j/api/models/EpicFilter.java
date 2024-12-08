package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.gitlab4j.api.models.AbstractEpic.EpicState;
import org.gitlab4j.models.Constants.EpicOrderBy;
import org.gitlab4j.models.Constants.SortOrder;
import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.ISO8601;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *  This class is used to filter Groups when getting lists of epics.
 */
public class EpicFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The author ID for filtering epic issues.
     */
    @JsonProperty("author_id")
    private Long authorId;

    /**
     * The username of the author for filtering epic issues.
     */
    @JsonProperty("author_username")
    private String authorUsername;

    /**
     * The labels associated with epic issues.
     */
    @JsonProperty("labels")
    private String labels;

    /**
     * The ordering criteria for the epics.
     */
    @JsonProperty("order_by")
    private EpicOrderBy orderBy;

    /**
     * The sorting order for the epics.
     */
    @JsonProperty("sort")
    private SortOrder sort;

    /**
     * The search string to filter epics.
     */
    @JsonProperty("search")
    private String search;

    /**
     * The state of the epic.
     */
    @JsonProperty("state")
    private EpicState state;

    /**
     * The date after which the epic was created.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("created_after")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAfter;

    /**
     * The date after which the epic was updated.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("updated_after")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAfter;

    /**
     * The date before which the epic was updated.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("updated_before")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedBefore;

    /**
     * Indicates if ancestor groups should be included in the filter.
     */
    @JsonProperty("include_ancestor_groups")
    private Boolean includeAncestorGroups;

    /**
     * Indicates if descendant groups should be included in the filter.
     */
    @JsonProperty("include_descendant_groups")
    private Boolean includeDescendantGroups;

    /**
     * The emoji reaction by the authenticated user to filter epics.
     */
    @JsonProperty("my_reaction_emoji")
    private String myReactionEmoji;

    /**
     * A map of epic fields and values to exclude from the filter.
     */
    @JsonProperty("not")
    private Map<EpicField, Object> not;

    public enum EpicField {
        AUTHOR_ID,
        AUTHOR_USERNAME,
        LABELS;

        private static JacksonJsonEnumHelper<EpicField> enumHelper = new JacksonJsonEnumHelper<>(EpicField.class);

        @JsonCreator
        public static EpicField forValue(String value) {
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
     * Add 'author id' filter.
     *
     * @param authorId the author id filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withAuthorId(Long authorId) {
        this.authorId = authorId;
        return (this);
    }

    /**
     * Add 'author username' filter.
     *
     * @param authorUsername the 'author username' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return (this);
    }

    /**
     * Add 'labels' filter.
     *
     * @param labels the labels filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withLabels(String labels) {
        this.labels = labels;
        return (this);
    }

    /**
     * Add 'order by' filter.
     *
     * @param orderBy the 'order by' filter
     * @return the reference to this GroupFilter instance
     */
    public EpicFilter withOrderBy(EpicOrderBy orderBy) {
        this.orderBy = orderBy;
        return (this);
    }

    /**
     * Add 'sort' filter.
     *
     * @param sort sort direction, ASC or DESC
     * @return the reference to this GroupFilter instance
     */
    public EpicFilter withSortOrder(SortOrder sort) {
        this.sort = sort;
        return (this);
    }

    /**
     * Add 'search' filter.
     *
     * @param search the 'search' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withSearch(String search) {
        this.search = search;
        return (this);
    }

    /**
     * Add 'state' filter.
     *
     * @param state the 'state' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withState(EpicState state) {
        this.state = state;
        return (this);
    }

    /**
     * Add 'created after' filter.
     *
     * @param createdAfter the 'created after' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
        return (this);
    }

    /**
     * Add 'updated after' filter.
     *
     * @param updatedAfter the 'updated after' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
        return (this);
    }

    /**
     * Add 'updated before' filter.
     *
     * @param updatedBefore the 'updated before' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withUpdatedBefore(Date updatedBefore) {
        this.updatedBefore = updatedBefore;
        return (this);
    }

    /**
     * Add 'include ancestor groups' filter.
     *
     * @param includeAncestorGroups the 'include ancestor groups' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withIncludeAncestorGroups(Boolean includeAncestorGroups) {
        this.includeAncestorGroups = includeAncestorGroups;
        return (this);
    }

    /**
     * Add 'include descendant groups' filter.
     *
     * @param includeDescendantGroups the 'include descendant groups' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withIncludeDescendantGroups(Boolean includeDescendantGroups) {
        this.includeDescendantGroups = includeDescendantGroups;
        return (this);
    }

    /**
     * Add 'my reaction emoji' filter.
     *
     * @param myReactionEmoji the 'my reaction emoji' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withMyReactionEmoji(String myReactionEmoji) {
        this.myReactionEmoji = myReactionEmoji;
        return (this);
    }

    /**
     * Add 'not' filter.
     *
     * @param not the 'not' filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withNot(Map<EpicField, Object> not) {
        this.not = not;
        return (this);
    }

    /**
     * Add author_id to the 'not' filter entry.
     *
     * @param authorId the id of the author to add to the filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withoutAuthorId(Long authorId) {
        return withNot(EpicField.AUTHOR_ID, authorId);
    }

    /**
     * Add author_username to the 'not' filter entry.
     *
     * @param authorUsername the username of the author to add to the filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withoutAuthorUsername(String authorUsername) {
        return withNot(EpicField.AUTHOR_USERNAME, authorUsername);
    }

    /**
     * Add labels to the 'not' filter entry.
     *
     * @param labels the labels to add to the filter
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withoutLabels(String... labels) {
        return withNot(EpicField.LABELS, String.join(",", labels));
    }

    /**
     * Add 'not' filter entry.
     *
     * @param field the field to be added to the 'not' value
     * @param value the value for the entry
     * @return the reference to this EpicFilter instance
     */
    public EpicFilter withNot(EpicField field, Object value) {
        if (not == null) {
            not = new LinkedHashMap<>();
        }
        not.put(field, value);
        return (this);
    }

    /**
     * Get the query params specified by this filter.
     *
     * @return a GitLabApiForm instance holding the query parameters for this GroupFilter instance
     */
    public GitLabForm getQueryParams() {
        return (new GitLabForm()
                .withParam("author_id", authorId)
                .withParam("author_username", authorUsername)
                .withParam("labels", labels)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("search", search)
                .withParam("state", state)
                .withParam("created_after", ISO8601.toString(createdAfter, false))
                .withParam("updated_after", ISO8601.toString(updatedAfter, false))
                .withParam("updated_before", ISO8601.toString(updatedBefore, false))
                .withParam("include_ancestor_groups", includeAncestorGroups)
                .withParam("include_descendant_groups", includeDescendantGroups)
                .withParam("my_reaction_emoji", myReactionEmoji)
                .withParam("not", toStringMap(not), false));
    }

    private Map<String, Object> toStringMap(Map<EpicField, Object> map) {
        if (map == null) {
            return null;
        }
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<EpicField, Object> entry : map.entrySet()) {
            result.put(entry.getKey().toString(), entry.getValue());
        }
        return result;
    }
}
