/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Event;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProjectHook;
import org.gitlab4j.api.models.ProjectUser;
import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.Visibility;

/**
 * This class provides an entry point to all the GitLab API project calls.
 */
public class ProjectApi extends AbstractApi implements Constants {

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
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects accessible by the authenticated user and in the specified page range.
     *
     * GET /projects
     *
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of projects accessible by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects");
        return (response.readEntity(new GenericType<List<Project>>() { }));
    }

    /**
     * Get a Pager instance of projects accessible by the authenticated user.
     *
     * GET /projects
     *
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager instance of projects accessible by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getProjects(int itemsPerPage) throws GitLabApiException {
        return (new Pager<Project>(this, Project.class, itemsPerPage, null, "projects"));
    }

    /**
     * Get a list of projects accessible by the authenticated user and matching the supplied filter parameters.
     * All filter parameters are optional.
     *
     * GET /projects
     *
     * @param archived limit by archived status
     * @param visibility limit by visibility public, internal, or private
     * @param orderBy return projects ordered by id, name, path, created_at, updated_at, or last_activity_at fields, default is created_at
     * @param sort return projects sorted in asc or desc order. Default is desc
     * @param search return list of projects matching the search criteria
     * @param simple return only the ID, URL, name, and path of each project
     * @param owned limit by projects owned by the current user
     * @param membership limit by projects that the current user is a member of
     * @param starred limit by projects starred by the current user
     * @param statistics include project statistics
     * @return a list of projects accessible by the authenticated user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link #getProjects(Boolean, Visibility,
     *      Constants.ProjectOrderBy, Constants.SortOrder, String, Boolean, Boolean, Boolean, Boolean, Boolean)}
     */
    public List<Project> getProjects(Boolean archived, Visibility visibility, String orderBy,
                                     String sort, String search, Boolean simple, Boolean owned, Boolean membership,
                                     Boolean starred, Boolean statistics) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("archived", archived)
                .withParam("visibility", visibility)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("search", search)
                .withParam("simple", simple)
                .withParam("owned", owned)
                .withParam("membership", membership)
                .withParam("starred", starred)
                .withParam("statistics", statistics)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());

        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects accessible by the authenticated user and matching the supplied filter parameters.
     * All filter parameters are optional.
     *
     * GET /projects
     *
     * @param archived limit by archived status
     * @param visibility limit by visibility public, internal, or private
     * @param orderBy return projects ordered by ID, NAME, PATH, CREATED_AT, UPDATED_AT, or
     *          LAST_ACTIVITY_AT fields, default is CREATED_AT
     * @param sort return projects sorted in asc or desc order. Default is desc
     * @param search return list of projects matching the search criteria
     * @param simple return only the ID, URL, name, and path of each project
     * @param owned limit by projects owned by the current user
     * @param membership limit by projects that the current user is a member of
     * @param starred limit by projects starred by the current user
     * @param statistics include project statistics
     * @return a list of projects accessible by the authenticated user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(Boolean archived, Visibility visibility, ProjectOrderBy orderBy,
            SortOrder sort, String search, Boolean simple, Boolean owned, Boolean membership,
            Boolean starred, Boolean statistics) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("archived", archived)
                .withParam("visibility", visibility)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("search", search)
                .withParam("simple", simple)
                .withParam("owned", owned)
                .withParam("membership", membership)
                .withParam("starred", starred)
                .withParam("statistics", statistics)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());

        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects accessible by the authenticated user and matching the supplied filter parameters.
     * All filter parameters are optional.
     *
     * GET /projects
     *
     * @param archived limit by archived status
     * @param visibility limit by visibility public, internal, or private
     * @param orderBy return projects ordered by ID, NAME, PATH, CREATED_AT, UPDATED_AT, or
     *          LAST_ACTIVITY_AT fields, default is CREATED_AT
     * @param sort return projects sorted in asc or desc order. Default is desc
     * @param search return list of projects matching the search criteria
     * @param simple return only the ID, URL, name, and path of each project
     * @param owned limit by projects owned by the current user
     * @param membership limit by projects that the current user is a member of
     * @param starred limit by projects starred by the current user
     * @param statistics include project statistics
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of projects accessible by the authenticated user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(Boolean archived, Visibility visibility, ProjectOrderBy orderBy,
            SortOrder sort, String search, Boolean simple, Boolean owned, Boolean membership,
            Boolean starred, Boolean statistics, int page, int perPage) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("archived", archived)
                .withParam("visibility", visibility)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("search", search)
                .withParam("simple", simple)
                .withParam("owned", owned)
                .withParam("membership", membership)
                .withParam("starred", starred)
                .withParam("statistics", statistics)
                .withParam(PAGE_PARAM,  page)
                .withParam(PER_PAGE_PARAM, perPage);

        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of projects accessible by the authenticated user and matching the supplied filter parameters.
     * All filter parameters are optional.
     *
     * GET /projects
     *
     * @param archived limit by archived status
     * @param visibility limit by visibility public, internal, or private
     * @param orderBy return projects ordered by ID, NAME, PATH, CREATED_AT, UPDATED_AT, or
     *          LAST_ACTIVITY_AT fields, default is CREATED_AT
     * @param sort return projects sorted in asc or desc order. Default is desc
     * @param search return list of projects matching the search criteria
     * @param simple return only the ID, URL, name, and path of each project
     * @param owned limit by projects owned by the current user
     * @param membership limit by projects that the current user is a member of
     * @param starred limit by projects starred by the current user
     * @param statistics include project statistics
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of projects accessible by the authenticated user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getProjects(Boolean archived, Visibility visibility, ProjectOrderBy orderBy,
            SortOrder sort, String search, Boolean simple, Boolean owned, Boolean membership,
            Boolean starred, Boolean statistics, int itemsPerPage) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("archived", archived)
                .withParam("visibility", visibility)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("search", search)
                .withParam("simple", simple)
                .withParam("owned", owned)
                .withParam("membership", membership)
                .withParam("starred", starred)
                .withParam("statistics", statistics);

        return (new Pager<Project>(this, Project.class, itemsPerPage, formData.asMap(), "projects"));
    }

    /**
     * Get a list of projects accessible by the authenticated user that match the provided search string.
     *
     * GET /projects?search=search
     *
     * @param search the project name search criteria
     * @return a list of projects accessible by the authenticated user that match the provided search string
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(String search) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects accessible by the authenticated user that match the provided search string.
     *
     * GET /projects?search=search
     *
     * @param search the project name search criteria
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of projects accessible by the authenticated user that match the provided search string
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(String search, int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search).withParam(PAGE_PARAM,  page).withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of projects accessible by the authenticated user that match the provided search string.
     *
     * GET /projects?search=search
     *
     * @param search the project name search criteria
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of projects accessible by the authenticated user that match the provided search string
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getProjects(String search, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search);
        return (new Pager<Project>(this, Project.class, itemsPerPage, formData.asMap(), "projects"));
    }

    /**
     * Get a list of projects that the authenticated user is a member of.
     *
     * GET /projects
     *
     * @return a list of projects that the authenticated user is a member of
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getMemberProjects() throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("membership", true).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects that the authenticated user is a member of in the specified page range.
     *
     * GET /projects
     *
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of projects that the authenticated user is a member of
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getMemberProjects(int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("membership", true).withParam(PAGE_PARAM,  page).withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of projects that the authenticated user is a member of.
     *
     * GET /projects
     *
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager o Project instances that the authenticated user is a member of
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getMemberProjects(int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("membership", true);
        return (new Pager<Project>(this, Project.class, itemsPerPage, formData.asMap(), "projects"));
    }

    /**
     * Get a list of all GitLab projects (admin only).
     *
     * GET /projects/all
     *
     * @return a list of all GitLab projects
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed, no longer supported by the GitLab API
     */
    public List<Project> getAllProjects() throws GitLabApiException {

        if (!isApiVersion(ApiVersion.V3)) {
            throw new GitLabApiException("Not supported by GitLab API version " + this.getApiVersion());
        }

        Form formData = new GitLabApiForm().withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", "all");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects owned by the authenticated user.
     *
     * GET /projects
     *
     * @return a list of projects owned by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getOwnedProjects() throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("owned", true).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() { }));
    }

    /**
     * Get a list of projects owned by the authenticated user in the specified page range.
     *
     * GET /projects
     *
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of projects owned by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getOwnedProjects(int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("owned", true).withParam(PAGE_PARAM,  page).withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of projects owned by the authenticated user.
     *
     * GET /projects
     *
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a list of projects owned by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getOwnedProjects(int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("owned", true);
        return (new Pager<Project>(this, Project.class, itemsPerPage, formData.asMap(), "projects"));
    }

    /**
     * Get a list of projects starred by the authenticated user.
     *
     * GET /projects
     *
     * @return a list of projects starred by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getStarredProjects() throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("starred", true).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a list of projects starred by the authenticated user in the specified page range.
     *
     * GET /projects
     *
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of projects starred by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getStarredProjects(int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("starred", true).withParam(PAGE_PARAM, page).withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of projects starred by the authenticated user.
     *
     * GET /projects
     *
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of projects starred by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getStarredProjects(int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("starred", true).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        return (new Pager<Project>(this, Project.class, itemsPerPage, formData.asMap(), "projects"));
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
     * Get an Optional instance with the value for the specific project, which is owned by the authentication user.
     *
     * GET /projects/:id
     *
     * @param projectId the ID of the project to get
     * @return an Optional instance with the specified project as a value
     */
    public Optional<Project> getOptionalProject(Integer projectId) {
        try {
            return (Optional.ofNullable(getProject(projectId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
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

        String projectPath = null;
        try {
            projectPath = URLEncoder.encode(namespace + "/" + project, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw (new GitLabApiException(uee));
        }

        Response response = get(Response.Status.OK, null, "projects", projectPath);
        return (response.readEntity(Project.class));
    }

    /**
     * Get an Optional instance with the value for the specific project, which is owned by the authentication user.
     *
     * GET /projects/:id
     *
     * @param namespace the name of the project namespace or group
     * @param project the name of the project
     * @return an Optional instance with the specified project as a value
     */
    public Optional<Project> getOptionalProject(String namespace, String project) {
        try {
            return (Optional.ofNullable(getProject(namespace, project)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
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
        GitLabApiForm formData = new GitLabApiForm().withParam("namespace_id", groupId).withParam("name", projectName, true);
        Response response = post(Response.Status.CREATED, formData, "projects");
        return (response.readEntity(Project.class));
    }

    /**
     * Create a new project with the current user's namespace.
     *
     * @param projectName the name of the project top create
     * @return the created project
     * @throws GitLabApiException if any exception occurs
     */
    public Project createProject(String projectName) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("name", projectName, true);
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
     * name (name or path are required) - new project name
     * path (name or path are required) - new project path
     * defaultBranch (optional) - master by default
     * description (optional) - short project description
     * visibility (optional) - Limit by visibility public, internal, or private
     * visibilityLevel (optional)
     * issuesEnabled (optional) - Enable issues for this project
     * mergeRequestsEnabled (optional) - Enable merge requests for this project
     * wikiEnabled (optional) - Enable wiki for this project
     * snippetsEnabled (optional) - Enable snippets for this project
     * jobsEnabled (optional) - Enable jobs for this project
     * containerRegistryEnabled (optional) - Enable container registry for this project
     * sharedRunnersEnabled (optional) - Enable shared runners for this project
     * publicJobs (optional) - If true, jobs can be viewed by non-project-members
     * onlyAllowMergeIfPipelineSucceeds (optional) - Set whether merge requests can only be merged with successful jobs
     * onlyAllowMergeIfAllDiscussionsAreResolved (optional) - Set whether merge requests can only be merged when all the discussions are resolved
     * lLfsEnabled (optional) - Enable LFS
     * requestAccessEnabled (optional) - Allow users to request member access
     * repositoryStorage (optional) - Which storage shard the repository is on. Available only to admins
     * approvalsBeforeMerge (optional) - How many approvers should approve merge request by default
     * printingMergeRequestLinkEnabled (optional) - Show link to create/view merge request when pushing from the command line
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
        String path = project.getPath();

        if ((name == null || name.trim().length() == 0) && (path == null || path.trim().length() == 0)) {
            return (null);
        }

        GitLabApiForm formData = new GitLabApiForm()
            .withParam("name", name)
            .withParam("path", path)
            .withParam("default_branch", project.getDefaultBranch())
            .withParam("description", project.getDescription())
            .withParam("issues_enabled", project.getIssuesEnabled())
            .withParam("merge_requests_enabled", project.getMergeRequestsEnabled())
            .withParam("jobs_enabled", project.getJobsEnabled())
            .withParam("wiki_enabled", project.getWikiEnabled())
            .withParam("container_registry_enabled", project.getContainerRegistryEnabled())
            .withParam("snippets_enabled", project.getSnippetsEnabled())
            .withParam("shared_runners_enabled", project.getSharedRunnersEnabled())
            .withParam("public_jobs", project.getPublicJobs())
            .withParam("visibility_level", project.getVisibilityLevel())
            .withParam("only_allow_merge_if_pipeline_succeeds", project.getOnlyAllowMergeIfPipelineSucceeds())
            .withParam("only_allow_merge_if_all_discussions_are_resolved", project.getOnlyAllowMergeIfAllDiscussionsAreResolved())
            .withParam("lfs_enabled", project.getLfsEnabled())
            .withParam("request_access_enabled", project.getRequestAccessEnabled())
            .withParam("repository_storage", project.getRepositoryStorage())
            .withParam("approvals_before_merge", project.getApprovalsBeforeMerge())
            .withParam("import_url", importUrl)
            .withParam("printing_merge_request_link_enabled", project.getPrintingMergeRequestLinkEnabled());

        if (isApiVersion(ApiVersion.V3)) {
            boolean isPublic = (project.getPublic() != null ? project.getPublic() : project.getVisibility() == Visibility.PUBLIC);
            formData.withParam("public", isPublic);
        } else {
            Visibility visibility = (project.getVisibility() != null ? project.getVisibility() :
                project.getPublic() == Boolean.TRUE ? Visibility.PUBLIC : null);
            formData.withParam("visibility", visibility);
        }

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
     * @param mergeRequestsEnabled Whether Merge Requests should be enabled, otherwise null indicates to use GitLab default
     * @param wikiEnabled Whether a Wiki should be enabled, otherwise null indicates to use GitLab default
     * @param snippetsEnabled Whether Snippets should be enabled, otherwise null indicates to use GitLab default
     * @param visibility The visibility of the project, otherwise null indicates to use GitLab default
     * @param visibilityLevel The visibility level of the project, otherwise null indicates to use GitLab default
     * @param importUrl The Import URL for the project, otherwise null
     * @return the GitLab Project
     * @throws GitLabApiException if any exception occurs
     */
    public Project createProject(String name, Integer namespaceId, String description, Boolean issuesEnabled, Boolean mergeRequestsEnabled,
            Boolean wikiEnabled, Boolean snippetsEnabled, Visibility visibility, Integer visibilityLevel, String importUrl) throws GitLabApiException {

        if (isApiVersion(ApiVersion.V3)) {
            Boolean isPublic = Visibility.PUBLIC == visibility;
            return (createProject(name, namespaceId, description, issuesEnabled, mergeRequestsEnabled,
                    wikiEnabled, snippetsEnabled, isPublic, visibilityLevel, importUrl));
        }

        if (name == null || name.trim().length() == 0) {
            return (null);
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("namespace_id", namespaceId)
                .withParam("description", description)
                .withParam("issues_enabled", issuesEnabled)
                .withParam("merge_requests_enabled", mergeRequestsEnabled)
                .withParam("wiki_enabled", wikiEnabled)
                .withParam("snippets_enabled", snippetsEnabled)
                .withParam("visibility_level", visibilityLevel)
                .withParam("visibility", visibility)
                .withParam("import_url", importUrl);

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
     * @param mergeRequestsEnabled Whether Merge Requests should be enabled, otherwise null indicates to use GitLab default
     * @param wikiEnabled Whether a Wiki should be enabled, otherwise null indicates to use GitLab default
     * @param snippetsEnabled Whether Snippets should be enabled, otherwise null indicates to use GitLab default
     * @param visibility The visibility of the project, otherwise null indicates to use GitLab default
     * @param visibilityLevel The visibility level of the project, otherwise null indicates to use GitLab default
     * @param printingMergeRequestLinkEnabled Show link to create/view merge request when pushing from the command line
     * @param importUrl The Import URL for the project, otherwise null
     * @return the GitLab Project
     * @throws GitLabApiException if any exception occurs
     */
    public Project createProject(String name, Integer namespaceId, String description, Boolean issuesEnabled, Boolean mergeRequestsEnabled,
            Boolean wikiEnabled, Boolean snippetsEnabled, Visibility visibility, Integer visibilityLevel,
            Boolean printingMergeRequestLinkEnabled, String importUrl) throws GitLabApiException {

        if (isApiVersion(ApiVersion.V3)) {
            Boolean isPublic = Visibility.PUBLIC == visibility;
            return (createProject(name, namespaceId, description, issuesEnabled, mergeRequestsEnabled,
                    wikiEnabled, snippetsEnabled, isPublic, visibilityLevel, importUrl));
        }

        if (name == null || name.trim().length() == 0) {
            return (null);
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("namespace_id", namespaceId)
                .withParam("description", description)
                .withParam("issues_enabled", issuesEnabled)
                .withParam("merge_requests_enabled", mergeRequestsEnabled)
                .withParam("wiki_enabled", wikiEnabled)
                .withParam("snippets_enabled", snippetsEnabled)
                .withParam("visibility_level", visibilityLevel)
                .withParam("visibility", visibility)
                .withParam("printing_merge_request_link_enabled", printingMergeRequestLinkEnabled)
                .withParam("import_url", importUrl);

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
     * @param mergeRequestsEnabled Whether Merge Requests should be enabled, otherwise null indicates to use GitLab default
     * @param wikiEnabled Whether a Wiki should be enabled, otherwise null indicates to use GitLab default
     * @param snippetsEnabled Whether Snippets should be enabled, otherwise null indicates to use GitLab default
     * @param isPublic Whether the project is public or private, if true same as setting visibilityLevel = 20, otherwise null indicates to use GitLab default
     * @param visibilityLevel The visibility level of the project, otherwise null indicates to use GitLab default
     * @param importUrl The Import URL for the project, otherwise null
     * @return the GitLab Project
     * @throws GitLabApiException if any exception occurs
     * @deprecated As of release 4.2.0, replaced by {@link #createProject(String, Integer, String, Boolean, Boolean,
     *      Boolean, Boolean, Visibility, Integer, String)}
     */
    @Deprecated
    public Project createProject(String name, Integer namespaceId, String description, Boolean issuesEnabled, Boolean mergeRequestsEnabled,
            Boolean wikiEnabled, Boolean snippetsEnabled, Boolean isPublic, Integer visibilityLevel, String importUrl) throws GitLabApiException {

        if (name == null || name.trim().length() == 0) {
            return (null);
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("namespace_id", namespaceId)
                .withParam("description", description)
                .withParam("issues_enabled", issuesEnabled)
                .withParam("merge_requests_enabled", mergeRequestsEnabled)
                .withParam("wiki_enabled", wikiEnabled)
                .withParam("snippets_enabled", snippetsEnabled)
                .withParam("visibility_level", visibilityLevel)
                .withParam("import_url", importUrl);

        if (isApiVersion(ApiVersion.V3)) {
            formData.withParam("public", isPublic);
        } else if (isPublic) {
            formData.withParam("visibility", Visibility.PUBLIC);
        }

        Response response = post(Response.Status.CREATED, formData, "projects");
        return (response.readEntity(Project.class));
    }

    /**
     * Updates a project. The following properties on the Project instance
     * are utilized in the edit of the project, null values are not updated:
     *
     * id (required) - existing project id
     * name (required) - project name
     * path (optional) - project path
     * defaultBranch (optional) - master by default
     * description (optional) - short project description
     * visibility (optional) - Limit by visibility public, internal, or private
     * issuesEnabled (optional) - Enable issues for this project
     * mergeRequestsEnabled (optional) - Enable merge requests for this project
     * wikiEnabled (optional) - Enable wiki for this project
     * snippetsEnabled (optional) - Enable snippets for this project
     * jobsEnabled (optional) - Enable jobs for this project
     * containerRegistryEnabled (optional) - Enable container registry for this project
     * sharedRunnersEnabled (optional) - Enable shared runners for this project
     * publicJobs (optional) - If true, jobs can be viewed by non-project-members
     * onlyAllowMergeIfPipelineSucceeds (optional) - Set whether merge requests can only be merged with successful jobs
     * onlyAllowMergeIfAllDiscussionsAreResolved (optional) - Set whether merge requests can only be merged when all the discussions are resolved
     * lLfsEnabled (optional) - Enable LFS
     * requestAccessEnabled (optional) - Allow users to request member access
     * repositoryStorage (optional) - Which storage shard the repository is on. Available only to admins
     * approvalsBeforeMerge (optional) - How many approvers should approve merge request by default
     * printingMergeRequestLinkEnabled (optional) - Show link to create/view merge request when pushing from the command line
     *
     * NOTE: The following parameters specified by the GitLab API edit project are not supported:
     *     import_url
     *     tag_list array
     *     avatar
     *     ci_config_path
     *
     * @param project the Project instance with the configuration for the new project
     * @return a Project instance with the newly updated project info
     * @throws GitLabApiException if any exception occurs
     */
    public Project updateProject(Project project) throws GitLabApiException {

        if (project == null) {
            throw new RuntimeException("Project instance cannot be null.");
        }

        Integer id = project.getId();
        if (id == null) {
            throw new RuntimeException("Project ID cannot be null.");
        }

        String name = project.getName();
        if (name == null || name.trim().length() == 0) {
            throw new RuntimeException("Project name cannot be null or empty.");
        }

        GitLabApiForm formData = new GitLabApiForm()
            .withParam("name", name, true)
            .withParam("path", project.getPath())
            .withParam("default_branch", project.getDefaultBranch())
            .withParam("description", project.getDescription())
            .withParam("issues_enabled", project.getIssuesEnabled())
            .withParam("merge_requests_enabled", project.getMergeRequestsEnabled())
            .withParam("jobs_enabled", project.getJobsEnabled())
            .withParam("wiki_enabled", project.getWikiEnabled())
            .withParam("snippets_enabled", project.getSnippetsEnabled())
            .withParam("container_registry_enabled", project.getContainerRegistryEnabled())
            .withParam("shared_runners_enabled", project.getSharedRunnersEnabled())
            .withParam("public_jobs", project.getPublicJobs())
            .withParam("only_allow_merge_if_pipeline_succeeds", project.getOnlyAllowMergeIfPipelineSucceeds())
            .withParam("only_allow_merge_if_all_discussions_are_resolved", project.getOnlyAllowMergeIfAllDiscussionsAreResolved())
            .withParam("lfs_enabled", project.getLfsEnabled())
            .withParam("request_access_enabled", project.getRequestAccessEnabled())
            .withParam("repository_storage", project.getRepositoryStorage())
            .withParam("approvals_before_merge", project.getApprovalsBeforeMerge())
            .withParam("printing_merge_request_link_enabled", project.getPrintingMergeRequestLinkEnabled());

        if (isApiVersion(ApiVersion.V3)) {
            formData.withParam("visibility_level", project.getVisibilityLevel());
            boolean isPublic = (project.getPublic() != null ? project.getPublic() : project.getVisibility() == Visibility.PUBLIC);
            formData.withParam("public", isPublic);
        } else {
            Visibility visibility = (project.getVisibility() != null ? project.getVisibility() :
                project.getPublic() == Boolean.TRUE ? Visibility.PUBLIC : null);
            formData.withParam("visibility", visibility);
        }

        Response response = putWithFormData(Response.Status.OK, formData, "projects", id);
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

        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.ACCEPTED);
        delete(expectedStatus, null, "projects", projectId);
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
     * Forks a project into the user namespace of the authenticated user or the one provided.
     * The forking operation for a project is asynchronous and is completed in a background job. 
     * The request will return immediately.
     *
     * POST /projects/:id/fork
     *
     * @param id the ID of the project to fork
     * @param namespace path of the namespace that the project will be forked to
     * @return the newly forked Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project forkProject(Integer id, String namespace) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("namespace", namespace, true);
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.CREATED);
        Response response = post(expectedStatus, formData, "projects", id, "fork");
        return (response.readEntity(Project.class));
    }

    /**
     * Forks a project into the user namespace of the authenticated user or the one provided.
     * The forking operation for a project is asynchronous and is completed in a background job. 
     * The request will return immediately.
     *
     * POST /projects/:id/fork
     *
     * @param project the project to fork
     * @param namespace path of the namespace that the project will be forked to
     * @return the newly forked Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project forkProject(Project project, String namespace) throws GitLabApiException {
        return (forkProject(project.getId(), namespace));
    }

    /**
     * Forks a project into the user namespace of the authenticated user or the one provided.
     * The forking operation for a project is asynchronous and is completed in a background job. 
     * The request will return immediately.
     *
     * POST /projects/:id/fork
     *
     * @param id the ID of the project to fork
     * @param namespaceId ID of the namespace that the project will be forked to
     * @return the newly forked Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project forkProject(Integer id, Integer namespaceId) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("namespace", namespaceId, true);
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.CREATED);
        Response response = post(expectedStatus, formData, "projects", id, "fork");
        return (response.readEntity(Project.class));
    }

    /**
     * Forks a project into the user namespace of the authenticated user or the one provided.
     * The forking operation for a project is asynchronous and is completed in a background job. 
     * The request will return immediately.
     *
     * POST /projects/:id/fork
     *
     * @param project the project to fork
     * @param namespaceId ID of the namespace that the project will be forked to
     * @return the newly forked Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project forkProject(Project project, Integer namespaceId) throws GitLabApiException {
        return (forkProject(project.getId(), namespaceId));
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
        Response response = get(Response.Status.OK, this.getDefaultPerPageParam(), "projects", projectId, "members");
        return (response.readEntity(new GenericType<List<Member>>() {}));
    }

    /**
     * Get a list of project team members in the specified page range.
     *
     * GET /projects/:id/members
     *
     * @param projectId the project ID to get team members for
     * @param page the page to get
     * @param perPage the number of Member instances per page
     * @return the members belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Member> getMembers(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "members");
        return (response.readEntity(new GenericType<List<Member>>() {}));
    }

    /**
     * Get a Pager of project team members.
     *
     * GET /projects/:id/members
     *
     * @param projectId the project ID to get team members for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the members belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Member> getMembers(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Member>(this, Member.class, itemsPerPage, null, "projects", projectId, "members"));
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
     * Gets a project team member.
     *
     * GET /projects/:id/members/:user_id
     *
     * @param projectId the project ID to get team member for
     * @param userId the user ID of the member
     * @return the member specified by the project ID/user ID pair
     * @throws GitLabApiException if any exception occurs
     */
    public Optional<Member> getOptionalMember(Integer projectId, Integer userId) throws GitLabApiException {
        try {
            return (Optional.ofNullable(getMember(projectId, userId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Adds a user to a project team. This is an idempotent method and can be called multiple times
     * with the same parameters. Adding team membership to a user that is already a member does not
     * affect the existing membership.
     *
     * POST /projects/:id/members
     *
     * @param projectId the project ID to add the team member to, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer projectId, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (addMember(projectId, userId, accessLevel, null));
    }

    /**
     * Adds a user to a project team. This is an idempotent method and can be called multiple times
     * with the same parameters. Adding team membership to a user that is already a member does not
     * affect the existing membership.
     *
     * POST /projects/:id/members
     *
     * @param projectId the project ID to add the team member to, required
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer projectId, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (addMember(projectId, userId, accessLevel.toValue(), null));
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
     * @param accessLevel the access level for the new member
     * @param expiresAt the date the membership in the group will expire
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer projectId, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (addMember(projectId, userId, accessLevel.toValue(), expiresAt));
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
     * @param accessLevel the access level for the new member
     * @param expiresAt the date the membership in the group will expire
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Integer projectId, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("user_id", userId, true)
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "members");
        return (response.readEntity(Member.class));
    }

    /**
     * Updates a member of a project.
     *
     * PUT /projects/:projectId/members/:userId
     *
     * @param projectId the project ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer projectId, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (updateMember(projectId, userId, accessLevel, null));
    }

    /**
     * Updates a member of a project.
     *
     * PUT /projects/:projectId/members/:userId
     *
     * @param projectId the project ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer projectId, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (updateMember(projectId, userId, accessLevel.toValue(), null));
    }

    /**
     * Updates a member of a project.
     *
     * PUT /projects/:projectId/members/:userId
     *
     * @param projectId the project ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer projectId, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (updateMember(projectId, userId, accessLevel.toValue(), expiresAt));
    }

    /**
     * Updates a member of a project.
     *
     * PUT /projects/:projectId/members/:userId
     *
     * @param projectId the project ID the member belongs to, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Integer projectId, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "members", userId);
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
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", projectId, "members", userId);
    }

    /**
     * Get a list of project users. This list includes all project members and all users assigned to project parent groups.
     *
     * GET /projects/:id/users
     *
     * @param projectId the project ID to get users for
     * @return the users belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProjectUser> getProjectUsers(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "users");
        return (response.readEntity(new GenericType<List<ProjectUser>>() {}));
    }

    /**
     * Get a list of project users matching the specified search string. This list includes all project members and all users assigned to project parent groups.
     *
     * GET /projects/:id/users
     *
     * @param projectId the project ID to get users for
     * @param search the string to match specific users
     * @return the users matching the search string and belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProjectUser> getProjectUsers(Integer projectId, String search) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("search", search)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "users");
        return (response.readEntity(new GenericType<List<ProjectUser>>() {}));
    }

    /**
     * Get the project events for specific project. Sorted from newest to latest.
     *
     * GET /projects/:id/events
     *
     * @param projectId the project ID to get events for
     * @return the project events for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getProjectEvents(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "events");
        return (response.readEntity(new GenericType<List<Event>>() {}));
    }

    /**
     * Get the project events for specific project. Sorted from newest to latest in the specified page range.
     *
     * GET /projects/:id/events
     *
     * @param projectId the project ID to get events for
     * @param page the page to get
     * @param perPage the number of Event instances per page
     * @return the project events for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getProjectEvents(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "events");
        return (response.readEntity(new GenericType<List<Event>>() {}));
    }

    /**
     * Get a Pager of project events for specific project. Sorted from newest to latest.
     *
     * GET /projects/:id/events
     *
     * @param projectId the project ID to get events for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of project events for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Event> getProjectEvents(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Event>(this, Event.class, itemsPerPage, null, "projects", projectId, "events"));
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
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "hooks");
        return (response.readEntity(new GenericType<List<ProjectHook>>() {}));
    }

    /**
     * Get list of project hooks in the specified page range.
     *
     * GET /projects/:id/hooks
     *
     * @param projectId the project ID to get project hooks for
     * @param page the page to get
     * @param perPage the number of ProjectHook instances per page
     * @return a list of project hooks for the specified project in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProjectHook> getHooks(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "hooks");
        return (response.readEntity(new GenericType<List<ProjectHook>>() {}));
    }

    /**
     * Get Pager of project hooks.
     *
     * GET /projects/:id/hooks
     *
     * @param projectId the project ID to get project hooks for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of project hooks for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<ProjectHook> getHooks(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<ProjectHook>(this, ProjectHook.class, itemsPerPage, null, "projects", projectId, "hooks"));
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
     * Get a specific hook for project as an Optional instance.
     *
     * GET /projects/:id/hooks/:hook_id
     *
     * @param projectId the project ID to get the hook for
     * @param hookId the ID of the hook to get
     * @return the project hook for the specified project ID/hook ID pair as an Optional instance
     */
    public Optional<ProjectHook> getOptionalHook(Integer projectId, Integer hookId) {
        try {
            return (Optional.ofNullable(getHook(projectId, hookId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
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
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", projectId, "hooks", hookId);
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
     * Get a list of project's issues. Only returns the first page
     *
     * GET /projects/:id/issues
     *
     * @param projectId the project ID to get the issues for
     * @return a list of project's issues
     * @throws GitLabApiException if any exception occurs
     * @deprecated Will be removed in version 5.0, replaced by {@link IssuesApi#getIssues(Integer)}
     */
    public List<Issue> getIssues(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get a list of project's issues using the specified page and per page settings.
     *
     * GET /projects/:id/issues
     *
     * @param projectId the project ID to get the issues for
     * @param page the page to get
     * @param perPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     * @deprecated Will be removed in version 5.0, replaced by {@link IssuesApi#getIssues(Integer, int, int)}
     */
    public List<Issue> getIssues(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get a Pager of project's issues.
     *
     * GET /projects/:id/issues
     *
     * @param projectId the project ID to get the issues for
     * @param itemsPerPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     * @deprecated Will be removed in version 5.0, replaced by {@link IssuesApi#getIssues(Integer, int)}
     */
    public Pager<Issue> getIssues(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Issue>(this, Issue.class, itemsPerPage, null, "projects", projectId, "issues"));
    }

    /**
     * Get a single project issues.
     *
     * GET /projects/:id/issues/:issue_iid
     *
     * @param projectId the project ID to get the issue for
     * @param issueId the internal ID of a project's issue
     * @return the specified Issue instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link IssuesApi#getIssue(Integer, Integer)}
     */
    public Issue getIssue(Integer projectId, Integer issueId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "issues", issueId);
        return (response.readEntity(Issue.class));
    }

    /**
     * Delete a project issue.
     *
     * DELETE /projects/:id/issues/:issue_iid
     *
     * @param projectId the project ID to delete the issue from
     * @param issueId the internal ID of a project's issue
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link IssuesApi#deleteIssue(Integer, Integer)}
     */
    public void deleteIssue(Integer projectId, Integer issueId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, getDefaultPerPageParam(), "projects", projectId, "issues", issueId);
    }

    /**
     * Get a list of project snippets.  This only returns the first page of snippets.
     *
     * GET /projects/:id/snippets
     *
     * @param projectId the project ID to get the snippets for
     * @return a list of project's snippets
     * @throws GitLabApiException if any exception occurs
     */
    public List<Snippet> getSnippets(Integer projectId) throws GitLabApiException {
        return (getSnippets(projectId, 1, this.getDefaultPerPage()));
    }

    /**
     * Get a list of project snippets.  This only returns the first page of snippets.
     *
     * GET /projects/:id/snippets
     *
     * @param projectId the project ID to get the snippets for
     * @param page the page to get
     * @param perPage the number of snippets per page
     * @return a list of project's snippets for the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Snippet> getSnippets(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "snippets");
        return (response.readEntity(new GenericType<List<Snippet>>() {}));
    }

    /**
     * Get a Pager of project's snippets.
     *
     * GET /projects/:id/snippets
     *
     * @param projectId the project ID to get the issues for
     * @param itemsPerPage the number of snippets per page
     * @return the Pager of snippets
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Snippet> getSnippets(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Snippet>(this, Snippet.class, itemsPerPage, null, "projects", projectId, "snippets"));
    }

    /**
     * Get a single of project snippet.
     *
     * GET /projects/:id/snippets/:snippet_id
     *
     * @param projectId the project ID to get the snippet for
     * @param snippetId the ID of the project's snippet
     * @return the specified project Snippet
     * @throws GitLabApiException if any exception occurs
     */
    public Snippet getSnippet(Integer projectId, Integer snippetId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "snippets", snippetId);
        return (response.readEntity(Snippet.class));
    }

    /**
     * Get a single of project snippet as an Optional instance.
     *
     * GET /projects/:id/snippets/:snippet_id
     *
     * @param projectId the project ID to get the snippet for
     * @param snippetId the ID of the project's snippet
     * @return the specified project Snippet as an Optional instance
     */
    public Optional<Snippet> getOptionalSnippet(Integer projectId, Integer snippetId) {
        try {
            return (Optional.ofNullable(getSnippet(projectId, snippetId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a new project snippet. The user must have permission to create new snippets.
     *
     * POST /projects/:id/snippets
     *
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param title the title of a snippet, required
     * @param filename the name of a snippet file, required
     * @param description the description of a snippet, optional
     * @param code the content of a snippet, required
     * @param visibility the snippet's visibility, required
     * @return a Snippet instance with info on the created snippet
     * @throws GitLabApiException if any exception occurs
     */
    public Snippet createSnippet(Integer projectId, String title, String filename, String description,
            String code, Visibility visibility) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("file_name", filename, true)
                .withParam("description", description)
                .withParam("code", code, true)
                .withParam("visibility", visibility, true);

        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "snippets");
        return (response.readEntity(Snippet.class));
    }

    /**
     * Updates an existing project snippet. The user must have permission to change an existing snippet.
     *
     * PUT /projects/:id/snippets/:snippet_id
     *
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param snippetId the ID of a project's snippet, required
     * @param title the title of a snippet, optional
     * @param filename the name of a snippet file, optional
     * @param description the description of a snippet, optioptionalonal
     * @param code the content of a snippet, optional
     * @param visibility the snippet's visibility, reqoptionaluired
     * @return a Snippet instance with info on the updated snippet
     * @throws GitLabApiException if any exception occurs
     */
    public Snippet updateSnippet(Integer projectId, Integer snippetId, String title, String filename, String description,
            String code, Visibility visibility) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title)
                .withParam("file_name", filename)
                .withParam("description", description)
                .withParam("code", code)
                .withParam("visibility", visibility);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "snippets", snippetId);
        return (response.readEntity(Snippet.class));
    }

    /*
     * Deletes an existing project snippet. This is an idempotent function and deleting a
     * non-existent snippet does not cause an error.
     *
     * DELETE /projects/:id/snippets/:snippet_id
     *
     * @param projectId the project ID of the snippet
     * @param snippetId the ID of the project's snippet
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteSnippet(Integer projectId, Integer snippetId) throws GitLabApiException {
        delete(Response.Status.NO_CONTENT, null, "projects", projectId, "snippets", snippetId);
    }

    /*
     * Get the raw project snippet as plain text.
     *
     * GET /projects/:id/snippets/:snippet_id/raw
     *
     * @param projectId the project ID of the snippet
     * @param snippetId the ID of the project's snippet
     * @return the raw project snippet plain text as an Optional instance
     * @throws GitLabApiException if any exception occurs
     */
    public String getRawSnippetContent(Integer projectId, Integer snippetId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "snippets", snippetId, "raw");
        return (response.readEntity(String.class));
    }

    /*
     * Get the raw project snippet plain text as an Optional instance.
     *
     * GET /projects/:id/snippets/:snippet_id/raw
     *
     * @param projectId the project ID of the snippet
     * @param snippetId the ID of the project's snippet
     * @return the raw project snippet plain text as an Optional instance
     */
    public Optional<String> getOptionalRawSnippetContent(Integer projectId, Integer snippetId) {
        try {
            return (Optional.ofNullable(getRawSnippetContent(projectId, snippetId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Share a project with the specified group.
     *
     * POST /projects/:id/share
     *
     * @param projectId the ID of the project to share, required
     * @param groupId the ID of the group to share with, required
     * @param accessLevel the permissions level to grant the group, required
     * @param expiresAt the share expiration date, optional
     * @throws GitLabApiException if any exception occurs
     */
    public void shareProject(Integer projectId, Integer groupId, AccessLevel accessLevel, Date expiresAt)
            throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
            .withParam("group_id", groupId, true)
            .withParam("group_access", accessLevel.toValue(), true)
            .withParam("expires_at", expiresAt);
        post(Response.Status.CREATED, formData, "projects", projectId, "share");
    }

    /**
     * Unshare the project from the group.
     *
     * DELETE /projects/:id/share/:group_id
     *
     * @param projectId the ID of the project to unshare, required
     * @param groupId the ID of the group to unshare, required
     * @throws GitLabApiException if any exception occurs
     */
    public void unshareProject(Integer projectId, Integer groupId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", projectId, "share", groupId);
    }
}
