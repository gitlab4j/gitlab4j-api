package org.gitlab4j.api.services;

import org.gitlab4j.api.GitLabApiForm;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SlackService extends NotificationService {
    
    private String defaultChannel;

    /**
     * Get the form data for this service based on it's properties.
     *
     * @return the form data for this service based on it's properties
     */
    @Override
    public GitLabApiForm servicePropertiesForm() {
        GitLabApiForm formData = new GitLabApiForm()
            .withParam("webhook", getWebhook(), true)
            .withParam("username", getUsername())
            .withParam("channel", getDefaultChannel())
            .withParam("notify_only_broken_pipelines", getNotifyOnlyBrokenPipelines())
            .withParam("notify_only_default_branch", getNotifyOnlyDefaultBranch())
            .withParam("push_events", getPushEvents())
            .withParam("issues_events", getIssuesEvents())
            .withParam("confidential_issues_events", getConfidentialIssuesEvents())
            .withParam("merge_requests_events", getMergeRequestsEvents())
            .withParam("tag_push_events", getTagPushEvents())
            .withParam("note_events", getNoteEvents())
            .withParam("confidential_note_events", getConfidentialNoteEvents())
            .withParam("pipeline_events", getPipelineEvents())
            .withParam("wiki_page_events", getWikiPageEvents())
            .withParam("push_channel", getPushChannel())
            .withParam("issue_channel", getIssueChannel())
            .withParam("confidential_issue_channel", getConfidentialIssueChannel())
            .withParam("merge_request_channel", getMergeRequestChannel())
            .withParam("note_channel", getNoteChannel())
            .withParam("confidential_note_channel", getConfidentialNoteChannel())
            .withParam("tag_push_channel", getTagPushChannel())
            .withParam("pipeline_channel", getPipelineChannel())
            .withParam("wiki_page_channel", getWikiPageChannel());
        return formData;
    }

    public SlackService withPushEvents(Boolean pushEvents) {
        return withPushEvents(pushEvents, this);
    }

    public SlackService withIssuesEvents(Boolean issuesEvents) {
        return withIssuesEvents(issuesEvents, this);
    }

    public SlackService withConfidentialIssuesEvents(Boolean confidentialIssuesEvents) {
        return withConfidentialIssuesEvents(confidentialIssuesEvents, this);
    }

    public SlackService withMergeRequestsEvents(Boolean mergeRequestsEvents) {
        return withMergeRequestsEvents(mergeRequestsEvents, this);
    }

    public SlackService withTagPushEvents(Boolean tagPushEvents) {
        return withTagPushEvents(tagPushEvents, this);
    }

    public SlackService withNoteEvents(Boolean noteEvents) {
        return withNoteEvents(noteEvents, this);
    }

    public SlackService withConfidentialNoteEvents(Boolean confidentialNoteEvents) {
        return withConfidentialNoteEvents(confidentialNoteEvents, this);
    }

    public SlackService withPipelineEvents(Boolean pipelineEvents) {
        return withPipelineEvents(pipelineEvents, this);
    }

    public SlackService withWikiPageEvents(Boolean wikiPageEvents) {
        return withWikiPageEvents(wikiPageEvents, this);
    }

    public SlackService withJobEvents(Boolean jobEvents) {
        return withPipelineEvents(jobEvents, this);
    }

    @JsonIgnore
    public String getWebhook() {
        return ((String) getProperty(WEBHOOK_PROP));
    }

    public void setWebhook(String webhook) {
        setProperty(WEBHOOK_PROP, webhook);
    }

    public SlackService withWebhook(String webhook) {
        setWebhook(webhook);
        return (this);
    }

    @JsonIgnore
    public String getUsername() {
        return ((String) getProperty(USERNAME_PROP));
    }

    public void setUsername(String username) {
        setProperty(USERNAME_PROP, username);
    }

    public SlackService withUsername(String username) {
        setUsername(username);
        return (this);
    }

    @JsonIgnore
    public String getDefaultChannel() {
        return (defaultChannel);
    }

    public void setDefaultChannel(String defaultChannel) {
        this.defaultChannel = defaultChannel;
    }

    public SlackService withDefaultChannelk(String defaultChannel) {
        this.defaultChannel = defaultChannel;
        return (this);
    }

    @JsonIgnore
    public Boolean getNotifyOnlyBrokenPipelines() {
        return ((Boolean) getProperty(NOTIFY_ONLY_BROKEN_PIPELINES_PROP, Boolean.FALSE));
    }

    public void setNotifyOnlyBrokenPipelines(Boolean notifyOnlyBrokenPipelines) {
        setProperty(NOTIFY_ONLY_BROKEN_PIPELINES_PROP, notifyOnlyBrokenPipelines);
    }

    public SlackService withNotifyOnlyBrokenPipelines(Boolean notifyOnlyBrokenPipelines) {
        setNotifyOnlyBrokenPipelines(notifyOnlyBrokenPipelines);
        return (this);
    }

    @JsonIgnore
    @Deprecated
    public Boolean getNotifyOnlyDefaultBranch() {
        return ((Boolean) getProperty(NOTIFY_ONLY_DEFAULT_BRANCH_PROP, Boolean.FALSE));
    }

    /**
     * @deprecated use {@link SlackService#setBranchesToBeNotified(BranchesToBeNotified)}
     * @param notifyOnlyDefaultBranch
     */
    @Deprecated
    public void setNotifyOnlyDefaultBranch(Boolean notifyOnlyDefaultBranch) {
        setProperty(NOTIFY_ONLY_DEFAULT_BRANCH_PROP, notifyOnlyDefaultBranch);
    }

    /**
     * @deprecated use {@link SlackService#withBranchesToBeNotified(BranchesToBeNotified)}
     * @param notifyOnlyDefaultBranch
     */
    @Deprecated
    public SlackService withNotifyOnlyDefaultBranch(Boolean notifyOnlyDefaultBranch) {
        setNotifyOnlyDefaultBranch(notifyOnlyDefaultBranch);
        return (this);
    }

    @JsonIgnore
    public BranchesToBeNotified getBranchesToBeNotified() {
        String branchesToBeNotified = getProperty(BRANCHES_TO_BE_NOTIFIED_PROP);

        if (branchesToBeNotified == null || branchesToBeNotified.isEmpty()) {
            return null;
        }

        return (BranchesToBeNotified.valueOf(branchesToBeNotified.toUpperCase()));
    }

    public void setBranchesToBeNotified(BranchesToBeNotified branchesToBeNotified) {
        setProperty(BRANCHES_TO_BE_NOTIFIED_PROP, branchesToBeNotified.toString());
    }

    public SlackService withBranchesToBeNotified(BranchesToBeNotified branchesToBeNotified) {
        setBranchesToBeNotified(branchesToBeNotified);
        return this;
    }

    @JsonIgnore
    public String getPushChannel() {
        return ((String) getProperty(PUSH_CHANNEL_PROP));
    }

    public void setPushChannel(String pushChannel) {
        setProperty(PUSH_CHANNEL_PROP, pushChannel);
    }

    public SlackService withPushChannel(String pushChannel) {
        setPushChannel(pushChannel);
        return (this);
    }

    @JsonIgnore
    public String getIssueChannel() {
        return ((String) getProperty(ISSUE_CHANNEL_PROP));
    }

    public void setIssueChannel(String issueChannel) {
        setProperty(ISSUE_CHANNEL_PROP, issueChannel);
    }

    public SlackService withIssueChannel(String issueChannel) {
        setIssueChannel(issueChannel);
        return (this);
    }

    @JsonIgnore
    public String getConfidentialIssueChannel() {
        return ((String) getProperty(CONFIDENTIAL_ISSUE_CHANNEL_PROP));
    }

    public void setConfidentialIssueChannel(String confidentialIssueChannel) {
        setProperty(CONFIDENTIAL_ISSUE_CHANNEL_PROP, confidentialIssueChannel);
    }

    public SlackService withConfidentialIssueChannel(String confidentialIssueChannel) {
        setConfidentialIssueChannel(confidentialIssueChannel);
        return (this);
    }

    @JsonIgnore
    public String getMergeRequestChannel() {
        return ((String) getProperty(MERGE_REQUEST_CHANNEL_PROP));
    }

    public void setMergeRequestChannel(String mergeRequestChannel) {
        setProperty(MERGE_REQUEST_CHANNEL_PROP, mergeRequestChannel);
    }

    public SlackService withMergeRequestChannel(String mergeRequestChannel) {
        setMergeRequestChannel(mergeRequestChannel);
        return (this);
    }

    @JsonIgnore
    public String getNoteChannel() {
        return ((String) getProperty(NOTE_CHANNEL_PROP));
    }

    public void setNoteChannel(String noteChannel) {
        setProperty(NOTE_CHANNEL_PROP, noteChannel);
    }

    public SlackService withNoteChannel(String noteChannel) {
        setNoteChannel(noteChannel);
        return (this);
    }
    
    @JsonIgnore
    public String getConfidentialNoteChannel() {
        return ((String) getProperty(CONFIDENTIAL_NOTE_CHANNEL_PROP));
    }

    public void setConfidentialNoteChannel(String noteChannel) {
        setProperty(NOTE_CHANNEL_PROP, noteChannel);
    }

    public SlackService withConfidentialNoteChannel(String confidentialNoteChannel) {
        setConfidentialNoteChannel(confidentialNoteChannel);
        return (this);
    }

    @JsonIgnore
    public String getTagPushChannel() {
        return ((String) getProperty(TAG_PUSH_CHANNEL_PROP));
    }

    public void setTagPushChannel(String tagPushChannel) {
        setProperty(TAG_PUSH_CHANNEL_PROP, tagPushChannel);
    }

    public SlackService withTagPushChannel(String tagPushChannel) {
        setTagPushChannel(tagPushChannel);
        return (this);
    }

    @JsonIgnore
    public String getPipelineChannel() {
        return ((String) getProperty(PIPELINE_CHANNEL_PROP));
    }

    public void setPipelineChannel(String pipelineChannel) {
        setProperty(PIPELINE_CHANNEL_PROP, pipelineChannel);
    }

    public SlackService withPipelineChannel(String pipelineChannel) {
        setPipelineChannel(pipelineChannel);
        return (this);
    }

    @JsonIgnore
    public String getWikiPageChannel() {
        return ((String) getProperty(WIKI_PAGE_CHANNEL_PROP));
    }

    public void setWikiPageChannel(String wikiPageChannel) {
        setProperty(WIKI_PAGE_CHANNEL_PROP, wikiPageChannel);
    }

    public SlackService withWikiPageChannel(String wikiPageChannel) {
        setWikiPageChannel(wikiPageChannel);
        return (this);
    }
}
