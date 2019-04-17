package org.gitlab4j.api.webhook;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.utils.JacksonJson;

public class EventChanges {

    private ChangeContainer<Date> updatedAt;
    private ChangeContainer<Integer> updatedById;
    private ChangeContainer<Date> dueDate;
    private ChangeContainer<Integer> milestoneId;
    private ChangeContainer<List<EventLabel>> labels;
    private ChangeContainer<List<Assignee>> assignees;
    private ChangeContainer<Integer> totalTimeSpent;
    private ChangeContainer<Boolean> confidential;

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

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
