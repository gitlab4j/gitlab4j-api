package com.messners.gitlab.api;

import java.io.IOException;
import java.util.List;

import com.messners.gitlab.api.models.Group;
import com.messners.gitlab.api.models.Member;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class GroupApi extends AbstractApi {

	GroupApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	

	/**
	 * GET /groups
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Group> getGroups () throws IOException {	
		 ClientResponse response = get(null, "groups");
		 return (response.getEntity(new GenericType<List<Group>>() {}));
	}
	
	
	/**
	 * GET /groups/:id
	 * 
	 * @param groupId
	 * @return
	 * @throws IOException
	 */
	public Group getGroup (int groupId) throws IOException {
		ClientResponse response = get(null, "groups", groupId);
		return (response.getEntity(Group.class));
	}
	
	
	/**
	 * DELETE /groups/:id
	 * 
	 * @param groupId
	 * @return
	 * @throws IOException
	 */
	public boolean deleteGroup (Integer groupId)  throws IOException {
		
		if (groupId == null) {
			throw new RuntimeException("groupId cannot be null");
		}
		
		ClientResponse response = delete(null, "groups", groupId);
		return (response.getStatus() == ClientResponse.Status.OK.getStatusCode());		
	}

	
	/**
	 * 
	 * @param group
	 * @return
	 * @throws IOException
	 */
	public boolean deleteGroup (Group group)  throws IOException {
		return (deleteGroup(group.getId()));
	}

	
	/**
	 * GET /groups/:id/members
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Member> getMembers (int groupId) throws IOException {	
		 ClientResponse response = get(null, "groups", groupId, "members");
		 return (response.getEntity(new GenericType<List<Member>>() {}));
	}
}
