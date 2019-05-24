package org.gitlab4j.api.webhook;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.utils.JacksonJson;

public class EventChanges {

    private ChangeContainer<String> state;
    private ChangeContainer<Date> updatedAt;
    private ChangeContainer<Integer> updatedById;
    private ChangeContainer<Date> dueDate;
    private ChangeContainer<Integer> milestoneId;
    private ChangeContainer<List<EventLabel>> labels;
    private ChangeContainer<List<Assignee>> assignees;
    private ChangeContainer<Integer> totalTimeSpent;
    private ChangeContainer<Boolean> confidential;
    private LinkedHashMap<String, ChangeContainer<Object>> otherProperties = new LinkedHashMap<>();

    public ChangeContainer<Date> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ChangeContainer<Date> updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ChangeContainer<Integer> getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(ChangeContainer<Integer> updatedById) {
        this.updatedById = updatedById;
    }

    public ChangeContainer<Date> getDueDate() {
        return dueDate;
    }

    public void setDueDate(ChangeContainer<Date> dueDate) {
        this.dueDate = dueDate;
    }

    public ChangeContainer<Integer> getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(ChangeContainer<Integer> milestoneId) {
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

    public ChangeContainer<Boolean> getConfidential() {
        return confidential;
    }

    public void setConfidential(ChangeContainer<Boolean> confidential) {
        this.confidential = confidential;
    }

    public ChangeContainer<String> getState() {
        return state;
    }

    public void setState(ChangeContainer<String> state) {
        this.state = state;
    }

    public <T> ChangeContainer<T> get(String property){
        if(otherProperties.containsKey(property)){
            try {
                final ChangeContainer<Object> container = otherProperties.get(property);
                //noinspection unchecked It's duty from caller to be sure to do that
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
