package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.GitLabForm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcceptMergeRequestParams implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The commit message for the merge.
     */
    @JsonProperty("merge_commit_message")
    private String mergeCommitMessage;

    /**
     * Indicates whether the merge should occur when the pipeline succeeds.
     */
    @JsonProperty("merge_when_pipeline_succeeds")
    private Boolean mergeWhenPipelineSucceeds;

    /**
     * The SHA associated with the merge request.
     */
    @JsonProperty("sha")
    private String sha;

    /**
     * Indicates whether the source branch should be removed after merging.
     */
    @JsonProperty("should_remove_source_branch")
    private Boolean shouldRemoveSourceBranch;

    /**
     * Indicates whether the merge should be squashed.
     */
    @JsonProperty("squash")
    private Boolean squash;

    /**
     * The commit message for the squash merge.
     */
    @JsonProperty("squash_commit_message")
    private String squashCommitMessage;

    /**
     * Custom merge commit message.
     *
     * @param mergeCommitMessage Custom merge commit message
     * @return The reference to this AcceptMergeRequestParams instance.
     */
    public AcceptMergeRequestParams withMergeCommitMessage(String mergeCommitMessage) {
        this.mergeCommitMessage = mergeCommitMessage;
        return this;
    }

    /**
     * If {@code true} the MR is merged when the pipeline succeeds.
     *
     * @param mergeWhenPipelineSucceeds If {@code true} the MR is merged when the pipeline succeeds.
     * @return The reference to this AcceptMergeRequestParams instance.
     */
    public AcceptMergeRequestParams withMergeWhenPipelineSucceeds(Boolean mergeWhenPipelineSucceeds) {
        this.mergeWhenPipelineSucceeds = mergeWhenPipelineSucceeds;
        return this;
    }

    /**
     * If present, then this SHA must match the HEAD of the source branch, otherwise the merge will fail.
     *
     * @param sha If present, then this SHA must match the HEAD of the source branch, otherwise the merge will fail.
     * @return The reference to this AcceptMergeRequestParams instance.
     */
    public AcceptMergeRequestParams withSha(String sha) {
        this.sha = sha;
        return this;
    }

    /**
     * If {@code true} removes the source branch.
     *
     * @param shouldRemoveSourceBranch If {@code true} removes the source branch.
     * @return The reference to this AcceptMergeRequestParams instance.
     */
    public AcceptMergeRequestParams withShouldRemoveSourceBranch(Boolean shouldRemoveSourceBranch) {
        this.shouldRemoveSourceBranch = shouldRemoveSourceBranch;
        return this;
    }

    /**
     * If {@code true} the commits will be squashed into a single commit on merge.
     *
     * @param squash If {@code true} the commits will be squashed into a single commit on merge.
     * @return The reference to this AcceptMergeRequestParams instance.
     */
    public AcceptMergeRequestParams withSquash(Boolean squash) {
        this.squash = squash;
        return this;
    }

    /**
     * Custom squash commit message.
     *
     * @param squashCommitMessage Custom squash commit message.
     * @return The reference to this AcceptMergeRequestParams instance.
     */
    public AcceptMergeRequestParams withSquashCommitMessage(String squashCommitMessage) {
        this.squashCommitMessage = squashCommitMessage;
        return this;
    }

    /**
     * Get the form params specified by this instance.
     *
     * @return a GitLabApiForm instance holding the form parameters for this AcceptMergeRequestParams instance
     */
    public GitLabForm getForm() {
        return new GitLabForm()
                .withParam("merge_commit_message", mergeCommitMessage)
                .withParam("merge_when_pipeline_succeeds", mergeWhenPipelineSucceeds)
                .withParam("sha", sha)
                .withParam("should_remove_source_branch", shouldRemoveSourceBranch)
                .withParam("squash", squash)
                .withParam("squash_commit_message", squashCommitMessage);
    }
}
