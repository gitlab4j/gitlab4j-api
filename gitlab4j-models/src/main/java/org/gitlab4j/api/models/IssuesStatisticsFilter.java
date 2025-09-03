package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.Constants.IssueScope;
import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.ISO8601;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *  This class is used to filter issues when getting issue statistics. of them.
 */
public class IssuesStatisticsFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * List of labels to filter issues by.
     */
    @JsonProperty("labels")
    private List<String> labels;

    /**
     * The milestone associated with the issues.
     */
    @JsonProperty("milestone")
    private String milestone;

    /**
     * The scope of the issues, e.g., assigned, created by, etc.
     */
    @JsonProperty("scope")
    private IssueScope scope;

    /**
     * The ID of the author of the issues.
     */
    @JsonProperty("author_id")
    private Long authorId;

    /**
     * The ID of the assignee of the issues.
     */
    @JsonProperty("assignee_id")
    private Long assigneeId;

    /**
     * The emoji reaction to filter by for the issues.
     */
    @JsonProperty("my_reaction_emoji")
    private String myReactionEmoji;

    /**
     * List of issue IDs to filter by.
     */
    @JsonProperty("iids")
    private List<Long> iids;

    /**
     * A search string to filter issues by.
     */
    @JsonProperty("search")
    private String search;

    /**
     * The field in which fuzzy search should be performed with the search query.
     */
    @JsonProperty("in")
    private String in;

    /**
     * Return issues created after the specified date. Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_after")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAfter;

    /**
     * Return issues created before the specified date. Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_before")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdBefore;

    /**
     * Return issues updated after the specified date. Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("updated_after")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date updatedAfter;

    /**
     * Return issues updated before the specified date. Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("updated_before")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date updatedBefore;

    /**
     * Return only confidential issues.
     */
    @JsonProperty("confidential")
    private Boolean confidential;

    public IssuesStatisticsFilter withLabels(List<String> labels) {
        this.labels = labels;
        return (this);
    }

    public IssuesStatisticsFilter withIids(List<Long> iids) {
        this.iids = iids;
        return (this);
    }

    public IssuesStatisticsFilter withMilestone(String milestone) {
        this.milestone = milestone;
        return (this);
    }

    public IssuesStatisticsFilter withScope(IssueScope scope) {
        this.scope = scope;
        return (this);
    }

    public IssuesStatisticsFilter withAuthorId(Long authorId) {
        this.authorId = authorId;
        return (this);
    }

    public IssuesStatisticsFilter withAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
        return (this);
    }

    public IssuesStatisticsFilter withMyReactionEmoji(String myReactionEmoji) {
        this.myReactionEmoji = myReactionEmoji;
        return (this);
    }

    public IssuesStatisticsFilter withSearch(String search) {
        this.search = search;
        return (this);
    }

    public IssuesStatisticsFilter withIn(String in) {
        this.in = in;
        return (this);
    }

    public IssuesStatisticsFilter withCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
        return (this);
    }

    public IssuesStatisticsFilter withCreatedBefore(Date createdBefore) {
        this.createdBefore = createdBefore;
        return (this);
    }

    public IssuesStatisticsFilter withUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
        return (this);
    }

    public IssuesStatisticsFilter withUpdatedBefore(Date updatedBefore) {
        this.updatedBefore = updatedBefore;
        return (this);
    }

    public IssuesStatisticsFilter withConfidential(Boolean confidential) {
        this.confidential = confidential;
        return (this);
    }

    @JsonIgnore
    public GitLabForm getQueryParams() {

        return (new GitLabForm()
                .withParam("labels", (labels != null ? String.join(",", labels) : null))
                .withParam("iids", iids)
                .withParam("milestone", milestone)
                .withParam("scope", scope)
                .withParam("author_id", authorId)
                .withParam("assignee_id", assigneeId)
                .withParam("my_reaction_emoji", myReactionEmoji)
                .withParam("search", search)
                .withParam("in", in)
                .withParam("created_after", ISO8601.toString(createdAfter, false))
                .withParam("created_before", ISO8601.toString(createdBefore, false))
                .withParam("updated_after", ISO8601.toString(updatedAfter, false))
                .withParam("updated_before", ISO8601.toString(updatedBefore, false))
                .withParam("confidential", confidential));
    }
}
