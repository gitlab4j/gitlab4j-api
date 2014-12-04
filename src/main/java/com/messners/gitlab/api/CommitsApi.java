package com.messners.gitlab.api;

import java.util.List;

import com.messners.gitlab.api.models.Commit;
import com.messners.gitlab.api.models.Diff;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * This class implements the client side API for the GitLab commits calls.
 * 
 * @author Greg Messner <greg@messners.com>
 */
public class CommitsApi extends AbstractApi {

	public CommitsApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	
	
	/**
	 * Get a list of repository commits in a project.
	 * 
	 * GET /projects/:id/repository/commits
	 * 
	 * @param projectId
	 * @return a List<Commit> containing the commits for the specified project ID
	 * @throws GitLabApiException 
	 */
	public List<Commit> getCommits (int projectId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId, "repository", "commits");
		return (response.getEntity(new GenericType<List<Commit>>() {}));		
	}

	
	/**
	 * Get a specific commit identified by the commit hash or name of a branch or tag.
	 * 
	 * GET /projects/:id/repository/commits/:sha
	 * 
	 * @param projectId
	 * @param sha a commit hash or name of a branch or tag
	 * @return the Commit instance for the specified project ID/sha pair
	 * @throws GitLabApiException 
	 */
	public Commit getCommits (int projectId, String sha) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId, "repository", "commits", sha);
		return (response.getEntity(Commit.class));		
	}

	
	/**
	 * Get the diff of a commit in a project.
	 * 
	 * GET /projects/:id/repository/commits/:sha/diff
	 * 
	 * @param projectId
	 * @param sha a commit hash or name of a branch or tag
	 * @return the Diff instance for the specified project ID/sha pair
	 * @throws GitLabApiException 
	 */
	public Diff getDiff (int projectId, String sha) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, 
				"projects", projectId, "repository", "commits", sha, "diff");
		return (response.getEntity(Diff.class));		
	}
}