package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.Constants.IssueState;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public abstract class AbstractIssue implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class TaskCompletionStatus implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer count;
        private Integer completedCount;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getCompletedCount() {
            return completedCount;
        }

        public void setCompletedCount(Integer completedCount) {
            this.completedCount = completedCount;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }

    @JsonProperty("assignee")
    private Assignee assignee;

    @JsonProperty("assignees")
    private List<Assignee> assignees;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("confidential")
    private Boolean confidential;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAt;

    @JsonProperty("closed_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date closedAt;

    @JsonProperty("closed_by")
    private User closedBy;

    @JsonProperty("description")
    private String description;

    @JsonProperty("due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    @JsonProperty("id")
    private ValueNode actualId;

    @JsonIgnore
    private String externalId;

    @JsonIgnore
    private Long id;

    @JsonProperty("iid")
    private Long iid;

    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("milestone")
    private Milestone milestone;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("state")
    private IssueState state;

    @JsonProperty("title")
    private String title;

    @JsonProperty("user_notes_count")
    private Integer userNotesCount;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("references")
    private References references;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("discussion_locked")
    private Boolean discussionLocked;

    @JsonProperty("time_stats")
    private TimeStats timeStats;

    @JsonProperty("severity")
    private String severity;

    @JsonProperty("issue_type")
    private String issueType;

    @JsonProperty("epic")
    private IssueEpic epic;

    @JsonProperty("upvotes")
    private Integer upvotes;

    @JsonProperty("downvotes")
    private Integer downvotes;

    @JsonProperty("merge_requests_count")
    private Integer mergeRequestsCount;

    @JsonProperty("has_tasks")
    private Boolean hasTasks;

    @JsonProperty("task_status")
    private String taskStatus;

    @JsonProperty("imported")
    private Boolean imported;

    @JsonProperty("imported_from")
    private String importedFrom;

    @JsonProperty("iteration")
    private Iteration iteration;

    @JsonProperty("task_completion_status")
    private TaskCompletionStatus taskCompletionStatus;

    @JsonProperty("health_status")
    private String healthStatus;

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getConfidential() {
        return confidential;
    }

    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ValueNode getActualId() {
        return actualId;
    }

    public void setActualId(ValueNode id) {
        actualId = id;
        if (actualId instanceof TextNode) {
            externalId = actualId.asText();
        } else if (actualId instanceof IntNode || actualId instanceof LongNode) {
            this.id = actualId.asLong();
        }
    }

    public Long getId() {
        return (id);
    }

    public void setId(Long id) {
        this.id = id;
        if (id != null) {
            actualId = new LongNode(id);
            externalId = null;
        }
    }

    public String getExternalId() {
        return (externalId);
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
        if (externalId != null) {
            actualId = new TextNode(externalId);
            id = null;
        }
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public IssueState getState() {
        return state;
    }

    public void setState(IssueState state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public User getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(User closedBy) {
        this.closedBy = closedBy;
    }

    public Integer getUserNotesCount() {
        return userNotesCount;
    }

    public void setUserNotesCount(Integer userNotesCount) {
        this.userNotesCount = userNotesCount;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public References getReferences() {
        return references;
    }

    public void setReferences(References references) {
        this.references = references;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getDiscussionLocked() {
        return discussionLocked;
    }

    public void setDiscussionLocked(Boolean discussionLocked) {
        this.discussionLocked = discussionLocked;
    }

    public TimeStats getTimeStats() {
        return timeStats;
    }

    public void setTimeStats(TimeStats timeStats) {
        this.timeStats = timeStats;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public Integer getMergeRequestsCount() {
        return mergeRequestsCount;
    }

    public void setMergeRequestsCount(Integer mergeRequestsCount) {
        this.mergeRequestsCount = mergeRequestsCount;
    }

    public Boolean getHasTasks() {
        return hasTasks;
    }

    public void setHasTasks(Boolean hasTasks) {
        this.hasTasks = hasTasks;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Boolean getImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    public String getImportedFrom() {
        return importedFrom;
    }

    public void setImportedFrom(String importedFrom) {
        this.importedFrom = importedFrom;
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }

    public TaskCompletionStatus getTaskCompletionStatus() {
        return taskCompletionStatus;
    }

    public void setTaskCompletionStatus(TaskCompletionStatus taskCompletionStatus) {
        this.taskCompletionStatus = taskCompletionStatus;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public IssueEpic getEpic() {
        return epic;
    }

    public void setEpic(IssueEpic epic) {
        this.epic = epic;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
