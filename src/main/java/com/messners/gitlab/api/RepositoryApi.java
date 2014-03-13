package com.messners.gitlab.api;

import java.io.IOException;
import java.util.List;

import com.messners.gitlab.api.models.Branch;
import com.messners.gitlab.api.models.Tag;
import com.messners.gitlab.api.models.TreeItem;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;


/**
 * This class provides an entry point to all the GitLab API repository calls.
 * 
 * @author Greg Messner <greg@messners.com>
 */
public class RepositoryApi extends AbstractApi {

	public RepositoryApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	
	
	/**
	 * GET /projects/:id/repository/branches
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public List<Branch> getBranches (Integer projectId) throws IOException {
		ClientResponse response = get(null, "projects", projectId, "repository", "branches");
		return (response.getEntity(new GenericType<List<Branch>>() {}));
	}

	
	/**
	 * GET /projects/:id/repository/branches/:branch
	 * 
	 * @param projectId
	 * @param branchName
	 * @return
	 * @throws IOException
	 */
	public Branch getBranch (Integer projectId, String branchName) throws IOException {
		ClientResponse response = get(null, "projects", projectId, "repository", "branches", branchName);
		return (response.getEntity(Branch.class));
	}
	
	
	/**
	 * PUT /projects/:id/repository/branches/:branch/protect
	 * 
	 * @param projectId
	 * @param branchName
	 * @return
	 * @throws IOException
	 */
	public Branch protectBranch (Integer projectId, String branchName) throws IOException {
		ClientResponse response = put(null, "projects", projectId, "repository", "branches", branchName, "protect");
		return (response.getEntity(Branch.class));
	}
	
	
	/**
	 * PUT /projects/:id/repository/branches/:branch/unprotect
	 * 
	 * @param projectId
	 * @param branchName
	 * @return
	 * @throws IOException
	 */
	public Branch unprotectBranch (Integer projectId, String branchName) throws IOException {
		ClientResponse response = put(null, "projects", projectId, "repository", "branches", branchName, "unprotect");
		return (response.getEntity(Branch.class));
	}
	
	
	/**
	 * GET /projects/:id/repository/tags
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public List<Tag> getTags (Integer projectId) throws IOException {
		ClientResponse response = put(null, "projects", projectId, "repository", "tags");
		 return (response.getEntity(new GenericType<List<Tag>>() {}));
	}
	
	
	/**
	 * GET /projects/:id/repository/tree
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public List<TreeItem> getTree (Integer projectId) throws IOException {
		ClientResponse response = put(null, "projects", projectId, "repository", "tree");
		 return (response.getEntity(new GenericType<List<TreeItem>>() {}));
	}
	
	
	/**
	 * GET /projects/:id/repository/blobs/:sha
	 * 
	 * @param projectId
	 * @param commitOrBranchName
	 * @return
	 * @throws IOException
	 */
	public String getRawFileContent (Integer projectId, String commitOrBranchName) throws IOException {
		ClientResponse response = get(null, "projects", projectId, "repository", "blobs", commitOrBranchName);
		return (response.getEntity(String.class));
	}

}
