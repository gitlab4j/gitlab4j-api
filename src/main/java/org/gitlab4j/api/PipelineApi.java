package org.gitlab4j.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.PipelineSchedule;
import org.gitlab4j.api.models.PipelineStatus;

/**
 * This class provides an entry point to all the GitLab API pipeline calls.
 */
public class PipelineApi extends AbstractApi implements Constants {

    public PipelineApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a list containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Pipeline> getPipelines(Object projectIdOrPath) throws GitLabApiException {
        return (getPipelines(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of pipelines in a project in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of Pipeline instances per page
     * @return a list containing the pipelines for the specified project ID in the specified page range
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Pipeline> getPipelines(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),
                "projects", getProjectIdOrPath(projectIdOrPath), "pipelines");
        return (response.readEntity(new GenericType<List<Pipeline>>() {}));
    }

    /**
     * Get a Pager of pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Pipeline instances that will be fetched per page
     * @return a Pager containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pager<Pipeline> getPipelines(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Pipeline>(this, Pipeline.class, itemsPerPage, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "pipelines"));
    }

    /**
     * Get a Stream of pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a Stream containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Stream<Pipeline> getPipelinesStream(Object projectIdOrPath) throws GitLabApiException {
        return (getPipelines(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param scope the scope of pipelines, one of: RUNNING, PENDING, FINISHED, BRANCHES, TAGS
     * @param status the status of pipelines, one of: RUNNING, PENDING, SUCCESS, FAILED, CANCELED, SKIPPED
     * @param ref the ref of pipelines
     * @param yamlErrors returns pipelines with invalid configurations
     * @param name the name of the user who triggered pipelines
     * @param username the username of the user who triggered pipelines
     * @param orderBy order pipelines by ID, STATUS, REF, USER_ID (default: ID)
     * @param sort sort pipelines in ASC or DESC order (default: DESC)
     * @return a list containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Pipeline> getPipelines(Object projectIdOrPath, PipelineScope scope, PipelineStatus status, String ref, boolean yamlErrors,
            String name, String username, PipelineOrderBy orderBy, SortOrder sort) throws GitLabApiException {

        return(getPipelines(projectIdOrPath, scope, status, ref, yamlErrors,
            name, username, orderBy, sort, getDefaultPerPage()).all());
    }

    /**
     * Get a list of pipelines in a project in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param scope the scope of pipelines, one of: RUNNING, PENDING, FINISHED, BRANCHES, TAGS
     * @param status the status of pipelines, one of: RUNNING, PENDING, SUCCESS, FAILED, CANCELED, SKIPPED
     * @param ref the ref of pipelines
     * @param yamlErrors returns pipelines with invalid configurations
     * @param name the name of the user who triggered pipelines
     * @param username the username of the user who triggered pipelines
     * @param orderBy order pipelines by ID, STATUS, REF, USER_ID (default: ID)
     * @param sort sort pipelines in ASC or DESC order (default: DESC)
     * @param page the page to get
     * @param perPage the number of Pipeline instances per page
     * @return a list containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Pipeline> getPipelines(Object projectIdOrPath, PipelineScope scope, PipelineStatus status, String ref, boolean yamlErrors,
            String name, String username, PipelineOrderBy orderBy, SortOrder sort, int page, int perPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope)
                .withParam("status", status)
                .withParam("ref", ref)
                .withParam("yaml_errors", yamlErrors)
                .withParam("name", name)
                .withParam("username", username)
                .withParam("order_by", orderBy)
                .withParam("sort", sort)
                .withParam("page", page)
                .withParam(PER_PAGE_PARAM, perPage);

        Response response = get(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "pipelines");
        return (response.readEntity(new GenericType<List<Pipeline>>() {}));
    }

    /**
     * Get a Stream of pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param scope the scope of pipelines, one of: RUNNING, PENDING, FINISHED, BRANCHES, TAGS
     * @param status the status of pipelines, one of: RUNNING, PENDING, SUCCESS, FAILED, CANCELED, SKIPPED
     * @param ref the ref of pipelines
     * @param yamlErrors returns pipelines with invalid configurations
     * @param name the name of the user who triggered pipelines
     * @param username the username of the user who triggered pipelines
     * @param orderBy order pipelines by ID, STATUS, REF, USER_ID (default: ID)
     * @param sort sort pipelines in ASC or DESC order (default: DESC)
     * @return a Stream containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Stream<Pipeline> getPipelinesStream(Object projectIdOrPath, PipelineScope scope, PipelineStatus status,
            String ref, boolean yamlErrors, String name, String username, PipelineOrderBy orderBy, SortOrder sort) throws GitLabApiException {

        return(getPipelines(projectIdOrPath, scope, status, ref, yamlErrors,
            name, username, orderBy, sort, getDefaultPerPage()).stream());
   }

    /**
     * Get a Pager of pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param scope the scope of pipelines, one of: RUNNING, PENDING, FINISHED, BRANCHES, TAGS
     * @param status the status of pipelines, one of: RUNNING, PENDING, SUCCESS, FAILED, CANCELED, SKIPPED
     * @param ref the ref of pipelines
     * @param yamlErrors returns pipelines with invalid configurations
     * @param name the name of the user who triggered pipelines
     * @param username the username of the user who triggered pipelines
     * @param orderBy order pipelines by ID, STATUS, REF, USER_ID (default: ID)
     * @param sort sort pipelines in ASC or DESC order (default: DESC)
     * @param itemsPerPage the number of Pipeline instances that will be fetched per page
     * @return a list containing the pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pager<Pipeline> getPipelines(Object projectIdOrPath, PipelineScope scope, PipelineStatus status, String ref, boolean yamlErrors,
            String name, String username, PipelineOrderBy orderBy, SortOrder sort, int itemsPerPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope)
                .withParam("status", status)
                .withParam("ref", ref)
                .withParam("yaml_errors", yamlErrors)
                .withParam("name", name)
                .withParam("username", username)
                .withParam("order_by", orderBy)
                .withParam("sort", sort);

        return (new Pager<Pipeline>(this, Pipeline.class, itemsPerPage, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "pipelines"));
    }

    /**
     * Get single pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/pipelines/:pipeline_id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param pipelineId the pipeline ID to get
     * @return a single pipelines for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline getPipeline(Object projectIdOrPath, int pipelineId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "pipelines", pipelineId);
        return (response.readEntity(Pipeline.class));
    }

    /**
     * Create a pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/pipeline</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref reference to commit
     * @return a Pipeline instance with the newly created pipeline info
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline createPipeline(Object projectIdOrPath, String ref) throws GitLabApiException {
        return (createPipeline(projectIdOrPath, ref, null));
    }

    /**
     * Create a pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/pipeline</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param ref reference to commit
     * @param variables a Map containing the variables available in the pipeline
     * @return a Pipeline instance with the newly created pipeline info
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline createPipeline(Object projectIdOrPath, String ref, Map<String, String> variables) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("ref", ref, true)
                .withParam("variables", variables, false);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "pipeline");
        return (response.readEntity(Pipeline.class));
    }

    /**
     * Delete a pipeline from a project.
     *
     * <pre><code>GitLab Endpoint: DELETE /projects/:id/pipelines/:pipeline_id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param pipelineId the pipeline ID to delete
     * @throws GitLabApiException if any exception occurs during execution
     */
    public void deletePipeline(Object projectIdOrPath, int pipelineId) throws GitLabApiException {
       delete(Response.Status.ACCEPTED, null, "projects", getProjectIdOrPath(projectIdOrPath), "pipelines", pipelineId);
    }

    /**
     * Retry a job in specified pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/pipelines/:pipeline_id/retry</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param pipelineId the pipeline ID to retry a job from
     * @return pipeline instance which just retried
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline retryPipelineJob(Object projectIdOrPath, int pipelineId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Response.Status.OK, formData, "projects", getProjectIdOrPath(projectIdOrPath), "pipelines", pipelineId, "retry");
        return (response.readEntity(Pipeline.class));
    }

    /**
     * Cancel jobs of specified pipelines in a project.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/pipelines/:pipeline_id/cancel</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param pipelineId the pipeline ID to cancel jobs
     * @return pipeline instance which just canceled
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pipeline cancelPipelineJobs(Object projectIdOrPath, int pipelineId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Response.Status.OK, formData, "projects", getProjectIdOrPath(projectIdOrPath), "pipelines", pipelineId, "cancel");
        return (response.readEntity(Pipeline.class));
    }

    /**
     * Get a list of the project pipeline_schedules for the specified project.
     *
     * <pre><code>GET /projects/:id/pipeline_schedules</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a list of pipeline schedules for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<PipelineSchedule> getPipelineSchedules(Object projectIdOrPath) throws GitLabApiException {
        return (getPipelineSchedules(projectIdOrPath, getDefaultPerPage()).all());
    }
    /**
     * Get list of project pipeline schedules in the specified page range.
     *
     * <pre><code>GET /projects/:id/pipeline_schedules</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param page the page to get
     * @param perPage the number of ProjectHook instances per page
     * @return a list of project pipeline_schedules for the specified project in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<PipelineSchedule> getPipelineSchedules(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", getProjectIdOrPath(projectIdOrPath), "pipeline_schedules");
        return (response.readEntity(new GenericType<List<PipelineSchedule>>() {}));
    }
    /**
     * Get Pager of project pipeline schedule.
     *
     * <pre><code>GET /projects/:id/pipeline_schedule</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of project pipeline_schedules for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<PipelineSchedule> getPipelineSchedules(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<PipelineSchedule>(this, PipelineSchedule.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "pipeline_schedules"));
    }

    /**
     * Get a Stream of the project pipeline schedule for the specified project.
     *
     * <pre><code>GET /projects/:id/pipeline_schedule</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @return a Stream of project pipeline schedules for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<PipelineSchedule> getPipelineSchedulesStream(Object projectIdOrPath) throws GitLabApiException {
        return (getPipelineSchedules(projectIdOrPath, getDefaultPerPage()).stream());
    }


    /**
     * Get a specific pipeline schedule for project.
     *
     * <pre><code>GET /projects/:id/pipeline_schedules/:pipeline_schedule_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pipelineScheduleId the ID of the hook to get
     * @return the project hook for the specified project ID/hook ID pair
     * @throws GitLabApiException if any exception occurs
     */
    public PipelineSchedule getPipelineSchedule(Object projectIdOrPath, Integer pipelineScheduleId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "pipeline_schedules", pipelineScheduleId);
        return (response.readEntity(PipelineSchedule.class));
    }

    /**
     * Get a specific pipeline schedule for project as an Optional instance.
     *
     * <pre><code>GET /projects/:id/pipeline_schedules/:pipeline_schedule_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pipelineScheduleId the ID of the hook to get
     * @return the project hook for the specified project ID/hook ID pair as an Optional instance
     */
    public Optional<PipelineSchedule> getOptionalPipelineSchedule (Object projectIdOrPath, Integer pipelineScheduleId) {
        try {
            return (Optional.ofNullable(getPipelineSchedule(projectIdOrPath, pipelineScheduleId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * create a pipeline schedule for a project.
     *
     * <pre><code>POST /projects/:id/pipeline_schedules</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pipelineSchedule a PipelineSchedule instance to create
     * @return the added PipelineSchedule instance
     * @throws GitLabApiException if any exception occurs
     */
    public PipelineSchedule createPipelineSchedule(Object projectIdOrPath, PipelineSchedule pipelineSchedule)
            throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("description", pipelineSchedule.getDescription(), true)
                .withParam("ref", pipelineSchedule.getRef(), true)
                .withParam("cron", pipelineSchedule.getCron(), true)
                .withParam("cron_timezone", pipelineSchedule.getCronTimezone(), false)
                .withParam("active", pipelineSchedule.getActive(), false);
        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "pipeline_schedules");
        return (response.readEntity(PipelineSchedule.class));
    }

    /**
     * Deletes a pipeline schedule from the project.
     *
     * <pre><code>DELETE /projects/:id/pipeline_schedules/:pipeline_schedule_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pipelineScheduleId the project schedule ID to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deletePipelineSchedule(Object projectIdOrPath, Integer pipelineScheduleId) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(GitLabApi.ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "pipeline_schedules", pipelineScheduleId);
    }

    /**
     * Modifies a pipeline schedule for project.
     *
     * <pre><code>PUT /projects/:id/pipeline_schedules/:pipeline_schedule_id</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pipelineSchedule the pipelineSchedule instance that contains the pipelineSchedule info to modify
     * @return the modified project schedule
     * @throws GitLabApiException if any exception occurs
     */
    public PipelineSchedule modifyPipelineSchedule(Object projectIdOrPath,PipelineSchedule pipelineSchedule) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("description", pipelineSchedule.getDescription(), false)
                .withParam("ref", pipelineSchedule.getRef(), false)
                .withParam("cron", pipelineSchedule.getCron(), false)
                .withParam("cron_timezone", pipelineSchedule.getCronTimezone(), false)
                .withParam("active", pipelineSchedule.getActive(), false);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "pipeline_schedules", pipelineSchedule.getId());
        return (response.readEntity(PipelineSchedule.class));
    }

    /**
     * Update the owner of the pipeline schedule of a project.
     *
     * <pre><code>POST /projects/:id/pipeline_schedules/:pipeline_schedule_id/take_ownership</code></pre>
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance, required
     * @param pipelineScheduleId the pipelineSchedule instance id that ownership has to be taken of
     * @return the modified project schedule
     * @throws GitLabApiException if any exception occurs
     */
    public PipelineSchedule takeOwnershipPipelineSchedule(Object projectIdOrPath, Integer pipelineScheduleId) throws GitLabApiException {

        Response response = post(Response.Status.OK, "", "projects", getProjectIdOrPath(projectIdOrPath),  "pipeline_schedules", pipelineScheduleId, "take_ownership");
        return (response.readEntity(PipelineSchedule.class));
    }
}
