package org.gitlab4j.api;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;

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
     * Creates a new project group. Available only for admin.
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

        delete(Response.Status.OK, null, "groups", groupId);
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
        Response response = post(Response.Status.OK, formData, "groups", groupId, "members");
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
        delete(Response.Status.OK, null, "groups", projectId, "members", userId);
    }
}
