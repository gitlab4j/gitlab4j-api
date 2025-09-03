package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PushRules implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("commit_message_regex")
    private String commitMessageRegex;

    @JsonProperty("commit_message_negative_regex")
    private String commitMessageNegativeRegex;

    @JsonProperty("branch_name_regex")
    private String branchNameRegex;

    @JsonProperty("deny_delete_tag")
    private Boolean denyDeleteTag;

    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    @JsonProperty("member_check")
    private Boolean memberCheck;

    @JsonProperty("prevent_secrets")
    private Boolean preventSecrets;

    @JsonProperty("author_email_regex")
    private String authorEmailRegex;

    @JsonProperty("file_name_regex")
    private String fileNameRegex;

    @JsonProperty("max_file_size")
    private Integer maxFileSize;

    @JsonProperty("commit_committer_check")
    private Boolean commitCommitterCheck;

    @JsonProperty("commit_committer_name_check")
    private Boolean commitCommitterNameCheck;

    @JsonProperty("reject_unsigned_commits")
    private Boolean rejectUnsignedCommits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public PushRules withProjectId(Long projectId) {
        this.projectId = projectId;
        return (this);
    }

    public String getCommitMessageRegex() {
        return commitMessageRegex;
    }

    public void setCommitMessageRegex(String commitMessageRegex) {
        this.commitMessageRegex = commitMessageRegex;
    }

    public PushRules withCommitMessageRegex(String commitMessageRegex) {
        this.commitMessageRegex = commitMessageRegex;
        return (this);
    }

    public String getCommitMessageNegativeRegex() {
        return commitMessageNegativeRegex;
    }

    public void setCommitMessageNegativeRegex(String commitMessageNegativeRegex) {
        this.commitMessageNegativeRegex = commitMessageNegativeRegex;
    }

    public PushRules withCommitMessageNegativeRegex(String commitMessageNegativeRegex) {
        this.commitMessageNegativeRegex = commitMessageNegativeRegex;
        return (this);
    }

    public String getBranchNameRegex() {
        return branchNameRegex;
    }

    public void setBranchNameRegex(String branchNameRegex) {
        this.branchNameRegex = branchNameRegex;
    }

    public PushRules withBranchNameRegex(String branchNameRegex) {
        this.branchNameRegex = branchNameRegex;
        return (this);
    }

    public Boolean getDenyDeleteTag() {
        return denyDeleteTag;
    }

    public void setDenyDeleteTag(Boolean denyDeleteTag) {
        this.denyDeleteTag = denyDeleteTag;
    }

    public PushRules withDenyDeleteTag(Boolean denyDeleteTag) {
        this.denyDeleteTag = denyDeleteTag;
        return (this);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getMemberCheck() {
        return memberCheck;
    }

    public void setMemberCheck(Boolean memberCheck) {
        this.memberCheck = memberCheck;
    }

    public PushRules withMemberCheck(Boolean memberCheck) {
        this.memberCheck = memberCheck;
        return (this);
    }

    public Boolean getPreventSecrets() {
        return preventSecrets;
    }

    public void setPreventSecrets(Boolean preventSecrets) {
        this.preventSecrets = preventSecrets;
    }

    public PushRules withPreventSecrets(Boolean preventSecrets) {
        this.preventSecrets = preventSecrets;
        return (this);
    }

    public String getAuthorEmailRegex() {
        return authorEmailRegex;
    }

    public void setAuthorEmailRegex(String authorEmailRegex) {
        this.authorEmailRegex = authorEmailRegex;
    }

    public PushRules withAuthorEmailRegex(String authorEmailRegex) {
        this.authorEmailRegex = authorEmailRegex;
        return (this);
    }

    public String getFileNameRegex() {
        return fileNameRegex;
    }

    public void setFileNameRegex(String fileNameRegex) {
        this.fileNameRegex = fileNameRegex;
    }

    public PushRules withFileNameRegex(String fileNameRegex) {
        this.fileNameRegex = fileNameRegex;
        return (this);
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public PushRules withMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
        return (this);
    }

    public Boolean getCommitCommitterCheck() {
        return commitCommitterCheck;
    }

    public Boolean getCommitCommitterNameCheck() {
        return commitCommitterNameCheck;
    }

    public void setCommitCommitterNameCheck(Boolean commitCommitterNameCheck) {
        this.commitCommitterNameCheck = commitCommitterNameCheck;
    }

    public void setCommitCommitterCheck(Boolean commitCommitterCheck) {
        this.commitCommitterCheck = commitCommitterCheck;
    }

    public PushRules withCommitCommitterCheck(Boolean commitCommitterCheck) {
        this.commitCommitterCheck = commitCommitterCheck;
        return (this);
    }

    public Boolean getRejectUnsignedCommits() {
        return rejectUnsignedCommits;
    }

    public void setRejectUnsignedCommits(Boolean rejectUnsignedCommits) {
        this.rejectUnsignedCommits = rejectUnsignedCommits;
    }

    public PushRules withRejectUnsignedCommits(Boolean rejectUnsignedCommits) {
        this.rejectUnsignedCommits = rejectUnsignedCommits;
        return (this);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
