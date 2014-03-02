package com.messners.gitlab.api;

import java.io.IOException;

import com.messners.gitlab.api.models.ErrorMessage;
import com.messners.gitlab.api.models.MergeRequest;
import com.messners.gitlab.api.models.MergeRequestComment;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.representation.Form;

public class MergeRequestApi extends AbstractApi {

	MergeRequestApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}

	
	/**
	 * POST /projects/:id/merge_request/:merge_request_id/comments
	 * 
	 * @param projectId
	 * @param mergeRequestId
	 * @param comments
	 * @throws IOException
	 */
	public MergeRequestComment addMergeRequestComment (Integer projectId, Integer mergeRequestId, String comments) throws IOException {
		
		Form formData = new Form();
		formData.add("note", comments);		
		ClientResponse response = post(formData, "projects", projectId, "merge_request", mergeRequestId, "comments");
		if (response.getStatus() != ClientResponse.Status.CREATED.getStatusCode()) {
			ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
			throw new IOException(errorMessage.getMessage());
		}
		
		return (response.getEntity(MergeRequestComment.class));
	}

	/**
	 * Creates a merge request and optionally assignes it.
	 * 
	 * @param projectId the ID of a project, required
	 * @param sourceBranch the source branch, required
	 * @param targetBranch the target branch, required
	 * @param title the title for the merge request, required
	 * @param description the description of the merge request
	 * @param assigneeId the Assignee user ID, optional
	 * @return the created MergeRequest instance
	 * @throws IOException 
	 */
	public MergeRequest createMergeRequest (Integer projectId, String sourceBranch, String targetBranch, String title, String description, Integer assigneeId) 
			throws IOException {
		
		/*
		 * Parameters:
		 *  id (required) - The ID of a project
		 *	source_branch (required) - The source branch
		 *	target_branch (required) - The target branch
		 *	assignee_id (optional) - Assignee user ID
		 *	title (required) - Title of MR	
		 */
		if (projectId == null) {
			throw new RuntimeException("projectId cannot be null");
		}
		
		Form formData = new Form();
		formData.add("source_branch", sourceBranch);		
		formData.add("target_branch", targetBranch);
		formData.add("title", title);
		
		if (description != null) {
			formData.add("description", description);
		}
		
		if (assigneeId != null) {
			formData.add("assignee_id", assigneeId);
		}
		
		ClientResponse response = post(formData, "projects", projectId, "merge_requests");
		if (response.getStatus() != ClientResponse.Status.CREATED.getStatusCode()) {
			ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
			throw new IOException(errorMessage.getMessage());
		}
		
		return (response.getEntity(MergeRequest.class));
	}

}
