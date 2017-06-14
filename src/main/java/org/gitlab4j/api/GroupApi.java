package org.gitlab4j.api;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        Response response = get(Response.Status.OK, null, "groups");
        return (response.readEntity(new GenericType<List<Group>>() {
        }));
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
        Response response = get(Response.Status.OK, null, "groups", groupId, "projects");
        return (response.readEntity(new GenericType<List<Project>>() {
        }));
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
    public Group getGroup(int groupId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "groups", groupId);
        return (response.readEntity(Group.class));
    }

    /**
     * Creates a new project group. Available only for users who can create groups.
     * 
     * POST /groups
     * 
     * @param name the name of the group to add
     * @param path the path for the group
     * @throws GitLabApiException if any exception occurs
     */
    public void addGroup(String name, String path) throws GitLabApiException {

        Form formData = new Form();
        formData.param("name", name);
        formData.param("path", path);
        post(Response.Status.CREATED, formData, "groups");
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
     * @throws GitLabApiException if any exception occurs
     */
    public void addGroup(String name, String path, String description, Boolean membershipLock,
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
        post(Response.Status.CREATED, formData, "groups");
    }

    /**
     * Creates a new project group. Available only for users who can create groups.
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
     * @param requestAccessEnabled (optional) - Allow users to request member access.
     * @param parentId (optional) - The parent group id for creating nested group.
     * @param sharedRunnersMinutesLimit (optional) - (admin-only) Pipeline minutes quota for this group
     * @return the updated Group instance
     * @throws GitLabApiException if any exception occurs
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
        Response response = get(Response.Status.OK, null, "groups", groupId, "members");
        return (response.readEntity(new GenericType<List<Member>>() {
        }));
    }

    /**
     * Adds a user to the list of group members.
     * 
     * POST /groups/:id/members
     * 
     * @param groupId the project ID to add the member to
     * @param userId the user ID of the member to add
     * @param accessLevel the access level for the new member
     * @return a Member instance for the added user
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer groupId, Integer userId, Integer accessLevel) throws GitLabApiException {

        Form formData = new Form();
        formData.param("user_id", userId.toString());
        formData.param("access_level", accessLevel.toString());
        Response response = post(Response.Status.CREATED, formData, "groups", groupId, "members");
        return (response.readEntity(Member.class));
    }

    /**
     * Removes member from the group team.
     * 
     * DELETE /groups/:id/members/:user_id
     * 
     * @param projectId the project ID to remove the member from
     * @param userId the user ID of the member to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void removeMember(Integer projectId, Integer userId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "groups", projectId, "members", userId);
    }
}