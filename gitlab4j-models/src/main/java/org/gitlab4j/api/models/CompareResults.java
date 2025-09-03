package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompareResults implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The commit associated with the comparison.
     */
    @JsonProperty("commit")
    private Commit commit;

    /**
     * The list of commits associated with the comparison.
     */
    @JsonProperty("commits")
    private List<Commit> commits;

    /**
     * The list of diffs associated with the comparison.
     */
    @JsonProperty("diffs")
    private List<Diff> diffs;

    /**
     * Indicates if the comparison timed out.
     */
    @JsonProperty("compare_timeout")
    private Boolean compareTimeout;

    /**
     * Indicates if the comparison was done on the same ref.
     */
    @JsonProperty("compare_same_ref")
    private Boolean compareSameRef;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Diff> getDiffs() {
        return diffs;
    }

    public void setDiffs(List<Diff> diffs) {
        this.diffs = diffs;
    }

    public Boolean getCompareTimeout() {
        return compareTimeout;
    }

    public void setCompareTimeout(Boolean compareTimeout) {
        this.compareTimeout = compareTimeout;
    }

    public Boolean getCompareSameRef() {
        return compareSameRef;
    }

    public void setCompareSameRef(Boolean compareSameRef) {
        this.compareSameRef = compareSameRef;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
