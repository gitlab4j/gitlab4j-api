package org.gitlab4j.api.webhook;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeploymentEvent extends AbstractEvent {
    private static final long serialVersionUID = 1L;

    public static final String X_GITLAB_EVENT = "Deployment Hook";
    public static final String OBJECT_KIND = "deployment";

    @JsonProperty("status")
    private String status;

    @JsonProperty("status_changed_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z")
    private String statusChangedAt;

    @JsonProperty("deployable_id")
    private Long deployableId;

    @JsonProperty("deployment_id")
    private Long deploymentId;

    @JsonProperty("deployable_url")
    private String deployableUrl;

    @JsonProperty("environment")
    private String environment;

    @JsonProperty("project")
    private EventProject project;

    @JsonProperty("short_sha")
    private String shortSha;

    @JsonProperty("user")
    private EventUser user;

    @JsonProperty("user_url")
    private String userUrl;

    @JsonProperty("commit_url")
    private String commitUrl;

    @JsonProperty("commit_title")
    private String commitTitle;

    @Override
    public String getObjectKind() {
        return (OBJECT_KIND);
    }

    public void setObjectKind(String objectKind) {
        if (!OBJECT_KIND.equals(objectKind))
            throw new RuntimeException("Invalid object_kind (" + objectKind + "), must be '" + OBJECT_KIND + "'");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusChangedAt() {
        return statusChangedAt;
    }

    public void setStatusChangedAt(String statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }

    public Long getDeployableId() {
        return deployableId;
    }

    public void setDeployableId(Long deployableId) {
        this.deployableId = deployableId;
    }

    public Long getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(Long deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getDeployableUrl() {
        return deployableUrl;
    }

    public void setDeployableUrl(String deployableUrl) {
        this.deployableUrl = deployableUrl;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public EventProject getProject() {
        return project;
    }

    public void setProject(EventProject project) {
        this.project = project;
    }

    public String getShortSha() {
        return shortSha;
    }

    public void setShortSha(String shortSha) {
        this.shortSha = shortSha;
    }

    public EventUser getUser() {
        return user;
    }

    public void setUser(EventUser user) {
        this.user = user;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getCommitUrl() {
        return commitUrl;
    }

    public void setCommitUrl(String commitUrl) {
        this.commitUrl = commitUrl;
    }

    public String getCommitTitle() {
        return commitTitle;
    }

    public void setCommitTitle(String commitTitle) {
        this.commitTitle = commitTitle;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
