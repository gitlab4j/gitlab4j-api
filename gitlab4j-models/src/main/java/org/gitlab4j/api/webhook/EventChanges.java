package org.gitlab4j.api.webhook;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class EventChanges {

    @JsonProperty("author_id")
    private ChangeContainer<Long> authorId;

    @JsonProperty("created_at")
    private ChangeContainer<Date> createdAt;

    @JsonProperty("updated_at")
    private ChangeContainer<Date> updatedAt;

    @JsonProperty("updated_by_id")
    private ChangeContainer<Long> updatedById;

    @JsonProperty("title")
    private ChangeContainer<String> title;

    @JsonProperty("description")
    private ChangeContainer<String> description;

    @JsonProperty("state")
    private ChangeContainer<String> state;

    @JsonProperty("milestone_id")
    private ChangeContainer<Long> milestoneId;

    @JsonProperty("labels")
    private ChangeContainer<List<EventLabel>> labels;

    @JsonProperty("assignees")
    private ChangeContainer<List<Assignee>> assignees;

    @JsonProperty("total_time_spent")
    private ChangeContainer<Integer> totalTimeSpent;

    @JsonProperty("other_properties")
    private Map<String, ChangeContainer<Object>> otherProperties = new LinkedHashMap<>();

    public ChangeContainer<Long> getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ChangeContainer<Long> authorId) {
        this.authorId = authorId;
    }

    public ChangeContainer<Date> getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ChangeContainer<Date> createdAt) {
        this.createdAt = createdAt;
    }

    public ChangeContainer<Date> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ChangeContainer<Date> updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ChangeContainer<Long> getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(ChangeContainer<Long> updatedById) {
        this.updatedById = updatedById;
    }

    public ChangeContainer<String> getTitle() {
        return title;
    }

    public void setTitle(ChangeContainer<String> title) {
        this.title = title;
    }

    public ChangeContainer<String> getDescription() {
        return description;
    }

    public void setDescription(ChangeContainer<String> description) {
        this.description = description;
    }

    public ChangeContainer<String> getState() {
        return state;
    }

    public void setState(ChangeContainer<String> state) {
        this.state = state;
    }

    public ChangeContainer<Long> getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(ChangeContainer<Long> milestoneId) {
        this.milestoneId = milestoneId;
    }

    public ChangeContainer<List<EventLabel>> getLabels() {
        return labels;
    }

    public void setLabels(ChangeContainer<List<EventLabel>> labels) {
        this.labels = labels;
    }

    public ChangeContainer<List<Assignee>> getAssignees() {
        return assignees;
    }

    public void setAssignees(ChangeContainer<List<Assignee>> assignees) {
        this.assignees = assignees;
    }

    public ChangeContainer<Integer> getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(ChangeContainer<Integer> totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    @SuppressWarnings("unchecked")
    public <T> ChangeContainer<T> get(String property) {

        if (otherProperties.containsKey(property)) {
            try {
                final ChangeContainer<Object> container = otherProperties.get(property);
                // noinspection unchecked : It's duty from caller to be sure to do that
                return container != null ? (ChangeContainer<T>) container : null;
            } catch (ClassCastException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @JsonAnyGetter
    public Map<String, ChangeContainer<Object>> any() {
        return this.otherProperties;
    }

    @JsonAnySetter
    public void set(String name, ChangeContainer<Object> value) {
        otherProperties.put(name, value);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
