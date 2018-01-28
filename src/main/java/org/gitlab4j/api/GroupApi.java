package org.gitlab4j.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
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
     * GET /groups
     *
     * @return the list of groups viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Group> getGroups() throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "groups");
        return (response.readEntity(new GenericType<List<Group>>() {}));
    }

    /**
     * Get a list of groups (As user: my groups, as admin: all groups) and in the specified page range.
     *
     * GET /groups
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
     * GET /groups
     *
     * @param itemsPerPage the number of Group instances that will be fetched per page
     * @return the list of groups viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Group> getGroups(int itemsPerPage) throws GitLabApiException {
        return (new Pager<Group>(this, Group.class, itemsPerPage, null, "groups"));
    }

    /**
     * Get all groups that match your string in their name or path.
     *
     * @param search the group name or path search criteria
     * @return a List containing matching Group instances
     * @throws GitLabApiException if any exception occurs
     */
    public List<Group> getGroups(String search) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "groups");
        return (response.readEntity(new GenericType<List<Group>>() {}));
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
     * Get a list of visible direct subgroups in this group.
     *
     * <p><code>GET /groups/:id/subgroups</code></p>
     *
     * @param groupId the group ID to get the sub groups for
     * @return a List&lt;Group&gt; containing the group's sub-groups
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.3.0
     */
    public List<Group> getSubGroups(Integer groupId) throws GitLabApiException {
        return (getSubGroups(groupId, null, null, null, null, null, null, null, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of visible direct subgroups in this group.
     *
     * <p><code>GET /groups/:id/subgroups</code></p>
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
        return (getSubGroups(groupId, skipGroups, allAvailable, search, orderBy, sortOrder, statistics, owned, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of visible direct subgroups in this group.
     *
     * <p><code>GET /groups/:id/subgroups</code></p>
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
     * <p><code>GET /groups/:id/subgroups</code></p>
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
     * Get a Pager of visible direct subgroups in this group.
     *
     * <p><code>GET /groups/:id/subgroups</code></p>
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
     * Get a list of projects belonging to the specified group ID.
     *
     * GET /groups/:id/projects
     *
     * @param groupId the group ID to list the projects for
     * @return a list of projects belonging to the specified group ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(int groupId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "groups", groupId, "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects belonging to the specified group ID in the specified page range.
     *
     * GET /groups/:id/projects
     *
     * @param groupId the group ID to list the projects for
     * @param page the page to get
     * @param perPage the number of Project instances per page
     * @return a list of projects belonging to the specified group ID in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(int groupId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "groups", groupId, "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of projects belonging to the specified group ID.
     *
     * GET /groups/:id/projects
     *
     * @param groupId the group ID to list the projects for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of projects belonging to the specified group ID
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getProjects(int groupId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Project>(this, Project.class, itemsPerPage, null, "groups", groupId, "projects"));
    }

    /**
     * Get all details of a group.
     *
     * GET /groups/:id
     *
     * @param groupId the group ID to get
     * @return the Group instance for the specified group ID
     * @throws GitLabApiException if any exception occurs
     */
    public Group getGroup(Integer groupId) throws GitLabApiException {
        return getGroup(groupId.toString());
    }

    /**
     * Get all details of a group as an Optional instance.
     *
     * GET /groups/:id
     *
     * @param groupId the group ID to get
     * @return the Group for the specified group ID as an Optional instance
     */
    public Optional<Group> getOptionalGroup(Integer groupId) {
        return (getOptionalGroup(groupId.toString()));
    }

    /**
     * Get all details of a group.
     *
     * GET /groups/:id
     *
     * @param groupPath the path of the group to get details for
     * @return the Group instance for the specified group path
     * @throws GitLabApiException if any exception occurs
     */
    public Group getGroup(String groupPath) throws GitLabApiException {
      Response response = get(Response.Status.OK, null, "groups", urlEncode(groupPath));
      return (response.readEntity(Group.class));
    }

    /**
     * Get all details of a group as an Optional instance.
     *
     * GET /groups/:id
     *
     * @param groupPath the path of the group to get details for
     * @return the Group for the specified group path as an Optional instance
     */
    public Optional<Group> getOptionalGroup(String groupPath) {
        try {
            return (Optional.ofNullable(getGroup(groupPath)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a new project group. Available only for users who can create groups.
     *
     * POST /groups
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

    /**
     * Creates a new project group. Available only for users who can create groups.
     *
     * POST /groups
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
     * Updates a  project group. Available only for users who can create groups.
     *
     * PUT /groups
     *
     * @param groupId the ID of the group to update
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
    public Group updateGroup(Integer groupId, String name, String path, String description, Visibility visibility,
            Boolean lfsEnabled, Boolean requestAccessEnabled, Integer parentId) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("name", name)
                .withParam("path", path)
                .withParam("description", description)
                .withParam("visibility", visibility)
                .withParam("lfs_enabled", lfsEnabled)
                .withParam("request_access_enabled", requestAccessEnabled)
                .withParam("parent_id", isApiVersion(ApiVersion.V3) ? null : parentId);
        Response response = put(Response.Status.OK, formData.asMap(), "groups", groupId);
        return (response.readEntity(Group.class));
    }

    /**
     * Creates a new project group. Available only for users who can create groups.
     *
     * POST /groups
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
     * PUT /groups
     *
     * @param groupId the ID of the group to update
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
     * @deprecated  Will be removed in version 5.0, replaced by {@link #updateGroup(Integer, String, String, String,
     *      Visibility, Boolean, Boolean, Integer)}
     */
    public Group updateGroup(Integer groupId, String name, String path, String description, Boolean membershipLock,
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
        Response response = put(Response.Status.OK, formData.asMap(), "groups", groupId);
        return (response.readEntity(Group.class));
    }

    /**
     * Removes group with all projects inside.
     *
     * DELETE /groups/:id
     *
     * @param groupId the group ID to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteGroup(Integer groupId) throws GitLabApiException {

        if (groupId == null) {
            throw new RuntimeException("groupId cannot be null");
        }

        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "groups", groupId);
    }

    /**
     * Removes group with all projects inside.
     *
     * DELETE /groups/:id
     *
     * @param group the Group instance to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteGroup(Group group) throws GitLabApiException {
        deleteGroup(group.getId());
    }

    /**
     * Get a list of group members viewable by the authenticated user.
     *
     * GET /groups/:id/members
     *
     * @param groupId the group ID to list the members for
     * @return a list of group members viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Member> getMembers(int groupId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "groups", groupId, "members");
        return (response.readEntity(new GenericType<List<Member>>() {}));
    }

    /**
     * Get a list of group members viewable by the authenticated user in the specified page range.
     *
     * GET /groups/:id/members
     *
     * @param groupId the group ID to list the members for
     * @param page the page to get
     * @param perPage the number of Member instances per page
     * @return a list of group members viewable by the authenticated user in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Member> getMembers(int groupId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK,  getPageQueryParams(page, perPage), "groups", groupId, "members");
        return (response.readEntity(new GenericType<List<Member>>() {}));
    }

    /**
     * Get a Pager of group members viewable by the authenticated user.
     *
     * GET /groups/:id/members
     *
     * @param groupId the group ID to list the members for
     * @param itemsPerPage the number of Member instances that will be fetched per page
     * @return a list of group members viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Member> getMembers(int groupId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Member>(this, Member.class, itemsPerPage, null, "groups", groupId, "members"));
    }

    /**
     * Get a group member viewable by the authenticated user.
     *
     * GET /groups/:id/members/:id
     *
     * @param groupId the group ID to get the member for
     * @param userId the member ID of the member to get
     * @return a member viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Member getMember(int groupId, int userId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "groups", groupId, "members", userId);
        return (response.readEntity(new GenericType<Member>() {}));
    }

    /**
     * Get a group member viewable by the authenticated user as an Optional instance.
     *
     * GET /groups/:id/members/:id
     *
     * @param groupId the group ID to get the member for
     * @param userId the member ID of the member to get
     * @return a member viewable by the authenticated user as an Optional instance
     */
    public Optional<Member> getOptionalMember(int groupId, int userId) {
        try {
            return (Optional.ofNullable(getMember(groupId, userId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Adds a user to the list of group members.
     *
     * POST /groups/:id/members
     *
     * @param groupId the project ID to add the member to, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer groupId, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (addMember(groupId, userId, accessLevel, null));
    }

    /**
     * Adds a user to the list of group members.
     *
     * POST /groups/:id/members
     *
     * @param groupId the project ID to add the member to, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer groupId, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (addMember(groupId, userId, accessLevel.toValue(), null));
    }

    /**
     * Adds a user to the list of group members.
     *
     * POST /groups/:id/members
     *
     * @param groupId the project ID to add the member to, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer groupId, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (addMember(groupId, userId, accessLevel.toValue(), expiresAt));
    }

    /**
     * Adds a user to the list of group members.
     *
     * POST /groups/:id/members
     *
     * @param groupId the project ID to add the member to, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer groupId, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("user_id", userId, true)
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = post(Response.Status.CREATED, formData, "groups", groupId, "members");
        return (response.readEntity(Member.class));
    }

    /**
     * Updates a member of a group.
     *
     * PUT /groups/:groupId/members/:userId
     *
     * @param groupId the group ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer groupId, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (updateMember(groupId, userId, accessLevel, null));
    }

    /**
     * Updates a member of a group.
     *
     * PUT /groups/:groupId/members/:userId
     *
     * @param groupId the group ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer groupId, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (updateMember(groupId, userId, accessLevel.toValue(), null));
    }

    /**
     * Updates a member of a group.
     *
     * PUT /groups/:groupId/members/:userId
     *
     * @param groupId the group ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer groupId, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (updateMember(groupId, userId, accessLevel.toValue(), expiresAt));
    }

    /**
     * Updates a member of a group.
     *
     * PUT /groups/:groupId/members/:userId
     *
     * @param groupId the group ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer groupId, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = put(Response.Status.OK, formData.asMap(), "groups", groupId, "members", userId);
        return (response.readEntity(Member.class));
    }

    /**
     * Removes member from the group.
     *
     * DELETE /groups/:id/members/:user_id
     *
     * @param groupId the group ID to remove the member from
     * @param userId the user ID of the member to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void removeMember(Integer groupId, Integer userId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "groups", groupId, "members", userId);
    }
}
