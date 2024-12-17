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

    /**
     * The assignee associated with the issue.
     */
    @JsonProperty("assignee")
    private Assignee assignee;

    /**
     * The list of assignees associated with the issue.
     */
    @JsonProperty("assignees")
    private List<Assignee> assignees;

    /**
     * The author of the issue.
     */
    @JsonProperty("author")
    private Author author;

    /**
     * Indicates whether the issue is confidential.
     */
    @JsonProperty("confidential")
    private Boolean confidential;

    /**
     * The date when the issue was created.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    /**
     * The date when the issue was last updated.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAt;

    /**
     * The date when the issue was closed.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("closed_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date closedAt;

    /**
     * The user who closed the issue.
     */
    @JsonProperty("closed_by")
    private User closedBy;

    /**
     * The description of the issue.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The due date of the issue.
     * Expected in format "yyyy-MM-dd".
     */
    @JsonProperty("due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    /**
     * The actual ID of the issue.
     */
    @JsonProperty("id")
    private ValueNode actualId;

    /**
     * The external ID of the issue (ignored during serialization).
     */
    @JsonIgnore
    private String externalId;

    /**
     * The internal ID of the issue (ignored during serialization).
     */
    @JsonIgnore
    private Long id;

    /**
     * The internal identifier for the issue.
     */
    @JsonProperty("iid")
    private Long iid;

    /**
     * The labels associated with the issue.
     */
    @JsonProperty("labels")
    private List<String> labels;

    /**
     * The milestone associated with the issue.
     */
    @JsonProperty("milestone")
    private Milestone milestone;

    /**
     * The project ID associated with the issue.
     */
    @JsonProperty("project_id")
    private Long projectId;

    /**
     * The state of the issue (e.g., open, closed).
     */
    @JsonProperty("state")
    private IssueState state;

    /**
     * The title of the issue.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The count of user notes on the issue.
     */
    @JsonProperty("user_notes_count")
    private Integer userNotesCount;

    /**
     * The web URL of the issue.
     */
    @JsonProperty("web_url")
    private String webUrl;

    /**
     * The references associated with the issue.
     */
    @JsonProperty("references")
    private References references;

    /**
     * The weight of the issue.
     */
    @JsonProperty("weight")
    private Integer weight;

    /**
     * Indicates whether discussions on the issue are locked.
     */
    @JsonProperty("discussion_locked")
    private Boolean discussionLocked;

    /**
     * The time statistics associated with the issue.
     */
    @JsonProperty("time_stats")
    private TimeStats timeStats;

    /**
     * The severity of the issue.
     */
    @JsonProperty("severity")
    private String severity;

    /**
     * The type of the issue.
     */
    @JsonProperty("issue_type")
    private String issueType;

    /**
     * The epic associated with the issue.
     */
    @JsonProperty("epic")
    private IssueEpic epic;

    /**
     * The upvotes for the issue.
     */
    @JsonProperty("upvotes")
    private Integer upvotes;

    /**
     * The downvotes for the issue.
     */
    @JsonProperty("downvotes")
    private Integer downvotes;

    /**
     * The number of merge requests associated with the issue.
     */
    @JsonProperty("merge_requests_count")
    private Integer mergeRequestsCount;

    /**
     * Indicates whether the issue has tasks.
     */
    @JsonProperty("has_tasks")
    private Boolean hasTasks;

    /**
     * The task status associated with the issue.
     */
    @JsonProperty("task_status")
    private String taskStatus;

    /**
     * Indicates whether the issue has been imported.
     */
    @JsonProperty("imported")
    private Boolean imported;

    /**
     * The source from which the issue was imported.
     */
    @JsonProperty("imported_from")
    private String importedFrom;

    /**
     * The iteration associated with the issue.
     */
    @JsonProperty("iteration")
    private Iteration iteration;

    /**
     * The task completion status associated with the issue.
     */
    @JsonProperty("task_completion_status")
    private TaskCompletionStatus taskCompletionStatus;

    /**
     * The health status associated with the issue.
     */
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
