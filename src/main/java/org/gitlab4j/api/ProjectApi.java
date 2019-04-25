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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Event;
import org.gitlab4j.api.models.FileUpload;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProjectFilter;
import org.gitlab4j.api.models.ProjectHook;
import org.gitlab4j.api.models.ProjectUser;
import org.gitlab4j.api.models.PushRules;
import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.Variable;
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
     * <pre><code>GET /projects</code></pre>
     *
     * @return a list of projects accessible by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects() throws GitLabApiException {
       return (getProjects(getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects accessible by the authenticated user and in the specified page range.
     *
     * <pre><code>GET /projects</code></pre>
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
     * <pre><code>GET /projects</code></pre>
     *
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager instance of projects accessible by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getProjects(int itemsPerPage) throws GitLabApiException {
        return (new Pager<Project>(this, Project.class, itemsPerPage, null, "projects"));
    }

    /**
     * Get a Stream of projects accessible by the authenticated user.
     *
     * <pre><code>GET /projects</code></pre>
     *
     * @return a Stream of projects accessible by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getProjectsStream() throws GitLabApiException {
       return (getProjects(getDefaultPerPage()).stream());
    }

    /**
     * Get a list of projects accessible by the authenticated user and matching the supplied filter parameters.
     * All filter parameters are optional.
     *
     * <pre><code>GET /projects</code></pre>
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
    @Deprecated
    public List<Project> getProjects(Boolean archived, Visibility visibility, String orderBy,
            String sort, String search, Boolean simple, Boolean owned, Boolean membership,
            Boolean starred, Boolean statistics) throws GitLabApiException {

        ProjectOrderBy projectOrderBy = ProjectOrderBy.valueOf(orderBy);
        SortOrder sortOrder = SortOrder.valueOf(sort);
        return (getProjects(archived, visibility, projectOrderBy, sortOrder, search, simple,
                owned, membership, starred, statistics, getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects accessible by the authenticated user and matching the supplied filter parameters.
     * All filter parameters are optional.
     *
     * <pre><code>GET /projects</code></pre>
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

        return (getProjects(archived, visibility, orderBy, sort, search, simple,
                owned, membership, starred, statistics, getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects accessible by the authenticated user and matching the supplied filter parameters.
     * All filter parameters are optional.
     *
     * <pre><code>GET /projects</code></pre>
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
     * <pre><code>GET /projects</code></pre>
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
     * <pre><code>GET /projects?search=search</code></pre>
     *
     * @param search the project name search criteria
     * @return a list of projects accessible by the authenticated user that match the provided search string
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getProjects(String search) throws GitLabApiException {
        return (getProjects(search, getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects accessible by the authenticated user that match the provided search string.
     *
     * <pre><code>GET /projects?search=search</code></pre>
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
     * <pre><code>GET /projects?search=search</code></pre>
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
     * Get a Stream of projects accessible by the authenticated user that match the provided search string.
     *
     * <pre><code>GET /projects?search=search</code></pre>
     *
     * @param search the project name search criteria
     * @return a Stream of projects accessible by the authenticated user that match the provided search string
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getProjectsStream(String search) throws GitLabApiException {
        return (getProjects(search, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of projects that the authenticated user is a member of.
     *
     * <pre><code>GET /projects?membership=true</code></pre>
     *
     * @return a list of projects that the authenticated user is a member of
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getMemberProjects() throws GitLabApiException {
        return (getMemberProjects(getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects that the authenticated user is a member of in the specified page range.
     *
     * <pre><code>GET /projects?membership=true</code></pre>
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
     * <pre><code>GET /projects?membership=true</code></pre>
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
     * Get a Stream of projects that the authenticated user is a member of.
     *
     * <pre><code>GET /projects?membership=true</code></pre>
     *
     * @return a list of projects that the authenticated user is a member of
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getMemberProjectsStream() throws GitLabApiException {
        return (getMemberProjects(getDefaultPerPage()).stream());
    }

    /**
     * Get a list of projects owned by the authenticated user.
     *
     * <pre><code>GET /projects?owned=true</code></pre>
     *
     * @return a list of projects owned by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getOwnedProjects() throws GitLabApiException {
        return (getOwnedProjects(getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects owned by the authenticated user in the specified page range.
     *
     * <pre><code>GET /projects?owned=true</code></pre>
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
     * <pre><code>GET /projects?owned=true</code></pre>
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
     * Get a Stream of projects owned by the authenticated user.
     *
     * <pre><code>GET /projects?owned=true</code></pre>
     *
     * @return a Stream of projects owned by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getOwnedProjectsStream() throws GitLabApiException {
        return (getOwnedProjects(getDefaultPerPage()).stream());
    }

    /**
     * Get a list of projects starred by the authenticated user.
     *
     * <pre><code>GET /projects?starred=true</code></pre>
     *
     * @return a list of projects starred by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getStarredProjects() throws GitLabApiException {
        return (getStarredProjects(getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects starred by the authenticated user in the specified page range.
     *
     * <pre><code>GET /projects?starred=true</code></pre>
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
     * <pre><code>GET /projects?starred=true</code></pre>
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
     * Get a Stream of projects starred by the authenticated user.
     *
     * <pre><code>GET /projects?starred=true</code></pre>
     *
     * @return a Stream of projects starred by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getStarredProjectsStream() throws GitLabApiException {
        return (getStarredProjects(getDefaultPerPage()).stream());
    }

    /**
     * Get a list of visible projects owned by the given user.
     *
     * <pre><code>GET /users/:user_id/projects</code></pre>
     *
     * @param userIdOrUsername the user ID, username of the user, or a User instance holding the user ID or username
     * @param filter the ProjectFilter instance holding the filter values for the query
     * @return a list of visible projects owned by the given use
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getUserProjects(Object userIdOrUsername, ProjectFilter filter) throws GitLabApiException {
        return (getUserProjects(userIdOrUsername, filter, getDefaultPerPage()).all());
    }

    /**
     * Get a list of visible projects owned by the given user in the specified page range.
     *
     * <pre><code>GET /users/:user_id/projects</code></pre>
     *
     * @param userIdOrUsername the user ID, username of the user, or a User instance holding the user ID or username
     * @param filter the ProjectFilter instance holding the filter values for the query
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of visible projects owned by the given use
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getUserProjects(Object userIdOrUsername, ProjectFilter filter, int page, int perPage) throws GitLabApiException {
        GitLabApiForm formData = filter.getQueryParams(page, perPage);
        Response response = get(Response.Status.OK, formData.asMap(),
                "users", getUserIdOrUsername(userIdOrUsername), "projects");
        return (response.readEntity(new GenericType<List<Project>>() {}));
    }

    /**
     * Get a Pager of visible projects owned by the given user.
     *
     * <pre><code>GET /users/:user_id/projects</code></pre>
     *
     * @param userIdOrUsername the user ID, username of the user, or a User instance holding the user ID or username
     * @param filter the ProjectFilter instance holding the filter values for the query
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of visible projects owned by the given use
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getUserProjects(Object userIdOrUsername, ProjectFilter filter, int itemsPerPage) throws GitLabApiException {
        GitLabApiForm formData = filter.getQueryParams();
        return (new Pager<Project>(this, Project.class, itemsPerPage, formData.asMap(),
                "users", getUserIdOrUsername(userIdOrUsername), "projects"));
    }

    /**
     * Get a Stream of visible projects owned by the given user.
     *
     * <pre><code>GET /users/:user_id/projects</code></pre>
     *
     * @param userIdOrUsername the user ID, username of the user, or a User instance holding the user ID or username
     * @param filter the ProjectFilter instance holding the filter values for the query
     * @return a Stream of visible projects owned by the given use
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getUserProjectsStream(Object userIdOrUsername, ProjectFilter filter) throws GitLabApiException {
        return (getUserProjects(userIdOrUsername, filter, getDefaultPerPage()).stream());
    }

    /**
     * Get a specific project, which is owned by the authentication user.
     *
     * <pre><code>GET /projects/:id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Project getProject(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", this.getProjectIdOrPath(projectIdOrPath));
        return (response.readEntity(Project.class));
    }

    /**
     * Get an Optional instance with the value for the specific project, which is owned by the authentication user.
     *
     * <pre><code>GET /projects/:id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return an Optional instance with the specified project as a value
     */
    public Optional<Project> getOptionalProject(Object projectIdOrPath) {
        try {
            return (Optional.ofNullable(getProject(projectIdOrPath)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Get a specific project, which is owned by the authentication user.
     *
     * <pre><code>GET /projects/:id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param includeStatistics include project statistics
     * @return the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Project getProject(Object projectIdOrPath, Boolean includeStatistics) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("statistics", includeStatistics);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", this.getProjectIdOrPath(projectIdOrPath));
        return (response.readEntity(Project.class));
    }

    /**
     * Get an Optional instance with the value for the specific project, which is owned by the authentication user.
     *
     * <pre><code>GET /projects/:id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param includeStatistics include project statistics
     * @return an Optional instance with the specified project as a value
     */
    public Optional<Project> getOptionalProject(Object projectIdOrPath, Boolean includeStatistics) {
        try {
            return (Optional.ofNullable(getProject(projectIdOrPath, includeStatistics)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Get a specific project, which is owned by the authentication user.
     *
     * <pre><code>GET /projects/:id</code></pre>
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
     * <pre><code>GET /projects/:id</code></pre>
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
     * Get a specific project, which is owned by the authentication user.
     *
     * <pre><code>GET /projects/:id</code></pre>
     *
     * @param namespace the name of the project namespace or group
     * @param project the name of the project to get
     * @param includeStatistics include project statistics
     * @return the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Project getProject(String namespace, String project, Boolean includeStatistics) throws GitLabApiException {

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

        Form formData = new GitLabApiForm().withParam("statistics", includeStatistics);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectPath);
        return (response.readEntity(Project.class));
    }

    /**
     * Get an Optional instance with the value for the specific project, which is owned by the authentication user.
     *
     * <pre><code>GET /projects/:id</code></pre>
     *
     * @param namespace the name of the project namespace or group
     * @param project the name of the project
     * @param includeStatistics include project statistics
     * @return an Optional instance with the specified project as a value
     */
    public Optional<Project> getOptionalProject(String namespace, String project, Boolean includeStatistics) {
        try {
            return (Optional.ofNullable(getProject(namespace, project, includeStatistics)));
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
     * mergeMethod (optional) - Set the merge method used
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
     * resolveOutdatedDiffDiscussions (optional) - Automatically resolve merge request diffs discussions on lines changed with a push
     * initialize_with_readme (optional) - Initialize project with README file
     * packagesEnabled (optional) - Enable or disable mvn packages repository feature
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
            .withParam("merge_method",  project.getMergeMethod())
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
            .withParam("printing_merge_request_link_enabled", project.getPrintingMergeRequestLinkEnabled())
            .withParam("resolve_outdated_diff_discussions", project.getResolveOutdatedDiffDiscussions())
            .withParam("initialize_with_readme", project.getInitializeWithReadme())
            .withParam("packages_enabled", project.getPackagesEnabled());

        if (isApiVersion(ApiVersion.V3)) {
            boolean isPublic = (project.getPublic() != null ? project.getPublic() : project.getVisibility() == Visibility.PUBLIC);
            formData.withParam("public", isPublic);

            if (project.getTagList() != null && !project.getTagList().isEmpty()) {
                throw new IllegalArgumentException("GitLab API v3 does not support tag lists when creating projects");
            }
        } else {
            Visibility visibility = (project.getVisibility() != null ? project.getVisibility() :
                project.getPublic() == Boolean.TRUE ? Visibility.PUBLIC : null);
            formData.withParam("visibility", visibility);

            if (project.getTagList() != null && !project.getTagList().isEmpty()) {
                formData.withParam("tag_list", String.join(",", project.getTagList()));
            }
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
     * id (required) - existing project id, either id or path must be provided
     * name (optional) - project name
     * path (optional) - project path, either id or path must be provided
     * defaultBranch (optional) - master by default
     * description (optional) - short project description
     * visibility (optional) - Limit by visibility public, internal, or private
     * issuesEnabled (optional) - Enable issues for this project
     * mergeMethod (optional) - Set the merge method used
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
     * resolveOutdatedDiffDiscussions (optional) - Automatically resolve merge request diffs discussions on lines changed with a push
     * packagesEnabled (optional) - Enable or disable mvn packages repository feature
     *
     * NOTE: The following parameters specified by the GitLab API edit project are not supported:
     *     import_url
     *     tag_list array
     *     avatar
     *     ci_config_path
     *     initialize_with_readme
     *
     * @param project the Project instance with the configuration for the new project
     * @return a Project instance with the newly updated project info
     * @throws GitLabApiException if any exception occurs
     */
    public Project updateProject(Project project) throws GitLabApiException {

        if (project == null) {
            throw new RuntimeException("Project instance cannot be null.");
        }

        // This will throw an exception if both id and path are not present
        Object projectIdentifier = getProjectIdOrPath(project);

        GitLabApiForm formData = new GitLabApiForm()
            .withParam("name", project.getName())
            .withParam("path", project.getPath())
            .withParam("default_branch", project.getDefaultBranch())
            .withParam("description", project.getDescription())
            .withParam("issues_enabled", project.getIssuesEnabled())
            .withParam("merge_method",  project.getMergeMethod())
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
            .withParam("printing_merge_request_link_enabled", project.getPrintingMergeRequestLinkEnabled())
            .withParam("resolve_outdated_diff_discussions", project.getResolveOutdatedDiffDiscussions())
            .withParam("packages_enabled", project.getPackagesEnabled());

        if (isApiVersion(ApiVersion.V3)) {
            formData.withParam("visibility_level", project.getVisibilityLevel());
            boolean isPublic = (project.getPublic() != null ? project.getPublic() : project.getVisibility() == Visibility.PUBLIC);
            formData.withParam("public", isPublic);

            if (project.getTagList() != null && !project.getTagList().isEmpty()) {
                throw new IllegalArgumentException("GitLab API v3 does not support tag lists when updating projects");
            }
        } else {
            Visibility visibility = (project.getVisibility() != null ? project.getVisibility() :
                project.getPublic() == Boolean.TRUE ? Visibility.PUBLIC : null);
            formData.withParam("visibility", visibility);

            if (project.getTagList() != null && !project.getTagList().isEmpty()) {
                formData.withParam("tag_list", String.join(",", project.getTagList()));
            }
        }

        Response response = putWithFormData(Response.Status.OK, formData, "projects", projectIdentifier);
        return (response.readEntity(Project.class));
    }

    /**
     * Removes project with all resources(issues, merge requests etc).
     *
     * <pre><code>DELETE /projects/:id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteProject(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.ACCEPTED);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath));
    }

    /**
     * Forks a project into the user namespace of the authenticated user or the one provided.
     * The forking operation for a project is asynchronous and is completed in a background job.
     * The request will return immediately.
     *
     * <pre><code>POST /projects/:id/fork</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param namespace path of the namespace that the project will be forked to
     * @return the newly forked Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project forkProject(Object projectIdOrPath, String namespace) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("namespace", namespace, true);
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.CREATED);
        Response response = post(expectedStatus, formData, "projects", getProjectIdOrPath(projectIdOrPath), "fork");
        return (response.readEntity(Project.class));
    }

    /**
     * Forks a project into the user namespace of the authenticated user or the one provided.
     * The forking operation for a project is asynchronous and is completed in a background job.
     * The request will return immediately.
     *
     * <pre><code>POST /projects/:id/fork</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param namespaceId ID of the namespace that the project will be forked to
     * @return the newly forked Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project forkProject(Object projectIdOrPath, Integer namespaceId) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("namespace", namespaceId, true);
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.CREATED);
        Response response = post(expectedStatus, formData, "projects", getProjectIdOrPath(projectIdOrPath), "fork");
        return (response.readEntity(Project.class));
    }

    /**
     * Create a forked from/to relation between existing projects.
     *
     * <pre><code>POST /projects/:id/fork/:forkFromId</code></pre>
     *
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param forkedFromId the ID of the project that was forked from
     * @return the updated Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project createForkedFromRelationship(Object projectIdOrPath, Integer forkedFromId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.CREATED);
        Response response = post(expectedStatus, (Form)null, "projects", this.getProjectIdOrPath(projectIdOrPath), "fork", forkedFromId);
        return (response.readEntity(Project.class));
    }

    /**
     * Delete an existing forked from relationship.
     *
     * <pre><code>DELETE /projects/:id/fork</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteForkedFromRelationship(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.ACCEPTED);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "fork");
    }

    /**
     * Get a list of project team members.
     *
     * <pre><code>GET /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return the members belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Member> getMembers(Object projectIdOrPath) throws GitLabApiException {
        return (getMembers(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of project team members in the specified page range.
     *
     * <pre><code>GET /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of Member instances per page
     * @return the members belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Member> getMembers(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "members");
        return (response.readEntity(new GenericType<List<Member>>() {}));
    }

    /**
     * Get a Pager of project team members.
     *
     * <pre><code>GET /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the members belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Member> getMembers(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Member>(this, Member.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "members"));
    }

    /**
     * Get a Stream of project team members.
     *
     * <pre><code>GET /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a Stream of the members belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Member> getMembersStream(Object projectIdOrPath) throws GitLabApiException {
        return (getMembers(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Gets a project team member.
     *
     * <pre><code>GET /projects/:id/members/:user_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member
     * @return the member specified by the project ID/user ID pair
     * @throws GitLabApiException if any exception occurs
     */
    public Member getMember(Object projectIdOrPath, Integer userId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "members", userId);
        return (response.readEntity(Member.class));
    }

    /**
     * Gets a project team member.
     *
     * <pre><code>GET /projects/:id/members/:user_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member
     * @return the member specified by the project ID/user ID pair
     */
    public Optional<Member> getOptionalMember(Object projectIdOrPath, Integer userId)  {
        try {
            return (Optional.ofNullable(getMember(projectIdOrPath, userId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Adds a user to a project team. This is an idempotent method and can be called multiple times
     * with the same parameters. Adding team membership to a user that is already a member does not
     * affect the existing membership.
     *
     * <pre><code>POST /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object projectIdOrPath, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (addMember(projectIdOrPath, userId, accessLevel, null));
    }

    /**
     * Adds a user to a project team. This is an idempotent method and can be called multiple times
     * with the same parameters. Adding team membership to a user that is already a member does not
     * affect the existing membership.
     *
     * <pre><code>POST /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member to add, required
     * @param accessLevel the access level for the new member, required
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object projectIdOrPath, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (addMember(projectIdOrPath, userId, accessLevel.toValue(), null));
    }

    /**
     * Adds a user to a project team. This is an idempotent method and can be called multiple times
     * with the same parameters. Adding team membership to a user that is already a member does not
     * affect the existing membership.
     *
     * <pre><code>POST /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member to add
     * @param accessLevel the access level for the new member
     * @param expiresAt the date the membership in the group will expire
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object projectIdOrPath, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (addMember(projectIdOrPath, userId, accessLevel.toValue(), expiresAt));
    }

    /**
     * Adds a user to a project team. This is an idempotent method and can be called multiple times
     * with the same parameters. Adding team membership to a user that is already a member does not
     * affect the existing membership.
     *
     * <pre><code>POST /projects/:id/members</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member to add
     * @param accessLevel the access level for the new member
     * @param expiresAt the date the membership in the group will expire
     * @return the added member
     * @throws GitLabApiException if any exception occurs
     */
    public Member addMember(Object projectIdOrPath, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("user_id", userId, true)
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "members");
        return (response.readEntity(Member.class));
    }

    /**
     * Updates a member of a project.
     *
     * <pre><code>PUT /projects/:projectId/members/:userId</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object projectIdOrPath, Integer userId, Integer accessLevel) throws GitLabApiException {
        return (updateMember(projectIdOrPath, userId, accessLevel, null));
    }

    /**
     * Updates a member of a project.
     *
     * <pre><code>PUT /projects/:projectId/members/:userId</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object projectIdOrPath, Integer userId, AccessLevel accessLevel) throws GitLabApiException {
        return (updateMember(projectIdOrPath, userId, accessLevel.toValue(), null));
    }

    /**
     * Updates a member of a project.
     *
     * <pre><code>PUT /projects/:projectId/members/:userId</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object projectIdOrPath, Integer userId, AccessLevel accessLevel, Date expiresAt) throws GitLabApiException {
        return (updateMember(projectIdOrPath, userId, accessLevel.toValue(), expiresAt));
    }

    /**
     * Updates a member of a project.
     *
     * <pre><code>PUT /projects/:projectId/members/:userId</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param userId the user ID of the member to update, required
     * @param accessLevel the new access level for the member, required
     * @param expiresAt the date the membership in the group will expire, optional
     * @return the updated member
     * @throws GitLabApiException if any exception occurs
     */
    public Member updateMember(Object projectIdOrPath, Integer userId, Integer accessLevel, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("access_level", accessLevel, true)
                .withParam("expires_at",  expiresAt, false);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "members", userId);
        return (response.readEntity(Member.class));
    }

    /**
     * Removes user from project team.
     *
     * <pre><code>DELETE /projects/:id/members/:user_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param userId the user ID of the member to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void removeMember(Object projectIdOrPath, Integer userId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "members", userId);
    }

    /**
     * Get a list of project users. This list includes all project members and all users assigned to project parent groups.
     *
     * <pre><code>GET /projects/:id/users</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return the users belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProjectUser> getProjectUsers(Object projectIdOrPath) throws GitLabApiException {
        return (getProjectUsers(projectIdOrPath, null, getDefaultPerPage()).all());
    }

    /**
     * Get a Pager of project users. This Pager includes all project members and all users assigned to project parent groups.
     *
     * <pre><code>GET /projects/:id/users</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of the users matching the search string and belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<ProjectUser> getProjectUsers(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (getProjectUsers(projectIdOrPath, null, itemsPerPage));
    }

    /**
     * Get a Stream of project users. This Stream includes all project members and all users assigned to project parent groups.
     *
     * <pre><code>GET /projects/:id/users</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of the users belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<ProjectUser> getProjectUsersStream(Object projectIdOrPath) throws GitLabApiException {
        return (getProjectUsers(projectIdOrPath, null, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of project users matching the specified search string. This list
     * includes all project members and all users assigned to project parent groups.
     *
     * <pre><code>GET /projects/:id/users</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param search the string to match specific users
     * @return the users matching the search string and belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProjectUser> getProjectUsers(Object projectIdOrPath, String search) throws GitLabApiException {
        return (getProjectUsers(projectIdOrPath, search, getDefaultPerPage()).all());
    }

    /**
     * Get a Pager of project users matching the specified search string. This Pager includes
     * all project members and all users assigned to project parent groups.
     *
     * <pre><code>GET /projects/:id/users</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param search the string to match specific users
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of the users matching the search string and belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<ProjectUser> getProjectUsers(Object projectIdOrPath, String search, int itemsPerPage) throws GitLabApiException {
        MultivaluedMap<String, String> params = (search != null ? new GitLabApiForm().withParam("search", search).asMap() : null);
        return (new Pager<ProjectUser>(this, ProjectUser.class, itemsPerPage, params,
                "projects", getProjectIdOrPath(projectIdOrPath), "users"));
    }

    /**
     * Get a Stream of project users matching the specified search string. This Stream
     * includes all project members and all users assigned to project parent groups.
     *
     * <pre><code>GET /projects/:id/users</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param search the string to match specific users
     * @return a Stream of the users matching the search string and belonging to the specified project and its parent groups
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<ProjectUser> getProjectUsersStream(Object projectIdOrPath, String search) throws GitLabApiException {
        return (getProjectUsers(projectIdOrPath, search, getDefaultPerPage()).stream());
    }

    /**
     * Get the project events for specific project. Sorted from newest to latest.
     *
     * <pre><code>GET /projects/:id/events</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return the project events for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getProjectEvents(Object projectIdOrPath) throws GitLabApiException {
        return (getProjectEvents(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get the project events for specific project. Sorted from newest to latest in the specified page range.
     *
     * <pre><code>GET /projects/:id/events</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param page the page to get
     * @param perPage the number of Event instances per page
     * @return the project events for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getProjectEvents(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "events");
        return (response.readEntity(new GenericType<List<Event>>() {}));
    }

    /**
     * Get a Pager of project events for specific project. Sorted from newest to latest.
     *
     * <pre><code>GET /projects/:id/events</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of project events for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Event> getProjectEvents(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Event>(this, Event.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "events"));
    }

    /**
     * Get a Stream of the project events for specific project. Sorted from newest to latest.
     *
     * <pre><code>GET /projects/:id/events</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of the project events for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Event> getProjectEventsStream(Object projectIdOrPath) throws GitLabApiException {
        return (getProjectEvents(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of the project hooks for the specified project.
     *
     * <pre><code>GET /projects/:id/hooks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a list of project hooks for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProjectHook> getHooks(Object projectIdOrPath) throws GitLabApiException {
        return (getHooks(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get list of project hooks in the specified page range.
     *
     * <pre><code>GET /projects/:id/hooks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param page the page to get
     * @param perPage the number of ProjectHook instances per page
     * @return a list of project hooks for the specified project in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProjectHook> getHooks(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "hooks");
        return (response.readEntity(new GenericType<List<ProjectHook>>() {}));
    }

    /**
     * Get Pager of project hooks.
     *
     * <pre><code>GET /projects/:id/hooks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of project hooks for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<ProjectHook> getHooks(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<ProjectHook>(this, ProjectHook.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "hooks"));
    }

    /**
     * Get a Stream of the project hooks for the specified project.
     *
     * <pre><code>GET /projects/:id/hooks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of project hooks for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<ProjectHook> getHooksStream(Object projectIdOrPath) throws GitLabApiException {
        return (getHooks(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a specific hook for project.
     *
     * <pre><code>GET /projects/:id/hooks/:hook_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param hookId the ID of the hook to get
     * @return the project hook for the specified project ID/hook ID pair
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook getHook(Object projectIdOrPath, Integer hookId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "hooks", hookId);
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Get a specific hook for project as an Optional instance.
     *
     * <pre><code>GET /projects/:id/hooks/:hook_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param hookId the ID of the hook to get
     * @return the project hook for the specified project ID/hook ID pair as an Optional instance
     */
    public Optional<ProjectHook> getOptionalHook(Object projectIdOrPath, Integer hookId) {
        try {
            return (Optional.ofNullable(getHook(projectIdOrPath, hookId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Adds a hook to project.
     *
     * <pre><code>POST /projects/:id/hooks</code></pre>
     *
     * @param projectName the name of the project
     * @param url the callback URL for the hook
     * @param enabledHooks a ProjectHook instance specifying which hooks to enable
     * @param enableSslVerification enable SSL verification
     * @param secretToken the secret token to pass back to the hook
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook addHook(String projectName, String url, ProjectHook enabledHooks, boolean enableSslVerification, String secretToken)
            throws GitLabApiException {

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
     * <pre><code>POST /projects/:id/hooks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param url the callback URL for the hook
     * @param enabledHooks a ProjectHook instance specifying which hooks to enable
     * @param enableSslVerification enable SSL verification
     * @param secretToken the secret token to pass back to the hook
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook addHook(Object projectIdOrPath, String url, ProjectHook enabledHooks, boolean enableSslVerification, String secretToken)
            throws GitLabApiException {

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
        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "hooks");
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Adds a hook to project.
     *
     * <pre><code>POST /projects/:id/hooks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param url the callback URL for the hook
     * @param doPushEvents flag specifying whether to do push events
     * @param doIssuesEvents flag specifying whether to do issues events
     * @param doMergeRequestsEvents flag specifying whether to do merge requests events
     * @return the added ProjectHook instance
     * @throws GitLabApiException if any exception occurs
     */
    public ProjectHook addHook(Object projectIdOrPath, String url, boolean doPushEvents,
            boolean doIssuesEvents, boolean doMergeRequestsEvents) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("url", url)
                .withParam("push_events", doPushEvents)
                .withParam("issues_enabled", doIssuesEvents)
                .withParam("merge_requests_events", doMergeRequestsEvents);

        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "hooks");
        return (response.readEntity(ProjectHook.class));
    }

    /**
     * Deletes a hook from the project.
     *
     * <pre><code>DELETE /projects/:id/hooks/:hook_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param hookId the project hook ID to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteHook(Object projectIdOrPath, Integer hookId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "hooks", hookId);
    }

    /**
     * Deletes a hook from the project.
     *
     * <pre><code>DELETE /projects/:id/hooks/:hook_id</code></pre>
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
     * <pre><code>PUT /projects/:id/hooks/:hook_id</code></pre>
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
     * Get a list of the project's issues.
     *
     * <pre><code>GET /projects/:id/issues</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a list of project's issues
     * @throws GitLabApiException if any exception occurs
     * @deprecated Will be removed in version 5.0, replaced by {@link IssuesApi#getIssues(Object)}
     */
    @Deprecated
    public List<Issue> getIssues(Object projectIdOrPath) throws GitLabApiException {
        return (getIssues(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of project's issues using the specified page and per page settings.
     *
     * <pre><code>GET /projects/:id/issues</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param page the page to get
     * @param perPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     * @deprecated Will be removed in version 5.0, replaced by {@link IssuesApi#getIssues(Object, int, int)}
     */
    @Deprecated
    public List<Issue> getIssues(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get a Pager of project's issues.
     *
     * <pre><code>GET /projects/:id/issues</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param itemsPerPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     * @deprecated Will be removed in version 5.0, replaced by {@link IssuesApi#getIssues(Object, int)}
     */
    @Deprecated
    public Pager<Issue> getIssues(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Issue>(this, Issue.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "issues"));
    }

    /**
     * Get a Stream of the project's issues.
     *
     * <pre><code>GET /projects/:id/issues</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of the project's issues
     * @throws GitLabApiException if any exception occurs
     * @deprecated Will be removed in version 5.0, replaced by {@link IssuesApi#getIssues(Object)}
     */
    @Deprecated
    public Stream<Issue> getIssuesStream(Object projectIdOrPath) throws GitLabApiException {
        return (getIssues(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a single project issue.
     *
     * <pre><code>GET /projects/:id/issues/:issue_iid</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param issueId the internal ID of a project's issue
     * @return the specified Issue instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link IssuesApi#getIssue(Object, Integer)}
     */
    @Deprecated
    public Issue getIssue(Object projectIdOrPath, Integer issueId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", getProjectIdOrPath(projectIdOrPath), "issues", issueId);
        return (response.readEntity(Issue.class));
    }

    /**
     * Delete a project issue.
     *
     * <pre><code>DELETE /projects/:id/issues/:issue_iid</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param issueId the internal ID of a project's issue
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link IssuesApi#deleteIssue(Object, Integer)}
     */
    @Deprecated
    public void deleteIssue(Object projectIdOrPath, Integer issueId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, getDefaultPerPageParam(), "projects", getProjectIdOrPath(projectIdOrPath), "issues", issueId);
    }

    /**
     * Get a list of the project snippets.
     *
     * <pre><code>GET /projects/:id/snippets</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a list of the project's snippets
     * @throws GitLabApiException if any exception occurs
     */
    public List<Snippet> getSnippets(Object projectIdOrPath) throws GitLabApiException {
        return (getSnippets(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of project snippets.
     *
     * <pre><code>GET /projects/:id/snippets</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param page the page to get
     * @param perPage the number of snippets per page
     * @return a list of project's snippets for the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Snippet> getSnippets(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "snippets");
        return (response.readEntity(new GenericType<List<Snippet>>() {}));
    }

    /**
     * Get a Pager of project's snippets.
     *
     * <pre><code>GET /projects/:id/snippets</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param itemsPerPage the number of snippets per page
     * @return the Pager of snippets
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Snippet> getSnippets(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Snippet>(this, Snippet.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "snippets"));
    }

    /**
     * Get a Stream of the project snippets.
     *
     * <pre><code>GET /projects/:id/snippets</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of the project's snippets
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Snippet> getSnippetsStream(Object projectIdOrPath) throws GitLabApiException {
        return (getSnippets(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a single of project snippet.
     *
     * <pre><code>GET /projects/:id/snippets/:snippet_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param snippetId the ID of the project's snippet
     * @return the specified project Snippet
     * @throws GitLabApiException if any exception occurs
     */
    public Snippet getSnippet(Object projectIdOrPath, Integer snippetId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "snippets", snippetId);
        return (response.readEntity(Snippet.class));
    }

    /**
     * Get a single of project snippet as an Optional instance.
     *
     * <pre><code>GET /projects/:id/snippets/:snippet_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param snippetId the ID of the project's snippet
     * @return the specified project Snippet as an Optional instance
     */
    public Optional<Snippet> getOptionalSnippet(Object projectIdOrPath, Integer snippetId) {
        try {
            return (Optional.ofNullable(getSnippet(projectIdOrPath, snippetId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a new project snippet. The user must have permission to create new snippets.
     *
     * <pre><code>POST /projects/:id/snippets</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param title the title of a snippet, required
     * @param filename the name of a snippet file, required
     * @param description the description of a snippet, optional
     * @param code the content of a snippet, required
     * @param visibility the snippet's visibility, required
     * @return a Snippet instance with info on the created snippet
     * @throws GitLabApiException if any exception occurs
     */
    public Snippet createSnippet(Object projectIdOrPath, String title, String filename, String description,
            String code, Visibility visibility) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("file_name", filename, true)
                .withParam("description", description)
                .withParam("code", code, true)
                .withParam("visibility", visibility, true);

        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "snippets");
        return (response.readEntity(Snippet.class));
    }

    /**
     * Updates an existing project snippet. The user must have permission to change an existing snippet.
     *
     * <pre><code>PUT /projects/:id/snippets/:snippet_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param snippetId the ID of a project's snippet, required
     * @param title the title of a snippet, optional
     * @param filename the name of a snippet file, optional
     * @param description the description of a snippet, optioptionalonal
     * @param code the content of a snippet, optional
     * @param visibility the snippet's visibility, reqoptionaluired
     * @return a Snippet instance with info on the updated snippet
     * @throws GitLabApiException if any exception occurs
     */
    public Snippet updateSnippet(Object projectIdOrPath, Integer snippetId, String title, String filename, String description,
            String code, Visibility visibility) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title)
                .withParam("file_name", filename)
                .withParam("description", description)
                .withParam("code", code)
                .withParam("visibility", visibility);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "snippets", snippetId);
        return (response.readEntity(Snippet.class));
    }

    /*
     * Deletes an existing project snippet. This is an idempotent function and deleting a
     * non-existent snippet does not cause an error.
     *
     * <pre><code>DELETE /projects/:id/snippets/:snippet_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param snippetId the ID of the project's snippet
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteSnippet(Object projectIdOrPath, Integer snippetId) throws GitLabApiException {
        delete(Response.Status.NO_CONTENT, null, "projects", getProjectIdOrPath(projectIdOrPath), "snippets", snippetId);
    }

    /**
     * Get the raw project snippet as plain text.
     *
     * <pre><code>GET /projects/:id/snippets/:snippet_id/raw</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param snippetId the ID of the project's snippet
     * @return the raw project snippet plain text as an Optional instance
     * @throws GitLabApiException if any exception occurs
     */
    public String getRawSnippetContent(Object projectIdOrPath, Integer snippetId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "snippets", snippetId, "raw");
        return (response.readEntity(String.class));
    }

    /**
     * Get the raw project snippet plain text as an Optional instance.
     *
     * <pre><code>GET /projects/:id/snippets/:snippet_id/raw</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param snippetId the ID of the project's snippet
     * @return the raw project snippet plain text as an Optional instance
     */
    public Optional<String> getOptionalRawSnippetContent(Object projectIdOrPath, Integer snippetId) {
        try {
            return (Optional.ofNullable(getRawSnippetContent(projectIdOrPath, snippetId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Share a project with the specified group.
     *
     * <pre><code>POST /projects/:id/share</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param groupId the ID of the group to share with, required
     * @param accessLevel the permissions level to grant the group, required
     * @param expiresAt the share expiration date, optional
     * @throws GitLabApiException if any exception occurs
     */
    public void shareProject(Object projectIdOrPath, Integer groupId, AccessLevel accessLevel, Date expiresAt)
            throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
            .withParam("group_id", groupId, true)
            .withParam("group_access", accessLevel, true)
            .withParam("expires_at", expiresAt);
        post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "share");
    }

    /**
     * Unshare the project from the group.
     *
     * <pre><code>DELETE /projects/:id/share/:group_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param groupId the ID of the group to unshare, required
     * @throws GitLabApiException if any exception occurs
     */
    public void unshareProject(Object projectIdOrPath, Integer groupId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "share", groupId);
    }

    /**
     * Archive a project
     *
     * <pre><code>POST /projects/:id/archive</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return the archived GitLab Project
     * @throws GitLabApiException if any exception occurs
     */
    public Project archiveProject(Object projectIdOrPath)
            throws GitLabApiException {
        Response response = post(Response.Status.CREATED, (new GitLabApiForm()), "projects", getProjectIdOrPath(projectIdOrPath), "archive");
        return (response.readEntity(Project.class));
    }

    /**
     * Unarchive a project
     *
     * <pre><code>POST /projects/:id/unarchive</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return the unarchived GitLab Project
     * @throws GitLabApiException if any exception occurs
     */
    public Project unarchiveProject(Object projectIdOrPath)
            throws GitLabApiException {
        Response response = post(Response.Status.CREATED, (new GitLabApiForm()), "projects", getProjectIdOrPath(projectIdOrPath), "unarchive");
        return (response.readEntity(Project.class));
    }

    /**
     * Uploads a file to the specified project to be used in an issue or merge request description, or a comment.
     *
     * <pre><code>POST /projects/:id/uploads</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param fileToUpload the File instance of the file to upload, required
     * @return a FileUpload instance with information on the just uploaded file
     * @throws GitLabApiException if any exception occurs
     */
    public FileUpload uploadFile(Object projectIdOrPath, File fileToUpload) throws GitLabApiException {
        return (uploadFile(projectIdOrPath, fileToUpload, null));
    }

    /**
     * Uploads a file to the specified project to be used in an issue or merge request description, or a comment.
     *
     * <pre><code>POST /projects/:id/uploads</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param fileToUpload the File instance of the file to upload, required
     * @param mediaType the media type of the file to upload, optional
     * @return a FileUpload instance with information on the just uploaded file
     * @throws GitLabApiException if any exception occurs
     */
    public FileUpload uploadFile(Object projectIdOrPath, File fileToUpload, String mediaType) throws GitLabApiException {
        Response response = upload(Response.Status.CREATED, "file", fileToUpload, mediaType, "projects", getProjectIdOrPath(projectIdOrPath), "uploads");
        return (response.readEntity(FileUpload.class));
    }

    /**
     * Get the project's push rules.
     *
     * <pre><code>GET /projects/:id/push_rule</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return the push rules for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public PushRules getPushRules(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "push_rule");
        return (response.readEntity(PushRules.class));
    }

    /**
     * Adds a push rule to a specified project.
     *
     * <pre><code>POST /projects/:id/push_rule</code></pre>
     *
     * The following properties on the PushRules instance are utilized in the creation of the push rule:
     *
     *<code>
     * denyDeleteTag (optional) - Deny deleting a tag
     * memberCheck (optional) - Restrict commits by author (email) to existing GitLab users
     * preventSecrets (optional) - GitLab will reject any files that are likely to contain secrets
     * commitMessageRegex (optional) - All commit messages must match this, e.g. Fixed \d+\..*
     * branchNameRegex (optional) - All branch names must match this, e.g. `(feature
     * authorEmailRegex (optional) - All commit author emails must match this, e.g. @my-company.com$
     * fileNameRegex (optional) - All committed filenames must not match this, e.g. `(jar
     * maxFileSize (optional) - Maximum file size (MB
     *</code>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pushRule the PushRule instance containing the push rule configuration to add
     * @return a PushRules instance with the newly created push rule info
     * @throws GitLabApiException if any exception occurs
     */
    public PushRules createPushRules(Object projectIdOrPath, PushRules pushRule) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
            .withParam("deny_delete_tag", pushRule.getDenyDeleteTag())
            .withParam("member_check", pushRule.getMemberCheck())
            .withParam("prevent_secrets", pushRule.getPreventSecrets())
            .withParam("commit_message_regex", pushRule.getCommitMessageRegex())
            .withParam("branch_name_regex", pushRule.getBranchNameRegex())
            .withParam("author_email_regex", pushRule.getAuthorEmailRegex())
            .withParam("file_name_regex", pushRule.getFileNameRegex())
            .withParam("max_file_size", pushRule.getMaxFileSize());

        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "push_rule");
        return (response.readEntity(PushRules.class));
    }

    /**
     * Updates a push rule for the specified project.
     *
     * <pre><code>PUT /projects/:id/push_rule/:push_rule_id</code></pre>
     *
     * The following properties on the PushRules instance are utilized when updating the push rule:
     *
     *<code>
     * denyDeleteTag (optional) - Deny deleting a tag
     * memberCheck (optional) - Restrict commits by author (email) to existing GitLab users
     * preventSecrets (optional) - GitLab will reject any files that are likely to contain secrets
     * commitMessageRegex (optional) - All commit messages must match this, e.g. Fixed \d+\..*
     * branchNameRegex (optional) - All branch names must match this, e.g. `(feature
     * authorEmailRegex (optional) - All commit author emails must match this, e.g. @my-company.com$
     * fileNameRegex (optional) - All committed filenames must not match this, e.g. `(jar
     * maxFileSize (optional) - Maximum file size (MB
     *</code>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pushRule the PushRules instance containing the push rule configuration to update
     * @return a PushRules instance with the newly created push rule info
     * @throws GitLabApiException if any exception occurs
     */
    public PushRules updatePushRules(Object projectIdOrPath, PushRules pushRule) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
            .withParam("deny_delete_tag", pushRule.getDenyDeleteTag())
            .withParam("member_check", pushRule.getMemberCheck())
            .withParam("prevent_secrets", pushRule.getPreventSecrets())
            .withParam("commit_message_regex", pushRule.getCommitMessageRegex())
            .withParam("branch_name_regex", pushRule.getBranchNameRegex())
            .withParam("author_email_regex", pushRule.getAuthorEmailRegex())
            .withParam("file_name_regex", pushRule.getFileNameRegex())
            .withParam("max_file_size", pushRule.getMaxFileSize());

        final Response response = putWithFormData(Response.Status.OK, formData, "projects", getProjectIdOrPath(projectIdOrPath), "push_rule");
        return (response.readEntity(PushRules.class));
    }

    /**
     * Removes a push rule from a project. This is an idempotent method and can be
     * called multiple times. Either the push rule is available or not.
     *
     * <pre><code>DELETE /projects/:id/push_rule</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @throws GitLabApiException if any exception occurs
     */
    public void deletePushRules(Object projectIdOrPath) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "push_rule");
    }

    /**
     * Get a list of projects that were forked from the specified project.
     *
     * <pre><code>GET /projects/:id/forks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a List of forked projects
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getForks(Object projectIdOrPath) throws GitLabApiException {
        return (getForks(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of projects that were forked from the specified project and in the specified page range.
     *
     * <pre><code>GET /projects/:id/forks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a List of forked projects
     * @throws GitLabApiException if any exception occurs
     */
    public List<Project> getForks(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),"projects", getProjectIdOrPath(projectIdOrPath), "forks");
        return (response.readEntity(new GenericType<List<Project>>() { }));
    }

    /**
     * Get a Pager of projects that were forked from the specified project.
     *
     * <pre><code>GET /projects/:id/forks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of projects
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Project> getForks(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return new Pager<Project>(this, Project.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "forks");
    }

    /**
     * Get a Stream of projects that were forked from the specified project.
     *
     * <pre><code>GET /projects/:id/forks</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of forked projects
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Project> getForksStream(Object projectIdOrPath) throws GitLabApiException {
        return (getForks(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Star a project.
     *
     * <pre><code>POST /projects/:id/star</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return a Project instance with the new project info
     * @throws GitLabApiException if any exception occurs
     */
    public Project starProject(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.CREATED);
        Response response = post(expectedStatus, (Form) null, "projects", getProjectIdOrPath(projectIdOrPath), "star");
        return (response.readEntity(Project.class));
    }

    /**
     * Unstar a project.
     *
     * <pre><code>POST /projects/:id/unstar</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return a Project instance with the new project info
     * @throws GitLabApiException if any exception occurs
     */
    public Project unstarProject(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.CREATED);
        Response response = post(expectedStatus, (Form) null, "projects", getProjectIdOrPath(projectIdOrPath), "unstar");
        return (response.readEntity(Project.class));
    }

    /**
     * Get languages used in a project with percentage value.
     *
     * <pre><code>GET /projects/:id/languages</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Map instance with the language as the key and the percentage as the value
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 10.8
     */
    public Map<String, Float> getProjectLanguages(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "languages");
        return (response.readEntity(new GenericType<Map<String, Float>>() {}));
    }

    /**
     * Transfer a project to a new namespace.  This was added in GitLab 11.1
     *
     * <pre><code>PUT /projects/:id/transfer.</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param namespace the namespace to transfer the project to
     * @return the updated Project
     * @throws GitLabApiException if any exception occurs
     */
    public Project transferProject(Object projectIdOrPath, String namespace) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("namespace", namespace, true);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "transfer");
        return (response.readEntity(Project.class));
    }

    /**
     * Uploads and sets the project avatar for the specified project.
     *
     * <pre><code>PUT /projects/:id/uploads</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param avatarFile the File instance of the avatar file to upload
     * @return the updated Project instance
     * @throws GitLabApiException if any exception occurs
     */
    public Project setProjectAvatar(Object projectIdOrPath, File avatarFile) throws GitLabApiException {
        Response response = putUpload(Response.Status.OK, "avatar", avatarFile, "projects", getProjectIdOrPath(projectIdOrPath));
        return (response.readEntity(Project.class));
    }

    /**
     * Get list of a project's variables.
     *
     * <pre><code>GitLab Endpoint: GET /groups/:id/variables</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a list of variables belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Variable> getVariables(Object projectIdOrPath) throws GitLabApiException {
        return (getVariables(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of variables for the specified project in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/variables</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param page the page to get
     * @param perPage the number of Variable instances per page
     * @return a list of variables belonging to the specified project in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Variable> getVariables(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "variables");
        return (response.readEntity(new GenericType<List<Variable>>() {}));
    }

    /**
     * Get a Pager of variables belonging to the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/variables</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param itemsPerPage the number of Variable instances that will be fetched per page
     * @return a Pager of variables belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Variable> getVariables(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Variable>(this, Variable.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "variables"));
    }

    /**
     * Get a Stream of variables belonging to the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/variables</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of variables belonging to the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Variable> getVariablesStream(Object projectIdOrPath) throws GitLabApiException {
        return (getVariables(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get the details of a project variable.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/variables/:key</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param key the key of an existing variable, required
     * @return the Variable instance for the specified variable
     * @throws GitLabApiException if any exception occurs
     */
    public Variable getVariable(Object projectIdOrPath, String key) throws GitLabApiException {
      Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "variables", key);
      return (response.readEntity(Variable.class));
    }

    /**
     * Get the details of a variable as an Optional instance.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/variables/:key</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param key the key of an existing variable, required
     * @return the Variable for the specified variable as an Optional instance
     */
    public Optional<Variable> getOptionalVariable(Object projectIdOrPath, String key) {
        try {
            return (Optional.ofNullable(getVariable(projectIdOrPath, key)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Create a new project variable.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/variables</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param key the key of a variable; must have no more than 255 characters; only A-Z, a-z, 0-9, and _ are allowed, required
     * @param value the value for the variable, required
     * @param isProtected whether the variable is protected, optional
     * @return a Variable instance with the newly created variable
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Variable createVariable(Object projectIdOrPath, String key, String value, Boolean isProtected) throws GitLabApiException {
        return (createVariable(projectIdOrPath, key, value, isProtected, null));
    }

    /**
     * Create a new project variable.
     *
     * <p>NOTE: Setting the environmentScope is only available on GitLab EE.</p>
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/variables</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param key the key of a variable; must have no more than 255 characters; only A-Z, a-z, 0-9, and _ are allowed, required
     * @param value the value for the variable, required
     * @param isProtected whether the variable is protected, optional
     * @param environmentScope the environment_scope of the variable, optional
     * @return a Variable instance with the newly created variable
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Variable createVariable(Object projectIdOrPath, String key, String value, Boolean isProtected, String environmentScope) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("key", key, true)
                .withParam("value", value, true)
                .withParam("protected", isProtected)
                .withParam("environment_scope",  environmentScope);
        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "variables");
        return (response.readEntity(Variable.class));
    }

    /**
     * Update a project variable.
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/variables/:key</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param key the key of an existing variable, required
     * @param value the value for the variable, required
     * @param isProtected whether the variable is protected, optional
     * @return a Variable instance with the updated variable
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Variable updateVariable(Object projectIdOrPath, String key, String value, Boolean isProtected) throws GitLabApiException {
        return (updateVariable(projectIdOrPath, key, value, isProtected, null));
    }

    /**
     * Update a project variable.
     *
     * <p>NOTE: Updating the environmentScope is only available on GitLab EE.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/variables/:key</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param key the key of an existing variable, required
     * @param value the value for the variable, required
     * @param isProtected whether the variable is protected, optional
     * @param environmentScope the environment_scope of the variable, optional.
     * @return a Variable instance with the updated variable
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Variable updateVariable(Object projectIdOrPath, String key, String value, Boolean isProtected, String environmentScope) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("value", value, true)
                .withParam("protected", isProtected)
                .withParam("environment_scope",  environmentScope);
        Response response = putWithFormData(Response.Status.OK, formData, "projects", getProjectIdOrPath(projectIdOrPath), "variables", key);
        return (response.readEntity(Variable.class));
    }

    /**
     * Deletes a project variable.
     *
     * <pre><code>DELETE /projects/:id/variables/:key</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param key the key of an existing variable, required
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteVariable(Object projectIdOrPath, String key) throws GitLabApiException {
        delete(Response.Status.NO_CONTENT, null, "projects", getProjectIdOrPath(projectIdOrPath), "variables", key);
    }
}
