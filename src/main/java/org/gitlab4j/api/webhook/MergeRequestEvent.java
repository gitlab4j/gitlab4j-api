package org.gitlab4j.api.webhook;

import java.util.List;

import org.gitlab4j.api.models.User;
import org.gitlab4j.api.utils.JacksonJson;

public class MergeRequestEvent extends AbstractEvent {

    public static final String X_GITLAB_EVENT = "Merge Request Hook";
    public static final String OBJECT_KIND = "merge_request";

    private User user;
    private EventProject project;
    private EventRepository repository;
    private ObjectAttributes objectAttributes;
    private List<EventLabel> labels;
    private EventChanges changes;

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

    public ObjectAttributes getObjectAttributes() {
        return this.objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributes objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    public List<EventLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<EventLabel> labels) {
        this.labels = labels;
    }

    public EventChanges getChanges() {
        return changes;
    }

    public void setChanges(EventChanges changes) {
        this.changes = changes;
    }

    public static class ObjectAttributes extends EventMergeRequest {
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
