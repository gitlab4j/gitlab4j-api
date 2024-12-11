package org.gitlab4j.api.webhook;

import java.util.List;

import org.gitlab4j.api.models.User;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkItemEvent extends AbstractEvent {
    private static final long serialVersionUID = 1L;

    public static final String X_GITLAB_EVENT = "Issue Hook";
    public static final String OBJECT_KIND = "work_item";

    @JsonProperty("user")
    private User user;

    @JsonProperty("project")
    private EventProject project;

    @JsonProperty("repository")
    private EventRepository repository;

    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;

    @JsonProperty("labels")
    private List<EventLabel> labels;

    @JsonProperty("changes")
    private WorkItemChanges changes;

    public String getObjectKind() {
        return (OBJECT_KIND);
    }

    public void setObjectKind(String objectKind) {
        if (!OBJECT_KIND.equals(objectKind))
            throw new RuntimeException("Invalid object_kind (" + objectKind + "), must be '" + OBJECT_KIND + "'");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventProject getProject() {
        return project;
    }

    public void setProject(EventProject project) {
        this.project = project;
    }

    public EventRepository getRepository() {
        return repository;
    }

    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }

    public List<EventLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<EventLabel> labels) {
        this.labels = labels;
    }

    public WorkItemChanges getChanges() {
        return changes;
    }

    public void setChanges(WorkItemChanges changes) {
        this.changes = changes;
    }

    public ObjectAttributes getObjectAttributes() {
        return this.objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributes objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    public static class ObjectAttributes extends EventWorkItem {}

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
