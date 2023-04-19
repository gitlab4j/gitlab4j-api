package org.gitlab4j.api.webhook;

import java.util.List;

import org.gitlab4j.api.models.Reviewer;

public class MergeRequestChanges extends EventChanges {

    private ChangeContainer<String> mergeStatus;
    private ChangeContainer<List<Reviewer>> reviewers;

    public ChangeContainer<String> getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(ChangeContainer<String> mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    public ChangeContainer<List<Reviewer>> getReviewers() {
        return reviewers;
    }

    public void setReviewers(ChangeContainer<List<Reviewer>> reviewers) {
        this.reviewers = reviewers;
    }

}
