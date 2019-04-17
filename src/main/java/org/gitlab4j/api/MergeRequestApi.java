package org.gitlab4j.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.MergeRequestFilter;
import org.gitlab4j.api.models.Participant;

/**
 * This class implements the client side API for the GitLab merge request calls.
 */
public class MergeRequestApi extends AbstractApi {

    public MergeRequestApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all merge requests matching the filter.
     *
     * <pre><code>GitLab Endpoint: GET /merge_requests</code></pre>
     *
     * @param filter a MergeRequestFilter instance with the filter settings
     * @return all merge requests for the specified project matching the filter
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(MergeRequestFilter filter) throws GitLabApiException {
        return (getMergeRequests(filter, getDefaultPerPage()).all());
    }

    /**
     * Get all merge requests matching the filter.
     *
     * <pre><code>GitLab Endpoint: GET /merge_requests</code></pre>
     *
     * @param filter a MergeRequestFilter instance with the filter settings
     * @param page the page to get
     * @param perPage the number of MergeRequest instances per page
     * @return all merge requests for the specified project matching the filter
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(MergeRequestFilter filter, int page, int perPage) throws GitLabApiException {

        MultivaluedMap<String, String> queryParams = (filter != null ?
            filter.getQueryParams(page, perPage).asMap() : getPageQueryParams(page, perPage));
        Response response;
        if (filter != null && (filter.getProjectId() != null && filter.getProjectId().intValue() > 0) ||
                (filter.getIids() != null && filter.getIids().size() > 0)) {

            if (filter.getProjectId() == null || filter.getProjectId().intValue() == 0) {
                throw new RuntimeException("project ID cannot be null or 0");
            }

            response = get(Response.Status.OK, queryParams, "projects", filter.getProjectId(), "merge_requests");
        } else {
            response = get(Response.Status.OK, queryParams, "merge_requests");
        }

        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Get all merge requests matching the filter.
     *
     * <pre><code>GitLab Endpoint: GET /merge_requests</code></pre>
     *
     * @param filter a MergeRequestFilter instance with the filter settings
     * @param itemsPerPage the number of MergeRequest instances that will be fetched per page
     * @return all merge requests for the specified project matching the filter
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<MergeRequest> getMergeRequests(MergeRequestFilter filter, int itemsPerPage) throws GitLabApiException {

        MultivaluedMap<String, String> queryParams = (filter != null ? filter.getQueryParams().asMap() : null);
        if (filter != null && (filter.getProjectId() != null && filter.getProjectId().intValue() > 0) ||
                (filter.getIids() != null && filter.getIids().size() > 0)) {

            if (filter.getProjectId() == null || filter.getProjectId().intValue() == 0) {
                throw new RuntimeException("project ID cannot be null or 0");
            }

            return (new Pager<MergeRequest>(this, MergeRequest.class, itemsPerPage, queryParams, "projects", filter.getProjectId(), "merge_requests"));
        } else {
            return (new Pager<MergeRequest>(this, MergeRequest.class, itemsPerPage, queryParams, "merge_requests"));
        }
    }

    /**
     * Get all merge requests matching the filter as a Stream.
     *
     * <pre><code>GitLab Endpoint: GET /merge_requests</code></pre>
     *
     * @param filter a MergeRequestFilter instance with the filter settings
     * @return a Stream containing all the merge requests for the specified project matching the filter
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<MergeRequest> getMergeRequestsStream(MergeRequestFilter filter) throws GitLabApiException {
        return (getMergeRequests(filter, getDefaultPerPage()).stream());
    }

    /**
     * Get all merge requests for the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Object projectIdOrPath) throws GitLabApiException {
        return (getMergeRequests(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get all merge requests for the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of MergeRequest instances per page
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Get all merge requests for the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of MergeRequest instances that will be fetched per page
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<MergeRequest> getMergeRequests(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<MergeRequest>(this, MergeRequest.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests"));
    }

    /**
     * Get all merge requests for the specified project as a Stream
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a Stream with all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<MergeRequest> getMergeRequestsStream(Object projectIdOrPath) throws GitLabApiException {
        return (getMergeRequests(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get all merge requests with a specific state for the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests?state=:state</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param state the state parameter can be used to get only merge requests with a given state (opened, closed, or merged) or all of them (all).
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Object projectIdOrPath, MergeRequestState state) throws GitLabApiException {
        return (getMergeRequests(projectIdOrPath, state, getDefaultPerPage()).all());
    }

    /**
     * Get all merge requests for the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param state the state parameter can be used to get only merge requests with a given state (opened, closed, or merged) or all of them (all).
     * @param page the page to get
     * @param perPage the number of MergeRequest instances per page
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Object projectIdOrPath, MergeRequestState state, int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("state", state)
                .withParam(PAGE_PARAM, page)
                .withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Get all merge requests for the specified project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param state the state parameter can be used to get only merge requests with a given state (opened, closed, or merged) or all of them (all).
     * @param itemsPerPage the number of MergeRequest instances that will be fetched per page
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<MergeRequest> getMergeRequests(Object projectIdOrPath, MergeRequestState state, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("state", state);
        return (new Pager<MergeRequest>(this, MergeRequest.class, itemsPerPage, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests"));
    }

    /**
     * Get all merge requests with a specific state for the specified project as a Stream.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests?state=:state</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param state the state parameter can be used to get only merge requests with a given state (opened, closed, or merged) or all of them (all).
     * @return a Stream with all the merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<MergeRequest> getMergeRequestsStream(Object projectIdOrPath, MergeRequestState state) throws GitLabApiException {
        return (getMergeRequests(projectIdOrPath, state, getDefaultPerPage()).stream());
    }

    /**
     * Get information about a single merge request.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return the specified MergeRequest instance
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest getMergeRequest(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid);
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Get information about a single merge request as an Optional instance.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return the specified MergeRequest as an Optional instance instance
     */
    public Optional<MergeRequest> getOptionalMergeRequest(Object projectIdOrPath, Integer mergeRequestIid) {
        try {
            return (Optional.ofNullable(getMergeRequest(projectIdOrPath, mergeRequestIid)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Get a list of merge request commits.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/commits</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return a list containing the commits for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, int mergeRequestIid) throws GitLabApiException {
        return (getCommits(projectIdOrPath, mergeRequestIid, getDefaultPerPage()).all());
    }

    /**
     * Get a list of merge request commits.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/commits</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @param page the page to get
     * @param perPage the number of commits per page
     * @return a list containing the commits for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(Object projectIdOrPath, int mergeRequestIid, int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("owned", true).withParam(PAGE_PARAM,  page).withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a Pager of merge request commits.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/commits</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @param itemsPerPage the number of Commit instances that will be fetched per page
     * @return a Pager containing the commits for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Commit> getCommits(Object projectIdOrPath, int mergeRequestIid, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Commit>(this, Commit.class, itemsPerPage, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "commits"));
    }

    /**
     * Get a Stream of merge request commits.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/commits</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return a Stream containing the commits for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Stream<Commit> getCommitsStream(Object projectIdOrPath, int mergeRequestIid) throws GitLabApiException {
        return (getCommits(projectIdOrPath, mergeRequestIid, getDefaultPerPage()).stream());
    }

    /**
     * Creates a merge request and optionally assigns a reviewer to it.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sourceBranch the source branch, required
     * @param targetBranch the target branch, required
     * @param title the title for the merge request, required
     * @param description the description of the merge request
     * @param assigneeId the Assignee user ID, optional
     * @param targetProjectId the ID of a target project, optional
     * @param labels labels for MR, optional
     * @param milestoneId the ID of a milestone, optional
     * @param removeSourceBranch Flag indicating if a merge request should remove the source branch when merging, optional
     * @param squash Squash commits into a single commit when merging, optional
     * @return the created MergeRequest instance
     * @throws GitLabApiException if any exception occurs
     * @since GitLab Starter 8.17, GitLab CE 11.0.
     */
    public MergeRequest createMergeRequest(Object projectIdOrPath, String sourceBranch, String targetBranch, String title, String description, Integer assigneeId,
            Integer targetProjectId, String[] labels, Integer milestoneId, Boolean removeSourceBranch, Boolean squash) throws GitLabApiException {

        Form formData = new Form();
        addFormParam(formData, "source_branch", sourceBranch, true);
        addFormParam(formData, "target_branch", targetBranch, true);
        addFormParam(formData, "title", title, true);
        addFormParam(formData, "description", description, false);
        addFormParam(formData, "assignee_id", assigneeId, false);
        addFormParam(formData, "target_project_id", targetProjectId, false);
        addFormParam(formData, "labels", labels == null ? null : String.join(",", labels), false);
        addFormParam(formData, "milestone_id", milestoneId, false);
        addFormParam(formData, "remove_source_branch", removeSourceBranch, false);
        addFormParam(formData, "squash", squash, false);

        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Creates a merge request and optionally assigns a reviewer to it.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sourceBranch the source branch, required
     * @param targetBranch the target branch, required
     * @param title the title for the merge request, required
     * @param description the description of the merge request
     * @param assigneeId the Assignee user ID, optional
     * @param targetProjectId the ID of a target project, optional
     * @param labels labels for MR, optional
     * @param milestoneId the ID of a milestone, optional
     * @param removeSourceBranch Flag indicating if a merge request should remove the source branch when merging, optional
     * @return the created MergeRequest instance
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest createMergeRequest(Object projectIdOrPath, String sourceBranch, String targetBranch, String title, String description, Integer assigneeId,
                Integer targetProjectId, String[] labels, Integer milestoneId, Boolean removeSourceBranch) throws GitLabApiException {
       return createMergeRequest(projectIdOrPath, sourceBranch, targetBranch, title, description, assigneeId, targetProjectId, labels, milestoneId, removeSourceBranch, null);
    }

    /**
     * Creates a merge request and optionally assigns a reviewer to it.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/merge_requests</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sourceBranch the source branch, required
     * @param targetBranch the target branch, required
     * @param title the title for the merge request, required
     * @param description the description of the merge request
     * @param assigneeId the Assignee user ID, optional
     * @return the created MergeRequest instance
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest createMergeRequest(Object projectIdOrPath, String sourceBranch, String targetBranch, String title, String description, Integer assigneeId)
            throws GitLabApiException {
        return createMergeRequest(projectIdOrPath, sourceBranch, targetBranch, title, description, assigneeId, null, null, null, null);
    }

    /**
     * Updates an existing merge request. You can change branches, title, or even close the MR.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/merge_requests/:merge_request_iid</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request to update
     * @param targetBranch the target branch, optional
     * @param title the title for the merge request
     * @param assigneeId the Assignee user ID, optional
     * @param description the description of the merge request, optional
     * @param stateEvent new state for the merge request, optional
     * @param labels comma separated list of labels, optional
     * @param milestoneId the ID of a milestone, optional
     * @param removeSourceBranch Flag indicating if a merge request should remove the source
     *                           branch when merging, optional
     * @param squash Squash commits into a single commit when merging, optional
     * @param discussionLocked Flag indicating if the merge request's discussion is locked, optional
     * @param allowCollaboration Allow commits from members who can merge to the target branch,
     *                           optional
     * @return the updated merge request
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest updateMergeRequest(Object projectIdOrPath, Integer mergeRequestIid,
            String targetBranch, String title, Integer assigneeId, String description,
            StateEvent stateEvent, String labels, Integer milestoneId, Boolean removeSourceBranch,
            Boolean squash, Boolean discussionLocked, Boolean allowCollaboration)
            throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("target_branch", targetBranch)
                .withParam("title", title)
                .withParam("assignee_id", assigneeId)
                .withParam("description", description)
                .withParam("state_event", stateEvent)
                .withParam("labels", labels)
                .withParam("milestone_id", milestoneId)
                .withParam("remove_source_branch", removeSourceBranch)
                .withParam("squash", squash)
                .withParam("discussion_locked", discussionLocked)
                .withParam("allow_collaboration", allowCollaboration);

        return updateMergeRequest(projectIdOrPath, mergeRequestIid, formData);
    }

    /**
     * Updates an existing merge request. You can change branches, title, or even close the MR.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/merge_requests/:merge_request_iid</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request to update
     * @param targetBranch the target branch, optional
     * @param title the title for the merge request
     * @param assigneeId the Assignee user ID, optional
     * @param description the description of the merge request, optional
     * @param stateEvent new state for the merge request, optional
     * @param labels comma separated list of labels, optional
     * @param milestoneId the ID of a milestone, optional
     * @return the updated merge request
     * @throws GitLabApiException if any exception occurs
     */
    @Deprecated
    public MergeRequest updateMergeRequest(Object projectIdOrPath, Integer mergeRequestIid, String targetBranch,
            String title, Integer assigneeId, String description, StateEvent stateEvent, String labels,
            Integer milestoneId) throws GitLabApiException {

        Form formData = new GitLabApiForm()
        .withParam("target_branch", targetBranch)
        .withParam("title", title)
        .withParam("assignee_id", assigneeId)
        .withParam("description", description)
        .withParam("state_event", stateEvent)
        .withParam("labels", labels)
        .withParam("milestone_id", milestoneId);

        return updateMergeRequest(projectIdOrPath, mergeRequestIid, formData);
    }

    /**
     * Updates an existing merge request. You can change branches, title, or even close the MR.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/merge_requests/:merge_request_iid</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request to update
     * @param sourceBranch the source branch
     * @param targetBranch the target branch
     * @param title the title for the merge request
     * @param description the description of the merge request
     * @param assigneeId the Assignee user ID, optional
     * @return the updated merge request
     * @throws GitLabApiException if any exception occurs
     * @deprecated as of release 4.4.3
     */
    @Deprecated
    public MergeRequest updateMergeRequest(Object projectIdOrPath, Integer mergeRequestIid, String sourceBranch, String targetBranch, String title, String description,
            Integer assigneeId) throws GitLabApiException {

        Form formData = new Form();
        addFormParam(formData, "source_branch", sourceBranch, false);
        addFormParam(formData, "target_branch", targetBranch, false);
        addFormParam(formData, "title", title, false);
        addFormParam(formData, "description", description, false);
        addFormParam(formData, "assignee_id", assigneeId, false);

        return updateMergeRequest(projectIdOrPath, mergeRequestIid, formData);
    }

    protected MergeRequest updateMergeRequest(Object projectIdOrPath, Integer mergeRequestIid,
            Form formData) throws GitLabApiException {

        if (mergeRequestIid == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath),
                "merge_requests", mergeRequestIid);
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Only for admins and project owners. Soft deletes the specified merge.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: DELETE /projects/:id/merge_requests/:merge_request_iid</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteMergeRequest(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {

        if (mergeRequestIid == null) {
            throw new RuntimeException("mergeRequestIid cannot be null");
        }

        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid);
    }

    /**
     * Merge changes to the merge request. If the MR has any conflicts and can not be merged,
     * you'll get a 405 and the error message 'Branch cannot be merged'. If merge request is
     * already merged or closed, you'll get a 406 and the error message 'Method Not Allowed'.
     * If the sha parameter is passed and does not match the HEAD of the source, you'll get
     * a 409 and the error message 'SHA does not match HEAD of source branch'.  If you don't
     * have permissions to accept this merge request, you'll get a 401.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/merge_requests/:merge_request_iid/merge</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return the merged merge request
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest acceptMergeRequest(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        return (acceptMergeRequest(projectIdOrPath, mergeRequestIid, null, null, null, null));
    }

    /**
     * Merge changes to the merge request. If the MR has any conflicts and can not be merged,
     * you'll get a 405 and the error message 'Branch cannot be merged'. If merge request is
     * already merged or closed, you'll get a 406 and the error message 'Method Not Allowed'.
     * If the sha parameter is passed and does not match the HEAD of the source, you'll get
     * a 409 and the error message 'SHA does not match HEAD of source branch'.  If you don't
     * have permissions to accept this merge request, you'll get a 401.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.  Additionally,
     * mergeWhenPipelineSucceeds sets the merge_when_build_succeeds flag for GitLab API V3.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/merge_requests/:merge_request_iid/merge</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @param mergeCommitMessage, custom merge commit message, optional
     * @param shouldRemoveSourceBranch, if true removes the source branch, optional
     * @param mergeWhenPipelineSucceeds, if true the MR is merged when the pipeline, optional
     * @return the merged merge request
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest acceptMergeRequest(Object projectIdOrPath, Integer mergeRequestIid,
            String mergeCommitMessage, Boolean shouldRemoveSourceBranch, Boolean mergeWhenPipelineSucceeds)
            throws GitLabApiException {
        return (acceptMergeRequest(projectIdOrPath, mergeRequestIid, mergeCommitMessage,
                shouldRemoveSourceBranch, mergeWhenPipelineSucceeds, null));
    }

    /**
     * Merge changes to the merge request. If the MR has any conflicts and can not be merged,
     * you'll get a 405 and the error message 'Branch cannot be merged'. If merge request is
     * already merged or closed, you'll get a 406 and the error message 'Method Not Allowed'.
     * If the sha parameter is passed and does not match the HEAD of the source, you'll get
     * a 409 and the error message 'SHA does not match HEAD of source branch'.  If you don't
     * have permissions to accept this merge request, you'll get a 401.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.  Additionally,
     * mergeWhenPipelineSucceeds sets the merge_when_build_succeeds flag for GitLab API V3.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/merge_requests/:merge_request_iid/merge</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @param mergeCommitMessage, custom merge commit message, optional
     * @param shouldRemoveSourceBranch, if true removes the source branch, optional
     * @param mergeWhenPipelineSucceeds, if true the MR is merged when the pipeline, optional
     * @param sha if present, then this SHA must match the HEAD of the source branch, otherwise the merge will fail, optional
     * @return the merged merge request
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest acceptMergeRequest(Object projectIdOrPath, Integer mergeRequestIid,
            String mergeCommitMessage, Boolean shouldRemoveSourceBranch, Boolean mergeWhenPipelineSucceeds, String sha)
            throws GitLabApiException {

        if (mergeRequestIid == null) {
            throw new RuntimeException("mergeRequestIid cannot be null");
        }

        Form formData = new GitLabApiForm()
                .withParam("merge_commit_message", mergeCommitMessage)
                .withParam("should_remove_source_branch", shouldRemoveSourceBranch)
                .withParam((isApiVersion(ApiVersion.V3) ?
                        "merge_when_build_succeeds" : "merge_when_pipeline_succeeds"),
                        mergeWhenPipelineSucceeds)
                .withParam("sha", sha);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "merge");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Cancel merge when pipeline succeeds. If you don't have permissions to accept this merge request,
     * you'll get a 401. If the merge request is already merged or closed, you get 405 and
     * error message 'Method Not Allowed'. In case the merge request is not set to be merged when the
     * pipeline succeeds, you'll also get a 406 error.
     *
     * <p>NOTE: GitLab API V4 uses IID (internal ID), V3 uses ID to identify the merge request.</p>
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/merge_requests/:merge_request_iid/cancel_merge_when_pipeline_succeeds</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return the updated merge request
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest cancelMergeRequest(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {

        if (mergeRequestIid == null) {
            throw new RuntimeException("mergeRequestIid cannot be null");
        }

        Response response = put(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "cancel_merge_when_pipeline_succeeds");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Get the merge request with approval information.
     *
     * Note: This API endpoint is only available on 8.9 EE and above.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/approvals</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return a MergeRequest instance with approval information included
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest getMergeRequestApprovals(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {

        if (mergeRequestIid == null) {
            throw new RuntimeException("mergeRequestIid cannot be null");
        }

        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "approvals");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Approve a merge request.
     *
     * Note: This API endpoint is only available on 8.9 EE and above.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/merge_requests/:merge_request_iid/approve</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @param sha the HEAD of the merge request, optional
     * @return a MergeRequest instance with approval information included
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest approveMergeRequest(Object projectIdOrPath, Integer mergeRequestIid, String sha) throws GitLabApiException {

        if (mergeRequestIid == null) {
            throw new RuntimeException("mergeRequestIid cannot be null");
        }

        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = post(Response.Status.OK, formData, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "approve");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Unapprove a merge request.
     *
     * Note: This API endpoint is only available on 8.9 EE and above.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/merge_requests/:merge_request_iid/unapprove</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return a MergeRequest instance with approval information included
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest unapproveMergeRequest(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {

        if (mergeRequestIid == null) {
            throw new RuntimeException("mergeRequestIid cannot be null");
        }

        Response response = post(Response.Status.OK, (Form)null, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "unapprove");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Get merge request with changes information.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/changes</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the IID of the merge request to get
     * @return a merge request including its changes
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest getMergeRequestChanges(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "changes");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Get list of participants of merge request.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/participants</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the IID of the merge request to get
     * @return a List containing all participants for the specified merge request
     * @throws GitLabApiException if any exception occurs
     */
    public List<Participant> getParticipants(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        return (getParticipants(projectIdOrPath, mergeRequestIid, getDefaultPerPage()).all());
    }

    /**
     * Get list of participants of merge request and in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/participants</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the IID of the merge request to get
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a List containing all participants for the specified merge request
     * @throws GitLabApiException if any exception occurs
     */
    public List<Participant> getParticipants(Object projectIdOrPath, Integer mergeRequestIid, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),
                    "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "participants");
        return (response.readEntity(new GenericType<List<Participant>>() { }));
    }

    /**
     * Get a Pager of the participants of merge request.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/participants</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the IID of the merge request to get
     * @param itemsPerPage the number of Participant instances that will be fetched per page
     * @return a Pager containing all participants for the specified merge request
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Participant> getParticipants(Object projectIdOrPath, Integer mergeRequestIid, int itemsPerPage) throws GitLabApiException {
        return new Pager<Participant>(this, Participant.class, itemsPerPage, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "participants");
    }

    /**
     * Get Stream of participants of merge request.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/participants</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the IID of the merge request to get
     * @return a Stream containing all participants for the specified merge request
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Participant> getParticipantsStream(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        return (getParticipants(projectIdOrPath, mergeRequestIid, getDefaultPerPage()).stream());
    }

    /**
     * Get list containing all the issues that would be closed by merging the provided merge request.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/closes_issues</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param mergeRequestIid the IID of the merge request to get the closes issues for
     * @return a List containing all the issues that would be closed by merging the provided merge request
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getClosesIssues(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        return (getClosesIssues(projectIdOrPath, mergeRequestIid, getDefaultPerPage()).all());
    }

    /**
     * Get list containing all the issues that would be closed by merging the provided merge request.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/closes_issues</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param mergeRequestIid the IID of the merge request to get the closes issues for
     * @param page the page to get
     * @param perPage the number of issues per page
     * @return a List containing all the issues that would be closed by merging the provided merge request
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getClosesIssues(Object projectIdOrPath, Integer mergeRequestIid, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),
                    "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "closes_issues");
        return (response.readEntity(new GenericType<List<Issue>>() { }));
    }

    /**
     * Get a Pager containing all the issues that would be closed by merging the provided merge request.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/closes_issues</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param mergeRequestIid the IID of the merge request to get the closes issues for
     * @param itemsPerPage the number of Issue instances that will be fetched per page
     * @return a Pager containing all the issues that would be closed by merging the provided merge request
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Issue> getClosesIssues(Object projectIdOrPath, Integer mergeRequestIid, int itemsPerPage) throws GitLabApiException {
        return new Pager<Issue>(this, Issue.class, itemsPerPage, null, 
                "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "closes_issues");
    }

    /**
     * Get Stream containing all the issues that would be closed by merging the provided merge request.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/closes_issues</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param mergeRequestIid the IID of the merge request to get the closes issues for
     * @return a Stream containing all the issues that would be closed by merging the provided merge request
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Issue> getClosesIssuesStream(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        return (getClosesIssues(projectIdOrPath, mergeRequestIid, getDefaultPerPage()).stream());
    }
}
