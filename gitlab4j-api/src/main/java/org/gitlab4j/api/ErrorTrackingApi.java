package org.gitlab4j.api;

import java.util.List;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import org.gitlab4j.api.models.ErrorTrackingClientKey;

/**
 * This class provides an entry point to the GitLab API error tracking.
 * <a href="https://docs.gitlab.com/api/error_tracking/">GitLab Error tracking API Documentation</a>
 */
public class ErrorTrackingApi extends AbstractApi {

    public ErrorTrackingApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Creates an integrated error tracking client key for a specified project.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/error_tracking/client_keys</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return the created ErrorTrackingClientKey
     * @throws GitLabApiException if any exception occurs
     */
    public ErrorTrackingClientKey createClientKey(Object projectIdOrPath) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm();
        Response response = post(
                Response.Status.CREATED,
                formData,
                "projects",
                getProjectIdOrPath(projectIdOrPath),
                "error_tracking",
                "client_keys");
        return (response.readEntity(ErrorTrackingClientKey.class));
    }

    /**
     * Lists all integrated error tracking client keys for a specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/error_tracking/client_keys</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return a list of ErrorTrackingClientKey
     * @throws GitLabApiException if any exception occurs
     */
    public List<ErrorTrackingClientKey> getClientKeys(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(
                Response.Status.OK,
                null,
                "projects",
                getProjectIdOrPath(projectIdOrPath),
                "error_tracking",
                "client_keys");
        return (response.readEntity(new GenericType<>() {}));
    }
}
