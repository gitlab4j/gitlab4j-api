package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Pipeline;

/**
 * This class provides an entry point to all the GitLab API pipeline calls.
 */
public class PipelineApi extends AbstractApi {

    public PipelineApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of pipelines in a project.
     *
     * GET /projects/:id/pipelines
     *
     * @param projectId the project ID to get the list of pipelines for
     * @return a list containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Pipeline> getPipelines(int projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "pipelines");
        return (response.readEntity(new GenericType<List<Pipeline>>() {
        }));
    }

    /**
     * Get a list of pipelines in a project.
     *
     * GET /projects/:id/pipelines
     *
     * @param projectId the project ID to get the list of pipelines for
     * @param scope the scope of pipelines, one of: running, pending, finished, branches, tags
     * @param status the status of pipelines, one of: running, pending, success, failed, canceled, skipped
     * @param ref the ref of pipelines
     * @param yamlErrors returns pipelines with invalid configurations
     * @param name the name of the user who triggered pipelines
     * @param username the username of the user who triggered pipelines
     * @param orderBy order pipelines by id, status, ref, or user_id (default: id)
     * @param sort sort pipelines in asc or desc order (default: desc)
     * @return a list containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Pipeline> getPipelines(int projectId, String scope, String status, String ref, boolean yamlErrors, 
            String name, String username, String orderBy, String sort) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope)
                .withParam("status", status)
                .withParam("ref", ref)
                .withParam("yaml_errors", yamlErrors)
                .withParam("name", name)
                .withParam("username", username)
                .withParam("order_by", orderBy)
                .withParam("sort", sort);

        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "pipelines");
        return (response.readEntity(new GenericType<List<Pipeline>>() {
        }));
    }

    /**
     * Get single pipelines in a project.
     *
     * GET /projects/:id/pipelines/:pipeline_id
     *
     * @param projectId the project ID to get the specified pipeline for
     * @param pipelineId the pipeline ID to get
     * @return a single pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline getPipeline(int projectId, int pipelineId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "pipelines", pipelineId);
        return (response.readEntity(Pipeline.class));
    }

    /**
     * Create a pipelines in a project.
     *
     * POST /projects/:id/pipelines
     *
     * @param projectId the project ID to create a pipeline in
     * @param ref reference to commit
     * @return a Pipeline instance with the newly created pipeline info
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline createPipeline(int projectId, String ref) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("ref", ref);

        Response response = post(Response.Status.OK, formData.asMap(), "projects", projectId, "pipelines");
        return (response.readEntity(Pipeline.class));
    }

    /**
     * Retry a job in specified pipelines in a project.
     *
     * POST /projects/:id/pipelines/:pipeline_id/retry
     *
     * @param projectId the project ID to retry a job for speficied pipeline
     * @param pipelineId the pipeline ID to retry a job from
     * @return pipeline instance which just retried
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline retryPipelineJob(int projectId, int pipelineId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Response.Status.OK, formData, "projects", projectId, "pipelines", pipelineId, "retry");
        return (response.readEntity(Pipeline.class));
    }

    /**
     * Cancel jobs of specified pipelines in a project.
     *
     * POST /projects/:id/pipelines/:pipeline_id/cancel
     *
     * @param projectId the project ID to cancel jobs for speficied pipeline
     * @param pipelineId the pipeline ID to cancel jobs
     * @return pipeline instance which just canceled
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline cancelPipelineJobs(int projectId, int pipelineId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Response.Status.OK, formData, "projects", projectId, "pipelines", pipelineId, "cancel");
        return (response.readEntity(Pipeline.class));
    }
}
