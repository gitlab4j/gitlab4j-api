package org.gitlab4j.api;

import java.util.List;
import java.util.stream.Stream;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.JobStatus;
import org.gitlab4j.api.models.Runner;
import org.gitlab4j.api.models.Runner.RunnerStatus;
import org.gitlab4j.api.models.Runner.RunnerType;
import org.gitlab4j.api.models.RunnerDetail;

/**
 * This class provides an entry point to all the GitLab API repository files calls.
 */
public class RunnersApi extends AbstractApi {

    public RunnersApi(final GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of all available runners available to the user.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getRunners() throws GitLabApiException {
        return (getRunners(null, null, getDefaultPerPage()).all());
    }

    /**
     * Get a list of all available runners available to the user with pagination support.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @param page    The page offset of runners
     * @param perPage The number of runners to get after the page offset
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getRunners(final int page, final int perPage) throws GitLabApiException {
        return getRunners(null, null, page, perPage);
    }

    /**
     * Get a list of all available runners available to the user.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @param itemsPerPage the number of Runner instances that will be fetched per page
     * @return a Pager containing the Runners for the user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getRunners(final int itemsPerPage) throws GitLabApiException {
        return (getRunners(null, null, itemsPerPage));
    }

    /**
     * Get a Stream of all available runners available to the user.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @return Stream of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Runner> getRunnersStream() throws GitLabApiException {
        return (getRunners(null, null, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of all available runners available to the user with pagination support.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getRunners(final RunnerType type, final RunnerStatus status) throws GitLabApiException {
        return (getRunners(type, status, getDefaultPerPage()).all());
    }

    /**
     * Get a list of specific runners available to the user.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @param page the page offset of runners
     * @param perPage the number of runners to get after the page offset
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getRunners(final RunnerType type, final RunnerStatus status, final int page, final int perPage) throws GitLabApiException {
        final GitLabApiForm formData = new GitLabApiForm(page, perPage)
                .withParam("type", type, false)
                .withParam("status", status, false);
        final Response response = get(Response.Status.OK, formData.asMap(), "runners");
        return (response.readEntity(new GenericType<List<Runner>>() {}));
    }

    /**
     * Get a list of specific runners available to the user.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Runners for the user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getRunners(final RunnerType type, final RunnerStatus status, final int itemsPerPage) throws GitLabApiException {
        final GitLabApiForm formData = new GitLabApiForm()
                .withParam("type", type, false)
                .withParam("status", status, false);
        return (new Pager<>(this, Runner.class, itemsPerPage, formData.asMap(), "runners"));
    }

    /**
     * Get a Stream of all available runners available to the user with pagination support.
     *
     * <pre><code>GitLab Endpoint: GET /runners</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @return Stream of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Runner> getRunnersStream(final RunnerType type, final RunnerStatus status) throws GitLabApiException {
        return (getRunners(type, status, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @return a List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getAllRunners() throws GitLabApiException {
        return (getAllRunners(null, null, getDefaultPerPage()).all());
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @param page    The page offset of runners
     * @param perPage The number of runners to get after the page offset
     * @return a list of all runners in the GitLab instance
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getAllRunners(final int page, final int perPage) throws GitLabApiException {
        return (getAllRunners(null, null, page, perPage));
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getAllRunners(final int itemsPerPage) throws GitLabApiException {
        return getAllRunners(null, null, itemsPerPage);
    }

    /**
     * Get a Stream of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @return a Stream of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Runner> getAllRunnersStream() throws GitLabApiException {
        return (getAllRunners(null, null, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @return a List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getAllRunners(final RunnerType type, final RunnerStatus status) throws GitLabApiException {
        return (getAllRunners(type, status, getDefaultPerPage()).all());
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @param page    The page offset of runners
     * @param perPage The number of runners to get after the page offset
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getAllRunners(final RunnerType type, final RunnerStatus status, final int page, final int perPage) throws GitLabApiException {
        final GitLabApiForm formData = new GitLabApiForm(page, perPage)
                .withParam("type", type, false)
                .withParam("status", status, false);
        final Response response = get(Response.Status.OK, formData.asMap(), "runners", "all");
        return (response.readEntity(new GenericType<List<Runner>>() {}));
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getAllRunners(final RunnerType type, final RunnerStatus status, final int itemsPerPage) throws GitLabApiException {
        final GitLabApiForm formData = new GitLabApiForm()
                .withParam("type", type, false)
                .withParam("status", status, false);
        return (new Pager<>(this, Runner.class, itemsPerPage, formData.asMap(), "runners", "all"));
    }

    /**
     * Get a Stream of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * <pre><code>GitLab Endpoint: GET /runners/all</code></pre>
     *
     * @param type the type of runners to show, one of: instance_type, group_type, project_type, or null
     * @param status the status of runners to show, one of: active, paused, online, offline, or null
     * @return a Stream of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Runner> getAllRunnersStream(final RunnerType type, final RunnerStatus status) throws GitLabApiException {
        return (getAllRunners(type, status, getDefaultPerPage()).stream());
    }

    /**
     * Get details of a runner.
     *
     * <pre><code>GitLab Endpoint: GET /runners/:id</code></pre>
     *
     * @param runnerId Runner id to get details for
     * @return RunnerDetail instance.
     * @throws GitLabApiException if any exception occurs
     */
    public RunnerDetail getRunnerDetail(final Long runnerId) throws GitLabApiException {

        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }

        final Response response = get(Response.Status.OK, null, "runners", runnerId);
        return (response.readEntity(RunnerDetail.class));
    }

    /**
     * Update details of a runner.
     *
     * <pre><code>GitLab Endpoint: PUT /runners/:id</code></pre>
     *
     * @param runnerId    The ID of a runner
     * @param description The description of a runner
     * @param active      The state of a runner; can be set to true or false
     * @param tagList     The list of tags for a runner; put array of tags, that should be finally assigned to a runner
     * @param runUntagged Flag indicating the runner can execute untagged jobs
     * @param locked      Flag indicating the runner is locked
     * @param accessLevel The access_level of the runner; not_protected or ref_protected
     * @return RunnerDetail instance.
     * @throws GitLabApiException if any exception occurs
     */
    public RunnerDetail updateRunner(final Long runnerId, final String description, final Boolean active, final List<String> tagList,
                                     final Boolean runUntagged, final Boolean locked, final RunnerDetail.RunnerAccessLevel accessLevel) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }

        final GitLabApiForm formData = new GitLabApiForm()
                .withParam("description", description, false)
                .withParam("active", active, false)
                .withParam("tag_list", tagList, false)
                .withParam("run_untagged", runUntagged, false)
                .withParam("locked", locked, false)
                .withParam("access_level", accessLevel, false);
        final Response response = put(Response.Status.OK, formData.asMap(), "runners", runnerId);
        return (response.readEntity(RunnerDetail.class));
    }

    /**
     * Remove a runner.
     *
     * <pre><code>GitLab Endpoint: DELETE /runners/:id</code></pre>
     *
     * @param runnerId The ID of a runner
     * @throws GitLabApiException if any exception occurs
     */
    public void removeRunner(final Long runnerId) throws GitLabApiException {

        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }

        delete(Response.Status.NO_CONTENT, null, "runners", runnerId);
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * <pre><code>GitLab Endpoint: GET /runners/:id/jobs</code></pre>
     *
     * @param runnerId The ID of a runner
     * @return List jobs that are being processed or were processed by specified Runner
     * @throws GitLabApiException if any exception occurs
     */
    public List<Job> getJobs(final Long runnerId) throws GitLabApiException {
        return (getJobs(runnerId, null, getDefaultPerPage()).all());
    }

    /**
     * Get a Stream of jobs that are being processed or were processed by specified Runner.
     *
     * <pre><code>GitLab Endpoint: GET /runners/:id/jobs</code></pre>
     *
     * @param runnerId The ID of a runner
     * @return a Stream of jobs that are being processed or were processed by specified Runner
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Job> getJobsStream(final Long runnerId) throws GitLabApiException {
        return (getJobs(runnerId, null, getDefaultPerPage()).stream());
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * <pre><code>GitLab Endpoint: GET /runners/:id/jobs</code></pre>
     *
     * @param runnerId The ID of a runner
     * @param status   Status of the job; one of: running, success, failed, canceled
     * @return List jobs that are being processed or were processed by specified Runner
     * @throws GitLabApiException if any exception occurs
     */
    public List<Job> getJobs(final Long runnerId, final JobStatus status) throws GitLabApiException {
        return (getJobs(runnerId, status, getDefaultPerPage()).all());
    }

    /**
     * Get a Stream of jobs that are being processed or were processed by specified Runner.
     *
     * <pre><code>GitLab Endpoint: GET /runners/:id/jobs</code></pre>
     *
     * @param runnerId The ID of a runner
     * @param status   Status of the job; one of: running, success, failed, canceled
     * @return a Stream of jobs that are being processed or were processed by specified Runner
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Job> getJobsStream(final Long runnerId, final JobStatus status) throws GitLabApiException {
        return (getJobs(runnerId, status, getDefaultPerPage()).stream());
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * <pre><code>GitLab Endpoint: GET /runners/:id/jobs</code></pre>
     *
     * @param runnerId     The ID of a runner
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Jobs for the Runner
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Job> getJobs(final Long runnerId, final int itemsPerPage) throws GitLabApiException {
        return (getJobs(runnerId, null, itemsPerPage));
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * <pre><code>GitLab Endpoint: GET /runners/:id/jobs</code></pre>
     *
     * @param runnerId     The ID of a runner
     * @param status       Status of the job; one of: running, success, failed, canceled
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Jobs for the Runner
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Job> getJobs(final Long runnerId, final JobStatus status, final int itemsPerPage) throws GitLabApiException {

        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }

        final GitLabApiForm formData = new GitLabApiForm().withParam("status", status, false);
        return (new Pager<>(this, Job.class, itemsPerPage, formData.asMap(), "runners", runnerId, "jobs"));
    }

    /**
     * List all runners (specific and shared) available in the project. Shared runners are listed if at least one
     * shared runner is defined and shared runners usage is enabled in the project's settings.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/runners</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project instance
     * @return List of all Runner available in the project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getProjectRunners(final Object projectIdOrPath) throws GitLabApiException {
        return (getProjectRunners(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a Stream  all runners (specific and shared) available in the project. Shared runners are listed if at least one
     * shared runner is defined and shared runners usage is enabled in the project's settings.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/runners</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project instance
     * @return a Stream of all Runner available in the project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Runner> getProjectRunnersStream(final Object projectIdOrPath) throws GitLabApiException {
        return (getProjectRunners(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * List all runners (specific and shared) available in the project. Shared runners are listed if at least one
     * shared runner is defined and shared runners usage is enabled in the project's settings.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/runners</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project instance
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return Pager of all Runner available in the project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getProjectRunners(final Object projectIdOrPath, final int itemsPerPage) throws GitLabApiException {
        return (new Pager<>(this, Runner.class, itemsPerPage, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "runners"));
    }

    /**
     * Enable an available specific runner in the project.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/runners</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project instance
     * @param runnerId  The ID of a runner
     * @return Runner instance of the Runner enabled
     * @throws GitLabApiException if any exception occurs
     */
    public Runner enableRunner(final Object projectIdOrPath, final Long runnerId) throws GitLabApiException {
        final GitLabApiForm formData = new GitLabApiForm().withParam("runner_id", runnerId, true);
        final Response response = post(Response.Status.CREATED, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "runners");
        return (response.readEntity(Runner.class));
    }

    /**
     * Disable a specific runner from the project. It works only if the project isn't the only project associated with
     * the specified runner. If so, an error is returned. Use the {@link #removeRunner(Long)} instead.
     *
     * <pre><code>GitLab Endpoint: DELETE /projects/:id/runners/:runner_id</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project instance
     * @param runnerId  The ID of a runner
     * @return Runner instance of the Runner disabled
     * @throws GitLabApiException if any exception occurs
     */
    public Runner disableRunner(final Object projectIdOrPath, final Long runnerId) throws GitLabApiException {
        final Response response = delete(Response.Status.OK, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "runners", runnerId);
        return (response.readEntity(Runner.class));
    }

    /**
     * Register a new runner for the gitlab instance.
     *
     * <pre><code>GitLab Endpoint: POST /runners/</code></pre>
     *
     * @param token       the token of the project (for project specific runners) or the token from the admin page
     * @param description The description of a runner
     * @param active      The state of a runner; can be set to true or false
     * @param tagList     The list of tags for a runner; put array of tags, that should be finally assigned to a runner
     * @param runUntagged Flag indicating the runner can execute untagged jobs
     * @param locked      Flag indicating the runner is locked
     * @param maximumTimeout the maximum timeout set when this Runner will handle the job
     * @return RunnerDetail instance.
     * @throws GitLabApiException if any exception occurs
     */
    public RunnerDetail registerRunner(final String token, final String description, final Boolean active, final List<String> tagList,
                                     final Boolean runUntagged, final Boolean locked, final Integer maximumTimeout) throws GitLabApiException {

        final GitLabApiForm formData = new GitLabApiForm()
                .withParam("token", token, true)
                .withParam("description", description, false)
                .withParam("active", active, false)
                .withParam("locked", locked, false)
                .withParam("run_untagged", runUntagged, false)
                .withParam("tag_list", tagList, false)
                .withParam("maximum_timeout", maximumTimeout, false);
        final Response response = post(Response.Status.CREATED, formData.asMap(), "runners");
        return (response.readEntity(RunnerDetail.class));
    }

    /**
     * Deletes a registered Runner.
     *
     * <pre><code>GitLab Endpoint: DELETE /runners/</code></pre>
     *
     * @param token the runners authentication token
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteRunner(final String token) throws GitLabApiException {
        final GitLabApiForm formData = new GitLabApiForm().withParam("token", token, true);
        delete(Response.Status.NO_CONTENT, formData.asMap(), "runners");
    }
}
