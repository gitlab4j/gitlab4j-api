package com.messners.gitlab.api;

import java.io.IOException;
import java.util.List;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.representation.Form;

/**
 * This class specifies methods to interact with a GitLab server utilizing the standard GitLab API.
 * 
 * @author Greg Messner <greg@messners.com>
 */
public class GitLabApi {
	
	private GitLabApiClient apiClient;
	

	/**
	 * Contructs a GitLabApi instance set up to interact with the GitLab server
	 * specified by hostUrl.
	 * 
	 * @param hostUrl
	 * @param privateToken 
	 */
	public GitLabApi (String hostUrl, String privateToken) {	
		apiClient = new GitLabApiClient(hostUrl, privateToken);
	}
	
	
	public List<Project> getProjects () throws IOException {	
		 ClientResponse response = apiClient.get(null, "projects");
		 return (response.getEntity(new GenericType<List<Project>>() {}));
	}
	
	public List<Project> getAllProjects () throws IOException {	
		 ClientResponse response = apiClient.get(null, "projects", "all");
		 return (response.getEntity(new GenericType<List<Project>>() {}));
	}
	
	public List<Project> getOwnedProjects () throws IOException {	
		 ClientResponse response = apiClient.get(null, "projects", "owned");
		 return (response.getEntity(new GenericType<List<Project>>() {}));
	}
	
	public Project getProject (Integer projectId) throws IOException {
		ClientResponse response = apiClient.get(null, "projects", projectId);
		return (response.getEntity(Project.class));
	}
	
	public List<Event> getProjectEvents (Integer projectId) throws IOException {
		ClientResponse response = apiClient.get(null, "project", projectId, "events");
		return (response.getEntity(new GenericType<List<Event>>() {}));
	}

	
	/**
	 * Creates new project owned by the current user.  
	 * 
	 * @param project the Project instance with the configuration for the new project
	 * @return a Project instance with the newly created project info
	 * @throws IOException
	 */	
	public Project createProject (Project project) throws IOException {
		return (createProject(project, null));
	}
	
	/**
	 * Creates new project owned by the current user.  The following properties on the Project instance
	 * are utilized in the creation of the project:
	 * 
	 * 	name (required) - new project name
	 * 	description (optional) - short project description
	 * 	issuesEnabled (optional)
	 * 	wallEnabled (optional)
	 * 	mergeRequestsEnabled (optional)
	 * 	wikiEnabled (optional)
	 * 	snippetsEnabled (optional)
	 * 	isPublic (optional) - if true same as setting visibility_level = 20
	 * 	visibilityLevel (optional)
	 * 
	 * @param project the Project instance with the configuration for the new project
	 * @param importUrl
	 * @return a Project instance with the newly created project info
	 * @throws IOException
	 */	
	public Project createProject (Project project, String importUrl) throws IOException {
		
		if (project == null) {
			return (null);
		}
		
		String name = project.getName();
		if (name == null || name.trim().length() == 0) {
			return (null);
		}
		
		Form formData = new Form();
		formData.add("name",  name);
		
		formData.add("description", project.getDescription());
		formData.add("issues_enabled", project.getIssuesEnabled());		
		formData.add("wall_enabled", project.getWallEnabled());
		formData.add("merge_requests_enabled", project.getMergeRequestsEnabled());
		formData.add("wiki_enabled", project.getWikiEnabled());
		formData.add("snippets_enabled", project.getSnippetsEnabled());
		formData.add("public", project.getPublic());
		formData.add("visibility_level", project.getVisibilityLevel());
		formData.add("import_url", importUrl);

		ClientResponse response = apiClient.post(formData, "projects");
		if (response.getStatus() != ClientResponse.Status.CREATED.getStatusCode()) {
			ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
			throw new RuntimeException(errorMessage.getMessage());
		}
		
		return (response.getEntity(Project.class));
	}
	
	
	public boolean deleteProject (Project project)  throws IOException {
		return (deleteProject(project.getId()));
	}
	

	public boolean deleteProject (Integer projectId)  throws IOException {
		
		if (projectId == null) {
			throw new RuntimeException("projectId cannot be null");
		}
		
		ClientResponse response = apiClient.delete(null, "projects", projectId);
		return (response.getStatus() == ClientResponse.Status.OK.getStatusCode());		
	}
	
	/**
	 * Creates a merge request and optionally assignes it.
	 * 
	 * @param projectId the ID of a project, required
	 * @param sourceBranch the source branch, required
	 * @param targetBranch the target branch, required
	 * @param title the title for the merge request, required
	 * @param assigneeId the Assignee user ID, optional
	 * @return the created MergeRequest instance
	 * @throws IOException 
	 */
	public MergeRequest createMergeRequest (Integer projectId, String sourceBranch, String targetBranch, String title, Integer assigneeId) 
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
		formData.add("id",  projectId);
		formData.add("source_branch", sourceBranch);		
		formData.add("target_branch", targetBranch);
		formData.add("title", title);
		
		if (assigneeId != null) {
			formData.add("assignee_id", assigneeId);
		}
		
		ClientResponse response = apiClient.post(formData, "projects", projectId, "merge-requests");
		if (response.getStatus() != ClientResponse.Status.CREATED.getStatusCode()) {
			ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
			throw new RuntimeException(errorMessage.getMessage());
		}
		
		return (response.getEntity(MergeRequest.class));
	}	
}
