package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class GroupHook implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the webhook.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The URL of the webhook.
     */
    @JsonProperty("url")
    private String url;

    /**
     * The name of the webhook.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The description of the webhook.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The group ID associated with the webhook.
     */
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * Indicates if push events should be sent.
     */
    @JsonProperty("push_events")
    private Boolean pushEvents;

    /**
     * The branch filter for push events.
     */
    @JsonProperty("push_events_branch_filter")
    private String pushEventsBranchFilter;

    /**
     * The strategy for filtering branches.
     */
    @JsonProperty("branch_filter_strategy")
    private String branchFilterStrategy;

    /**
     * Indicates if issue events should be sent.
     */
    @JsonProperty("issues_events")
    private Boolean issuesEvents;

    /**
     * Indicates if confidential issues events should be sent.
     */
    @JsonProperty("confidential_issues_events")
    private Boolean confidentialIssuesEvents;

    /**
     * Indicates if merge request events should be sent.
     */
    @JsonProperty("merge_requests_events")
    private Boolean mergeRequestsEvents;

    /**
     * Indicates if tag push events should be sent.
     */
    @JsonProperty("tag_push_events")
    private Boolean tagPushEvents;

    /**
     * Indicates if note events should be sent.
     */
    @JsonProperty("note_events")
    private Boolean noteEvents;

    /**
     * Indicates if confidential note events should be sent.
     */
    @JsonProperty("confidential_note_events")
    private Boolean confidentialNoteEvents;

    /**
     * Indicates if job events should be sent.
     */
    @JsonProperty("job_events")
    private Boolean jobEvents;

    /**
     * Indicates if pipeline events should be sent.
     */
    @JsonProperty("pipeline_events")
    private Boolean pipelineEvents;

    /**
     * Indicates if wiki page events should be sent.
     */
    @JsonProperty("wiki_page_events")
    private Boolean wikiPageEvents;

    /**
     * Indicates if deployment events should be sent.
     */
    @JsonProperty("deployment_events")
    private Boolean deploymentEvents;

    /**
     * Indicates if feature flag events should be sent.
     */
    @JsonProperty("feature_flag_events")
    private Boolean featureFlagEvents;

    /**
     * Indicates if release events should be sent.
     */
    @JsonProperty("releases_events")
    private Boolean releasesEvents;

    /**
     * Indicates if subgroup events should be sent.
     */
    @JsonProperty("subgroup_events")
    private Boolean subgroupEvents;

    /**
     * Indicates if member events should be sent.
     */
    @JsonProperty("member_events")
    private Boolean memberEvents;

    /**
     * Indicates if SSL verification should be enabled for the webhook.
     */
    @JsonProperty("enable_ssl_verification")
    private Boolean enableSslVerification;

    /**
     * The alert status of the webhook.
     */
    @JsonProperty("alert_status")
    private String alertStatus;

    /**
     * The date until the webhook is disabled. Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("disabled_until")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date disabledUntil;

    /**
     * Indicates if repository update events should be sent.
     */
    @JsonProperty("repository_update_events")
    private Boolean repositoryUpdateEvents;

    /**
     * The creation date of the webhook. Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    /**
     * Indicates if resource access token events should be sent.
     */
    @JsonProperty("resource_access_token_events")
    private Boolean resourceAccessTokenEvents;

    /**
     * A custom webhook template to use for the webhook.
     */
    @JsonProperty("custom_webhook_template")
    private String customWebhookTemplate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getPushEvents() {
        return pushEvents;
    }

    public void setPushEvents(Boolean pushEvents) {
        this.pushEvents = pushEvents;
    }

    public String getPushEventsBranchFilter() {
        return pushEventsBranchFilter;
    }

    public void setPushEventsBranchFilter(String pushEventsBranchFilter) {
        this.pushEventsBranchFilter = pushEventsBranchFilter;
    }

    public String getBranchFilterStrategy() {
        return branchFilterStrategy;
    }

    public void setBranchFilterStrategy(String branchFilterStrategy) {
        this.branchFilterStrategy = branchFilterStrategy;
    }

    public Boolean getIssuesEvents() {
        return issuesEvents;
    }

    public void setIssuesEvents(Boolean issuesEvents) {
        this.issuesEvents = issuesEvents;
    }

    public Boolean getConfidentialIssuesEvents() {
        return confidentialIssuesEvents;
    }

    public void setConfidentialIssuesEvents(Boolean confidentialIssuesEvents) {
        this.confidentialIssuesEvents = confidentialIssuesEvents;
    }

    public Boolean getMergeRequestsEvents() {
        return mergeRequestsEvents;
    }

    public void setMergeRequestsEvents(Boolean mergeRequestsEvents) {
        this.mergeRequestsEvents = mergeRequestsEvents;
    }

    public Boolean getTagPushEvents() {
        return tagPushEvents;
    }

    public void setTagPushEvents(Boolean tagPushEvents) {
        this.tagPushEvents = tagPushEvents;
    }

    public Boolean getNoteEvents() {
        return noteEvents;
    }

    public void setNoteEvents(Boolean noteEvents) {
        this.noteEvents = noteEvents;
    }

    public Boolean getConfidentialNoteEvents() {
        return confidentialNoteEvents;
    }

    public void setConfidentialNoteEvents(Boolean confidentialNoteEvents) {
        this.confidentialNoteEvents = confidentialNoteEvents;
    }

    public Boolean getJobEvents() {
        return jobEvents;
    }

    public void setJobEvents(Boolean jobEvents) {
        this.jobEvents = jobEvents;
    }

    public Boolean getPipelineEvents() {
        return pipelineEvents;
    }

    public void setPipelineEvents(Boolean pipelineEvents) {
        this.pipelineEvents = pipelineEvents;
    }

    public Boolean getWikiPageEvents() {
        return wikiPageEvents;
    }

    public void setWikiPageEvents(Boolean wikiPageEvents) {
        this.wikiPageEvents = wikiPageEvents;
    }

    public Boolean getDeploymentEvents() {
        return deploymentEvents;
    }

    public void setDeploymentEvents(Boolean deploymentEvents) {
        this.deploymentEvents = deploymentEvents;
    }

    public Boolean getFeatureFlagEvents() {
        return featureFlagEvents;
    }

    public void setFeatureFlagEvents(Boolean featureFlagEvents) {
        this.featureFlagEvents = featureFlagEvents;
    }

    public Boolean getReleasesEvents() {
        return releasesEvents;
    }

    public void setReleasesEvents(Boolean releasesEvents) {
        this.releasesEvents = releasesEvents;
    }

    public Boolean getSubgroupEvents() {
        return subgroupEvents;
    }

    public void setSubgroupEvents(Boolean subgroupEvents) {
        this.subgroupEvents = subgroupEvents;
    }

    public Boolean getMemberEvents() {
        return memberEvents;
    }

    public void setMemberEvents(Boolean memberEvents) {
        this.memberEvents = memberEvents;
    }

    public Boolean getEnableSslVerification() {
        return enableSslVerification;
    }

    public void setEnableSslVerification(Boolean enableSslVerification) {
        this.enableSslVerification = enableSslVerification;
    }

    public String getAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }

    public Date getDisabledUntil() {
        return disabledUntil;
    }

    public void setDisabledUntil(Date disabledUntil) {
        this.disabledUntil = disabledUntil;
    }

    public Boolean getRepositoryUpdateEvents() {
        return repositoryUpdateEvents;
    }

    public void setRepositoryUpdateEvents(Boolean repositoryUpdateEvents) {
        this.repositoryUpdateEvents = repositoryUpdateEvents;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getResourceAccessTokenEvents() {
        return resourceAccessTokenEvents;
    }

    public void setResourceAccessTokenEvents(Boolean resourceAccessTokenEvents) {
        this.resourceAccessTokenEvents = resourceAccessTokenEvents;
    }

    public String getCustomWebhookTemplate() {
        return customWebhookTemplate;
    }

    public void setCustomWebhookTemplate(String customWebhookTemplate) {
        this.customWebhookTemplate = customWebhookTemplate;
    }
}
