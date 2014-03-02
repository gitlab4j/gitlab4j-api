package com.messners.gitlab.api;

import java.io.IOException;

import com.messners.gitlab.api.models.User;
import com.sun.jersey.api.client.ClientResponse;

public class UserApi extends AbstractApi {

	UserApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	
	
	/**
	 * GET /users/:id
	 * 
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public User getUser (int userId) throws IOException {
		ClientResponse response = get(null, "users", userId);
		return (response.getEntity(User.class));
	}
}
