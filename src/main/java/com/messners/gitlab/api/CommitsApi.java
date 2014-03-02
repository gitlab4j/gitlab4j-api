package com.messners.gitlab.api;

import java.io.IOException;
import java.util.List;

import com.messners.gitlab.api.models.Commit;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

public class CommitsApi extends AbstractApi {

	public CommitsApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	
	
	/**
	 * GET /projects/:id/repository/commits/branch
	 * 
	 * @param branch
	 * @return
	 */
	public List<Commit> getCommits (int projectId, String branch) throws IOException {		
		ClientResponse response = get(null, "projects", projectId, "repository", "commits", branch);
		return (response.getEntity(new GenericType<List<Commit>>() {}));		
	}
}
