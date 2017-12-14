package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.MergeRequest;

/**
 * This class implements the client side API for the GitLab merge request calls.
 */
public class MergeRequestApi extends AbstractApi {

    public MergeRequestApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all merge requests for the specified project.
     *
     * GET /projects/:id/merge_requests
     *
     * @param projectId the project ID to get the merge requests for
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Get all merge requests for the specified project.
     *
     * GET /projects/:id/merge_requests
     *
     * @param projectId the project ID to get the merge requests for
     * @param page the page to get
     * @param perPage the number of MergeRequest instances per page
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Get all merge requests for the specified project.
     *
     * GET /projects/:id/merge_requests
     *
     * @param projectId the project ID to get the merge requests for
     * @param itemsPerPage the number of MergeRequest instances that will be fetched per page
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<MergeRequest> getMergeRequests(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<MergeRequest>(this, MergeRequest.class, itemsPerPage, null, "projects", projectId, "merge_requests"));
    }

    /**
     * Get all merge requests for the specified project.
     *
     * GET /projects/:id/merge_requests
     *
     * @param projectId the project ID to get the merge requests for
     * @param page the page to get
     * @param perPage the number of MergeRequest instances per page
     * @param state the state parameter can be used to get only merge requests with a given state (opened, closed, or merged) or all of them (all).
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Integer projectId, int page, int perPage, MergeRequestState state) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("state", state)
                .withParam(PAGE_PARAM, page)
                .withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }


    /**
     * Get all merge requests for the specified project.
     *
     * GET /projects/:id/merge_requests
     *
     * @param projectId the project ID to get the merge requests for
     * @param itemsPerPage the number of MergeRequest instances that will be fetched per page
     * @param state the state parameter can be used to get only merge requests with a given state (opened, closed, or merged) or all of them (all).
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<MergeRequest> getMergeRequests(Integer projectId, int itemsPerPage, MergeRequestState state) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("state", state);
        return (new Pager<MergeRequest>(this, MergeRequest.class, itemsPerPage, formData.asMap(), "projects", projectId, "merge_requests"));
    }

    /**
     * Get all merge requests with a specific state for the specified project.
     *
     * GET /projects/:id/merge_requests?state=:state
     *
     * @param projectId the project ID to get the merge requests for
     * @param state the state parameter can be used to get only merge requests with a given state (opened, closed, or merged) or all of them (all).
     * @return all merge requests for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequests(Integer projectId, MergeRequestState state) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("state", state)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Get information about a single merge request.
     *
     * GET /projects/:id/merge_requests/:merge_request_id
     *
     * @param projectId the project ID of the merge request
     * @param mergeRequestId the ID of the merge request
     * @return the specified MergeRequest instance
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest getMergeRequest(Integer projectId, Integer mergeRequestId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "merge_requests", mergeRequestId);
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Get a list of merge request commits.
     *
     * GET /projects/:id/merge_requests/:merge_request_iid/commits
     *
     * @param projectId the project ID for the merge request
     * @param mergeRequestId the ID of the merge request
     * @return a list containing the commits for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId, int mergeRequestId) throws GitLabApiException {
        return (getCommits(projectId, mergeRequestId, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of merge request commits.
     *
     * GET /projects/:id/merge_requests/:merge_request_iid/commits
     *
     * @param projectId the project ID for the merge request
     * @param mergeRequestId the ID of the merge request
     * @param page the page to get
     * @param perPage the number of commits per page
     * @return a list containing the commits for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Commit> getCommits(int projectId, int mergeRequestId, int page, int perPage) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("owned", true).withParam(PAGE_PARAM,  page).withParam(PER_PAGE_PARAM, perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "merge_requests", mergeRequestId, "commits");
        return (response.readEntity(new GenericType<List<Commit>>() {}));
    }

    /**
     * Get a Pager of merge request commits.
     *
     * GET /projects/:id/merge_requests/:merge_request_iid/commits
     *
     * @param projectId the project ID for the merge request
     * @param mergeRequestId the ID of the merge request
     * @param itemsPerPage the number of Commit instances that will be fetched per page
     * @return a Pager containing the commits for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Commit> getCommits(int projectId, int mergeRequestId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Commit>(this, Commit.class, itemsPerPage, null,
                "projects", projectId, "merge_requests", mergeRequestId, "commits"));
    }

    /**
     * Creates a merge request and optionally assigns a reviewer to it.
     *
     * POST /projects/:id/merge_requests
     *
     * @param projectId the ID of a project, required
     * @param sourceBranch the source branch, required
     * @param targetBranch the target branch, required
     * @param title the title for the merge request, required
     * @param description the description of the merge request
     * @param assigneeId the Assignee user ID, optional
     * @return the created MergeRequest instance
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest createMergeRequest(Integer projectId, String sourceBranch, String targetBranch, String title, String description, Integer assigneeId)
            throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Form formData = new Form();
        addFormParam(formData, "source_branch", sourceBranch, true);
        addFormParam(formData, "target_branch", targetBranch, true);
        addFormParam(formData, "title", title, true);
        addFormParam(formData, "description", description, false);
        addFormParam(formData, "assignee_id", assigneeId, false);

        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "merge_requests");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Updates an existing merge request. You can change branches, title, or even close the MR.
     *
     * PUT /projects/:id/merge_requests/:merge_request_id
     *
     * @param projectId the ID of a project
     * @param mergeRequestId the internal ID of the merge request to update
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
    public MergeRequest updateMergeRequest(Integer projectId, Integer mergeRequestId, String targetBranch,
            String title, Integer assigneeId, String description, StateEvent stateEvent, String labels,
            Integer milestoneId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Form formData = new GitLabApiForm()
        .withParam("target_branch", targetBranch)
        .withParam("title", title)
        .withParam("assignee_id", assigneeId)
        .withParam("description", description)
        .withParam("state_event", stateEvent)
        .withParam("labels", labels)
        .withParam("milestone_id", milestoneId);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "merge_requests", mergeRequestId);
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Updates an existing merge request. You can change branches, title, or even close the MR.
     *
     * PUT /projects/:id/merge_requests/:merge_request_id
     *
     * @param projectId the ID of a project
     * @param mergeRequestId the ID of the merge request to update
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
    public MergeRequest updateMergeRequest(Integer projectId, Integer mergeRequestId, String sourceBranch, String targetBranch, String title, String description,
            Integer assigneeId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Form formData = new Form();
        addFormParam(formData, "source_branch", sourceBranch, false);
        addFormParam(formData, "target_branch", targetBranch, false);
        addFormParam(formData, "title", title, false);
        addFormParam(formData, "description", description, false);
        addFormParam(formData, "assignee_id", assigneeId, false);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "merge_requests", mergeRequestId);
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Only for admins and project owners. Soft deletes the specified merge.
     *
     * DELETE /projects/:id/merge_requests/:merge_request_iid
     *
     * @param projectId the ID of a project
     * @param mergeRequestId the internal ID of the merge request\
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteMergeRequest(Integer projectId, Integer mergeRequestId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", projectId, "merge_requests", mergeRequestId);
    }

    /**
     * Merge changes to the merge request. If the MR has any conflicts and can not be merged,
     * you'll get a 405 and the error message 'Branch cannot be merged'. If merge request is
     * already merged or closed, you'll get a 406 and the error message 'Method Not Allowed'.
     * If the sha parameter is passed and does not match the HEAD of the source, you'll get
     * a 409 and the error message 'SHA does not match HEAD of source branch'.  If you don't
     * have permissions to accept this merge request, you'll get a 401.
     *
     * PUT /projects/:id/merge_requests/:merge_request_iid/merge
     *
     * @param projectId the ID of a project
     * @param mergeRequestId the internal ID of the merge request
     * @param mergeCommitMessage, custom merge commit message, optional
     * @param shouldRemoveSourceBranch, if true removes the source branch, optional
     * @param mergeWhenPipelineSucceeds, if true the MR is merged when the pipeline, optional
     * @return the merged merge request
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest acceptMergeRequest(Integer projectId, Integer mergeRequestId, 
            String mergeCommitMessage, Boolean shouldRemoveSourceBranch, Boolean mergeWhenPipelineSucceeds)
            throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Form formData = new GitLabApiForm()
                .withParam("merge_commit_message", mergeCommitMessage)
                .withParam("should_remove_source_branch", shouldRemoveSourceBranch)
                .withParam("merge_when_pipeline_succeeds", mergeWhenPipelineSucceeds);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "merge_requests", mergeRequestId, "merge");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Cancel merge when pipeline succeeds. If you don't have permissions to accept this merge request,
     * you'll get a 401. If the merge request is already merged or closed, you get 405 and
     * error message 'Method Not Allowed'. In case the merge request is not set to be merged when the
     * pipeline succeeds, you'll also get a 406 error.
     *
     * PUT /projects/:id/merge_requests/:merge_request_iid/cancel_merge_when_pipeline_succeeds
     *
     * @param projectId the ID of a project
     * @param mergeRequestId the internal ID of the merge request
     * @return the updated merge request
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest cancelMergeRequest(Integer projectId, Integer mergeRequestId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Response response = put(Response.Status.OK, null, "projects", projectId, "merge_requests", mergeRequestId, "cancel_merge_when_pipeline_succeeds");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Get the merge request with approval information.
     *
     * Note: This API endpoint is only available on 8.9 EE and above.
     *
     * GET /projects/:id/merge_requests/:merge_request_iid/approvals
     *
     * @param projectId the project ID of the merge request
     * @param mergeRequestId the internal ID of the merge request
     * @return a MergeRequest instance with approval information included
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest getMergeRequestApprovals(Integer projectId, Integer mergeRequestId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Response response = get(Response.Status.OK, null, "projects", projectId, "merge_requests", mergeRequestId, "approvals");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Approve a merge request.
     *
     * Note: This API endpoint is only available on 8.9 EE and above.
     *
     * POST /projects/:id/merge_requests/:merge_request_iid/approve
     *
     * @param projectId the project ID of the merge request
     * @param mergeRequestId the internal ID of the merge request
     * @param sha the HEAD of the merge request, optional
     * @return a MergeRequest instance with approval information included
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest approveMergeRequest(Integer projectId, Integer mergeRequestId, String sha) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = post(Response.Status.OK, formData, "projects", projectId, "merge_requests", mergeRequestId, "approve");
        return (response.readEntity(MergeRequest.class));
    }

    /**
     * Unapprove a merge request.
     *
     * Note: This API endpoint is only available on 8.9 EE and above.
     *
     * POST /projects/:id/merge_requests/:merge_request_iid/unapprove
     *
     * @param projectId the project ID of the merge request
     * @param mergeRequestId the internal ID of the merge request
     * @return a MergeRequest instance with approval information included
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest unapproveMergeRequest(Integer projectId, Integer mergeRequestId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        Response response = post(Response.Status.OK, (Form)null, "projects", projectId, "merge_requests", mergeRequestId, "unapprove");
        return (response.readEntity(MergeRequest.class));
    }
}
