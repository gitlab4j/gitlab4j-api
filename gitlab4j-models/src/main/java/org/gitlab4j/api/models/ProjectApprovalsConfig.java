package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.GitLabForm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectApprovalsConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("approvals_before_merge")
    private Integer approvalsBeforeMerge;

    @JsonProperty("reset_approvals_on_push")
    private Boolean resetApprovalsOnPush;

    @JsonProperty("selective_code_owner_removals")
    private Boolean selectiveCodeOwnerRemovals;

    @JsonProperty("disable_overriding_approvers_per_merge_request")
    private Boolean disableOverridingApproversPerMergeRequest;

    @JsonProperty("merge_requests_author_approval")
    private Boolean mergeRequestsAuthorApproval;

    @JsonProperty("merge_requests_disable_committers_approval")
    private Boolean mergeRequestsDisableCommittersApproval;

    @JsonProperty("require_password_to_approve")
    private Boolean requirePasswordToApprove;

    public Integer getApprovalsBeforeMerge() {
        return approvalsBeforeMerge;
    }

    public void setApprovalsBeforeMerge(Integer approvalsBeforeMerge) {
        this.approvalsBeforeMerge = approvalsBeforeMerge;
    }

    public ProjectApprovalsConfig withApprovalsBeforeMerge(Integer approvalsBeforeMerge) {
        this.approvalsBeforeMerge = approvalsBeforeMerge;
        return (this);
    }

    public Boolean getResetApprovalsOnPush() {
        return resetApprovalsOnPush;
    }

    public void setResetApprovalsOnPush(Boolean resetApprovalsOnPush) {
        this.resetApprovalsOnPush = resetApprovalsOnPush;
    }

    public ProjectApprovalsConfig withResetApprovalsOnPush(Boolean resetApprovalsOnPush) {
        this.resetApprovalsOnPush = resetApprovalsOnPush;
        return (this);
    }

    public Boolean getSelectiveCodeOwnerRemovals() {
        return selectiveCodeOwnerRemovals;
    }

    public void setSelectiveCodeOwnerRemovals(Boolean selectiveCodeOwnerRemovals) {
        this.selectiveCodeOwnerRemovals = selectiveCodeOwnerRemovals;
    }

    public ProjectApprovalsConfig withSelectiveCodeOwnerRemovals(Boolean selectiveCodeOwnerRemovals) {
        this.selectiveCodeOwnerRemovals = selectiveCodeOwnerRemovals;
        return this;
    }

    public Boolean getDisableOverridingApproversPerMergeRequest() {
        return disableOverridingApproversPerMergeRequest;
    }

    public void setDisableOverridingApproversPerMergeRequest(Boolean disableOverridingApproversPerMergeRequest) {
        this.disableOverridingApproversPerMergeRequest = disableOverridingApproversPerMergeRequest;
    }

    public ProjectApprovalsConfig withDisableOverridingApproversPerMergeRequest(
            Boolean disableOverridingApproversPerMergeRequest) {
        this.disableOverridingApproversPerMergeRequest = disableOverridingApproversPerMergeRequest;
        return (this);
    }

    public Boolean getMergeRequestsAuthorApproval() {
        return mergeRequestsAuthorApproval;
    }

    public void setMergeRequestsAuthorApproval(Boolean mergeRequestsAuthorApproval) {
        this.mergeRequestsAuthorApproval = mergeRequestsAuthorApproval;
    }

    public ProjectApprovalsConfig withMergeRequestsAuthorApproval(Boolean mergeRequestsAuthorApproval) {
        this.mergeRequestsAuthorApproval = mergeRequestsAuthorApproval;
        return (this);
    }

    public Boolean getMergeRequestsDisableCommittersApproval() {
        return mergeRequestsDisableCommittersApproval;
    }

    public void setMergeRequestsDisableCommittersApproval(Boolean mergeRequestsDisableCommittersApproval) {
        this.mergeRequestsDisableCommittersApproval = mergeRequestsDisableCommittersApproval;
    }

    public ProjectApprovalsConfig withMergeRequestsDisableCommittersApproval(
            Boolean mergeRequestsDisableCommittersApproval) {
        this.mergeRequestsDisableCommittersApproval = mergeRequestsDisableCommittersApproval;
        return (this);
    }

    public Boolean getRequirePasswordToApprove() {
        return requirePasswordToApprove;
    }

    public void setRequirePasswordToApprove(Boolean requirePasswordToApprove) {
        this.requirePasswordToApprove = requirePasswordToApprove;
    }

    public ProjectApprovalsConfig withRequirePasswordToApprove(Boolean requirePasswordToApprove) {
        this.requirePasswordToApprove = requirePasswordToApprove;
        return this;
    }

    /**
     * Get the form params specified by this instance.
     *
     * @return a GitLabApiForm instance holding the form parameters for this ProjectApprovalsConfig instance
     */
    @JsonIgnore
    public GitLabForm getForm() {
        return new GitLabForm()
                .withParam("approvals_before_merge", approvalsBeforeMerge)
                .withParam("reset_approvals_on_push", resetApprovalsOnPush)
                .withParam("selective_code_owner_removals", selectiveCodeOwnerRemovals)
                .withParam("disable_overriding_approvers_per_merge_request", disableOverridingApproversPerMergeRequest)
                .withParam("merge_requests_author_approval", mergeRequestsAuthorApproval)
                .withParam("merge_requests_disable_committers_approval", mergeRequestsDisableCommittersApproval)
                .withParam("require_password_to_approve", requirePasswordToApprove);
    }
}
