package com.messners.gitlab.api;

import java.util.List;

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
	 * @throws GitLabApiException 
	 */
	public List<Project> getProjects () throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects");
		return (response.getEntity(new GenericType<List<Project>>() {}));
	}
	

	/**
	 * Get a list of all GitLab projects (admin only).
	 *
	 * GET /projects/all
	 * 
	 * @return
	 * @throws GitLabApiException 
	 */
	public List<Project> getAllProjects () throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", "all");
		return (response.getEntity(new GenericType<List<Project>>() {}));
	}

	
	/**
	 * Get a list of projects owned by the authenticated user.
	 * 
	 * GET /projects/owned
	 * 
	 * @return
	 * @throws GitLabApiException 
	 */
	public List<Project> getOwnedProjects () throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", "owned");
		return (response.getEntity(new GenericType<List<Project>>() {}));
	}

	
	/**
	 * Get a specific project, which is owned by the authentication user.
	 *
	 * GET /projects/:id
	 * 
	 * @param projectId
	 * @return
	 * @throws GitLabApiException 
	 */
	public Project getProject (Integer projectId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId);
		return (response.getEntity(Project.class));
	}
	
	
	/**
	 * Create a new project in the specified group.
	 * 
	 * @param groupId
	 * @param projectName
	 * @return
	 * @throws GitLabApiException 
	 */
	public Project createProject (Integer groupId, String projectName)  throws GitLabApiException {
		
		Form formData = new Form();
		addFormParam(formData, "namespace_id", groupId);
		addFormParam(formData, "name",  projectName, true);
	
		ClientResponse response = post(ClientResponse.Status.CREATED, formData, "projects");
		return (response.getEntity(Project.class));
	}
	
	
	/**
	 * Creates new project owned by the current user.  
	 * 
	 * @param project the Project instance with the configuration for the new project
	 * @return a Project instance with the newly created project info
	 * @throws IOException
	 * @throws GitLabApiException 
	 */	
	public Project createProject (Project project) throws GitLabApiException {
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
	 * @throws GitLabApiException 
	 */	
	public Project createProject (Project project, String importUrl) throws GitLabApiException {
		
		if (project == null) {
			return (null);
		}
		
		String name = project.getName();
		if (name == null || name.trim().length() == 0) {
			return (null);
		}
		
		Form formData = new Form();
		if (project.getNamespace() != null) {
			addFormParam(formData, "namespace_id", project.getNamespace().getId());
		}		
		
		addFormParam(formData, "name",  name, true);
		addFormParam(formData, "description", project.getDescription());
		addFormParam(formData, "issues_enabled", project.getIssuesEnabled());		
		addFormParam(formData, "wall_enabled", project.getWallEnabled());
		addFormParam(formData, "merge_requests_enabled", project.getMergeRequestsEnabled());
		addFormParam(formData, "wiki_enabled", project.getWikiEnabled());
		addFormParam(formData, "snippets_enabled", project.getSnippetsEnabled());
		addFormParam(formData, "public", project.getPublic());
		addFormParam(formData, "visibility_level", project.getVisibilityLevel());
		addFormParam(formData, "import_url", importUrl);
	
		ClientResponse response = post(ClientResponse.Status.CREATED, formData, "projects");
		return (response.getEntity(Project.class));
	}

	
	/**
	 * Removes project with all resources(issues, merge requests etc).
	 * 
	 * DELETE /projects/:id
	 * 
	 * @param projectId
	 * @throws GitLabApiException 
	 */
	public void deleteProject (Integer projectId)  throws GitLabApiException {
		
		if (projectId == null) {
			throw new RuntimeException("projectId cannot be null");
		}
		
		delete(ClientResponse.Status.OK, null, "projects", projectId);
	}

	
	/**
	 * Removes project with all resources(issues, merge requests etc).
	 * 
	 * DELETE /projects/:id
	 * 
	 * @param project
	 * @throws GitLabApiException 
	 */
	public void deleteProject (Project project)  throws GitLabApiException {
		deleteProject(project.getId());
	}
	
	
	/**
	 * Get a list of project team members.
	 * 
	 * GET /projects/:id/members
	 * 
	 * @param projectId
	 * @return
	 * @throws GitLabApiException 
	 */
	public List<Member> getMembers (Integer projectId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId, "members");
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
	 * @throws GitLabApiException 
	 */
	public Member getMember (Integer projectId, Integer userId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId, "members", userId);
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
	 * @throws GitLabApiException 
	 */
	public Member addMember (Integer projectId, Integer userId, Integer accessLevel) throws GitLabApiException {
		
		Form formData = new Form();
		formData.add("user_id",  userId);		
		formData.add("access_level",  accessLevel);		
		ClientResponse response = post(ClientResponse.Status.OK, formData, "projects", projectId, "members");
		return (response.getEntity(Member.class));
	}
	
	
	/**
	 * Removes user from project team.
	 * 
	 * DELETE /projects/:id/members/:user_id
	 * 
	 * @param projectId
	 * @param userId
	 * @throws GitLabApiException 
	 */
	public boolean removeMember (Integer projectId, Integer userId) throws GitLabApiException {		
		ClientResponse response = delete(ClientResponse.Status.OK, null, "projects", projectId, "members", userId);
		return (response.getStatus() == ClientResponse.Status.OK.getStatusCode());
	}


	/**
	 * Get a project events for specific project.  Sorted from newest to latest.
	 * 
	 * GET /projects/:id/events
	 * 
	 * @param projectId
	 * @return
	 * @throws GitLabApiException 
	 */
	public List<Event> getProjectEvents (Integer projectId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId, "events");
		return (response.getEntity(new GenericType<List<Event>>() {}));
	}

	
	/**
	 * Get list of project hooks.
	 * 
	 * GET /projects/:id/hooks
	 * 
	 * @param projectId
	 * @return
	 * @throws GitLabApiException 
	 */
	public List<ProjectHook> getHooks (Integer projectId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId, "hooks");
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
	 * @throws GitLabApiException 
	 */
	public ProjectHook getHook (Integer projectId, Integer hookId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "projects", projectId, "hooks", hookId);
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
	 * @throws GitLabApiException 
	 */
	public ProjectHook addHook (Project project, String url, 
			boolean doPushEvents, boolean doIssuesEvents, boolean doMergeRequestsEvents)
			throws GitLabApiException {
		
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
	 * @throws GitLabApiException 
	 */
	public ProjectHook addHook (Integer projectId, String url, 
			boolean doPushEvents, boolean doIssuesEvents, boolean doMergeRequestsEvents)
			throws GitLabApiException {
		
		Form formData = new Form();
		formData.add("url",  url);		
		formData.add("push_events", doPushEvents);
		formData.add("issues_enabled", doIssuesEvents);	
		formData.add("merge_requests_events", doMergeRequestsEvents);

		ClientResponse response = post(ClientResponse.Status.CREATED, formData, "projects", projectId, "hooks");
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
	 * @throws GitLabApiException 
	 */
	public ProjectHook modifyHook (ProjectHook hook)
			throws GitLabApiException {
		
		Form formData = new Form();
		formData.add("url",  hook.getUrl());		
		formData.add("push_events", hook.getPushEvents());
		formData.add("issues_enabled", hook.getIssuesEvents());	
		formData.add("merge_requests_events", hook.getMergeRequestsEvents());

		ClientResponse response = put(ClientResponse.Status.OK, formData, "projects", hook.getProjectId(), "hooks", hook.getId());
		return (response.getEntity(ProjectHook.class));	
	}
}
