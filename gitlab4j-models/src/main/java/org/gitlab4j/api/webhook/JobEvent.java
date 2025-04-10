package org.gitlab4j.api.webhook;

import java.util.Date;

import org.gitlab4j.api.models.Runner;
import org.gitlab4j.models.utils.JacksonJson;

public class JobEvent extends AbstractEvent {
    private static final long serialVersionUID = 1L;

    public static final String JOB_HOOK_X_GITLAB_EVENT = "Job Hook";
    public static final String OBJECT_KIND = "job";

    private String ref;
    private Boolean tag;
    private String beforeSha;
    private String sha;
    private Long jobId;
    private String jobName;
    private String jobStage;
    private String jobStatus;
    private Date jobStartedAt;
    private Date jobFinishedAt;
    private Integer jobDuration;
    private Boolean jobAllowFailure;
    private String jobFailureReason;
    private Long buildId;
    private String buildName;
    private String buildStage;
    private String buildStatus;
    private Date buildCreatedAt;
    private Date buildStartedAt;
    private Date buildFinishedAt;
    private Integer buildDuration;
    private Double buildQueuedDuration;
    private Boolean buildAllowFailure;
    private String buildFailureReason;
    private Integer retriesCount;
    private Integer pipelineId;
    private Long projectId;
    private String projectName;
    private EventUser user;
    private BuildCommit commit;
    private EventRepository repository;
    private EventProject project;
    private Runner runner;

    @Override
    public String getObjectKind() {
        return (OBJECT_KIND);
    }

    public void setObjectKind(String objectKind) {
        if (!OBJECT_KIND.equals(objectKind))
            throw new RuntimeException("Invalid object_kind (" + objectKind + "), must be '" + OBJECT_KIND + "'");
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public String getBeforeSha() {
        return beforeSha;
    }

    public void setBeforeSha(String beforeSha) {
        this.beforeSha = beforeSha;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobStage() {
        return jobStage;
    }

    public void setJobStage(String jobStage) {
        this.jobStage = jobStage;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getJobStartedAt() {
        return jobStartedAt;
    }

    public void setJobStartedAt(Date jobStartedAt) {
        this.jobStartedAt = jobStartedAt;
    }

    public Date getJobFinishedAt() {
        return jobFinishedAt;
    }

    public void setJobFinishedAt(Date jobFinishedAt) {
        this.jobFinishedAt = jobFinishedAt;
    }

    public Integer getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(Integer jobDuration) {
        this.jobDuration = jobDuration;
    }

    public Boolean getJobAllowFailure() {
        return jobAllowFailure;
    }

    public void setJobAllowFailure(Boolean jobAllowFailure) {
        this.jobAllowFailure = jobAllowFailure;
    }

    public String getJobFailureReason() {
        return jobFailureReason;
    }

    public void setJobFailureReason(String jobFailureReason) {
        this.jobFailureReason = jobFailureReason;
    }

    public Long getBuildId() {
        return buildId;
    }

    public void setBuildId(Long buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildStage() {
        return buildStage;
    }

    public void setBuildStage(String buildStage) {
        this.buildStage = buildStage;
    }

    public String getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    public Date getBuildCreatedAt() {
        return buildCreatedAt;
    }

    public void setBuildCreatedAt(Date buildCreatedAt) {
        this.buildCreatedAt = buildCreatedAt;
    }

    public Date getBuildStartedAt() {
        return buildStartedAt;
    }

    public void setBuildStartedAt(Date buildStartedAt) {
        this.buildStartedAt = buildStartedAt;
    }

    public Date getBuildFinishedAt() {
        return buildFinishedAt;
    }

    public void setBuildFinishedAt(Date buildFinishedAt) {
        this.buildFinishedAt = buildFinishedAt;
    }

    public Integer getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDuration(Integer buildDuration) {
        this.buildDuration = buildDuration;
    }

    public Double getBuildQueuedDuration() {
        return buildQueuedDuration;
    }

    public void setBuildQueuedDuration(Double buildQueuedDuration) {
        this.buildQueuedDuration = buildQueuedDuration;
    }

    public Boolean getBuildAllowFailure() {
        return buildAllowFailure;
    }

    public void setBuildAllowFailure(Boolean buildAllowFailure) {
        this.buildAllowFailure = buildAllowFailure;
    }

    public String getBuildFailureReason() {
        return buildFailureReason;
    }

    public void setBuildFailureReason(String buildFailureReason) {
        this.buildFailureReason = buildFailureReason;
    }

    public Integer getRetriesCount() {
        return retriesCount;
    }

    public void setRetriesCount(Integer retriesCount) {
        this.retriesCount = retriesCount;
    }

    public Integer getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Integer pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public EventUser getUser() {
        return user;
    }

    public void setUser(EventUser user) {
        this.user = user;
    }

    public BuildCommit getCommit() {
        return commit;
    }

    public void setCommit(BuildCommit commit) {
        this.commit = commit;
    }

    public EventRepository getRepository() {
        return repository;
    }

    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }

    public EventProject getProject() {
        return project;
    }

    public void setProject(EventProject project) {
        this.project = project;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
