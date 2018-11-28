package org.gitlab4j.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Comment;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitAction;
import org.gitlab4j.api.models.CommitPayload;
import org.gitlab4j.api.models.CommitRef;
import org.gitlab4j.api.models.CommitRef.RefType;
import org.gitlab4j.api.models.CommitStatus;
import org.gitlab4j.api.models.CommitStatusFilter;
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
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath) throws GitLabApiException {
        return (getCommits(projectIdOrPath, null, null, null));
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of commits per page
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        return (getCommits(projectIdOrPath, null, null, null, page, perPage));
    }

    /**
     * Get a Pager of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Commit instances that will be fetched per page
     * @return a Pager containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Commit> getCommits(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (getCommits(projectIdOrPath, null, null, null, itemsPerPage));
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, String ref, Date since, Date until) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false))
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @param path the path to file of a project
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, String ref, Date since, Date until, String path) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam(PER_PAGE_PARAM, getDefaultPerPage())
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false))
                .withParam("path", (path == null ? null : urlEncode(path)));
        Response response = get(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a list of file commits in a project
     *
     * GET /projects/:id/repository/commits?path=:file_path
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param path the path to file of a project
     * @return a list containing the commits for the specified project ID and file
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, String ref, String path) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam(PER_PAGE_PARAM, getDefaultPerPage())
                .withParam("ref_name", ref)
                .withParam("path", (path == null ? null : urlEncode(path)));
        Response response = get(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @param page the page to get
     * @param perPage the number of commits per page
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, String ref, Date since, Date until, int page, int perPage) throws GitLabApiException {
        return getCommits(projectIdOrPath, ref, since, until, null, page, perPage);
    }

    /**
     * Get a list of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @param path the path to file of a project
     * @param page the page to get
     * @param perPage the number of commits per page
     * @return a list containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, String ref, Date since, Date until, String path, int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false))
                .withParam("path", (path == null ? null : urlEncode(path)))
                .withParam(PAGE_PARAM,  page)
                .withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a Pager of repository commits in a project.
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @param itemsPerPage the number of Commit instances that will be fetched per page
     * @return a Pager containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Commit> getCommits(Object projectIdOrPath, String ref, Date since, Date until, int itemsPerPage) throws GitLabApiException {
        return getCommits(projectIdOrPath, ref, since,until, null, itemsPerPage);
    }

    /**
     * Get a Pager of repository commits in a project
     *
     * GET /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref the name of a repository branch or tag or if not given the default branch
     * @param since only commits after or on this date will be returned
     * @param until only commits before or on this date will be returned
     * @param itemsPerPage the number of Commit instances that will be fetched per page
     * @param path the path to file of a project
     * @return a Pager containing the commits for the specified project ID
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Commit> getCommits(Object projectIdOrPath, String ref, Date since, Date until, String path, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("ref_name", ref)
                .withParam("since", ISO8601.toString(since, false))
                .withParam("until", ISO8601.toString(until, false))
                .withParam("path", (path == null ? null : urlEncode(path)));
        return (new Pager<Commit>(this, Commit.class, itemsPerPage, formData.asMap(),  "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits"));
    }

    /**
     * Get a specific commit identified by the commit hash or name of a branch or tag.
     *
     * GET /projects/:id/repository/commits/:sha
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @return the Commit instance for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Commit getCommit(Object projectIdOrPath, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha);
        return (response.readEntity(Commit.class));
    }

    /**
     * Get a specific commit identified by the commit hash or name of a branch or tag as an Optional instance
     *
     * GET /projects/:id/repository/commits/:sha
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @return the Commit for the specified project ID/sha pair as an Optional instance
     */
    public Optional<Commit> getOptionalCommit(Object projectIdOrPath, String sha) {
        try {
            return (Optional.ofNullable(getCommit(projectIdOrPath, sha)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Get a specific commit identified by the commit hash or name of a branch or tag as an Optional instance
     *
     * GET /projects/:id/repository/commits/:sha/refs
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @return Get all references (from branches or tags) a commit is pushed to
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     * @since Gitlab 10.6
     */
    public List<CommitRef> getCommitRefs(Object projectIdOrPath, String sha) throws GitLabApiException {
        return getCommitRefs(projectIdOrPath, sha, RefType.ALL);
    }

    /**
     * Get a specific commit identified by the commit hash or name of a branch or tag as an Optional instance
     *
     * GET /projects/:id/repository/commits/:sha/refs?type=:refType
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @param refType the scope of commits. Possible values branch, tag, all. Default is all.
     * @return Get all references (from branches or tags) a commit is pushed to
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     * @since Gitlab 10.6
     */
    public List<CommitRef> getCommitRefs(Object projectIdOrPath, String sha, CommitRef.RefType refType) throws GitLabApiException {
        Form form = new GitLabApiForm()
                .withParam("type", refType)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, form.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha, "refs");
        return (response.readEntity(new GenericType<List<CommitRef>>(){}));
    }

    /**
     * Get a list of repository commit statuses that meet the provided filter.
     *
     * GET /projects/:id/repository/commits/:sha/statuses
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the commit SHA
     * @param filter the commit statuses file, contains ref, stage, name, all
     * @return a List containing the commit statuses for the specified project and sha that meet the provided filter
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<CommitStatus> getCommitStatuses(Object projectIdOrPath, String sha, CommitStatusFilter filter) throws GitLabApiException {
        return (getCommitStatuses(projectIdOrPath, sha, filter, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of repository commit statuses that meet the provided filter.
     *
     * GET /projects/:id/repository/commits/:sha/statuses
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the commit SHA
     * @param filter the commit statuses file, contains ref, stage, name, all
     * @param page the page to get
     * @param perPage the number of commits statuses per page
     * @return a List containing the commit statuses for the specified project and sha that meet the provided filter
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<CommitStatus> getCommitStatuses(Object projectIdOrPath, String sha,
            CommitStatusFilter filter, int page, int perPage) throws GitLabApiException {

        if (projectIdOrPath == null) {
            throw new RuntimeException("projectIdOrPath cannot be null");
        }

        if (sha == null || sha.trim().isEmpty()) {
            throw new RuntimeException("sha cannot be null");
        }

        MultivaluedMap<String, String> queryParams = (filter != null ?
                filter.getQueryParams(page, perPage).asMap() : getPageQueryParams(page, perPage));
        Response response = get(Response.Status.OK, queryParams, 
                "projects", this.getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha, "statuses");
        return (response.readEntity(new GenericType<List<CommitStatus>>() {}));
    }

    /**
     * Get a Pager of repository commit statuses that meet the provided filter.
     *
     * GET /projects/:id/repository/commits/:sha/statuses
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the commit SHA
     * @param filter the commit statuses file, contains ref, stage, name, all
     * @param itemsPerPage the number of CommitStatus instances that will be fetched per page
     * @return a Pager containing the commit statuses for the specified project and sha that meet the provided filter
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<CommitStatus> getCommitStatuses(Object projectIdOrPath, String sha, 
            CommitStatusFilter filter, int itemsPerPage) throws GitLabApiException {

        if (projectIdOrPath == null) {
            throw new RuntimeException("projectIdOrPath cannot be null");
        }

        if (sha == null || sha.trim().isEmpty()) {
            throw new RuntimeException("sha cannot be null");
        }

        MultivaluedMap<String, String> queryParams = (filter != null ? filter.getQueryParams().asMap() : null);
        return (new Pager<CommitStatus>(this, CommitStatus.class, itemsPerPage, queryParams,
                "projects", this.getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha, "statuses"));
    }

    /**
     * <p>Add or update the build status of a commit.  The following fluent methods are available on the
     * CommitStatus instance for setting up the status:</p>
     * <pre><code>
     * withCoverage(Float)
     * withDescription(String)
     * withName(String)
     * withRef(String)
     * withTargetUrl(String)
     * </code></pre>
     * <pre><code>
     * POST /projects/:id/statuses/:sha
     * </code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance (required)
     * @param sha a commit SHA (required)
     * @param state the state of the status. Can be one of the following: PENDING, RUNNING, SUCCESS, FAILED, CANCELED (required)
     * @param status the CommitSatus instance hoilding the optional parms: ref, name, target_url, description, and coverage
     * @return a CommitStatus instance with the updated info
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public CommitStatus addCommitStatus(Object projectIdOrPath, String sha, CommitBuildState state, CommitStatus status) throws GitLabApiException {

        if (projectIdOrPath == null) {
            throw new RuntimeException("projectIdOrPath cannot be null");
        }

        if (sha == null || sha.trim().isEmpty()) {
            throw new RuntimeException("sha cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state", state, true);
        if (status != null) {
            formData.withParam("ref", status.getRef())
                .withParam("name", status.getName())
                .withParam("target_url", status.getTargetUrl())
                .withParam("description", status.getDescription())
                .withParam("coverage", status.getCoverage());
        }

        Response response = post(Response.Status.OK, formData, "projects", getProjectIdOrPath(projectIdOrPath), "statuses", sha);
        return (response.readEntity(CommitStatus.class));
    }

    /**
     * Get the list of diffs of a commit in a project.
     *
     * GET /projects/:id/repository/commits/:sha/diff
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @return a List of Diff instances for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Diff> getDiff(Object projectIdOrPath, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha, "diff");
        return (response.readEntity(new GenericType<List<Diff>>() {}));
    }

    /**
     * Get the comments of a commit in a project.
     *
     * GET /projects/:id/repository/commits/:sha/comments
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @return a List of Comment instances for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Comment> getComments(Object projectIdOrPath, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha, "comments");
        return (response.readEntity(new GenericType<List<Comment>>() {}));
    }

    /**
     * Get a Pager of the comments of a commit in a project.
     *
     * GET /projects/:id/repository/commits/:sha/comments
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @param itemsPerPage the number of Comment instances that will be fetched per page
     * @return a List of Comment instances for the specified project ID/sha pair
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Comment> getComments(Object projectIdOrPath, String sha, int itemsPerPage) throws GitLabApiException {
        return new Pager<Comment>(this, Comment.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha, "comments");
    }

    /**
     * Add a comment to a commit.  In order to post a comment in a particular line of a particular file,
     * you must specify the full commit SHA, the path, the line and lineType should be NEW.
     *
     * POST /projects/:id/repository/commits/:sha/comments
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @param note the text of the comment, required
     * @param path the file path relative to the repository, optional
     * @param line the line number where the comment should be placed, optional
     * @param lineType the line type, optional
     * @return a Comment instance for the posted comment
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Comment addComment(Object projectIdOrPath, String sha, String note, String path, Integer line, LineType lineType) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("note", note, true)
                .withParam("path", path)
                .withParam("line", line)
                .withParam("line_type", lineType);
        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits", sha, "comments");
        return (response.readEntity(Comment.class));
    }

    /**
     * Add a comment to a commit.
     *
     * POST /projects/:id/repository/commits/:sha/comments
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha a commit hash or name of a branch or tag
     * @param note the text of the comment, required
     * @return a Comment instance for the posted comment
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Comment addComment(Object projectIdOrPath, String sha, String note) throws GitLabApiException {
        return (addComment(projectIdOrPath, sha, note, null, null, null));
    }

    /**
     * Create a commit with multiple files and actions.
     *
     * POST /projects/:id/repository/commits
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branch tame of the branch to commit into. To create a new branch, also provide startBranch
     * @param commitMessage the commit message
     * @param startBranch the name of the branch to start the new commit from
     * @param authorEmail the commit author's email address
     * @param authorName the commit author's name
     * @param actions the array of CommitAction to commit as a batch
     * @return the create Commit instance
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Commit createCommit(Object projectIdOrPath, String branch, String commitMessage, String startBranch,
            String authorEmail, String authorName, List<CommitAction> actions) throws GitLabApiException {

        CommitPayload payload = new CommitPayload();
        payload.setBranch(branch);
        payload.setCommitMessage(commitMessage);
        payload.setStartBranch(startBranch);
        payload.setAuthorEmail(authorEmail);
        payload.setAuthorName(authorName);
        payload.setActions(actions);

        Response response = post(Response.Status.CREATED, payload, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "commits");
        return (response.readEntity(Commit.class));
    }
}
