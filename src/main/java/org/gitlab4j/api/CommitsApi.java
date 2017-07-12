package org.gitlab4j.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.utils.ISO8601;

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
        return (getCommits(projectId, null, null, null));
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
        return (getCommits(projectId, null, null, null, page, perPage));
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
        return (getCommits(projectId, null, null, null, itemsPerPage));
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectId the project ID to get the list of commits for
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId, String ref, Date since, Date until) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false))
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectId the project ID to get the list of commits for
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @param page the page to get
     * @param perPage the number of commits per page
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId, String ref, Date since, Date until, int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false))
                .withParam(PAGE_PARAM,  page)
                .withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a Pager of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectId the project ID to get the list of commits for
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @param itemsPerPage the number of Commit instances that will be fetched per page
     * @return a Pager containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Commit> getCommits(int projectId, String ref, Date since, Date until, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false));
        return (new Pager<Commit>(this, Commit.class, itemsPerPage, formData.asMap(),  "projects", projectId, "repository", "commits"));
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
     * Get the list of diffs of a commit in a project.
     *
     * GET /projects/:id/repository/commits/:sha/diff
     *
     * @param projectId the project ID that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @return a List of Diff instances for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Diff> getDiff(int projectId, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "commits", sha, "diff");
        return (response.readEntity(new GenericType<List<Diff>>() {}));
    }

    /**
     * Get the list of diffs of a commit in a project.
     *
     * GET /projects/:id/repository/commits/:sha/diff
     *
     * @param projectPath the project path that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @return a List of Diff instances for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Diff> getDiff(String projectPath, String sha) throws GitLabApiException {

        try {
            projectPath = URLEncoder.encode(projectPath, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw (new GitLabApiException(uee));
        }

        Response response = get(Response.Status.OK, null, "projects", projectPath, "repository", "commits", sha, "diff");
        return (response.readEntity(new GenericType<List<Diff>>() {}));
    }
}
