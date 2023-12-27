package org.gitlab4j.api.webhook;

import java.util.Date;

import org.gitlab4j.api.models.User;
import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The documentation at: <a href="https://docs.gitlab.com/ee/user/project/integrations/webhook_events.html#job-events">
 * Job Events</a> is incorrect, this class represents the actual content of the Job Hook event.
 */
public class BuildEvent extends AbstractEvent {
    private static final long serialVersionUID = 1L;

    public static final String JOB_HOOK_X_GITLAB_EVENT = "Job Hook";
    public static final String OBJECT_KIND = "build";

    private String ref;
    private Boolean tag;
    private String beforeSha;
    private String sha;
    private Long buildId;
    private String buildName;
    private String buildStage;
    private String buildStatus;
    private Date buildStartedAt;
    private Date buildFinishedAt;
    private Float buildDuration;

    private Float buildQueuedDuration;
    private Boolean buildAllowFailure;
    private String buildFailureReason;
    private Long projectId;

    private Long pipelineId;
    private String projectName;
    private User user;
    private BuildCommit commit;
    private EventRepository repository;

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

    public Date getBuildStartedAt() {
        return buildStartedAt;
    }

    public void setBuildStartedAt(Date buildStartedAt) {
        this.buildStartedAt = buildStartedAt;
    }

    /**
     * @deprecated Replaced by {@link #getBuildStartedAt()}
     * @return the buildstarted at Date
     */
    @Deprecated
    @JsonIgnore
    public Date getBuildStarted_at() {
        return buildStartedAt;
    }

    /**
     * @deprecated Replaced by {@link #setBuildStartedAt(Date)}
     * @param buildStartedAt new buildstarted at value
     */
    @Deprecated
    @JsonIgnore
    public void setBuildStarted_at(Date buildStartedAt) {
        this.buildStartedAt = buildStartedAt;
    }

    public Date getBuildFinishedAt() {
        return buildFinishedAt;
    }

    public void setBuildFinishedAt(Date buildFinishedAt) {
        this.buildFinishedAt = buildFinishedAt;
    }

    /**
     * @deprecated Replaced by {@link #getBuildFinishedAt()}
     * @return the buildfinished at Date
     */
    @Deprecated
    @JsonIgnore
    public Date getBuildFinished_at() {
        return buildFinishedAt;
    }

    /**
     * @deprecated Replaced by {@link #setBuildFinishedAt(Date)}
     * @param buildFinishedAt new buildfinished at value
     */
    @Deprecated
    @JsonIgnore
    public void setBuildFinished_at(Date buildFinishedAt) {
        this.buildFinishedAt = buildFinishedAt;
    }

    public Float getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDuration(Float buildDuration) {
        this.buildDuration = buildDuration;
    }

    public Float getBuildQueuedDuration() {
        return buildQueuedDuration;
    }

    public void setBuildQueuedDuration(Float buildQueuedDuration) {
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
