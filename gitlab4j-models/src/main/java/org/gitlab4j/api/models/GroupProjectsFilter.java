package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.Constants.ProjectOrderBy;
import org.gitlab4j.models.Constants.SortOrder;
import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  This class is used to filter Projects when getting lists of projects for a specified group.
 */
public class GroupProjectsFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Indicates if the project is archived.
     */
    @JsonProperty("archived")
    private Boolean archived;

    /**
     * The visibility level of the project.
     */
    @JsonProperty("visibility")
    private Visibility visibility;

    /**
     * The ordering criteria for the project.
     */
    @JsonProperty("order_by")
    private ProjectOrderBy orderBy;

    /**
     * The sorting order for the project.
     */
    @JsonProperty("sort")
    private SortOrder sort;

    /**
     * A search string to filter projects by.
     */
    @JsonProperty("search")
    private String search;

    /**
     * Indicates if the project filter is simplified.
     */
    @JsonProperty("simple")
    private Boolean simple;

    /**
     * Indicates if the current user owns the project.
     */
    @JsonProperty("owned")
    private Boolean owned;

    /**
     * Indicates if the project is starred by the current user.
     */
    @JsonProperty("starred")
    private Boolean starred;

    /**
     * Indicates if the project has custom attributes.
     */
    @JsonProperty("with_custom_attributes")
    private Boolean withCustomAttributes;

    /**
     * Indicates if the project has issues enabled.
     */
    @JsonProperty("with_issues_enabled")
    private Boolean withIssuesEnabled;

    /**
     * Indicates if the project has merge requests enabled.
     */
    @JsonProperty("with_merge_requests_enabled")
    private Boolean withMergeRequestsEnabled;

    /**
     * Indicates if the project should include shared projects.
     */
    @JsonProperty("with_shared")
    private Boolean withShared;

    /**
     * Indicates if the project includes subgroups.
     */
    @JsonProperty("include_subgroups")
    private Boolean includeSubGroups;

    /**
     * Limit by archived status.
     *
     * @param archived if true will only return archived projects
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withArchived(Boolean archived) {
        this.archived = archived;
        return (this);
    }

    /**
     * Limit by visibility public, internal, or private.
     *
     * @param visibility the visibility to match
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return (this);
    }

    /**
     * Return projects ordered by id, name, path, created_at, updated_at, or last_activity_at fields. Default is created_at.
     *
     * @param orderBy specifies what field to order by
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withOrderBy(ProjectOrderBy orderBy) {
        this.orderBy = orderBy;
        return (this);
    }

    /**
     * Return projects sorted in asc or desc order. Default is desc.
     *
     * @param sort sort direction, ASC or DESC
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withSortOder(SortOrder sort) {
        this.sort = sort;
        return (this);
    }

    /**
     * Return list of projects matching the search criteria.
     *
     * @param search the search criteria
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withSearch(String search) {
        this.search = search;
        return (this);
    }

    /**
     * Return only limited fields for each project. This is a no-op without
     * authentication as then only simple fields are returned.
     *
     * @param simple if true, return only limited fields for each project
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withSimple(Boolean simple) {
        this.simple = simple;
        return (this);
    }

    /**
     * Limit by projects explicitly owned by the current user
     *
     * @param owned if true, limit to projects explicitly owned by the current user
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withOwned(Boolean owned) {
        this.owned = owned;
        return (this);
    }

    /**
     * Limit by projects starred by the current user.
     *
     * @param starred if true, limit by projects starred by the current user
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withStarred(Boolean starred) {
        this.starred = starred;
        return (this);
    }

    /**
     *  Include custom attributes in response (admins only).
     *
     * @param withCustomAttributes if true, include custom attributes in the repsonse
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withCustomAttributes(Boolean withCustomAttributes) {
        this.withCustomAttributes = withCustomAttributes;
        return (this);
    }

    /**
     * Limit by enabled issues feature
     *
     * @param withIssuesEnabled if true, limit by enabled issues feature
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withIssuesEnabled(Boolean withIssuesEnabled) {
        this.withIssuesEnabled = withIssuesEnabled;
        return (this);
    }

    /**
     * Limit by enabled merge requests feature
     *
     * @param withMergeRequestsEnabled if true, imit by enabled merge requests feature
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withMergeRequestsEnabled(Boolean withMergeRequestsEnabled) {
        this.withMergeRequestsEnabled = withMergeRequestsEnabled;
        return (this);
    }

    /**
     * Include projects that are located in subgroups
     *
     * @param includeSubGroups if true, projects from subgroups will be included
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withIncludeSubGroups(Boolean includeSubGroups) {
        this.includeSubGroups = includeSubGroups;
        return (this);
    }

    /**
     * Include projects that are shared with this group
     *
     * @param withShared if true, projects that are shared with this group will be included
     * @return the reference to this ProjectFilter instance
     */
    public GroupProjectsFilter withShared(Boolean withShared) {
        this.withShared = withShared;
        return (this);
    }

    /**
     * Get the query params specified by this filter.
     *
     * @return a GitLabApiForm instance holding the query parameters for this ProjectFilter instance
     */
    public GitLabForm getQueryParams() {
        return (new GitLabForm()
                .withParam("archived", archived)
                .withParam("visibility", visibility)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("search", search)
                .withParam("simple", simple)
                .withParam("owned", owned)
                .withParam("starred", starred)
                .withParam("with_custom_attributes", withCustomAttributes)
                .withParam("with_issues_enabled", withIssuesEnabled)
                .withParam("with_merge_requests_enabled ", withMergeRequestsEnabled)
                .withParam("with_shared", withShared)
                .withParam("include_subgroups", includeSubGroups));
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
