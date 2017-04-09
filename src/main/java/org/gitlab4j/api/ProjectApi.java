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

    public ProjectApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of projects accessible by the authenticated user.
     *
     * GET /projects
     * 
     * @return a list of projects accessible by the authenticated user
     * @throws GitLabApiException if any exception occurs
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
     * @throws GitLabApiException if any exception occurs
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
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getOwnedProjects() throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("owned", true);
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {
        }));
    }

    /**
     * Get a specific project, which is owned by the authentication user.
     *
     * GET /projects/:id
     * 
     * @param projectId the ID of the project to get
     * @return the specified project
     * @throws GitLabApiException if any exception occurs
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
     * @param project the name of the project to get
     * @return the specified project
     * @throws GitLabApiException if any exception occurs
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
     * @param groupId the group ID to create the project under
     * @param projectName the name of the project top create
     * @return the created project
     * @throws GitLabApiException if any exception occurs
     */
    public Project createProject(Integer groupId, String projectName) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("namespace_id", groupId)
                .withParam("name", projectName, true);

        Response response = post(Response.Status.CREATED, formData, "projects");
        return (response.readEntity(Project.class));
    }

    /**
     * Creates new project owned by the current user.
     * 
     * @param project the Project instance with the configuration for the new project
     * @return a Project instance with the newly created project info
     * @throws GitLabApiException if any exception occurs
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
     * @param importUrl the URL to import the repository from
     * @return a Project instance with the newly created project info
     * @throws GitLabApiException if any exception occurs
     */
    public Project createProject(Project project, String importUrl) throws GitLabApiException {

        if (project == null) {
            return (null);
        }

        String name = project.getName();
        if (name == null || name.trim().length() == 0) {
            return (null);
        }

        GitLabApiForm formData = new GitLabApiForm()
            .withParam("name", name, true)
            .withParam("description", project.getDescription())
            .withParam("issues_enabled", project.getIssuesEnabled())
            .withParam("wall_enabled", project.getWallEnabled())
            .withParam("merge_requests_enabled", project.getMergeRequestsEnabled())
            .withParam("wiki_enabled", project.getWikiEnabled())
            .withParam("snippets_enabled", project.getSnippetsEnabled())
            .withParam("public", project.getPublic())
            .withParam("visibility_level", project.getVisibilityLevel())
            .withParam("import_url", importUrl);
        
        if (project.getNamespace() != null) {
            formData.withParam("namespace_id", project.getNamespace().getId());
        }

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
     * @param isPublic Whether the project is public or private, if true same as setting visibilityLevel = 20, otherwise null indicates to use GitLab default
     * @param visibilityLevel The visibility level of the project, otherwise null indicates to use GitLab default
     * @param importUrl The Import URL for the project, otherwise null
     * @return the GitLab Project
     * @throws GitLabApiException if any exception occurs
     */
    public Project createProject(String name, Integer namespaceId, String description, Boolean issuesEnabled, Boolean wallEnabled, Boolean mergeRequestsEnabled,
            Boolean wikiEnabled, Boolean snippetsEnabled, Boolean isPublic, Integer visibilityLevel, String importUrl) throws GitLabApiException {

        if (name == null || name.trim().length() == 0) {
            return (null);
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("namespace_id", namespaceId)
                .withParam("description", description)
                .withParam("issues_enabled", issuesEnabled)
                .withParam("wall_enabled", wallEnabled)
                .withParam("merge_requests_enabled", mergeRequestsEnabled)
                .withParam("wiki_enabled", wikiEnabled)
                .withParam("snippets_enabled", snippetsEnabled)
                .withParam("public", isPublic)
                .withParam("visibility_level", visibilityLevel)
                .withParam("import_url", importUrl);

        Response response = post(Response.Status.CREATED, formData, "projects");
        return (response.readEntity(Project.class));
    }

    /**
     * Removes project with all resources(issues, merge requests etc).
     * 
     * DELETE /projects/:id
     * 
     * @param projectId the project ID to remove
     * @throws GitLabApiException if any exception occurs
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
     * @param project the Project instance to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteProject(Project project) throws GitLabApiException {
        deleteProject(project.getId());
    }

    /**
     * Get a list of project team members.
     * 
     * GET /projects/:id/members
     * 
     * @param projectId the project ID to get team members for
     * @return the members belonging to the specified project
     * @throws GitLabApiException if any exception occurs
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
     * @param projectId the project ID to get team member for
     * @param userId the user ID of the member
     * @return the member specified by the project ID/user ID pair
     * @throws GitLabApiException if any exception occurs
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
     * @param projectId the project ID to add the team member to
     * @param userId the user ID of the member to add
     * @param accessLevel
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer projectId, Integer userId, Integer accessLevel) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("user_id", userId, true)
                .withParam("access_level", accessLevel, true);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "members");
        return (response.readEntity(Member.class));
    }

    /**
     * Removes user from project team.
     * 
     * DELETE /projects/:id/members/:user_id
     * 
     * @param projectId the project ID to remove the team member from
     * @param userId the user ID of the member to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void removeMember(Integer projectId, Integer userId) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", projectId, "members", userId);
    }

    /**
     * Get a project events for specific project. Sorted from newest to latest.
     * 
     * GET /projects/:id/events
     * 
     * @param projectId the project ID to get events for
     * @return the project events for the specified project
     * @throws GitLabApiException if any exception occurs
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
     * @param projectId the project ID to get project hooks for
     * @return a list of project hooks for the specified project
     * @throws GitLabApiException if any exception occurs
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
     * @param projectId the project ID to get the hook for
     * @param hookId the ID of the hook to get
     * @return the project hook for the specified project ID/hook ID pair
     * @throws GitLabApiException if any exception occurs
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
     * @param projectName the name of the project
     * @param url the callback URL for the hook
     * @param enabledHooks a ProjectHook instance specifying which hooks to enable
     * @param enableSslVerification enable SSL verification
     * @param secretToken the secret token to pass back to the hook
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook addHook(String projectName, String url, ProjectHook enabledHooks, boolean enableSslVerification, String secretToken) throws GitLabApiException {

        if (projectName == null) {
            return (null);
        }
        
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("url", url, true)
                .withParam("push_events", enabledHooks.getPushEvents(), false)
                .withParam("issues_events", enabledHooks.getIssuesEvents(), false)
                .withParam("merge_requests_events", enabledHooks.getMergeRequestsEvents(), false)
                .withParam("tag_push_events", enabledHooks.getTagPushEvents(), false)
                .withParam("note_events", enabledHooks.getNoteEvents(), false)
                .withParam("job_events", enabledHooks.getJobEvents(), false)
                .withParam("pipeline_events", enabledHooks.getPipelineEvents(), false)
                .withParam("wiki_events", enabledHooks.getWikiPageEvents(), false)
                .withParam("enable_ssl_verification", enabledHooks.getEnableSslVerification())
                .withParam("token", secretToken, false);
        Response response = post(Response.Status.CREATED, formData, "projects", projectName, "hooks");
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Adds a hook to project.
     * 
     * POST /projects/:id/hooks
     * 
     * @param projectId the project ID to add the project hook to
     * @param url the callback URL for the hook
     * @param enabledHooks a ProjectHook instance specifying which hooks to enable
     * @param enableSslVerification enable SSL verification
     * @param secretToken the secret token to pass back to the hook
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook addHook(Integer projectId, String url, ProjectHook enabledHooks, boolean enableSslVerification, String secretToken) throws GitLabApiException {

        if (projectId == null) {
            return (null);
        }
        
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("url", url, true)
                .withParam("push_events", enabledHooks.getPushEvents(), false)
                .withParam("issues_events", enabledHooks.getIssuesEvents(), false)
                .withParam("merge_requests_events", enabledHooks.getMergeRequestsEvents(), false)
                .withParam("tag_push_events", enabledHooks.getTagPushEvents(), false)
                .withParam("note_events", enabledHooks.getNoteEvents(), false)
                .withParam("job_events", enabledHooks.getJobEvents(), false)
                .withParam("pipeline_events", enabledHooks.getPipelineEvents(), false)
                .withParam("wiki_events", enabledHooks.getWikiPageEvents(), false)
                .withParam("enable_ssl_verification", enabledHooks.getEnableSslVerification())
                .withParam("token", secretToken, false);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "hooks");
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Adds a hook to project.
     * 
     * POST /projects/:id/hooks
     * 
     * @param project the Project instance to add the project hook to
     * @param url the callback URL for the hook
     * @param enabledHooks a ProjectHook instance specifying which hooks to enable
     * @param enableSslVerification enable SSL verification
     * @param secretToken the secret token to pass back to the hook
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook addHook(Project project, String url, ProjectHook enabledHooks, boolean enableSslVerification, String secretToken) throws GitLabApiException {

        if (project == null) {
            return (null);
        }

        return (addHook(project.getId(), url, enabledHooks, enableSslVerification, secretToken));
    }

    /**
     * Adds a hook to project.
     * 
     * POST /projects/:id/hooks
     * 
     * @param project the Project instance to add the project hook to
     * @param url the callback URL for the hook
     * @param doPushEvents flag specifying whether to do push events
     * @param doIssuesEvents flag specifying whether to do issues events
     * @param doMergeRequestsEvents flag specifying whether to do merge requests events
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
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
     * @param projectId the project ID to add the project hook to
     * @param url the callback URL for the hook
     * @param doPushEvents flag specifying whether to do push events
     * @param doIssuesEvents flag specifying whether to do issues events
     * @param doMergeRequestsEvents flag specifying whether to do merge requests events
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook addHook(Integer projectId, String url, boolean doPushEvents, boolean doIssuesEvents, boolean doMergeRequestsEvents) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("url", url)
                .withParam("push_events", doPushEvents)
                .withParam("issues_enabled", doIssuesEvents)
                .withParam("merge_requests_events", doMergeRequestsEvents);

        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "hooks");
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Deletes a hook from the project.
     * 
     * DELETE /projects/:id/hooks/:hook_id
     * 
     * @param projectId the project ID to delete the project hook from
     * @param hookId the project hook ID to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteHook(Integer projectId, Integer hookId) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", projectId, "hooks", hookId);
    }

    /**
     * Deletes a hook from the project.
     * 
     * DELETE /projects/:id/hooks/:hook_id
     * 
     * @param hook the ProjectHook instance to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteHook(ProjectHook hook) throws GitLabApiException {
        deleteHook(hook.getProjectId(), hook.getId());
    }

    /**
     * Modifies a hook for project.
     * 
     * PUT /projects/:id/hooks/:hook_id
     * 
     * @param hook the ProjectHook instance that contains the project hook info to modify
     * @return the modified project hook
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook modifyHook(ProjectHook hook) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
            .withParam("url", hook.getUrl(), true)
            .withParam("push_events", hook.getPushEvents(), false)
            .withParam("issues_events", hook.getIssuesEvents(), false)
            .withParam("merge_requests_events", hook.getMergeRequestsEvents(), false)
            .withParam("tag_push_events", hook.getTagPushEvents(), false)
            .withParam("note_events", hook.getNoteEvents(), false)
            .withParam("job_events", hook.getJobEvents(), false)
            .withParam("pipeline_events", hook.getPipelineEvents(), false)
            .withParam("wiki_events", hook.getWikiPageEvents(), false)
            .withParam("enable_ssl_verification", hook.getEnableSslVerification(), false)
            .withParam("token", hook.getToken(), false);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", hook.getProjectId(), "hooks", hook.getId());
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Get a list of project's issues.
     *
     * GET /projects/:id/issues
     *
     * @param projectId the project ID to get the issues for
     * @return a list of project's issues
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getIssues(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {
        }));
    }
}
