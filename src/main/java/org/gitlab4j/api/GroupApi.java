package org.gitlab4j.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.GroupProjectsFilter;
import org.gitlab4j.api.models.Visibility;

/**
 * This class implements the client side API for the GitLab groups calls.
 */
public class GroupApi extends AbstractApi {

    public GroupApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of groups. (As user: my groups, as admin: all groups)
     *
     * <pre><code>GitLab Endpoint: GET /groups</code></pre>
     *
     * @return the list of groups viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Group> getGroups() throws GitLabApiException {
        return (getGroups(getDefaultPerPage()).all());
    }

    /**
     * Get a list of groups (As user: my groups, as admin: all groups) and in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /groups</code></pre>
     *
     * @param page the page to get
     * @param perPage the number of Group instances per page
     * @return the list of groups viewable by the authenticated userin the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Group> getGroups(int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "groups");
        return (response.readEntity(new GenericType<List<Group>>() {}));
    }

    /**
     * Get a Pager of groups. (As user: my groups, as admin: all groups)
     *
     * <pre><code>GitLab Endpoint: GET /groups</code></pre>
     *
     * @param itemsPerPage the number of Group instances that will be fetched per page
     * @return the list of groups viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Group> getGroups(int itemsPerPage) throws GitLabApiException {
        return (new Pager<Group>(this, Group.class, itemsPerPage, null, "groups"));
    }

    /**
     * Get a Stream of groups. (As user: my groups, as admin: all groups)
     *
     * <pre><code>GitLab Endpoint: GET /groups</code></pre>
     *
     * @return a Stream of groups viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Group> getGroupsStream() throws GitLabApiException {
        return (getGroups(getDefaultPerPage()).stream());
    }

    /**
     * Get all groups that match your string in their name or path.
     *
     * @param search the group name or path search criteria
     * @return a List containing matching Group instances
     * @throws GitLabApiException if any exception occurs
     */
    public List<Group> getGroups(String search) throws GitLabApiException {
        return (getGroups(search, getDefaultPerPage()).all());
    }

    /**
     * Get all groups that match your string in their name or path.
     *
     * @param search the group name or path search criteria
     * @param page the page to get
     * @param perPage the number of Group instances per page
     * @return a List containing matching Group instances
     * @throws GitLabApiException if any exception occurs
     */
    public List<Group> getGroups(String search, int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search).withParam(PAGE_PARAM, page).withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "groups");
        return (response.readEntity(new GenericType<List<Group>>() {}));
    }

    /**
     * Get all groups that match your string in their name or path.
     *
     * @param search the group name or path search criteria
     * @param itemsPerPage the number of Group instances that will be fetched per page
     * @return a Pager containing matching Group instances
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Group> getGroups(String search, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search);
        return (new Pager<Group>(this, Group.class, itemsPerPage, formData.asMap(), "groups"));
    }

    /**
     * Get all groups that match your string in their name or path as a Stream.
     *
     * @param search the group name or path search criteria
     * @return a Stream containing matching Group instances
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Group> getGroupsStream(String search) throws GitLabApiException {
        return (getGroups(search, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of visible direct subgroups in this group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/subgroups</code></pre>
     *
     * @param groupId the group ID to get the sub groups for
     * @return a List&lt;Group&gt; containing the group's sub-groups
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public List<Group> getSubGroups(Integer groupId) throws GitLabApiException {
        return (getSubGroups(groupId, getDefaultPerPage()).all());
    }

    /**
     * Get a Pager of visible direct subgroups in this group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/subgroups</code></pre>
     *
     * @param groupId the group ID to get the sub groups for
     * @param itemsPerPage the number of Group instances that will be fetched per page
     * @return a Pager containing matching Group instances
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public Pager<Group> getSubGroups(Integer groupId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Group>(this, Group.class, itemsPerPage, null, "groups", groupId, "subgroups"));
    }

    /**
     * Get a Stream of visible direct subgroups in this group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/subgroups</code></pre>
     *
     * @param groupId the group ID to get the sub groups for
     * @return a Stream&lt;Group&gt; containing the group's sub-groups
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public Stream<Group> getSubGroupsStream(Integer groupId) throws GitLabApiException {
        return (getSubGroups(groupId, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of visible direct subgroups in this group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/subgroups</code></pre>
     *
     * @param groupId the group ID to get the sub groups for
     * @param skipGroups skip the group IDs passed
     * @param allAvailable show all the groups you have access to (defaults to false for authenticated users)
     * @param search return the list of authorized groups matching the search criteria
     * @param orderBy order groups by NAME or PATH. Default is NAME
     * @param sortOrder order groups in ASC or DESC order. Default is ASC
     * @param statistics include group statistics (admins only)
     * @param owned limit to groups owned by the current user
     * @return a List&lt;Group&gt; of the matching subgroups
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public List<Group> getSubGroups(Integer groupId, List<Integer> skipGroups, Boolean allAvailable, String search,
            GroupOrderBy orderBy, SortOrder sortOrder, Boolean statistics, Boolean owned) throws GitLabApiException {
        return (getSubGroups(groupId, skipGroups, allAvailable, search, orderBy, sortOrder, statistics, owned, getDefaultPerPage()).all());
    }

    /**
     * Get a list of visible direct subgroups in this group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/subgroups</code></pre>
     *
     * @param groupId the group ID to get the sub groups for
     * @param skipGroups skip the group IDs passed
     * @param allAvailable show all the groups you have access to (defaults to false for authenticated users)
     * @param search return the list of authorized groups matching the search criteria
     * @param orderBy order groups by NAME or PATH. Default is NAME
     * @param sortOrder order groups in ASC or DESC order. Default is ASC
     * @param statistics include group statistics (admins only)
     * @param owned limit to groups owned by the current user
     * @param page the page to get
     * @param perPage the number of Group instances per page
     * @return a List&lt;Group&gt; of the matching subgroups
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public List<Group> getSubGroups(Integer groupId, List<Integer> skipGroups, Boolean allAvailable, String search,
            GroupOrderBy orderBy, SortOrder sortOrder, Boolean statistics, Boolean owned,  int page, int perPage)
            throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("skip_groups", skipGroups)
                .withParam("all_available", allAvailable)
                .withParam("search", search)
                .withParam("order_by", orderBy)
                .withParam("sort_order", sortOrder)
                .withParam("statistics", statistics)
                .withParam("owned", owned)
                .withParam(PAGE_PARAM, page)
                .withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "groups", groupId, "subgroups");
        return (response.readEntity(new GenericType<List<Group>>() {}));
    }

    /**
     * Get a Pager of visible direct subgroups in this group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/subgroups</code></pre>
     *
     * @param groupId the group ID to get the sub groups for
     * @param skipGroups skip the group IDs passed
     * @param allAvailable show all the groups you have access to (defaults to false for authenticated users)
     * @param search return the list of authorized groups matching the search criteria
     * @param orderBy order groups by NAME or PATH. Default is NAME
     * @param sortOrder order groups in ASC or DESC order. Default is ASC
     * @param statistics include group statistics (admins only)
     * @param owned limit to groups owned by the current user
     * @param itemsPerPage the number of Group instances that will be fetched per page
     * @return a Pager containing matching Group instances
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public Pager<Group> getSubGroups(Integer groupId, List<Integer> skipGroups, Boolean allAvailable, String search,
            GroupOrderBy orderBy, SortOrder sortOrder, Boolean statistics, Boolean owned, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("skip_groups", skipGroups)
                .withParam("all_available", allAvailable)
                .withParam("search", search)
                .withParam("order_by", orderBy)
                .withParam("sort_order", sortOrder)
                .withParam("statistics", statistics)
                .withParam("owned", owned);
        return (new Pager<Group>(this, Group.class, itemsPerPage, formData.asMap(), "groups", groupId, "subgroups"));
    }

    /**
     * Get a Stream of visible direct subgroups in this group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/subgroups</code></pre>
     *
     * @param groupId the group ID to get the sub groups for
     * @param skipGroups skip the group IDs passed
     * @param allAvailable show all the groups you have access to (defaults to false for authenticated users)
     * @param search return the list of authorized groups matching the search criteria
     * @param orderBy order groups by NAME or PATH. Default is NAME
     * @param sortOrder order groups in ASC or DESC order. Default is ASC
     * @param statistics include group statistics (admins only)
     * @param owned limit to groups owned by the current user
     * @return a Stream&lt;Group&gt; of the matching subgroups
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public Stream<Group> getSubGroupsStream(Integer groupId, List<Integer> skipGroups, Boolean allAvailable, String search,
            GroupOrderBy orderBy, SortOrder sortOrder, Boolean statistics, Boolean owned) throws GitLabApiException {
        return (getSubGroups(groupId, skipGroups, allAvailable, search, orderBy, sortOrder, statistics, owned, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of projects belonging to the specified group ID and filter.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/projects</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param filter the GroupProjectsFilter instance holding the filter values for the query
     * @return a List containing Project instances that belong to the group and match the provided filter
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(Object groupIdOrPath, GroupProjectsFilter filter) throws GitLabApiException {
        return (getProjects(groupIdOrPath, filter, getDefaultPerPage()).all());
    }

    /**
     * Get a Pager of projects belonging to the specified group ID and filter.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/projects</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param filter the GroupProjectsFilter instance holding the filter values for the query
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager containing Project instances that belong to the group and match the provided filter
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getProjects(Object groupIdOrPath, GroupProjectsFilter filter, int itemsPerPage) throws GitLabApiException {
        GitLabApiForm formData = filter.getQueryParams();
        return (new Pager<Project>(this, Project.class, itemsPerPage, formData.asMap(), "groups", getGroupIdOrPath(groupIdOrPath), "projects"));
    }

    /**
     * Get a Stream of projects belonging to the specified group ID and filter.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/projects</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param filter the GroupProjectsFilter instance holding the filter values for the query
     * @return a Stream containing Project instances that belong to the group and match the provided filter
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getProjectsStream(Object groupIdOrPath, GroupProjectsFilter filter) throws GitLabApiException {
        return (getProjects(groupIdOrPath, filter, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of projects belonging to the specified group ID.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/projects</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @return a list of projects belonging to the specified group ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(Object groupIdOrPath) throws GitLabApiException {
        return (getProjects(groupIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects belonging to the specified group ID in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/projects</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param page the page to get
     * @param perPage the number of Project instances per page
     * @return a list of projects belonging to the specified group ID in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(Object groupIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "groups", getGroupIdOrPath(groupIdOrPath), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of projects belonging to the specified group ID.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/projects</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of projects belonging to the specified group ID
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getProjects(Object groupIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Project>(this, Project.class, itemsPerPage, null, "groups", getGroupIdOrPath(groupIdOrPath), "projects"));
    }

    /**
     * Get a Stream of projects belonging to the specified group ID.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/projects</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @return a Stream of projects belonging to the specified group ID
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getProjectsStream(Object groupIdOrPath) throws GitLabApiException {
        return (getProjects(groupIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get all details of a group.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @return the Group instance for the specified group path
     * @throws GitLabApiException if any exception occurs
     */
    public Group getGroup(Object groupIdOrPath) throws GitLabApiException {
      Response response = get(Response.Status.OK, null, "groups", getGroupIdOrPath(groupIdOrPath));
      return (response.readEntity(Group.class));
    }

    /**
     * Get all details of a group as an Optional instance.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @return the Group for the specified group path as an Optional instance
     */
    public Optional<Group> getOptionalGroup(Object groupIdOrPath) {
        try {
            return (Optional.ofNullable(getGroup(groupIdOrPath)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a new project group. Available only for users who can create groups.
     *
     * <pre><code>GitLab Endpoint: POST /groups</code></pre>
     *
     * @param name the name of the group to add
     * @param path the path for the group
     * @return the created Group instance
     * @throws GitLabApiException if any exception occurs
     */
    public Group addGroup(String name, String path) throws GitLabApiException {

        Form formData = new Form();
        formData.param("name", name);
        formData.param("path", path);
        Response response = post(Response.Status.CREATED, formData, "groups");
        return (response.readEntity(Group.class));
    }

    public Group addGroup(Group group) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("name", group.getName())
                .withParam("path", group.getPath())
                .withParam("description", group.getDescription())
                .withParam("visibility", group.getVisibility())
                .withParam("lfs_enabled", group.getLfsEnabled())
                .withParam("request_access_enabled", group.getRequestAccessEnabled())
                .withParam("parent_id", isApiVersion(ApiVersion.V3) ? null : group.getParentId());
        Response response = post(Response.Status.CREATED, formData, "groups");
        return (response.readEntity(Group.class));
    }

    /**
     * Creates a new project group. Available only for users who can create groups.
     *
     * <pre><code>GitLab Endpoint: POST /groups</code></pre>
     *
     * @param name the name of the group to add
     * @param path the path for the group
     * @param description (optional) - The group's description
     * @param visibility (optional) - The group's visibility. Can be private, internal, or public.
     * @param lfsEnabled (optional) - Enable/disable Large File Storage (LFS) for the projects in this group
     * @param requestAccessEnabled (optional) - Allow users to request member access
     * @param parentId (optional) - The parent group id for creating nested group
     * @return the created Group instance
     * @throws GitLabApiException if any exception occurs
     */
    public Group addGroup(String name, String path, String description, Visibility visibility,
            Boolean lfsEnabled, Boolean requestAccessEnabled, Integer parentId) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("name", name)
                .withParam("path", path)
                .withParam("description", description)
                .withParam("visibility", visibility)
                .withParam("lfs_enabled", lfsEnabled)
                .withParam("request_access_enabled", requestAccessEnabled)
                .withParam("parent_id", isApiVersion(ApiVersion.V3) ? null : parentId);
        Response response = post(Response.Status.CREATED, formData, "groups");
        return (response.readEntity(Group.class));
    }

    /**
     * Updates a project group. Available only for users who can create groups.
     *
     * <pre><code>GitLab Endpoint: PUT /groups</code></pre>
     *
     * @param group to update
     * @return updated group instance
     * @throws GitLabApiException at any exception
     */
    public Group updateGroup(Group group) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("name", group.getName())
                .withParam("path", group.getPath())
                .withParam("description", group.getDescription())
                .withParam("visibility", group.getVisibility())
                .withParam("lfs_enabled", group.getLfsEnabled())
                .withParam("request_access_enabled", group.getRequestAccessEnabled())
                .withParam("parent_id", isApiVersion(ApiVersion.V3) ? null : group.getParentId());
        Response response = put(Response.Status.OK, formData.asMap(), "groups", group.getId());
        return (response.readEntity(Group.class));
    }

    /**
     * Updates a project group. Available only for users who can create groups.
     *
     * <pre><code>GitLab Endpoint: PUT /groups</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param name the name of the group to add
     * @param path the path for the group
     * @param description (optional) - The group's description
     * @param visibility (optional) - The group's visibility. Can be private, internal, or public.
     * @param lfsEnabled (optional) - Enable/disable Large File Storage (LFS) for the projects in this group
     * @param requestAccessEnabled (optional) - Allow users to request member access
     * @param parentId (optional) - The parent group id for creating nested group
     * @return the updated Group instance
     * @throws GitLabApiException if any exception occurs
     */
    public Group updateGroup(Object groupIdOrPath, String name, String path, String description, Visibility visibility,
            Boolean lfsEnabled, Boolean requestAccessEnabled, Integer parentId) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("name", name)
                .withParam("path", path)
                .withParam("description", description)
                .withParam("visibility", visibility)
                .withParam("lfs_enabled", lfsEnabled)
                .withParam("request_access_enabled", requestAccessEnabled)
                .withParam("parent_id", isApiVersion(ApiVersion.V3) ? null : parentId);
        Response response = put(Response.Status.OK, formData.asMap(), "groups", getGroupIdOrPath(groupIdOrPath));
        return (response.readEntity(Group.class));
    }

    /**
     * Creates a new project group. Available only for users who can create groups.
     *
     * <pre><code>GitLab Endpoint: POST /groups</code></pre>
     *
     * @param name the name of the group to add
     * @param path the path for the group
     * @param description (optional) - The group's description
     * @param membershipLock (optional, boolean) - Prevent adding new members to project membership within this group
     * @param shareWithGroupLock (optional, boolean) - Prevent sharing a project with another group within this group
     * @param visibility (optional) - The group's visibility. Can be private, internal, or public.
     * @param lfsEnabled (optional) - Enable/disable Large File Storage (LFS) for the projects in this group
     * @param requestAccessEnabled (optional) - Allow users to request member access.
     * @param parentId (optional) - The parent group id for creating nested group.
     * @param sharedRunnersMinutesLimit (optional) - (admin-only) Pipeline minutes quota for this group
     * @return the created Group instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link #addGroup(String, String, String, Visibility,
     *      Boolean, Boolean, Integer)}
     */
    public Group addGroup(String name, String path, String description, Boolean membershipLock,
            Boolean shareWithGroupLock, Visibility visibility, Boolean lfsEnabled, Boolean requestAccessEnabled,
            Integer parentId, Integer sharedRunnersMinutesLimit) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("name", name)
                .withParam("path", path)
                .withParam("description", description)
                .withParam("membership_lock", membershipLock)
                .withParam("share_with_group_lock", shareWithGroupLock)
                .withParam("visibility", visibility)
                .withParam("lfs_enabled", lfsEnabled)
                .withParam("request_access_enabled", requestAccessEnabled)
                .withParam("parent_id", parentId)
                .withParam("shared_runners_minutes_limit", sharedRunnersMinutesLimit);
        Response response = post(Response.Status.CREATED, formData, "groups");
        return (response.readEntity(Group.class));
    }

    /**
     * Updates a project group. Available only for users who can create groups.
     *
     * <pre><code>GitLab Endpoint: PUT /groups</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param name the name of the group to add
     * @param path the path for the group
     * @param description (optional) - The group's description
     * @param membershipLock (optional, boolean) - Prevent adding new members to project membership within this group
     * @param shareWithGroupLock (optional, boolean) - Prevent sharing a project with another group within this group
     * @param visibility (optional) - The group's visibility. Can be private, internal, or public.
     * @param lfsEnabled (optional) - Enable/disable Large File Storage (LFS) for the projects in this group
     * @param requestAccessEnabled (optional) - Allow users to request member access
     * @param parentId (optional) - The parent group id for creating nested group
     * @param sharedRunnersMinutesLimit (optional) - (admin-only) Pipeline minutes quota for this group
     * @return the updated Group instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link #updateGroup(Object, String, String, String,
     *      Visibility, Boolean, Boolean, Integer)}
     */
    public Group updateGroup(Object groupIdOrPath, String name, String path, String description, Boolean membershipLock,
            Boolean shareWithGroupLock, Visibility visibility, Boolean lfsEnabled, Boolean requestAccessEnabled,
            Integer parentId, Integer sharedRunnersMinutesLimit) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("name", name)
                .withParam("path", path)
                .withParam("description", description)
                .withParam("membership_lock", membershipLock)
                .withParam("share_with_group_lock", shareWithGroupLock)
                .withParam("visibility", visibility)
                .withParam("lfs_enabled", lfsEnabled)
                .withParam("request_access_enabled", requestAccessEnabled)
                .withParam("parent_id", parentId)
                .withParam("shared_runners_minutes_limit", sharedRunnersMinutesLimit);
        Response response = put(Response.Status.OK, formData.asMap(), "groups", getGroupIdOrPath(groupIdOrPath));
        return (response.readEntity(Group.class));
    }

    /**
     * Removes group with all projects inside.
     *
     * <pre><code>GitLab Endpoint: DELETE /groups/:id</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteGroup(Object groupIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "groups", getGroupIdOrPath(groupIdOrPath));
    }

    /**
     * Get a list of group members viewable by the authenticated user.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/members</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @return a list of group members viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Member> getMembers(Object groupIdOrPath) throws GitLabApiException {
        return (getMembers(groupIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of group members viewable by the authenticated user in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/members</code></pre>
     *
     *@param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param page the page to get
     * @param perPage the number of Member instances per page
     * @return a list of group members viewable by the authenticated user in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Member> getMembers(Object groupIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "groups", getGroupIdOrPath(groupIdOrPath), "members");
        return (response.readEntity(new GenericType<List<Member>>() {}));
    }

    /**
     * Get a Pager of group members viewable by the authenticated user.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/members</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param itemsPerPage the number of Member instances that will be fetched per page
     * @return a list of group members viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Member> getMembers(Object groupIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Member>(this, Member.class, itemsPerPage, null, "groups", getGroupIdOrPath(groupIdOrPath), "members"));
    }

    /**
     * Get a Stream of group members viewable by the authenticated user.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/members</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @return a Stream of group members viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Member> getMembersStream(Object groupIdOrPath) throws GitLabApiException {
        return (getMembers(groupIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a group member viewable by the authenticated user.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/members/:id</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param userId the member ID of the member to get
     * @return a member viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Member getMember(Object groupIdOrPath, int userId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "groups", getGroupIdOrPath(groupIdOrPath), "members", userId);
        return (response.readEntity(new GenericType<Member>() {}));
    }

    /**
     * Get a group member viewable by the authenticated user as an Optional instance.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/members/:id</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param userId the member ID of the member to get
     * @return a member viewable by the authenticated user as an Optional instance
     */
    public Optional<Member> getOptionalMember(Object groupIdOrPath, int userId) {
        try {
            return (Optional.ofNullable(getMember(groupIdOrPath, userId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Adds a user to the list of group members.
     *
     * <pre><code>GitLab Endpoint: POST /groups/:id/members</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object groupIdOrPath, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (addMember(groupIdOrPath, userId, accessLevel, null));
    }

    /**
     * Adds a user to the list of group members.
     *
     * <pre><code>GitLab Endpoint: POST /groups/:id/members</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object groupIdOrPath, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (addMember(groupIdOrPath, userId, accessLevel.toValue(), null));
    }

    /**
     * Adds a user to the list of group members.
     *
     * <pre><code>GitLab Endpoint: POST /groups/:id/members</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object groupIdOrPath, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (addMember(groupIdOrPath, userId, accessLevel.toValue(), expiresAt));
    }

    /**
     * Adds a user to the list of group members.
     *
     * <pre><code>GitLab Endpoint: POST /groups/:id/members</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object groupIdOrPath, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("user_id", userId, true)
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = post(Response.Status.CREATED, formData, "groups", getGroupIdOrPath(groupIdOrPath), "members");
        return (response.readEntity(Member.class));
    }

    /**
     * Updates a member of a group.
     *
     * <pre><code>GitLab Endpoint: PUT /groups/:groupId/members/:userId</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object groupIdOrPath, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (updateMember(groupIdOrPath, userId, accessLevel, null));
    }

    /**
     * Updates a member of a group.
     *
     * <pre><code>GitLab Endpoint: PUT /groups/:groupId/members/:userId</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object groupIdOrPath, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (updateMember(groupIdOrPath, userId, accessLevel.toValue(), null));
    }

    /**
     * Updates a member of a group.
     *
     * <pre><code>GitLab Endpoint: PUT /groups/:groupId/members/:userId</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object groupIdOrPath, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (updateMember(groupIdOrPath, userId, accessLevel.toValue(), expiresAt));
    }

    /**
     * Updates a member of a group.
     *
     * <pre><code>GitLab Endpoint: PUT /groups/:groupId/members/:userId</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object groupIdOrPath, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = put(Response.Status.OK, formData.asMap(), "groups", getGroupIdOrPath(groupIdOrPath), "members", userId);
        return (response.readEntity(Member.class));
    }

    /**
     * Removes member from the group.
     *
     * <pre><code>GitLab Endpoint: DELETE /groups/:id/members/:user_id</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path, required
     * @param userId the user ID of the member to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void removeMember(Object groupIdOrPath, Integer userId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "groups", getGroupIdOrPath(groupIdOrPath), "members", userId);
    }

    /**
     * Syncs the group with its linked LDAP group. Only available to group owners and administrators.
     *
     * <pre><code>GitLab Endpoint: POST /groups/:id/ldap_sync</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @throws GitLabApiException if any exception occurs
     */
    public void ldapSync(Object groupIdOrPath) throws GitLabApiException {
        post(Response.Status.NO_CONTENT, (Form)null, "groups", getGroupIdOrPath(groupIdOrPath), "ldap_sync");
    }

    /**
     * Adds an LDAP group link.
     *
     * <pre><code>GitLab Endpoint: POST /groups/:id/ldap_group_links</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param cn the CN of a LDAP group
     * @param groupAccess the minimum access level for members of the LDAP group
     * @param provider the LDAP provider for the LDAP group
     * @throws GitLabApiException if any exception occurs
     */
    public void addLdapGroupLink(Object groupIdOrPath, String cn, AccessLevel groupAccess, String provider) throws GitLabApiException {

        if (groupAccess == null) {
            throw new RuntimeException("groupAccess cannot be null or empty");
        }

        addLdapGroupLink(groupIdOrPath, cn, groupAccess.toValue(), provider);
    }

    /**
     * Adds an LDAP group link.
     *
     * <pre><code>GitLab Endpoint: POST /groups/:id/ldap_group_links</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param cn the CN of a LDAP group
     * @param groupAccess the minimum access level for members of the LDAP group
     * @param provider the LDAP provider for the LDAP group
     * @throws GitLabApiException if any exception occurs
     */
    public void addLdapGroupLink(Object groupIdOrPath, String cn, Integer groupAccess, String provider) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("cn",  cn, true)
                .withParam("group_access", groupAccess, true)
                .withParam("provider",  provider, true);
        post(Response.Status.CREATED, formData, "groups", getGroupIdOrPath(groupIdOrPath), "ldap_group_links");
    }

    /**
     * Deletes an LDAP group link.
     *
     * <pre><code>GitLab Endpoint: DELETE /groups/:id/ldap_group_links/:cn</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param cn the CN of the LDAP group link to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteLdapGroupLink(Object groupIdOrPath, String cn) throws GitLabApiException {

        if (cn == null || cn.trim().isEmpty()) {
            throw new RuntimeException("cn cannot be null or empty");
        }

        delete(Response.Status.OK, null, "groups", getGroupIdOrPath(groupIdOrPath), "ldap_group_links", cn);
    }

    /**
     * Deletes an LDAP group link for a specific LDAP provider.
     *
     * <pre><code>GitLab Endpoint: DELETE /groups/:id/ldap_group_links/:provider/:cn</code></pre>
     *
     * @param groupIdOrPath the group ID, path of the group, or a Group instance holding the group ID or path
     * @param cn the CN of the LDAP group link to delete
     * @param provider the name of the LDAP provider
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteLdapGroupLink(Object groupIdOrPath, String cn, String provider) throws GitLabApiException {

        if (cn == null || cn.trim().isEmpty()) {
            throw new RuntimeException("cn cannot be null or empty");
        }

        if (provider == null || provider.trim().isEmpty()) {
            throw new RuntimeException("LDAP provider cannot be null or empty");
        }

        delete(Response.Status.OK, null, "groups", getGroupIdOrPath(groupIdOrPath), "ldap_group_links", provider, cn);
    }
}
