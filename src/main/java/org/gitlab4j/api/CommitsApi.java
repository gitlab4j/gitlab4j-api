package org.gitlab4j.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Comment;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitAction;
import org.gitlab4j.api.models.CommitPayload;
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
     * @param path the path to file of a project
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId, String ref, Date since, Date until, String path) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam(PER_PAGE_PARAM, getDefaultPerPage())
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false))
                .withParam("path", (path == null ? null : urlEncode(path)));
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a list of file commits in a project
     *
     * GET /projects/:id/repository/commits?path=:file_path
     *
     * @param projectId the project ID to get the list of commits for
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param path the path to file of a project
     * @return a list containing the commits for the specified project ID and file
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId, String ref, String path) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam(PER_PAGE_PARAM, getDefaultPerPage())
                .withParam("ref_name", ref)
                .withParam("path", (path == null ? null : urlEncode(path)));
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
     * Get a specific commit identified by the commit hash or name of a branch or tag as an Optional instance
     *
     * GET /projects/:id/repository/commits/:sha
     *
     * @param projectId the project ID that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @return the Commit for the specified project ID/sha pair as an Optional instance
     */
    public Optional<Commit> getOptionalCommit(int projectId, String sha) {
        try {
            return (Optional.ofNullable(getCommit(projectId, sha)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
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

    /**
     * Get the comments of a commit in a project.
     *
     * GET /projects/:id/repository/commits/:sha/comments
     *
     * @param projectId the project ID that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @return a List of Comment instances for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Comment> getComments(int projectId, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "commits", sha, "comments");
        return (response.readEntity(new GenericType<List<Comment>>() {}));
    }

    /**
     * Add a comment to a commit.  In order to post a comment in a particular line of a particular file,
     * you must specify the full commit SHA, the path, the line and lineType should be NEW.
     *
     * POST /projects/:id/repository/commits/:sha/comments
     *
     * @param projectId the project ID that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @param note the text of the comment, required
     * @param path the file path relative to the repository, optional
     * @param line the line number where the comment should be placed, optional
     * @param lineType the line type, optional
     * @return a Comment instance for the posted comment
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Comment addComment(int projectId, String sha, String note, String path, Integer line, LineType lineType) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("note", note, true)
                .withParam("path", path)
                .withParam("line", line)
                .withParam("line_type", lineType);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "repository", "commits", sha, "comments");
        return (response.readEntity(Comment.class));
    }

    /**
     * Add a comment to a commit.
     *
     * POST /projects/:id/repository/commits/:sha/comments
     *
     * @param projectId the project ID that the commit belongs to
     * @param sha a commit hash or name of a branch or tag
     * @param note the text of the comment, required
     * @return a Comment instance for the posted comment
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Comment addComment(int projectId, String sha, String note) throws GitLabApiException {
        return (addComment(projectId, sha, note, null, null, null));
    }

    /**
     * Create a commit with multiple files and actions.
     *
     * POST /projects/:id/repository/commits
     *
     * @param projectId the ID of the project
     * @param branch tame of the branch to commit into. To create a new branch, also provide startBranch
     * @param commitMessage the commit message
     * @param startBranch the name of the branch to start the new commit from
     * @param authorEmail the commit author's email address
     * @param authorName the commit author's name
     * @param actions the array of CommitAction to commit as a batch
     * @return the create Commit instance
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Commit createCommit(int projectId, String branch, String commitMessage, String startBranch,
            String authorEmail, String authorName, List<CommitAction> actions) throws GitLabApiException {

        CommitPayload payload = new CommitPayload();
        payload.setBranch(branch);
        payload.setCommitMessage(commitMessage);
        payload.setStartBranch(startBranch);
        payload.setAuthorEmail(authorEmail);
        payload.setAuthorName(authorName);
        payload.setActions(actions);

        Response response = post(Response.Status.CREATED, payload, "projects", projectId, "repository", "commits");
        return (response.readEntity(Commit.class));
    }

    /**
     * Create a commit with multiple files and actions.
     *
     * POST /projects/:id/repository/commits
     *
     * @param project the path of the project
     * @param branch tame of the branch to commit into. To create a new branch, also provide startBranch
     * @param commitMessage the commit message
     * @param startBranch the name of the branch to start the new commit from
     * @param authorEmail the commit author's email address
     * @param authorName the commit author's name
     * @param actions the array of CommitAction to commit as a batch
     * @return the create Commit instance
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Commit createCommit(String project, String branch, String commitMessage, String startBranch,
            String authorEmail, String authorName, List<CommitAction> actions) throws GitLabApiException {

        CommitPayload payload = new CommitPayload();
        payload.setBranch(branch);
        payload.setCommitMessage(commitMessage);
        payload.setStartBranch(startBranch);
        payload.setAuthorEmail(authorEmail);
        payload.setAuthorName(authorName);
        payload.setActions(actions);

        Response response = post(Response.Status.CREATED, payload, "projects", urlEncode(project), "repository", "commits");
        return (response.readEntity(Commit.class));
    }
}
