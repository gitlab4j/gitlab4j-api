package org.gitlab4j.api.systemhooks;

import java.util.List;

import org.gitlab4j.api.webhook.EventProject;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositorySystemHookEvent extends AbstractSystemHookEvent {
    private static final long serialVersionUID = 1L;

    public static final String REPOSITORY_UPDATE_EVENT = "repository_update";

    @JsonProperty("event_name")
    private String eventName;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("user_avatar")
    private String userAvatar;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("project")
    private EventProject project;

    @JsonProperty("changes")
    private List<RepositoryChange> changes;

    @JsonProperty("refs")
    private List<String> refs;

    @Override
    public String getEventName() {
        return (eventName);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public EventProject getProject() {
        return project;
    }

    public void setProject(EventProject project) {
        this.project = project;
    }

    public List<RepositoryChange> getChanges() {
        return changes;
    }

    public void setChanges(List<RepositoryChange> changes) {
        this.changes = changes;
    }

    public List<String> getRefs() {
        return refs;
    }

    public void setRefs(List<String> refs) {
        this.refs = refs;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
