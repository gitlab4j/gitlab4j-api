package org.gitlab4j.api;

import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.JobStatus;
import org.gitlab4j.api.models.Runner;
import org.gitlab4j.api.models.RunnerAccessLevel;
import org.gitlab4j.api.models.RunnerDetail;
import org.gitlab4j.api.models.RunnerScope;

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
    public List<Runner> getRunners(RunnerScope scope) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope.toValue());
        Response response = get(Response.Status.OK, formData.asMap(), "runners");
        return (response.readEntity(new GenericType<List<Runner>>() {
        }));
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
    public List<Runner> getAllRunners(RunnerScope scope) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("scope", scope.toValue());
        Response response = get(Response.Status.OK, formData.asMap(), "runners", "all");
        return (response.readEntity(new GenericType<List<Runner>>() {
        }));
    }

    /**
     * Get details of a runner.
     *
     * GET /runners/:id
     *
     * @param id Runner id to get details for
     * @return RunnerDetail instance.
     * @throws GitLabApiException if any exception occurs
     */
    public RunnerDetail getRunnerDetails(Integer id) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "runners", id);
        return (response.readEntity(RunnerDetail.class));
    }

    /**
     * Update details of a runner.
     *
     * PUT /runners/:id
     *
     * @param id          The ID of a runner
     * @param description The description of a runner
     * @param active      The state of a runner; can be set to true or false
     * @param tagList     The list of tags for a runner; put array of tags, that should be finally assigned to a runner
     * @param runUntagged Flag indicating the runner can execute untagged jobs
     * @param locked      Flag indicating the runner is locked
     * @param accessLevel The access_level of the runner; not_protected or ref_protected
     * @return RunnerDetail instance.
     * @throws GitLabApiException if any exception occurs
     */
    public RunnerDetail updateRunner(Integer id, String description, Boolean active, List<String> tagList,
                                     Boolean runUntagged, Boolean locked, RunnerAccessLevel accessLevel) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("description", description, false)
                .withParam("active", active, false)
                .withParam("tag_list", tagList, false)
                .withParam("run_untagged", runUntagged, false)
                .withParam("locked", locked, false)
                .withParam("access_level", accessLevel.getValue(), false);
        Response response = put(Response.Status.OK, formData.asMap(), "runners", id);
        return (response.readEntity(RunnerDetail.class));
    }

    /**
     * Remove a runner.
     *
     * DELETE /runners/:id
     *
     * @param id The ID of a runner
     * @throws GitLabApiException if any exception occurs
     */
    public void removeRunner(Integer id) throws GitLabApiException {
        Response response = delete(Response.Status.NO_CONTENT, null, "runners", id);
    }

    /**
     * List jobs that are being processed or were processed by specified Runner.
     *
     * GET /runners/:id/jobs
     *
     * @param id     The ID of a runner
     * @param status Status of the job; one of: running, success, failed, canceled
     * @return List jobs that are being processed or were processed by specified Runner
     * @throws GitLabApiException if any exception occurs
     */
    public List<Job> getJobs(Integer id, JobStatus status) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("status", status.toValue(), false);
        Response response = get(Response.Status.OK, formData.asMap(), "runners", id, "jobs");
        return (response.readEntity(new GenericType<List<Job>>() {
        }));
    }

    /**
     * List all runners (specific and shared) available in the project. Shared runners are listed if at least one
     * shared runner is defined and shared runners usage is enabled in the project's settings.
     *
     * GET /projects/:id/runners
     *
     * @param projectId
     * @return
     */
    public List<Runner> getProjectRunners(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "runners");
        return (response.readEntity(new GenericType<List<Runner>>() {
        }));
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
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("runner_id", runnerId, true);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "runners");
        return (response.readEntity(Runner.class));
    }

    /**
     * Disable a specific runner from the project. It works only if the project isn't the only project associated with
     * the specified runner. If so, an error is returned. Use the {@link{#removeRunner(Integer)}} instead.
     *
     * DELETE /projects/:id/runners/:runner_id
     *
     * @param projectId The ID of the project owned by the authenticated user
     * @param runnerId  The ID of a runner
     * @return Runner instance of the Runner disabled
     * @throws GitLabApiException if any exception occurs
     */
    public Runner disableRunner(Integer projectId, Integer runnerId) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("runner_id", runnerId, true);
        Response response = delete(Response.Status.OK, formData.asMap(), "projects", projectId, "runners");
        return (response.readEntity(Runner.class));
    }
}
