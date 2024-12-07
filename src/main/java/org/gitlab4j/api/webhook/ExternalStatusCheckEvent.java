package org.gitlab4j.api.webhook;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.webhook.MergeRequestEvent.ObjectAttributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalStatusCheckEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("object_kind")
    private String objectKind;

    @JsonProperty("event_type")
    private String eventType;

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

    @JsonProperty("external_approval_rule")
    private EventExternalStatusCheck externalApprovalRule;

    public String getObjectKind() {
        return objectKind;
    }

    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public EventExternalStatusCheck getExternalApprovalRule() {
        return externalApprovalRule;
    }

    public void setExternalApprovalRule(EventExternalStatusCheck externalApprovalRule) {
        this.externalApprovalRule = externalApprovalRule;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
