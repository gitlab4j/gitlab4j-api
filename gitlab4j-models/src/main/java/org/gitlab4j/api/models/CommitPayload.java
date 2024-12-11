package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitPayload implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The name of the branch for the commit.
     */
    @JsonProperty("branch")
    private String branch;

    /**
     * The commit message.
     */
    @JsonProperty("commit_message")
    private String commitMessage;

    /**
     * The starting branch for the commit.
     */
    @JsonProperty("start_branch")
    private String startBranch;

    /**
     * The starting SHA for the commit.
     */
    @JsonProperty("start_sha")
    private String startSha;

    /**
     * The starting project associated with the commit.
     */
    @JsonProperty("start_project")
    private Object startProject;

    /**
     * The list of commit actions associated with the commit.
     */
    @JsonProperty("actions")
    private List<CommitAction> actions;

    /**
     * The email of the commit author.
     */
    @JsonProperty("author_email")
    private String authorEmail;

    /**
     * The name of the commit author.
     */
    @JsonProperty("author_name")
    private String authorName;

    /**
     * Indicates whether stats are included in the commit.
     */
    @JsonProperty("stats")
    private Boolean stats;

    /**
     * Indicates whether the commit is forced.
     */
    @JsonProperty("force")
    private Boolean force;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public CommitPayload withBranch(String branch) {
        this.branch = branch;
        return (this);
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    public CommitPayload withCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
        return (this);
    }

    public String getStartBranch() {
        return startBranch;
    }

    public void setStartBranch(String startBranch) {
        this.startBranch = startBranch;
    }

    public CommitPayload withStartBranch(String startBranch) {
        this.startBranch = startBranch;
        return (this);
    }

    public String getStartSha() {
        return startSha;
    }

    public void setStartSha(String startSha) {
        this.startSha = startSha;
    }

    public CommitPayload withStartSha(String startSha) {
        this.startSha = startSha;
        return (this);
    }

    public Object getStartProject() {
        return startProject;
    }

    public void setStartProject(Object startProject) {
        this.startProject = startProject;
    }

    public CommitPayload withStartProject(Object startProject) {
        this.startProject = startProject;
        return (this);
    }

    public List<CommitAction> getActions() {
        return actions;
    }

    public void setActions(List<CommitAction> actions) {
        this.actions = actions;
    }

    public CommitPayload withActions(List<CommitAction> actions) {
        this.actions = actions;
        return (this);
    }

    public CommitPayload withAction(CommitAction action) {

        if (actions == null) {
            actions = new ArrayList<>();
        }

        actions.add(action);
        return (this);
    }

    public CommitPayload withAction(CommitAction.Action action, String filePath) {
        return (withAction(action, null, filePath));
    }

    public CommitPayload withAction(CommitAction.Action action, String content, String filePath) {
        CommitAction commitAction =
                new CommitAction().withAction(action).withContent(content).withFilePath(filePath);

        return (withAction(commitAction));
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public CommitPayload withAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return (this);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public CommitPayload withAuthorName(String authorName) {
        this.authorName = authorName;
        return (this);
    }

    public Boolean getStats() {
        return stats;
    }

    public void setStats(Boolean stats) {
        this.stats = stats;
    }

    public CommitPayload withStats(Boolean stats) {
        this.stats = stats;
        return (this);
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public CommitPayload withForce(Boolean force) {
        this.force = force;
        return (this);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
