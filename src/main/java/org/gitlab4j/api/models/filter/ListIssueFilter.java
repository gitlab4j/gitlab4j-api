package org.gitlab4j.api.models.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import org.gitlab4j.api.Constants;
import org.gitlab4j.api.Constants.IssueOrderBy;
import org.gitlab4j.api.Constants.IssueScope;
import org.gitlab4j.api.Constants.IssueState;
import org.gitlab4j.api.Constants.SortOrder;
import org.gitlab4j.api.GitLabApiForm;
import org.gitlab4j.api.utils.ISO8601;

/**
 * Created by zhengrenjie on 2018/09/11 12:31
 *
 *  This class is used to filter issues when getting lists of them.
 */
public class ListIssueFilter {

    /**
     * Return only the milestone having the given iid.
     */
    private List<String> iids;

    /**
     * {@link org.gitlab4j.api.Constants.IssueState} Return all issues or just those that are opened or closed.
     */
    private IssueState state;

    /**
     * Comma-separated list of label names, issues must have all labels to be returned. No+Label lists all issues with no labels.
     */
    private String labels;

    /**
     * The milestone title. No+Milestone lists all issues with no milestone.
     */
    private String milestone;

    /**
     * {@link org.gitlab4j.api.Constants.IssueScope} Return issues for the given scope: created_by_me, assigned_to_me or all. For versions before 11.0, use the now deprecated created-by-me or assigned-to-me scopes instead.
     */
    private IssueScope scope;

    /**
     * Return issues created by the given user id.
     */
    private Integer authorId;

    /**
     * Return issues assigned to the given user id.
     */
    private Integer assigneeId;

    /**
     * Return issues reacted by the authenticated user by the given emoji.
     */
    private String myReactionEmoji;

    /**
     * {@link org.gitlab4j.api.Constants.IssueOrderBy} Return issues ordered by created_at or updated_at fields. Default is created_at.
     */
    private IssueOrderBy orderBy;

    /**
     * {@link org.gitlab4j.api.Constants.SortOrder} Return issues sorted in asc or desc order. Default is desc.
     */
    private SortOrder sort;

    /**
     * Search project issues against their title and description.
     */
    private String search;

    /**
     * Return issues created on or after the given time.
     */
    private Date createdAfter;

    /**
     * Return issues created on or before the given time.
     */
    private Date createdBefore;

    /**
     * Return issues updated on or after the given time.
     */
    private Date updatedAfter;

    /**
     * Return issues updated on or before the given time.
     */
    private Date updatedBefore;


    /*- properties -*/
    public List<String> getIids() {
        return iids;
    }

    public void setIids(List<String> iids) {
        this.iids = iids;
    }

    public IssueState getState() {
        return state;
    }

    public void setState(IssueState state) {
        this.state = state;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public IssueScope getScope() {
        return scope;
    }

    public void setScope(IssueScope scope) {
        this.scope = scope;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getMyReactionEmoji() {
        return myReactionEmoji;
    }

    public void setMyReactionEmoji(String myReactionEmoji) {
        this.myReactionEmoji = myReactionEmoji;
    }

    public IssueOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(IssueOrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public SortOrder getSort() {
        return sort;
    }

    public void setSort(SortOrder sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Date getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
    }

    public Date getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Date createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Date getUpdatedAfter() {
        return updatedAfter;
    }

    public void setUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    public Date getUpdatedBefore() {
        return updatedBefore;
    }

    public void setUpdatedBefore(Date updatedBefore) {
        this.updatedBefore = updatedBefore;
    }

    /*- constructor -*/
    public ListIssueFilter() {}

    @JsonIgnore
    private ListIssueFilter(Builder builder) {
        this.iids            = builder.iids;
        this.state           = builder.state;
        this.labels          = builder.labels;
        this.milestone       = builder.milestone;
        this.scope           = builder.scope;
        this.authorId        = builder.authorId;
        this.assigneeId      = builder.assigneeId;
        this.myReactionEmoji = builder.myReactionEmoji;
        this.orderBy         = builder.orderBy;
        this.sort            = builder.sort;
        this.search          = builder.search;
        this.createdAfter    = builder.createdAfter;
        this.createdBefore   = builder.createdBefore;
        this.updatedAfter    = builder.updatedAfter;
        this.updatedBefore   = builder.updatedBefore;
    }

    /*- params generator -*/
    @JsonIgnore
    public GitLabApiForm getQueryParams(int page, int perPage) {
        return (getQueryParams()
                .withParam(Constants.PAGE_PARAM, page)
                .withParam(Constants.PER_PAGE_PARAM, perPage));
    }

    @JsonIgnore
    public GitLabApiForm getQueryParams() {
        return (new GitLabApiForm()
                .withParam("iids", iids)
                .withParam("state", state)
                .withParam("labels", labels)
                .withParam("milestone", milestone)
                .withParam("scope", scope)
                .withParam("author_id", authorId)
                .withParam("assignee_id", assigneeId)
                .withParam("my_reaction_emoji", myReactionEmoji)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("search", search)
                .withParam("created_after", ISO8601.toString(createdAfter, false))
                .withParam("created_before", ISO8601.toString(createdBefore, false))
                .withParam("updated_after", ISO8601.toString(updatedAfter, false))
                .withParam("updated_before", ISO8601.toString(updatedBefore, false)));
    }

    /*- builder -*/
    public static class Builder {

        private List<String> iids;
        private IssueState state;
        private String labels;
        private String milestone;
        private IssueScope scope;
        private Integer authorId;
        private Integer assigneeId;
        private String myReactionEmoji;
        private IssueOrderBy orderBy;
        private SortOrder sort;
        private String search;
        private Date createdAfter;
        private Date createdBefore;
        private Date updatedAfter;
        private Date updatedBefore;

        /*- filters -*/
        public Builder withIids(List<String> iids) {
            this.iids = iids;
            return (this);
        }

        public Builder withState(IssueState state) {
            this.state = state;
            return (this);
        }

        public Builder withLabels(String labels) {
            this.labels = labels;
            return (this);
        }

        public Builder withMilestone(String milestone) {
            this.milestone = milestone;
            return (this);
        }

        public Builder withScope(IssueScope scope) {
            this.scope = scope;
            return (this);
        }

        public Builder withAuthorId(Integer authorId) {
            this.authorId = authorId;
            return (this);
        }

        public Builder withAssigneeId(Integer assigneeId) {
            this.assigneeId = assigneeId;
            return (this);
        }

        public Builder withMyReactionEmoji(String myReactionEmoji) {
            this.myReactionEmoji = myReactionEmoji;
            return (this);
        }

        public Builder withOrderBy(IssueOrderBy orderBy) {
            this.orderBy = orderBy;
            return (this);
        }

        public Builder withSort(SortOrder sort) {
            this.sort = sort;
            return (this);
        }

        public Builder withSearch(String search) {
            this.search = search;
            return (this);
        }

        public Builder withCreatedAfter(Date createdAfter) {
            this.createdAfter = createdAfter;
            return (this);
        }

        public Builder withCreatedBefore(Date createdBefore) {
            this.createdBefore = createdBefore;
            return (this);
        }

        public Builder withUpdatedAfter(Date updatedAfter) {
            this.updatedAfter = updatedAfter;
            return (this);
        }

        public Builder withUpdatedBefore(Date updatedBefore) {
            this.updatedBefore = updatedBefore;
            return (this);
        }

        public ListIssueFilter build() {
            return new ListIssueFilter(this);
        }
    }
}
