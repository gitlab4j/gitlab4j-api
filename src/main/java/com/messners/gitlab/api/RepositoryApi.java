package com.messners.gitlab.api;

import com.messners.gitlab.api.models.Branch;
import com.messners.gitlab.api.models.Tag;
import com.messners.gitlab.api.models.TreeItem;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;


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
	 * Get a list of repository branches from a project, sorted by name alphabetically.
	 * 
	 * GET /projects/:id/repository/branches
	 * 
	 * @param projectId
	 * @return the list of repository branches for mthe specified project ID
	 * @throws GitLabApiException 
	 */
	public List<Branch> getBranches (Integer projectId) throws GitLabApiException {		
		Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "branches");
		return (response.readEntity(new GenericType<List<Branch>>() {}));
	}


	/**
	 * Get a single project repository branch.
	 * 
	 * GET /projects/:id/repository/branches/:branch
	 * 
	 * @param projectId
	 * @param branchName
	 * @return the branch info for the specified project ID/branch name pair
	 * @throws GitLabApiException 
	 */
	public Branch getBranch (Integer projectId, String branchName) throws GitLabApiException {
		Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "branches", branchName);
		return (response.readEntity(Branch.class));
	}


	/**
	 * Creates a branch for the project. Support as of version 6.8.x 
	 * 
	 * POST /projects/:id/repository/branches
	 * 
	 * @param projectId the project to create the branch for
	 * @param branchName the name of the branch to create
	 * @param ref Source to create the branch from, can be an existing branch, tag or commit SHA
	 * @return  the branch info for the created branch
	 * @throws GitLabApiException
	 */
    public Branch createBranch (Integer projectId, String branchName, String ref) throws GitLabApiException {
        Form formData = new Form();
        formData.param("branch_name ", branchName);
        formData.param("ref ", ref);
        Response response = post(Response.Status.OK, formData, "projects", projectId, "repository", "branches");
        return (response.readEntity(Branch.class));
    }
	
	
	/**
	 * Protects a single project repository branch. This is an idempotent function, 
	 * protecting an already protected repository branch will not produce an error.
	 * 
	 * PUT /projects/:id/repository/branches/:branch/protect
	 * 
	 * @param projectId
	 * @param branchName
	 * @return the branch info for the protected branch 
	 * @throws GitLabApiException 
	 */
	public Branch protectBranch (Integer projectId, String branchName) throws GitLabApiException {		
		Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "branches", branchName, "protect");
		return (response.readEntity(Branch.class));
	}
	
	
	/**
	 * Unprotects a single project repository branch. This is an idempotent function, unprotecting an 
	 * already unprotected repository branch will not produce an error.
	 * 
	 * PUT /projects/:id/repository/branches/:branch/unprotect
	 * 
	 * @param projectId
	 * @param branchName
	 * @return the branch info for the unprotected branch 
	 * @throws GitLabApiException 
	 */
	public Branch unprotectBranch (Integer projectId, String branchName) throws GitLabApiException {		
		Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "branches", branchName, "unprotect");
		return (response.readEntity(Branch.class));
	}
	
	
	/**
	 * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
	 * 
	 * GET /projects/:id/repository/tags
	 * 
	 * @param projectId
	 * @return the list of tags for the specified project ID
	 * @throws GitLabApiException 
	 */
	public List<Tag> getTags (Integer projectId) throws GitLabApiException {		
		Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "tags");
		 return (response.readEntity(new GenericType<List<Tag>>() {}));
	}
	
	
	/**
	 * Get a list of repository files and directories in a project.
	 * 
	 * GET /projects/:id/repository/tree
	 * 
	 * @param projectId
	 * @return a tree with the diurectories and files of a project
	 * @throws GitLabApiException 
	 */
	public List<TreeItem> getTree (Integer projectId) throws GitLabApiException {		
		Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "tree");
		return (response.readEntity(new GenericType<List<TreeItem>>() {}));
	}
	
	
	/**
	 * Get the raw file contents for a file by commit sha and path.
	 * 
	 * GET /projects/:id/repository/blobs/:sha
	 * 
	 * @param projectId
	 * @param commitOrBranchName
	 * @return a string with the file content for the specified file
	 * @throws GitLabApiException 
	 */
	public String getRawFileContent (Integer projectId, String commitOrBranchName, String filepath) throws GitLabApiException {
		
		Form formData = new Form();
		addFormParam(formData, "filepath", filepath, true);		
		Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "blobs", commitOrBranchName);
		return (response.readEntity(String.class));
	}
}
