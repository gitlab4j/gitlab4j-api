package org.gitlab4j.api;

import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.JobStatus;
import org.gitlab4j.api.models.Runner;
import org.gitlab4j.api.models.RunnerDetail;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class provides an entry point to all the GitLab API repository files calls.
 */
public class RunnersApi extends AbstractApi {
    public RunnersApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of all available runners available to the user.
     *
     * GET /runners
     *
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getRunners() throws GitLabApiException {
        return getRunners(null);
    }

    /**
     * Get a list of specific runners available to the user.
     *
     * GET /runners
     *
     * @param scope The scope of specific runners to show, one of: active, paused, online; showing all runners null
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getRunners(Runner.RunnerStatus scope) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope, false);
        Response response = get(Response.Status.OK, formData.asMap(), "runners");
        return (response.readEntity(new GenericType<List<Runner>>() {
        }));
    }

    /**
     * Get a list of all available runners available to the user.
     *
     * GET /runners
     *
     * @param itemsPerPage the number of Runner instances that will be fetched per page
     * @return a Pager containing the Runners for the user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getRunners(int itemsPerPage) throws GitLabApiException {
        return getRunners(null, itemsPerPage);
    }

    /**
     * Get a list of specific runners available to the user.
     *
     * GET /runners
     *
     * @param scope        The scope of specific runners to show, one of: active, paused, online; showing all runners null
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Runners for the user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getRunners(Runner.RunnerStatus scope, int itemsPerPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope, false);
        return (new Pager<>(this, Runner.class, itemsPerPage, formData.asMap(), "runners"));
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * GET /runners/all
     *
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getAllRunners() throws GitLabApiException {
        return getAllRunners(null);
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * GET /runners/all
     *
     * @param scope The scope of specific runners to show, one of: active, paused, online; showing all runners null
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getAllRunners(Runner.RunnerStatus scope) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope, false);
        Response response = get(Response.Status.OK, formData.asMap(), "runners", "all");
        return (response.readEntity(new GenericType<List<Runner>>() {
        }));
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * GET /runners/all
     *
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return List of Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getAllRunners(int itemsPerPage) throws GitLabApiException {
        return getAllRunners(null, itemsPerPage);
    }

    /**
     * Get a list of all runners in the GitLab instance (specific and shared). Access is restricted to users with admin privileges.
     *
     * GET /runners/all
     *
     * @param scope        The scope of specific runners to show, one of: active, paused, online; showing all runners null
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Runners
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getAllRunners(Runner.RunnerStatus scope, int itemsPerPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope, false);
        return (new Pager<>(this, Runner.class, itemsPerPage, formData.asMap(), "runners"));
    }

    /**
     * Get details of a runner.
     *
     * GET /runners/:id
     *
     * @param runnerId Runner id to get details for
     * @return RunnerDetail instance.
     * @throws GitLabApiException if any exception occurs
     */
    public RunnerDetail getRunnerDetail(Integer runnerId) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }
        Response response = get(Response.Status.OK, null, "runners", runnerId);
        return (response.readEntity(RunnerDetail.class));
    }

    /**
     * Update details of a runner.
     *
     * PUT /runners/:id
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
    public RunnerDetail updateRunner(Integer runnerId, String description, Boolean active, List<String> tagList,
                                     Boolean runUntagged, Boolean locked, RunnerDetail.RunnerAccessLevel accessLevel) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("description", description, false)
                .withParam("active", active, false)
                .withParam("tag_list", tagList, false)
                .withParam("run_untagged", runUntagged, false)
                .withParam("locked", locked, false)
                .withParam("access_level", accessLevel, false);
        Response response = put(Response.Status.OK, formData.asMap(), "runners", runnerId);
        return (response.readEntity(RunnerDetail.class));
    }

    /**
     * Remove a runner.
     *
     * DELETE /runners/:id
     *
     * @param runnerId The ID of a runner
     * @throws GitLabApiException if any exception occurs
     */
    public void removeRunner(Integer runnerId) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }

        delete(Response.Status.NO_CONTENT, null, "runners", runnerId);
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * GET /runners/:id/jobs
     *
     * @param runnerId The ID of a runner
     * @return List jobs that are being processed or were processed by specified Runner
     * @throws GitLabApiException if any exception occurs
     */
    public List<Job> getJobs(Integer runnerId) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }
        return getJobs(runnerId, null);
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * GET /runners/:id/jobs
     *
     * @param runnerId The ID of a runner
     * @param status   Status of the job; one of: running, success, failed, canceled
     * @return List jobs that are being processed or were processed by specified Runner
     * @throws GitLabApiException if any exception occurs
     */
    public List<Job> getJobs(Integer runnerId, JobStatus status) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("status", status, false);
        Response response = get(Response.Status.OK, formData.asMap(), "runners", runnerId, "jobs");
        return (response.readEntity(new GenericType<List<Job>>() {
        }));
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * GET /runners/:id/jobs
     *
     * @param runnerId     The ID of a runner
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Jobs for the Runner
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Job> getJobs(Integer runnerId, int itemsPerPage) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }
        return getJobs(runnerId, null, itemsPerPage);
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * GET /runners/:id/jobs
     *
     * @param runnerId     The ID of a runner
     * @param status       Status of the job; one of: running, success, failed, canceled
     * @param itemsPerPage The number of Runner instances that will be fetched per page
     * @return a Pager containing the Jobs for the Runner
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Job> getJobs(Integer runnerId, JobStatus status, int itemsPerPage) throws GitLabApiException {
        if (runnerId == null) {
            throw new RuntimeException("runnerId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("status", status, false);
        return (new Pager<>(this, Job.class, itemsPerPage, formData.asMap(), "runners", runnerId, "jobs"));
    }

    /**
     * List all runners (specific and shared) available in the project. Shared runners are listed if at least one
     * shared runner is defined and shared runners usage is enabled in the project's settings.
     *
     * GET /projects/:id/runners
     *
     * @param projectId The ID of the project owned by the authenticated user
     * @return List of all Runner available in the project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Runner> getProjectRunners(Integer projectId) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(Response.Status.OK, null, "projects", projectId, "runners");
        return (response.readEntity(new GenericType<List<Runner>>() {
        }));
    }

    /**
     * List all runners (specific and shared) available in the project. Shared runners are listed if at least one
     * shared runner is defined and shared runners usage is enabled in the project's settings.
     *
     * GET /projects/:id/runners
     *
     * @param projectId The ID of the project owned by the authenticated user
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return Pager of all Runner available in the project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Runner> getProjectRunners(Integer projectId, int itemsPerPage) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        return (new Pager<>(this, Runner.class, itemsPerPage, null, "projects", projectId, "runners"));
    }

    /**
     * Enable an available specific runner in the project.
     *
     * POST /projects/:id/runners
     *
     * @param projectId The ID of the project owned by the authenticated user
     * @param runnerId  The ID of a runner
     * @return Runner instance of the Runner enabled
     * @throws GitLabApiException if any exception occurs
     */
    public Runner enableRunner(Integer projectId, Integer runnerId) throws GitLabApiException {
        if (projectId == null || runnerId == null) {
            throw new RuntimeException("projectId or runnerId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("runner_id", runnerId, true);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", projectId, "runners");
        return (response.readEntity(Runner.class));
    }

    /**
     * Disable a specific runner from the project. It works only if the project isn't the only project associated with
     * the specified runner. If so, an error is returned. Use the {@link #removeRunner(Integer)} instead.
     *
     * DELETE /projects/:id/runners/:runner_id
     *
     * @param projectId The ID of the project owned by the authenticated user
     * @param runnerId  The ID of a runner
     * @return Runner instance of the Runner disabled
     * @throws GitLabApiException if any exception occurs
     */
    public Runner disableRunner(Integer projectId, Integer runnerId) throws GitLabApiException {
        if (projectId == null || runnerId == null) {
            throw new RuntimeException("projectId or runnerId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("runner_id", runnerId, true);
        Response response = delete(Response.Status.OK, formData.asMap(), "projects", projectId, "runners");
        return (response.readEntity(Runner.class));
    }
}
