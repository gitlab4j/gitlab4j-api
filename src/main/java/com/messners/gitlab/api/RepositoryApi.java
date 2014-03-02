package com.messners.gitlab.api;

import java.io.IOException;

import com.messners.gitlab.api.models.Branch;
import com.sun.jersey.api.client.ClientResponse;

public class RepositoryApi extends AbstractApi {

	public RepositoryApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	
	/**
	 * GET /projects/:id/repository/branches/:branch
	 * 
	 * @param gitLabApi TODO
	 * @param projectId
	 * @param branchName
	 * @return
	 * @throws IOException
	 */
	public Branch getBranch (GitLabApi gitLabApi, Integer projectId, String branchName) throws IOException {
		ClientResponse response = get(null, "projects", projectId, "repository", "branches", branchName);
		return (response.getEntity(Branch.class));
	}

}
