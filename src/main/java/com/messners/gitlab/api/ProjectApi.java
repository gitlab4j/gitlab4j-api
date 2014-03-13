package com.messners.gitlab.api;

import java.io.IOException;
import java.util.List;

import com.messners.gitlab.api.models.ErrorMessage;
import com.messners.gitlab.api.models.Event;
import com.messners.gitlab.api.models.Member;
import com.messners.gitlab.api.models.Project;
import com.messners.gitlab.api.models.ProjectHook;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.representation.Form;

public class ProjectApi extends AbstractApi {
	
	ProjectApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	

	/**
	 * Get a list of projects accessible by the authenticated user.
	 *
	 * GET /projects
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Project> getProjects () throws IOException {	
		 ClientResponse response = get(null, "projects");
		 return (response.getEntity(new GenericType<List<Project>>() {}));
	}
	

	/**
	 * Get a list of all GitLab projects (admin only).
	 *
	 * GET /projects/all
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Project> getAllProjects () throws IOException {	
		 ClientResponse response = get(null, "projects", "all");
		 return (response.getEntity(new GenericType<List<Project>>() {}));
	}

	
	/**
	 * Get a list of projects owned by the authenticated user.
	 * 
	 * GET /projects/owned
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Project> getOwnedProjects () throws IOException {	
		 ClientResponse response = get(null, "projects", "owned");
		 return (response.getEntity(new GenericType<List<Project>>() {}));
	}

	
	/**
	 * Get a specific project, which is owned by the authentication user.
	 *
	 * GET /projects/:id
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public Project getProject (Integer projectId) throws IOException {
		ClientResponse response = get(null, "projects", projectId);
		return (response.getEntity(Project.class));
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
	
		ClientResponse response = post(formData, "projects");
		if (response.getStatus() != ClientResponse.Status.CREATED.getStatusCode()) {
			ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
			throw new RuntimeException(errorMessage.getMessage());
		}
		
		return (response.getEntity(Project.class));
	}

	
	/**
	 * Removes project with all resources(issues, merge requests etc).
	 * 
	 * DELETE /projects/:id
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public boolean deleteProject (Integer projectId)  throws IOException {
		
		if (projectId == null) {
			throw new RuntimeException("projectId cannot be null");
		}
		
		ClientResponse response = delete(null, "projects", projectId);
		return (response.getStatus() == ClientResponse.Status.OK.getStatusCode());		
	}

	
	/**
	 * Removes project with all resources(issues, merge requests etc).
	 * 
	 * DELETE /projects/:id
	 * 
	 * @param project
	 * @return
	 * @throws IOException
	 */
	public boolean deleteProject (Project project)  throws IOException {
		return (deleteProject(project.getId()));
	}
	
	
	/**
	 * Get a list of project team members.
	 * 
	 * GET /projects/:id/members
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public List<Member> getMembers (Integer projectId) throws IOException {
		ClientResponse response = get(null, "project", projectId, "members");
		return (response.getEntity(new GenericType<List<Member>>() {}));
	}
	
	
	/**
	 * Gets a project team member.
	 * 
	 * GET /projects/:id/members/:user_id
	 * 
	 * @param projectId
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public Member getMember (Integer projectId, Integer userId) throws IOException {
		ClientResponse response = get(null, "project", projectId, "members", userId);
		return (response.getEntity(Member.class));
	}
	
	
	/**
	 * Adds a user to a project team. This is an idempotent method and can be called multiple times
	 * with the same parameters. Adding team membership to a user that is already a member does not
	 * affect the existing membership.
	 * 
	 * POST /projects/:id/members
	 * 
	 * @param projectId
	 * @param userId
	 * @param accessLevel
	 * @return
	 * @throws IOException
	 */
	public Member addMember (Integer projectId, Integer userId, Integer accessLevel) throws IOException {
		
		Form formData = new Form();
		formData.add("user_id",  userId);		
		formData.add("access_level",  accessLevel);		
		ClientResponse response = post(formData, "project", projectId, "members");
		return (response.getEntity(Member.class));
	}
	
	
	/**
	 * Removes user from project team.
	 * 
	 * DELETE /projects/:id/members/:user_id
	 * 
	 * @param projectId
	 * @param userId
	 * @throws IOException
	 */
	public boolean removeMember (Integer projectId, Integer userId) throws IOException {
		ClientResponse response = delete(null, "project", projectId, "members", userId);
		return (response.getStatus() == ClientResponse.Status.OK.getStatusCode());
	}


	/**
	 * Get a project events for specific project.  Sorted from newest to latest.
	 * 
	 * GET /projects/:id/events
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public List<Event> getProjectEvents (Integer projectId) throws IOException {
		ClientResponse response = get(null, "project", projectId, "events");
		return (response.getEntity(new GenericType<List<Event>>() {}));
	}

	
	/**
	 * Get list of project hooks.
	 * 
	 * GET /projects/:id/hooks
	 * 
	 * @param projectId
	 * @return
	 * @throws IOException
	 */
	public List<ProjectHook> getHooks (Integer projectId) throws IOException {
		 ClientResponse response = get(null, "projects", projectId, "hooks");
		 return (response.getEntity(new GenericType<List<ProjectHook>>() {} ));
	}
	
	
	/**
	 * Get a specific hook for project.
	 * 
	 * GET /projects/:id/hooks/:hook_id
	 * 
	 * @param projectId
	 * @param hookId
	 * @return
	 * @throws IOException
	 */
	public ProjectHook getHook (Integer projectId, Integer hookId) throws IOException {
		 ClientResponse response = get(null, "projects", projectId, "hooks", hookId);
		 return (response.getEntity(ProjectHook.class));
	}
	
	
	/**
	 * Adds a hook to project.
	 * 
	 * POST /projects/:id/hooks
	 * 
	 * @param project
	 * @param url
	 * @param doPushEvents
	 * @param doIssuesEvents
	 * @param doMergeRequestsEvents
	 * @return
	 * @throws IOException
	 */
	public ProjectHook addHook (Project project, String url, 
			boolean doPushEvents, boolean doIssuesEvents, boolean doMergeRequestsEvents)
			throws IOException {
		
		if (project == null) {
			return (null);
		}
		
		return (addHook(project.getId(), url, doPushEvents, doIssuesEvents, doMergeRequestsEvents));
	}
	
	
	/**
	 * Adds a hook to project.
	 * 
	 * POST /projects/:id/hooks
	 * 
	 * @param projectId
	 * @param url
	 * @param doPushEvents
	 * @param doIssuesEvents
	 * @param doMergeRequestsEvents
	 * @return
	 * @throws IOException
	 */
	public ProjectHook addHook (Integer projectId, String url, 
			boolean doPushEvents, boolean doIssuesEvents, boolean doMergeRequestsEvents)
			throws IOException {
		
		Form formData = new Form();
		formData.add("url",  url);		
		formData.add("push_events", doPushEvents);
		formData.add("issues_enabled", doIssuesEvents);	
		formData.add("merge_requests_events", doMergeRequestsEvents);

		ClientResponse response = post(formData, "projects", projectId, "hooks");
		if (response.getStatus() != ClientResponse.Status.CREATED.getStatusCode()) {
			ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
			throw new RuntimeException(errorMessage.getMessage());
		}
		
		return (response.getEntity(ProjectHook.class));	
	}


	/**
	 * Modifies a hook for project.
	 * 
	 * PUT /projects/:id/hooks/:hook_id
	 * 
	 * @param projectId
	 * @param url
	 * @param doPushEvents
	 * @param doIssuesEvents
	 * @param doMergeRequestsEvents
	 * @return
	 * @throws IOException
	 */
	public ProjectHook modifyHook (ProjectHook hook)
			throws IOException {
		
		Form formData = new Form();
		formData.add("url",  hook.getUrl());		
		formData.add("push_events", hook.getPushEvents());
		formData.add("issues_enabled", hook.getIssuesEvents());	
		formData.add("merge_requests_events", hook.getMergeRequestsEvents());

		ClientResponse response = put(formData, "projects", hook.getProjectId(), "hooks", hook.getId());
		return (response.getEntity(ProjectHook.class));	
	}
}
