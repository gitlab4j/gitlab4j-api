package org.gitlab4j.api.webhook;

import java.util.List;

import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.models.Reviewer;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MergeRequestEvent extends AbstractEvent {
    private static final long serialVersionUID = 1L;

    public static final String X_GITLAB_EVENT = "Merge Request Hook";
    public static final String OBJECT_KIND = "merge_request";

    @JsonProperty("user")
    private EventUser user;

    @JsonProperty("project")
    private EventProject project;

    @JsonProperty("repository")
    private EventRepository repository;

    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;

    @JsonProperty("labels")
    private List<EventLabel> labels;

    @JsonProperty("changes")
    private MergeRequestChanges changes;

    @JsonProperty("assignees")
    private List<Assignee> assignees;

    @JsonProperty("reviewers")
    private List<Reviewer> reviewers;

    public String getObjectKind() {
        return (OBJECT_KIND);
    }

    public void setObjectKind(String objectKind) {
        if (!OBJECT_KIND.equals(objectKind))
            throw new RuntimeException("Invalid object_kind (" + objectKind + "), must be '" + OBJECT_KIND + "'");
    }

    public EventUser getUser() {
        return user;
    }

    public void setUser(EventUser user) {
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

    public MergeRequestChanges getChanges() {
        return changes;
    }

    public void setChanges(MergeRequestChanges changes) {
        this.changes = changes;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public List<Reviewer> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

    public static class ObjectAttributes extends EventMergeRequest {}

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
