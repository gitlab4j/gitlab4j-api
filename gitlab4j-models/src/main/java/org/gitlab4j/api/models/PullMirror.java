package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

public class PullMirror implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String lastError;
    private Date lastSuccessfulUpdateAt;
    private Date lastUpdateAt;
    private Date lastUpdateStartedAt;
    private String updateStatus;
    private String url;
    private Boolean enabled;
    private Boolean mirrorTriggerBuilds;
    private Boolean onlyMirrorProtectedBranches;
    private Boolean mirrorOverwritesDivergedBranches;
    private String mirrorBranchRegex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public Date getLastSuccessfulUpdateAt() {
        return lastSuccessfulUpdateAt;
    }

    public void setLastSuccessfulUpdateAt(Date lastSuccessfulUpdateAt) {
        this.lastSuccessfulUpdateAt = lastSuccessfulUpdateAt;
    }

    public Date getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(Date lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public Date getLastUpdateStartedAt() {
        return lastUpdateStartedAt;
    }

    public void setLastUpdateStartedAt(Date lastUpdateStartedAt) {
        this.lastUpdateStartedAt = lastUpdateStartedAt;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getMirrorTriggerBuilds() {
        return mirrorTriggerBuilds;
    }

    public void setMirrorTriggerBuilds(Boolean mirrorTriggerBuilds) {
        this.mirrorTriggerBuilds = mirrorTriggerBuilds;
    }

    public Boolean getOnlyMirrorProtectedBranches() {
        return onlyMirrorProtectedBranches;
    }

    public void setOnlyMirrorProtectedBranches(Boolean onlyMirrorProtectedBranches) {
        this.onlyMirrorProtectedBranches = onlyMirrorProtectedBranches;
    }

    public Boolean getMirrorOverwritesDivergedBranches() {
        return mirrorOverwritesDivergedBranches;
    }

    public void setMirrorOverwritesDivergedBranches(Boolean mirrorOverwritesDivergedBranches) {
        this.mirrorOverwritesDivergedBranches = mirrorOverwritesDivergedBranches;
    }

    public String getMirrorBranchRegex() {
        return mirrorBranchRegex;
    }

    public void setMirrorBranchRegex(String mirrorBranchRegex) {
        this.mirrorBranchRegex = mirrorBranchRegex;
    }

    @Override
    public String toString() {
        return JacksonJson.toJsonString(this);
    }
}
