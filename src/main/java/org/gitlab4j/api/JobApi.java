package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.gitlab4j.api.models.Job;

/**
 * This class provides an entry point to all the GitLab API job calls.
 */
public class JobApi extends AbstractApi implements Constants {

    public JobApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of jobs in a project.
     *
     * GET /projects/:id/jobs
     *
     * @param projectId the project ID to get the list of jobs for
     * @return a list containing the jobs for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Job> getJobs(int projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "jobs");
        return (response.readEntity(new GenericType<List<Job>>() {
        }));
    }

    /**
     * Get a list of jobs in a project in the specified page range.
     *
     * GET /projects/:id/jobs
     *
     * @param projectId the project ID to get the list of jobs for
     * @param page the page to get
     * @param perPage the number of Job instances per page
     * @return a list containing the jobs for the specified project ID in the specified page range
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Job> getJobs(int projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "jobs");
        return (response.readEntity(new GenericType<List<Job>>() {
        }));
    }

    /**
     * Get a Pager of jobs in a project.
     *
     * GET /projects/:id/jobs
     *
     * @param projectId the project ID to get the list of jobs for
     * @param itemsPerPage the number of Job instances that will be fetched per page
     * @return a Pager containing the jobs for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Pager<Job> getJobs(int projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Job>(this, Job.class, itemsPerPage, null, "projects", projectId, "jobs"));
    }

    /**
     * Get a list of jobs in a project.
     *
     * GET /projects/:id/jobs
     *
     * @param projectId the project ID to get the list of jobs for
     * @param scope the scope of jobs, one of: CREATED, PENDING, RUNNING, FAILED, SUCCESS, CANCELED, SKIPPED, MANUAL
     * @return a list containing the jobs for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Job> getJobs(int projectId, JobScope scope) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("scope", scope).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "jobs");
        return (response.readEntity(new GenericType<List<Job>>() {}));
    }

    /**
     * Get a list of jobs in a pipeline.
     *
     * GET /projects/:id/pipelines/:pipeline_id/jobs
     *
     * @param projectId the project ID to get the list of pipeline for
     * @param pipelineId the pipeline ID to get the list of jobs for
     * @return a list containing the jobs for the specified project ID and pipeline ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Job> getJobsForPipeline(int projectId, int pipelineId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), 
                "projects", projectId, "pipelines", pipelineId, "jobs");
        return (response.readEntity(new GenericType<List<Job>>() {}));
    }

    /**
     * Get a list of jobs in a pipeline.
     *
     * GET /projects/:id/pipelines/:pipeline_id/jobs
     *
     * @param projectId the project ID to get the list of pipeline for
     * @param pipelineId the pipeline ID to get the list of jobs for
     * @param scope the scope of jobs, one of: CREATED, PENDING, RUNNING, FAILED, SUCCESS, CANCELED, SKIPPED, MANUAL
     * @return a list containing the jobs for the specified project ID and pipeline ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public List<Job> getJobsForPipeline(int projectId, int pipelineId, JobScope scope) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("scope", scope).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "pipelines", pipelineId, "jobs");
        return (response.readEntity(new GenericType<List<Job>>() {}));
    }

    /**
     * Get single job in a project.
     *
     * GET /projects/:id/jobs/:job_id
     *
     * @param projectId the project ID to get the specified job for
     * @param jobId the job ID to get
     * @return a single job for the specified project ID
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Job getJob(int projectId, int jobId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "jobs", jobId);
        return (response.readEntity(Job.class));
    }

    /**
     * Download the artifacts file from the given reference name and job provided the job finished successfully.
     * The file will be saved to the specified directory. If the file already exists in the directory it will
     * be overwritten.
     *
     * GET /projects/:id/jobs/artifacts/:ref_name/download?job=name
     *
     * @param projectId the ID of the project
     * @param ref the ref from a repository
     * @param jobName the name of the job to download the artifacts for
     * @param directory the File instance of the directory to save the file to, if null will use "java.io.tmpdir"
     * @return a File instance pointing to the download of the specified artifacts file
     * @throws GitLabApiException if any exception occurs
     */
    public File downloadArtifactsFile(Integer projectId, String ref, String jobName, File directory) throws GitLabApiException {

        Form formData = new GitLabApiForm().withParam("job", jobName, true);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", projectId, "jobs", "artifacts", ref, "download");

        try {

            if (directory == null)
                directory = new File(System.getProperty("java.io.tmpdir"));

            String filename = jobName + "-artifacts.zip";
            File file = new File(directory, filename);

            InputStream in = response.readEntity(InputStream.class);
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return (file);

        } catch (IOException ioe) {
            throw new GitLabApiException(ioe);
        }
    }

    /**
     * Get an InputStream pointing to the artifacts file from the given reference name and job
     * provided the job finished successfully. The file will be saved to the specified directory.
     * If the file already exists in the directory it will be overwritten.
     *
     * GET /projects/:id/jobs/artifacts/:ref_name/download?job=name
     *
     * @param projectId the ID of the project
     * @param ref the ref from a repository
     * @param jobName the name of the job to download the artifacts for
     * @return an InputStream to read the specified artifacts file from
     * @throws GitLabApiException if any exception occurs
     */
    public InputStream downloadArtifactsFile(Integer projectId, String ref, String jobName) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("job", jobName, true);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", projectId, "jobs", "artifacts", ref, "download");
        return (response.readEntity(InputStream.class));
    }

    /**
     * Cancel specified job in a project.
     *
     * POST /projects/:id/jobs/:job_id/cancel
     *
     * @param projectId the project ID to cancel specified job
     * @param jobId the ID to cancel job
     * @return job instance which just canceled
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Job cancleJob(int projectId, int jobId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "jobs", jobId, "cancel");
        return (response.readEntity(Job.class));
    }

    /**
     * Retry specified job in a project.
     *
     * POST /projects/:id/jobs/:job_id/retry
     *
     * @param projectId the project ID to retry speficied job
     * @param jobId the ID to retry job
     * @return job instance which just retried
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Job retryJob(int projectId, int jobId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Status.CREATED, formData, "projects", projectId, "jobs", jobId, "retry");
        return (response.readEntity(Job.class));
    }

    /**
     * Erase specified job in a project.
     *
     * POST /projects/:id/jobs/:job_id/erase
     *
     * @param projectId the project ID to erase specified job
     * @param jobId the ID to erase job
     * @return job instance which just erased
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Job eraseJob(int projectId, int jobId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Status.CREATED, formData, "projects", projectId, "jobs", jobId, "erase");
        return (response.readEntity(Job.class));
    }

    /**
     * Play specified job in a project.
     *
     * POST /projects/:id/jobs/:job_id/play
     *
     * @param projectId the project ID to play specified job
     * @param jobId the ID to play job
     * @return job instance which just played
     * @throws GitLabApiException if any exception occurs during execution
     */
    public Job playJob(int projectId, int jobId) throws GitLabApiException {
        GitLabApiForm formData = null;
        Response response = post(Status.CREATED, formData, "projects", projectId, "jobs", jobId, "play");
        return (response.readEntity(Job.class));
    }
}
