package com.messners.gitlab.api;

import java.util.List;

import com.messners.gitlab.api.models.Group;
import com.messners.gitlab.api.models.Member;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.representation.Form;

public class GroupApi extends AbstractApi {

	GroupApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	

	/**
	 * Get a list of groups. (As user: my groups, as admin: all groups)
	 * 
	 * GET /groups
	 * 
	 * @return
	 * @throws GitLabApiException 
	 */
	public List<Group> getGroups () throws GitLabApiException {	
		ClientResponse response = get(ClientResponse.Status.OK, null, "groups");
		return (response.getEntity(new GenericType<List<Group>>() {}));
	}
	
	
	/**
	 * Get all details of a group.
	 * 
	 * GET /groups/:id
	 * 
	 * @param groupId
	 * @return
	 * @throws GitLabApiException 
	 */
	public Group getGroup (int groupId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "groups", groupId);
		return (response.getEntity(Group.class));
	}
	
	
	/**
	 * Creates a new project group. Available only for admin.
	 * 
	 * POST /groups
	 * 
	 * @param name
	 * @param path
	 */
	public void addGroup (String name, String path) throws GitLabApiException {
		
		Form formData = new Form();
		formData.add("name",  name);		
		formData.add("path",  path);		
		post(ClientResponse.Status.OK, formData, "groups");
	}
	
	
	/**
	 * Removes group with all projects inside.
	 * 
	 * DELETE /groups/:id
	 * 
	 * @param groupId
	 * @throws GitLabApiException 
	 */
	public void deleteGroup (Integer groupId) throws GitLabApiException {
		
		if (groupId == null) {
			throw new RuntimeException("groupId cannot be null");
		}
		
		delete(ClientResponse.Status.OK, null, "groups", groupId);
	}

	
	/**
	 * Removes group with all projects inside.
	 * 
	 * DELETE /groups/:id
	 * 
	 * @param group
	 * @throws GitLabApiException 
	 */
	public void deleteGroup (Group group)  throws GitLabApiException {
		 deleteGroup(group.getId());
	}

	
	/**
	 * Get a list of group members viewable by the authenticated user.
	 * 
	 * GET /groups/:id/members
	 * 
	 * @return
	 * @throws GitLabApiException 
	 */
	public List<Member> getMembers (int groupId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "groups", groupId, "members");
		return (response.getEntity(new GenericType<List<Member>>() {}));
	}
	
	/**
	 * Adds a user to the list of group members.
	 * 
	 * POST /groups/:id/members
	 * 
	 * @param groupId
	 * @param userId
	 * @param accessLevel
	 * @return
	 * @throws GitLabApiException 
	 */
	public Member addMember (Integer groupId, Integer userId, Integer accessLevel) throws GitLabApiException {
		
		Form formData = new Form();
		formData.add("user_id",  userId);		
		formData.add("access_level",  accessLevel);		
		ClientResponse response = post(ClientResponse.Status.OK, formData, "groups", groupId, "members");
		return (response.getEntity(Member.class));
	}
	
	
	/**
	 * Removes member from the group team.
	 * 
	 * DELETE /groups/:id/members/:user_id
	 * 
	 * @param projectId
	 * @param userId
	 * @throws GitLabApiException 
	 */
	public void removeMember (Integer projectId, Integer userId) throws GitLabApiException {		
		delete(ClientResponse.Status.OK, null, "groups", projectId, "members", userId);
	}
}
