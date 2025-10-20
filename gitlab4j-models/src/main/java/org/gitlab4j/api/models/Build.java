package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.webhook.EventEnvironment;
import org.gitlab4j.models.utils.JacksonJson;

/**
 * @author Yaris van Thiel
 */
public class Build {

    private Long id;
    private String stage;
    private String name;
    private BuildStatus status;
    private Date createdAt;
    private Date startedAt;
    private Date finishedAt;
    private Float duration;
    private Float queuedDuration;
    private String failureReason;
    private String when;
    private Boolean manual;
    private Boolean allowFailure;
    private User user;
    private Runner runner;
    private ArtifactsFile artifactsFile;
    private EventEnvironment environment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildStatus getStatus() {
        return status;
    }

    public void setStatus(BuildStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getQueuedDuration() {
        return queuedDuration;
    }

    public void setQueuedDuration(Float queuedDuration) {
        this.queuedDuration = queuedDuration;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Boolean getAllowFailure() {
        return allowFailure;
    }

    public void setAllowFailure(Boolean allowFailure) {
        this.allowFailure = allowFailure;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public ArtifactsFile getArtifactsFile() {
        return artifactsFile;
    }

    public void setArtifactsFile(ArtifactsFile artifactsFile) {
        this.artifactsFile = artifactsFile;
    }

    public EventEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(EventEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
