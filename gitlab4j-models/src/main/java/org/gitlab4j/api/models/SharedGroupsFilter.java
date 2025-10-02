package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.Constants.GroupOrderBy;
import org.gitlab4j.models.Constants.SortOrder;
import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.JacksonJson;

/**
 *  This class is used to filter Groups when getting lists of groups for a specified group where it has been invited.
 */
public class SharedGroupsFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Long> skipGroups;
    private String search;
    private GroupOrderBy orderBy;
    private SortOrder sort;
    private Visibility visibility;
    private AccessLevel minAccessLevel;
    private Boolean withCustomAttributes;

    /**
     * Do not include the provided groups IDs.
     *
     * @param skipGroups List of group IDs to not include in the search
     * @return the reference to this SharedGroupsFilter instance
     */
    public SharedGroupsFilter withSkipGroups(List<Long> skipGroups) {
        this.skipGroups = skipGroups;
        return (this);
    }

    /**
     * Return list of groups matching the search criteria.
     *
     * @param search the search criteria
     * @return the reference to this SharedGroupsFilter instance
     */
    public SharedGroupsFilter withSearch(String search) {
        this.search = search;
        return (this);
    }

    /**
     * Return groups ordered by name, path, id, or similarity. Default is name.
     *
     * @param orderBy specifies what field to order by
     * @return the reference to this SharedGroupsFilter instance
     */
    public SharedGroupsFilter withOrderBy(GroupOrderBy orderBy) {
        this.orderBy = orderBy;
        return (this);
    }

    /**
     * Return groups sorted in asc or desc order. Default is desc.
     *
     * @param sort sort direction, ASC or DESC
     * @return the reference to this SharedGroupsFilter instance
     */
    public SharedGroupsFilter withSortOder(SortOrder sort) {
        this.sort = sort;
        return (this);
    }

    /**
     * Limit by visibility public, internal, or private.
     *
     * @param visibility the visibility to match
     * @return the reference to this SharedGroupsFilter instance
     */
    public SharedGroupsFilter withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return (this);
    }

    /**
     * Limit to groups where current user has at least the specified role (access_level).
     *
     * @param minAccessLevel the minimum access level to match
     * @return the reference to this SharedGroupsFilter instance
     */
    public SharedGroupsFilter withMinAccessLevel(AccessLevel minAccessLevel) {
        this.minAccessLevel = minAccessLevel;
        return (this);
    }

    /**
     *  Include custom attributes in response (admins only).
     *
     * @param withCustomAttributes if true, include custom attributes in the repsonse
     * @return the reference to this SharedGroupsFilter instance
     */
    public SharedGroupsFilter withCustomAttributes(Boolean withCustomAttributes) {
        this.withCustomAttributes = withCustomAttributes;
        return (this);
    }

    /**
     * Get the query params specified by this filter.
     *
     * @return a GitLabApiForm instance holding the query parameters for this SharedGroupsFilter instance
     */
    public GitLabForm getQueryParams() {
        return (new GitLabForm()
                .withParam("skip_groups", skipGroups)
                .withParam("search", search)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("visibility", visibility)
                .withParam("simple", minAccessLevel)
                .withParam("with_custom_attributes", withCustomAttributes));
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
