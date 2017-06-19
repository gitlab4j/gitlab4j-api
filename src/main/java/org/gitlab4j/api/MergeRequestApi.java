package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

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
     * Get information about a single merge request.
     *
     * GET /projects/:id/merge_request/:merge_request_id
     *
     * @param projectId the project ID of the merge request
     * @param mergeRequestId the ID of the merge request
     * @return the specified MergeRequest instance
     * @throws GitLabApiException if any exception occurs
     */
    public MergeRequest getMergeRequest(Integer projectId, Integer mergeRequestId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "merge_request", mergeRequestId);
        return (response.readEntity(MergeRequest.class));
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
     * PUT /projects/:id/merge_request/:merge_request_id
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
     */
    public MergeRequest updateMergeRequest(Integer projectId, Integer mergeRequestId, String sourceBranch, String targetBranch, String title, String description,
            Integer assigneeId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("mergeRequestId cannot be null");
        }

        if (mergeRequestId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Form formData = new Form();
        addFormParam(formData, "source_branch", sourceBranch, false);
        addFormParam(formData, "target_branch", targetBranch, false);
        addFormParam(formData, "title", title, false);
        addFormParam(formData, "description", description, false);
        addFormParam(formData, "assignee_id", assigneeId, false);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "merge_request", mergeRequestId);
        return (response.readEntity(MergeRequest.class));
    }
}
