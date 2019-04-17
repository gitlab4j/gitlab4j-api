package org.gitlab4j.api.systemhooks;

import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.webhook.EventProject;

public class RepositorySystemHookEvent extends AbstractSystemHookEvent {

    public static final String REPOSITORY_UPDATE_EVENT = "repository_update";

    private String eventName;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userAvatar;

    private Integer projectId;
    private EventProject project;

    private List<RepositoryChange> changes;
    private List<String> refs;

    public String getEventName() {
        return (eventName);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
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

    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
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