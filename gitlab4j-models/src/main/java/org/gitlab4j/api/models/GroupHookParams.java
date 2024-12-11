package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.GitLabForm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupHookParams implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * The authentication token for the webhook.
     */
    @JsonProperty("token")
    private String token;

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

    public GitLabForm getForm() {

        return new GitLabForm()
                .withParam("url", url, true)
                .withParam("name", name)
                .withParam("description", description)
                .withParam("push_events", pushEvents)
                .withParam("push_events_branch_filter", pushEventsBranchFilter)
                .withParam("branch_filter_strategy", branchFilterStrategy)
                .withParam("issues_events", issuesEvents)
                .withParam("confidential_issues_events", confidentialIssuesEvents)
                .withParam("merge_requests_events", mergeRequestsEvents)
                .withParam("tag_push_events", tagPushEvents)
                .withParam("note_events", noteEvents)
                .withParam("confidential_note_events", confidentialNoteEvents)
                .withParam("job_events", jobEvents)
                .withParam("pipeline_events", pipelineEvents)
                .withParam("wiki_page_events", wikiPageEvents)
                .withParam("deployment_events", deploymentEvents)
                .withParam("feature_flag_events", featureFlagEvents)
                .withParam("releases_events", releasesEvents)
                .withParam("subgroup_events", subgroupEvents)
                .withParam("member_events", memberEvents)
                .withParam("enable_ssl_verification", enableSslVerification)
                .withParam("token", token)
                .withParam("resource_access_token_events", resourceAccessTokenEvents)
                .withParam("custom_webhook_template", customWebhookTemplate);
    }

    public GroupHookParams setBranchFilterStrategy(String branchFilterStrategy) {
        this.branchFilterStrategy = branchFilterStrategy;
        return this;
    }

    public GroupHookParams setUrl(String url) {
        this.url = url;
        return this;
    }

    public GroupHookParams setName(String name) {
        this.name = name;
        return this;
    }

    public GroupHookParams setDescription(String description) {
        this.description = description;
        return this;
    }

    public GroupHookParams setPushEvents(Boolean pushEvents) {
        this.pushEvents = pushEvents;
        return this;
    }

    public GroupHookParams setPushEventsBranchFilter(String pushEventsBranchFilter) {
        this.pushEventsBranchFilter = pushEventsBranchFilter;
        return this;
    }

    public GroupHookParams setIssuesEvents(Boolean issuesEvents) {
        this.issuesEvents = issuesEvents;
        return this;
    }

    public GroupHookParams setConfidentialIssuesEvents(Boolean confidentialIssuesEvents) {
        this.confidentialIssuesEvents = confidentialIssuesEvents;
        return this;
    }

    public GroupHookParams setMergeRequestsEvents(Boolean mergeRequestsEvents) {
        this.mergeRequestsEvents = mergeRequestsEvents;
        return this;
    }

    public GroupHookParams setTagPushEvents(Boolean tagPushEvents) {
        this.tagPushEvents = tagPushEvents;
        return this;
    }

    public GroupHookParams setNoteEvents(Boolean noteEvents) {
        this.noteEvents = noteEvents;
        return this;
    }

    public GroupHookParams setConfidentialNoteEvents(Boolean confidentialNoteEvents) {
        this.confidentialNoteEvents = confidentialNoteEvents;
        return this;
    }

    public GroupHookParams setJobEvents(Boolean jobEvents) {
        this.jobEvents = jobEvents;
        return this;
    }

    public GroupHookParams setPipelineEvents(Boolean pipelineEvents) {
        this.pipelineEvents = pipelineEvents;
        return this;
    }

    public GroupHookParams setWikiPageEvents(Boolean wikiPageEvents) {
        this.wikiPageEvents = wikiPageEvents;
        return this;
    }

    public GroupHookParams setDeploymentEvents(Boolean deploymentEvents) {
        this.deploymentEvents = deploymentEvents;
        return this;
    }

    public GroupHookParams setFeatureFlagEvents(Boolean featureFlagEvents) {
        this.featureFlagEvents = featureFlagEvents;
        return this;
    }

    public GroupHookParams setReleasesEvents(Boolean releasesEvents) {
        this.releasesEvents = releasesEvents;
        return this;
    }

    public GroupHookParams setSubgroupEvents(Boolean subgroupEvents) {
        this.subgroupEvents = subgroupEvents;
        return this;
    }

    public GroupHookParams setMemberEvents(Boolean memberEvents) {
        this.memberEvents = memberEvents;
        return this;
    }

    public GroupHookParams setEnableSslVerification(Boolean enableSslVerification) {
        this.enableSslVerification = enableSslVerification;
        return this;
    }

    public GroupHookParams setToken(String token) {
        this.token = token;
        return this;
    }

    public GroupHookParams setResourceAccessTokenEvents(Boolean resourceAccessTokenEvents) {
        this.resourceAccessTokenEvents = resourceAccessTokenEvents;
        return this;
    }

    public GroupHookParams setCustomWebhookTemplate(String customWebhookTemplate) {
        this.customWebhookTemplate = customWebhookTemplate;
        return this;
    }
}
