package org.gitlab4j.api.models;

import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

public class MergeRequestDiff extends MergeRequestVersion {
    private static final long serialVersionUID = 1L;

    private List<Commit> commits;
    private List<Diff> diffs;

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

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
