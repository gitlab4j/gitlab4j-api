package org.gitlab4j.api;

import org.gitlab4j.api.models.Event;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProjectHook;
import org.glassfish.jersey.uri.UriComponent;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * This class provides an entry point to all the GitLab API project calls.
 */
public class ProjectApi extends AbstractApi {

    ProjectApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of projects accessible by the authenticated user.
     *
     * GET /projects
     * 
     * @return a list of projects accessible by the authenticated user
     * @throws GitLabApiException
     */
    public List<Project> getProjects() throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects");
        return (response.readEntity(new GenericType<List<Project>>() {
        }));
    }

    /**
     * Get a list of all GitLab projects (admin only).
     *
     * GET /projects/all
     * 
     * @return a list of all GitLab projects
     * @throws GitLabApiException
     */
    public List<Project> getAllProjects() throws GitLabApiException {
        Response response = get(Response.Status.OK, UriComponent.decodeQuery("per_page=9999", true), "projects", "all");
        return (response.readEntity(new GenericType<List<Project>>() {
        }));
    }

    /**
     * Get a list of projects owned by the authenticated user.
     * 
     * GET /projects/owned
     * 
     * @return a list of projects owned by the authenticated user
     * @throws GitLabApiException
     */
    public List<Project> getOwnedProjects() throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", "owned");
        return (response.readEntity(new GenericType<List<Project>>() {
        }));
    }

    /**
     * Get a specific project, which is owned by the authentication user.
     *
     * GET /projects/:id
     * 
     * @param projectId
     * @return the specified project
     * @throws GitLabApiException
     */
    public Project getProject(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId);
        return (response.readEntity(Project.class));
    }

    /**
     * Get a specific project, which is owned by the authentication user.
     *
     * GET /projects/:id
     * 
     * @param namespace the name of the project namespace or group
     * @param project
     * @return the specified project
     * @throws GitLabApiException
     */
    public Project getProject(String namespace, String project) throws GitLabApiException {

        if (namespace == null) {
            throw new RuntimeException("namespace cannot be null");
        }

        if (project == null) {
            throw new RuntimeException("project cannot be null");
        }

        String pid = null;
        try {
            pid = URLEncoder.encode(namespace + "/" + project, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw (new GitLabApiException(uee));
        }

        Response response = get(Response.Status.OK, null, "projects", pid);
        return (response.readEntity(Project.class));
    }

    /**
     * Create a new project in the specified group.
     * 
     * @param groupId
     * @param projectName
     * @return the created project
     * @throws GitLabApiException
     */
    public Project createProject(Integer groupId, String projectName) throws GitLabApiException {

        Form formData = new Form();
        addFormParam(formData, "namespace_id", groupId);
        addFormParam(formData, "name", projectName, true);

        Response response = post(Response.Status.CREATED, formData, "projects");
        return (response.readEntity(Project.class));
    }

    /**
     * Creates new project owned by the current user.
     * 
     * @param project the Project instance with the configuration for the new project
     * @return a Project instance with the newly created project info
     * @throws GitLabApiException
     */
    public Project createProject(Project project) throws GitLabApiException {
        return (createProject(project, null));
    }

    /**
     * Creates new project owned by the current user. The following properties on the Project instance
     * are utilized in the creation of the project:
     * 
     * name (required) - new project name
     * description (optional) - short project description
     * issuesEnabled (optional)
     * wallEnabled (optional)
     * mergeRequestsEnabled (optional)
     * wikiEnabled (optional)
     * snippetsEnabled (optional)
     * isPublic (optional) - if true same as setting visibility_level = 20
     * visibilityLevel (optional)
     * 
     * @param project the Project instance with the configuration for the new project
     * @param importUrl
     * @return a Project instance with the newly created project info
     * @throws GitLabApiException
     */
    public Project createProject(Project project, String importUrl) throws GitLabApiException {

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

        addFormParam(formData, "name", name, true);
        addFormParam(formData, "description", project.getDescription());
        addFormParam(formData, "issues_enabled", project.getIssuesEnabled());
        addFormParam(formData, "wall_enabled", project.getWallEnabled());
        addFormParam(formData, "merge_requests_enabled", project.getMergeRequestsEnabled());
        addFormParam(formData, "wiki_enabled", project.getWikiEnabled());
        addFormParam(formData, "snippets_enabled", project.getSnippetsEnabled());
        addFormParam(formData, "public", project.getPublic());
        addFormParam(formData, "visibility_level", project.getVisibilityLevel());
        addFormParam(formData, "import_url", importUrl);

        Response response = post(Response.Status.CREATED, formData, "projects");
        return (response.readEntity(Project.class));
    }

    /**
     * Creates a Project
     *
     * @param name The name of the project
     * @param namespaceId The Namespace for the new project, otherwise null indicates to use the GitLab default (user)
     * @param description A description for the project, null otherwise
     * @param issuesEnabled Whether Issues should be enabled, otherwise null indicates to use GitLab default
     * @param wallEnabled Whether The Wall should be enabled, otherwise null indicates to use GitLab default
     * @param mergeRequestsEnabled Whether Merge Requests should be enabled, otherwise null indicates to use GitLab default
     * @param wikiEnabled Whether a Wiki should be enabled, otherwise null indicates to use GitLab default
     * @param snippetsEnabled Whether Snippets should be enabled, otherwise null indicates to use GitLab default
     * @param publik Whether the project is public or private, if true same as setting visibilityLevel = 20, otherwise null indicates to use GitLab default
     * @param visibilityLevel The visibility level of the project, otherwise null indicates to use GitLab default
     * @param importUrl The Import URL for the project, otherwise null
     * @return the Gitlab Project
     * @throws GitLabApiException
     */
    public Project createProject(String name, Integer namespaceId, String description, Boolean issuesEnabled, Boolean wallEnabled, Boolean mergeRequestsEnabled,
            Boolean wikiEnabled, Boolean snippetsEnabled, Boolean publik, Integer visibilityLevel, String importUrl) throws GitLabApiException {

        if (name == null || name.trim().length() == 0) {
            return (null);
        }

        Form formData = new Form();
        addFormParam(formData, "name", name, true);
        addFormParam(formData, "namespace_id", namespaceId);
        addFormParam(formData, "description", description);
        addFormParam(formData, "issues_enabled", issuesEnabled);
        addFormParam(formData, "wall_enabled", wallEnabled);
        addFormParam(formData, "merge_requests_enabled", mergeRequestsEnabled);
        addFormParam(formData, "wiki_enabled", wikiEnabled);
        addFormParam(formData, "snippets_enabled", snippetsEnabled);
        addFormParam(formData, "public", publik);
        addFormParam(formData, "visibility_level", visibilityLevel);
        addFormParam(formData, "import_url", importUrl);

        Response response = post(Response.Status.CREATED, formData, "projects");
        return (response.readEntity(Project.class));
    }

    /**
     * Removes project with all resources(issues, merge requests etc).
     * 
     * DELETE /projects/:id
     * 
     * @param projectId
     * @throws GitLabApiException
     */
    public void deleteProject(Integer projectId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        delete(Response.Status.OK, null, "projects", projectId);
    }

    /**
     * Removes project with all resources(issues, merge requests etc).
     * 
     * DELETE /projects/:id
     * 
     * @param project
     * @throws GitLabApiException
     */
    public void deleteProject(Project project) throws GitLabApiException {
        deleteProject(project.getId());
    }

    /**
     * Get a list of project team members.
     * 
     * GET /projects/:id/members
     * 
     * @param projectId
     * @return the members belonging to the specified project
     * @throws GitLabApiException
     */
    public List<Member> getMembers(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "members");
        return (response.readEntity(new GenericType<List<Member>>() {
        }));
    }

    /**
     * Gets a project team member.
     * 
     * GET /projects/:id/members/:user_id
     * 
     * @param projectId
     * @param userId
     * @return the member specified by the project ID/user ID pair
     * @throws GitLabApiException
     */
    public Member getMember(Integer projectId, Integer userId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "members", userId);
        return (response.readEntity(Member.class));
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
     * @return the added member
     * @throws GitLabApiException
     */
    public Member addMember(Integer projectId, Integer userId, Integer accessLevel) throws GitLabApiException {

        Form formData = new Form();
        formData.param("user_id", userId.toString());
        formData.param("access_level", accessLevel.toString());
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "members");
        return (response.readEntity(Member.class));
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
    public void removeMember(Integer projectId, Integer userId) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", projectId, "members", userId);
    }

    /**
     * Get a project events for specific project. Sorted from newest to latest.
     * 
     * GET /projects/:id/events
     * 
     * @param projectId
     * @return the project events for the specified project
     * @throws GitLabApiException
     */
    public List<Event> getProjectEvents(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "events");
        return (response.readEntity(new GenericType<List<Event>>() {
        }));
    }

    /**
     * Get list of project hooks.
     * 
     * GET /projects/:id/hooks
     * 
     * @param projectId
     * @return a list of project hooks for the specified project
     * @throws GitLabApiException
     */
    public List<ProjectHook> getHooks(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "hooks");
        return (response.readEntity(new GenericType<List<ProjectHook>>() {
        }));
    }

    /**
     * Get a specific hook for project.
     * 
     * GET /projects/:id/hooks/:hook_id
     * 
     * @param projectId
     * @param hookId
     * @return the project hook for the specified project ID/hook ID pair
     * @throws GitLabApiException
     */
    public ProjectHook getHook(Integer projectId, Integer hookId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "hooks", hookId);
        return (response.readEntity(ProjectHook.class));
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
     * @return the added project hook
     * @throws GitLabApiException
     */
    public ProjectHook addHook(Project project, String url, boolean doPushEvents, boolean doIssuesEvents, boolean doMergeRequestsEvents) throws GitLabApiException {

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
     * @return the added project hook
     * @throws GitLabApiException
     */
    public ProjectHook addHook(Integer projectId, String url, boolean doPushEvents, boolean doIssuesEvents, boolean doMergeRequestsEvents) throws GitLabApiException {

        Form formData = new Form();
        formData.param("url", url);
        formData.param("push_events", Boolean.toString(doPushEvents));
        formData.param("issues_enabled", Boolean.toString(doIssuesEvents));
        formData.param("merge_requests_events", Boolean.toString(doMergeRequestsEvents));

        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "hooks");
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Deletes a hook from the project.
     * 
     * DELETE /projects/:id/hooks/:hook_id
     * 
     * @param projectId
     * @param hookId
     * @throws GitLabApiException
     */
    public void deleteHook(Integer projectId, Integer hookId) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", projectId, "hooks", hookId);
    }

    /**
     * Deletes a hook from the project.
     * 
     * DELETE /projects/:id/hooks/:hook_id
     * 
     * @param hook
     * @throws GitLabApiException
     */
    public void deleteHook(ProjectHook hook) throws GitLabApiException {
        deleteHook(hook.getProjectId(), hook.getId());
    }

    /**
     * Modifies a hook for project.
     * 
     * PUT /projects/:id/hooks/:hook_id
     * 
     * @param hook
     * @return the modified project hook
     * @throws GitLabApiException
     */
    public ProjectHook modifyHook(ProjectHook hook) throws GitLabApiException {

        Form formData = new Form();
        formData.param("url", hook.getUrl());
        formData.param("push_events", hook.getPushEvents().toString());
        formData.param("issues_enabled", hook.getIssuesEvents().toString());
        formData.param("merge_requests_events", hook.getMergeRequestsEvents().toString());

        Response response = put(Response.Status.OK, formData.asMap(), "projects", hook.getProjectId(), "hooks", hook.getId());
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Get a list of project's issues.
     *
     * GET /projects/:id/issues
     *
     * @param projectId
     * @return a list of project's issues
     * @throws GitLabApiException
     */
    public List<Issue> getIssues(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {
        }));
    }
}
