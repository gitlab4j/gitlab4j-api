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

    MergeRequestApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all merge requests for the specified project.
     *
     * GET /projects/:id/merge_requests
     *
     * @param projectId the project ID to get the merge requests for
     * @return all merge requests for the specified project
     * @throws GitLabApiException
     */
    public List<MergeRequest> getMergeRequests(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {
        }));
    }

    /**
     * Get information about a single merge request.
     *
     * GET /projects/:id/merge_request/:merge_request_id
     *
     * @param projectId
     * @param mergeRequestId
     * @return the specified MergeRequest instance
     * @throws GitLabApiException
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
     * @throws GitLabApiException
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
     * @param projectId
     * @param mergeRequestId
     * @param sourceBranch
     * @param targetBranch
     * @param title
     * @param description
     * @param assigneeId
     * @return the updated merge request
     * @throws GitLabApiException
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

    /**
     * Adds a comment to a merge request.
     *
     * POST /projects/:id/merge_request/:merge_request_id/comments
     *
     * @param projectId
     * @param mergeRequestId
     * @param comments
     * @return the added merge request comment
     * @throws GitLabApiException
     */
    /*
    public MergeRequestComment addMergeRequestComment(Integer projectId, Integer mergeRequestId, String comments) throws GitLabApiException {

        Form formData = new Form();
        formData.param("note", comments);
        Response response = post(Response.Status.OK, formData, "projects", projectId, "merge_request", mergeRequestId, "comments");
        return (response.readEntity(MergeRequestComment.class));
    }
    */
}
