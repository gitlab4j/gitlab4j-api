package org.gitlab4j.api.webhook;

import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventIssue {

    @JsonProperty("assignee_id")
    private Long assigneeId;

    @JsonProperty("author_id")
    private Long authorId;

    @JsonProperty("branch_name")
    private String branchName;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("iid")
    private Long iid;

    @JsonProperty("milestone_id")
    private String milestoneId;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("state")
    private String state;

    @JsonProperty("title")
    private String title;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAt;

    @JsonProperty("url")
    private String url;

    @JsonProperty("action")
    private String action;

    @JsonProperty("assignee_ids")
    private List<Long> assigneeIds;

    @JsonProperty("updated_by_id")
    private Long updatedById;

    @JsonProperty("last_edited_at")
    private Date lastEditedAt;

    @JsonProperty("last_edited_by_id")
    private Long lastEditedById;

    @JsonProperty("relative_position")
    private Long relativePosition;

    @JsonProperty("state_id")
    private Long stateId;

    @JsonProperty("confidential")
    private Boolean confidential;

    @JsonProperty("discussion_locked")
    private Boolean discussionLocked;

    @JsonProperty("due_date")
    private Date dueDate;

    @JsonProperty("moved_to_id")
    private Long movedToId;

    @JsonProperty("duplicated_to_id")
    private Long duplicatedToId;

    @JsonProperty("time_estimate")
    private Long timeEstimate;

    @JsonProperty("total_time_spent")
    private Long totalTimeSpent;

    @JsonProperty("time_change")
    private Long timeChange;

    @JsonProperty("human_time_estimate")
    private String humanTimeEstimate;

    @JsonProperty("human_total_time_spent")
    private String humanTotalTimeSpent;

    @JsonProperty("human_time_change")
    private String humanTimeChange;

    @JsonProperty("weight")
    private Long weight;

    @JsonProperty("health_status")
    private String healthStatus;

    @JsonProperty("type")
    private String type;

    @JsonProperty("severity")
    private String severity;

    @JsonProperty("labels")
    private List<EventLabel> labels;

    public Long getAssigneeId() {
        return this.assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIid() {
        return this.iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getMilestoneId() {
        return this.milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Long> getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(List<Long> assigneeIds) {
        this.assigneeIds = assigneeIds;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Date getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(Date lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }

    public Long getLastEditedById() {
        return lastEditedById;
    }

    public void setLastEditedById(Long lastEditedById) {
        this.lastEditedById = lastEditedById;
    }

    public Long getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(Long relativePosition) {
        this.relativePosition = relativePosition;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Boolean getConfidential() {
        return confidential;
    }

    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    public Boolean getDiscussionLocked() {
        return discussionLocked;
    }

    public void setDiscussionLocked(Boolean discussionLocked) {
        this.discussionLocked = discussionLocked;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getMovedToId() {
        return movedToId;
    }

    public void setMovedToId(Long movedToId) {
        this.movedToId = movedToId;
    }

    public Long getDuplicatedToId() {
        return duplicatedToId;
    }

    public void setDuplicatedToId(Long duplicatedToId) {
        this.duplicatedToId = duplicatedToId;
    }

    public Long getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Long timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public Long getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Long totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public Long getTimeChange() {
        return timeChange;
    }

    public void setTimeChange(Long timeChange) {
        this.timeChange = timeChange;
    }

    public String getHumanTimeEstimate() {
        return humanTimeEstimate;
    }

    public void setHumanTimeEstimate(String humanTimeEstimate) {
        this.humanTimeEstimate = humanTimeEstimate;
    }

    public String getHumanTotalTimeSpent() {
        return humanTotalTimeSpent;
    }

    public void setHumanTotalTimeSpent(String humanTotalTimeSpent) {
        this.humanTotalTimeSpent = humanTotalTimeSpent;
    }

    public String getHumanTimeChange() {
        return humanTimeChange;
    }

    public void setHumanTimeChange(String humanTimeChange) {
        this.humanTimeChange = humanTimeChange;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public List<EventLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<EventLabel> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
