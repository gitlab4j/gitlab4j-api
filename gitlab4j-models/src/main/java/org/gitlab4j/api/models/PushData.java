package org.gitlab4j.api.models;

import org.gitlab4j.models.Constants.ActionType;
import org.gitlab4j.models.utils.JacksonJson;

public class PushData {

    private Integer commitCount;
    private ActionType action;
    private String refType;
    private String commitFrom;
    private String commitTo;
    private String ref;
    private String commitTitle;

    public Integer getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(Integer commit_count) {
        this.commitCount = commit_count;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getCommitFrom() {
        return commitFrom;
    }

    public void setCommitFrom(String commitFrom) {
        this.commitFrom = commitFrom;
    }

    public String getCommitTo() {
        return commitTo;
    }

    public void setCommitTo(String commitTo) {
        this.commitTo = commitTo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCommitTitle() {
        return commitTitle;
    }

    public void setCommitTitle(String commitTitle) {
        this.commitTitle = commitTitle;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
