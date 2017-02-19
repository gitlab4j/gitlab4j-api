package org.gitlab4j.api;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Project;

/**
 * Access for the services API.
 * See
 * <a href="https://github.com/gitlabhq/gitlabhq/blob/master/doc/api/services.md">GitLab documentation</a>.
 * It is quite restricted as you may not retrieve the API but only set or delete.
 */
public class ServicesApi extends AbstractApi {
    public ServicesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Activates the gitlab-ci service.
     *
     * PUT /projects/:id/services/gitlab-ci
     * @param projectId id of the project
     * @param token for authentication
     * @param projectCIUrl URL of the GitLab-CI project
     */
    public void setGitLabCI(Integer projectId, String token, String projectCIUrl) throws GitLabApiException {
        final Form formData = new Form();
        formData.param("token", token);
        formData.param("project_url", projectCIUrl);
        put(Response.Status.OK, formData.asMap(), "projects", projectId, "services", "gitlab-ci");
    }

    /**
     * Activates the gitlab-ci service.
     *
     * PUT /projects/:id/services/gitlab-ci
     * @param project the project
     * @param token for authentication
     * @param projectCIUrl URL of the GitLab-CI project
     */
    public void setGitLabCI(Project project, String token, String projectCIUrl) throws GitLabApiException {
        setGitLabCI(project.getId(), token, projectCIUrl);
    }

    /**
     * Deletes the gitlab-ci service.
     *
     * DELETE /projects/:id/services/gitlab-ci
     *
     * @param projectId id of the project
     * @throws GitLabApiException
     */
    public void deleteGitLabCI(Integer projectId) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", projectId, "services", "gitlab-ci");
    }

    /**
     * DELETE /projects/:id/services/gitlab-ci
     * @param project to delete
     * @throws GitLabApiException
     */
    public void deleteGitLabCI(Project project) throws GitLabApiException {
        deleteGitLabCI(project.getId());
    }

    /**
     * Activates HipChat notifications.
     *
     * PUT /projects/:id/services/hipchat
     *
     * @param projectId id of the project
     * @param token for authentication
     * @param room HipChat Room
     * @param server HipChat Server URL
     *
     * @throws GitLabApiException
     */
    public void setHipChat(Integer projectId, String token, String room, String server) throws GitLabApiException {
        final Form formData = new Form();
        formData.param("token", token);
        formData.param("room", room);
        formData.param("server", server);
        put(Response.Status.OK, formData.asMap(), "projects", projectId, "services", "hipchat");
    }

    /**
     * Activates HipChat notifications.
     *
     * PUT /projects/:id/services/hipchat
     *
     * @param project
     * @param token
     * @param room
     * @param server
     * @throws GitLabApiException
     */
    public void setHipChat(Project project, String token, String room, String server) throws GitLabApiException {
        setHipChat(project.getId(), token, room, server);
    }

    /**
     * Deletes the gitlab-ci service.
     *
     * DELETE /projects/:id/services/hipchat
     *
     * @param projectId id of the project
     * @throws GitLabApiException
     */
    public void deleteHipChat(Integer projectId) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", projectId, "services", "hipchat");
    }

    /**
     * Deletes the gitlab-ci service.
     *
     * DELETE /projects/:id/services/hipchat
     *
     * @param project the project
     * @throws GitLabApiException
     */
    public void deleteHipChat(Project project) throws GitLabApiException {
        deleteHipChat(project.getId());
    }
}
