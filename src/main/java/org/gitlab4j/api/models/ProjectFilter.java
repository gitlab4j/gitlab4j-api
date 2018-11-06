package org.gitlab4j.api.models;

import org.gitlab4j.api.Constants.ProjectOrderBy;
import org.gitlab4j.api.Constants.SortOrder;
import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApiForm;

/**
 *  This class is used to filter Projects when getting lists of projects for a specified user.
 */
public class ProjectFilter {

    private Boolean archived;
    private Visibility visibility;
    private ProjectOrderBy orderBy;
    private SortOrder sort;
    private String search;
    private Boolean simple;
    private Boolean owned;
    private Boolean membership;
    private Boolean starred;
    private Boolean statistics;
    private Boolean withCustomAttributes;
    private Boolean withIssuesEnabled;
    private Boolean withMergeRequestsEnabled;
    private AccessLevel minAccessLevel;

    /**
     * Limit by archived status.
     *
     * @param archived if true will only return archived projects
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withArchived(Boolean archived) {
        this.archived = archived;
        return (this);  
    }

    /**
     * Limit by visibility public, internal, or private.
     *
     * @param visibility the visibility to match
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return (this);       
    }

    /**
     * Return projects ordered by id, name, path, created_at, updated_at, or last_activity_at fields. Default is created_at.
     *
     * @param orderBy specifies what field to order by
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withOrderBy(ProjectOrderBy orderBy) {
        this.orderBy = orderBy;
        return (this);
    }

    /**
     * Return projects sorted in asc or desc order. Default is desc.
     *
     * @param sort sort direction, ASC or DESC
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withSortOder(SortOrder sort) {
        this.sort = sort;
        return (this);
    }

    /**
     * Return list of projects matching the search criteria.
     *
     * @param search the search criteria
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withSearch(String search) {
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
    public ProjectFilter withSimple(Boolean simple) {
        this.simple = simple;
        return (this);
    }

    /**
     * Limit by projects explicitly owned by the current user
     *
     * @param owned if true, limit to projects explicitly owned by the current user
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withOwned(Boolean owned) {
        this.owned = owned;
        return (this);
    }

    /**
     * Limit by projects that the current user is a member of
     *
     * @param membership if true, limit by projects that the current user is a member of
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withMembership(Boolean membership) {
        this.membership = membership;
        return (this);
    }

    /**
     * Limit by projects starred by the current user.
     *
     * @param starred if true, limit by projects starred by the current user
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withStarred(Boolean starred) {
        this.starred = starred;
        return (this);
    }

    /**
     * Include project statistics.
     *
     * @param statistics if true, include project statistics
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withStatistics(Boolean statistics) {
        this.statistics = statistics;
        return (this);
    }

    /**
     *  Include custom attributes in response (admins only).
     * 
     * @param withCustomAttributes if true, include custom attributes in the repsonse
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withCustomAttributes(Boolean withCustomAttributes) {
        this.withCustomAttributes = withCustomAttributes;
        return (this);
    }

    /**
     * Limit by enabled issues feature
     *
     * @param withIssuesEnabled if true, limit by enabled issues feature
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withIssuesEnabled(Boolean withIssuesEnabled) {
        this.withIssuesEnabled = withIssuesEnabled;
        return (this);
    }

    /**
     * Limit by enabled merge requests feature
     *
     * @param withMergeRequestsEnabled if true, imit by enabled merge requests feature
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter withMergeRequestsEnabled(Boolean withMergeRequestsEnabled) {
        this.withMergeRequestsEnabled = withMergeRequestsEnabled;
        return (this);
    }

    /**
     * Limit by current user minimal access level
     *
     * @param minAccessLevel limit by current user minimal access level
     * @return the reference to this ProjectFilter instance
     */
    public ProjectFilter minAccessLevel(AccessLevel minAccessLevel) {
        this.minAccessLevel = minAccessLevel;
        return (this);
    }

    /**
     * Get the query params specified by this filter.
     *
     * @param page specifies the page number
     * @param perPage specifies the number of items per page
     * @return a GitLabApiForm instance holding the query parameters for this ProjectFilter instance
     */
    public GitLabApiForm getQueryParams(int page, int perPage) {
        return (getQueryParams()
                .withParam(Constants.PAGE_PARAM, page)
                .withParam(Constants.PER_PAGE_PARAM, perPage));
    }

    /**
     * Get the query params specified by this filter.
     *
     * @return a GitLabApiForm instance holding the query parameters for this ProjectFilter instance
     */
    public GitLabApiForm getQueryParams() {
        return (new GitLabApiForm()
            .withParam("archived", archived)
            .withParam("visibility", visibility)
            .withParam("order_by", orderBy)
            .withParam("sort", sort)
            .withParam("search", search)
            .withParam("simple", simple)
            .withParam("owned", owned)
            .withParam("membership", membership)
            .withParam("starred", starred)
            .withParam("statistics", statistics)
            .withParam("with_custom_attributes", withCustomAttributes)
            .withParam("with_issues_enabled", withIssuesEnabled)
            .withParam("with_merge_requests_enabled ", withMergeRequestsEnabled))
            .withParam("min_access_level ", (minAccessLevel != null ? minAccessLevel.toValue() : null)
        );
    }
}
