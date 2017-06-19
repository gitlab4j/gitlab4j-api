package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Diff;

/**
 * This class implements the client side API for the GitLab commits calls.
 */
public class CommitsApi extends AbstractApi {

    public CommitsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectId the project ID to get the list of commits for
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectId the project ID to get the list of commits for
     * @param page the page to get
     * @param perPage the number of commits per page
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a Pager of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectId the project ID to get the list of commits for
     * @param itemsPerPage the number of Commit instances that will be fetched per page
     * @return a Pager containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Commit> getCommits(int projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Commit>(this, Commit.class, itemsPerPage, null,  "projects", projectId, "repository", "commits"));
    }

    /**
     * Get a specific commit identified by the commit hash or name of a branch or tag.
     *
     * GET /projects/:id/repository/commits/:sha
     *
     * @param projectId the project ID that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @return the Commit instance for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Commit getCommit(int projectId, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "repository", "commits", sha);
        return (response.readEntity(Commit.class));
    }

    /**
     * Get the diff of a commit in a project.
     *
     * GET /projects/:id/repository/commits/:sha/diff
     *
     * @param projectId the project ID that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @return the Diff instance for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Diff getDiff(int projectId, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "commits", sha, "diff");
        return (response.readEntity(Diff.class));
    }
}
