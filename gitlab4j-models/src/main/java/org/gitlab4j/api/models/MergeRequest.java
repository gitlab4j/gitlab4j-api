package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MergeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("allow_collaboration")
    private Boolean allowCollaboration;

    @JsonProperty("allow_maintainer_to_push")
    private Boolean allowMaintainerToPush;

    @JsonProperty("approvals_before_merge")
    private Integer approvalsBeforeMerge;

    @JsonProperty("assignee")
    private Assignee assignee;

    @JsonProperty("assignees")
    private List<Assignee> assignees;

    @JsonProperty("reviewers")
    private List<Reviewer> reviewers;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("blocking_discussions_resolved")
    private Boolean blockingDiscussionsResolved;

    @JsonProperty("changes")
    private List<Diff> changes;

    @JsonProperty("changes_count")
    private String changesCount;

    @JsonProperty("closed_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date closedAt;

    @JsonProperty("closed_by")
    private Participant closedBy;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("discussion_locked")
    private Boolean discussionLocked;

    @JsonProperty("diverged_commits_count")
    private Integer divergedCommitsCount;

    @JsonProperty("downvotes")
    private Integer downvotes;

    @JsonProperty("draft")
    private Boolean draft;

    @JsonProperty("force_remove_source_branch")
    private Boolean forceRemoveSourceBranch;

    @JsonProperty("has_conflicts")
    private Boolean hasConflicts;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("iid")
    private Long iid;

    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("latest_build_finished_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date latestBuildFinishedAt;

    @JsonProperty("latest_build_started_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date latestBuildStartedAt;

    @JsonProperty("merge_commit_sha")
    private String mergeCommitSha;

    @JsonProperty("squash_commit_sha")
    private String squashCommitSha;

    /**
     * @deprecated since 15.6, use {@link #detailedMergeStatus} instead.
     * see https://docs.gitlab.com/ee/update/deprecations.html#merge_status-api-field
     */
    @Deprecated
    @JsonProperty("merge_status")
    private String mergeStatus;

    @JsonProperty("detailed_merge_status")
    private String detailedMergeStatus;

    @JsonProperty("merged_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date mergedAt;

    /**
     * @deprecated since 14.7, use {@link #mergeUser} instead.
     * see https://docs.gitlab.com/ee/update/deprecations.html#merged_by-api-field
     */
    @Deprecated
    @JsonProperty("merged_by")
    private Participant mergedBy;

    @JsonProperty("merge_user")
    private Participant mergeUser;

    @JsonProperty("merge_when_pipeline_succeeds")
    private Boolean mergeWhenPipelineSucceeds;

    @JsonProperty("merge_error")
    private String mergeError;

    @JsonProperty("milestone")
    private Milestone milestone;

    @JsonProperty("pipeline")
    private Pipeline pipeline;

    @JsonProperty("head_pipeline")
    private Pipeline headPipeline;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("should_remove_source_branch")
    private Boolean shouldRemoveSourceBranch;

    @JsonProperty("source_branch")
    private String sourceBranch;

    @JsonProperty("source_project_id")
    private Long sourceProjectId;

    @JsonProperty("squash")
    private Boolean squash;

    @JsonProperty("state")
    private String state;

    @JsonProperty("subscribed")
    private Boolean subscribed;

    @JsonProperty("target_branch")
    private String targetBranch;

    @JsonProperty("target_project_id")
    private Long targetProjectId;

    @JsonProperty("task_completion_status")
    private TaskCompletionStatus taskCompletionStatus;

    @JsonProperty("references")
    private References references;

    @JsonProperty("time_stats")
    private TimeStats timeStats;

    @JsonProperty("title")
    private String title;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updatedAt;

    @JsonProperty("upvotes")
    private Integer upvotes;

    @JsonProperty("user_notes_count")
    private Integer userNotesCount;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("work_in_progress")
    private Boolean workInProgress;

    @JsonProperty("diff_refs")
    private DiffRef diffRefs;

    @JsonProperty("rebase_in_progress")
    private Boolean rebaseInProgress;

    // Approval-specific fields
    @JsonProperty("approvals_required")
    private Integer approvalsRequired;

    @JsonProperty("approvals_left")
    private Integer approvalsLeft;

    @JsonProperty("approved_by")
    @JsonSerialize(using = JacksonJson.UserListSerializer.class)
    @JsonDeserialize(using = JacksonJson.UserListDeserializer.class)
    private List<User> approvedBy;

    public Boolean getAllowCollaboration() {
        return allowCollaboration;
    }

    public void setAllowCollaboration(Boolean allowCollaboration) {
        this.allowCollaboration = allowCollaboration;
    }

    public Boolean getAllowMaintainerToPush() {
        return allowMaintainerToPush;
    }

    public void setAllowMaintainerToPush(Boolean allowMaintainerToPush) {
        this.allowMaintainerToPush = allowMaintainerToPush;
    }

    public Integer getApprovalsBeforeMerge() {
        return approvalsBeforeMerge;
    }

    public void setApprovalsBeforeMerge(Integer approvalsBeforeMerge) {
        this.approvalsBeforeMerge = approvalsBeforeMerge;
    }

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

    public Boolean getBlockingDiscussionsResolved() {
        return blockingDiscussionsResolved;
    }

    public void setBlockingDiscussionsResolved(Boolean blockingDiscussionsResolved) {
        this.blockingDiscussionsResolved = blockingDiscussionsResolved;
    }

    public List<Diff> getChanges() {
        return changes;
    }

    public void setChanges(List<Diff> changes) {
        this.changes = changes;
    }

    public String getChangesCount() {
        return changesCount;
    }

    public void setChangesCount(String changesCount) {
        this.changesCount = changesCount;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public Participant getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Participant closedBy) {
        this.closedBy = closedBy;
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

    public Boolean getDiscussionLocked() {
        return discussionLocked;
    }

    public void setDiscussionLocked(Boolean discussionLocked) {
        this.discussionLocked = discussionLocked;
    }

    public Integer getDivergedCommitsCount() {
        return divergedCommitsCount;
    }

    public void setDivergedCommitsCount(Integer divergedCommitsCount) {
        this.divergedCommitsCount = divergedCommitsCount;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public Boolean getForceRemoveSourceBranch() {
        return forceRemoveSourceBranch;
    }

    public void setForceRemoveSourceBranch(Boolean forceRemoveSourceBranch) {
        this.forceRemoveSourceBranch = forceRemoveSourceBranch;
    }

    public Boolean getHasConflicts() {
        return hasConflicts;
    }

    public void setHasConflicts(Boolean hasConflicts) {
        this.hasConflicts = hasConflicts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getLatestBuildFinishedAt() {
        return latestBuildFinishedAt;
    }

    public void setLatestBuildFinishedAt(Date latestBuildFinishedAt) {
        this.latestBuildFinishedAt = latestBuildFinishedAt;
    }

    public Date getLatestBuildStartedAt() {
        return latestBuildStartedAt;
    }

    public void setLatestBuildStartedAt(Date latestBuildStartedAt) {
        this.latestBuildStartedAt = latestBuildStartedAt;
    }

    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    public void setMergeCommitSha(String mergeCommitSha) {
        this.mergeCommitSha = mergeCommitSha;
    }

    public String getSquashCommitSha() {
        return squashCommitSha;
    }

    public void setSquashCommitSha(String squashCommitSha) {
        this.squashCommitSha = squashCommitSha;
    }

    /**
     * @deprecated since 15.6, use {@link #getDetailedMergeStatus()} instead.
     */
    @Deprecated
    public String getMergeStatus() {
        return mergeStatus;
    }

    /**
     * @deprecated since 15.6, use {@link #setDetailedMergeStatus(String)} instead.
     */
    @Deprecated
    public void setMergeStatus(String mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    public String getDetailedMergeStatus() {
        return detailedMergeStatus;
    }

    public void setDetailedMergeStatus(String detailedMergeStatus) {
        this.detailedMergeStatus = detailedMergeStatus;
    }

    public Date getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(Date mergedAt) {
        this.mergedAt = mergedAt;
    }

    /**
     * @deprecated since 14.7, use {@link #getMergeUser()} instead.
     * see https://docs.gitlab.com/ee/update/deprecations.html#merged_by-api-field
     */
    @Deprecated
    public Participant getMergedBy() {
        return mergedBy;
    }

    /**
     * @deprecated since 14.7, use {@link #setMergeUser(Participant)} instead.
     * see https://docs.gitlab.com/ee/update/deprecations.html#merged_by-api-field
     */
    @Deprecated
    public void setMergedBy(Participant mergedBy) {
        this.mergedBy = mergedBy;
    }

    public Participant getMergeUser() {
        return mergeUser;
    }

    public void setMergeUser(Participant mergeUser) {
        this.mergeUser = mergeUser;
    }

    public Boolean getMergeWhenPipelineSucceeds() {
        return mergeWhenPipelineSucceeds;
    }

    public void setMergeWhenPipelineSucceeds(Boolean mergeWhenPipelineSucceeds) {
        this.mergeWhenPipelineSucceeds = mergeWhenPipelineSucceeds;
    }

    public String getMergeError() {
        return mergeError;
    }

    public void setMergeError(String mergeError) {
        this.mergeError = mergeError;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Pipeline getHeadPipeline() {
        return headPipeline;
    }

    public void setHeadPipeline(Pipeline headPipeline) {
        this.headPipeline = headPipeline;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Boolean getShouldRemoveSourceBranch() {
        return shouldRemoveSourceBranch;
    }

    public void setShouldRemoveSourceBranch(Boolean shouldRemoveSourceBranch) {
        this.shouldRemoveSourceBranch = shouldRemoveSourceBranch;
    }

    public String getSourceBranch() {
        return sourceBranch;
    }

    public void setSourceBranch(String sourceBranch) {
        this.sourceBranch = sourceBranch;
    }

    public Long getSourceProjectId() {
        return sourceProjectId;
    }

    public void setSourceProjectId(Long sourceProjectId) {
        this.sourceProjectId = sourceProjectId;
    }

    public Boolean getSquash() {
        return squash;
    }

    public void setSquash(Boolean squash) {
        this.squash = squash;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getTargetBranch() {
        return targetBranch;
    }

    public void setTargetBranch(String targetBranch) {
        this.targetBranch = targetBranch;
    }

    public Long getTargetProjectId() {
        return targetProjectId;
    }

    public void setTargetProjectId(Long targetProjectId) {
        this.targetProjectId = targetProjectId;
    }

    public TaskCompletionStatus getTaskCompletionStatus() {
        return taskCompletionStatus;
    }

    public void setTaskCompletionStatus(TaskCompletionStatus taskCompletionStatus) {
        this.taskCompletionStatus = taskCompletionStatus;
    }

    public References getReferences() {
        return references;
    }

    public void setReferences(References references) {
        this.references = references;
    }

    public TimeStats getTimeStats() {
        return timeStats;
    }

    public void setTimeStats(TimeStats timeStats) {
        this.timeStats = timeStats;
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

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
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

    public Boolean getWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(Boolean workInProgress) {
        this.workInProgress = workInProgress;
    }

    /**
     * Get the number of approvals required for the merge request.
     *
     * NOTE: This property will only be used when listing, approiving, or unapproving a merge request.
     *
     * @return the number of approvals required for the merge request
     */
    public Integer getApprovalsRequired() {
        return approvalsRequired;
    }

    /**
     * Set the number of approvals required for the merge request.
     *
     * NOTE: This property will only be used when listing, approiving, or unapproving a merge request.
     *
     * @param approvalsRequired the number of approvals required for the merge request
     */
    public void setApprovalsRequired(Integer approvalsRequired) {
        this.approvalsRequired = approvalsRequired;
    }

    /**
     * Get the number of approvals left for the merge request.
     *
     * NOTE: This property will only be used when listing, approiving, or unapproving a merge request.
     *
     * @return the number of approvals left for the merge request
     */
    public Integer getApprovalsLeft() {
        return approvalsLeft;
    }

    /**
     * Set the number of approvals missing for the merge request.
     *
     * NOTE: This property will only be used when listing, approiving, or unapproving a merge request.
     *
     * @param approvalsLeft the number of approvals missing for the merge request
     */
    public void setApprovalsLeft(Integer approvalsLeft) {
        this.approvalsLeft = approvalsLeft;
    }

    /**
     * Get the list of users that have approved the merge request.
     *
     * NOTE: This property will only be used when listing, approiving, or unapproving a merge request.
     *
     * @return the list of users that have approved the merge request
     */
    public List<User> getApprovedBy() {
        return approvedBy;
    }

    /**
     * Set the list of users that have approved the merge request.
     *
     * NOTE: This property will only be used when listing, approiving, or unapproving a merge request.
     *
     * @param approvedBy the list of users that have approved the merge request
     */
    public void setApprovedBy(List<User> approvedBy) {
        this.approvedBy = approvedBy;
    }

    public DiffRef getDiffRefs() {
        return diffRefs;
    }

    public void setDiffRefs(final DiffRef diffRefs) {
        this.diffRefs = diffRefs;
    }

    public Boolean getRebaseInProgress() {
        return rebaseInProgress;
    }

    public void setRebaseInProgress(Boolean rebaseInProgress) {
        this.rebaseInProgress = rebaseInProgress;
    }

    public static final boolean isValid(MergeRequest mergeRequest) {
        return (mergeRequest != null && mergeRequest.getId() != null);
    }

    public List<Reviewer> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
