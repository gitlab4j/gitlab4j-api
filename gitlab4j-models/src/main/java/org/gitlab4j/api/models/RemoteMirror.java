package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class RemoteMirror implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("last_error")
    private String lastError;

    @JsonProperty("last_successful_update_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date lastSuccessfulUpdateAt;

    @JsonProperty("last_update_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date lastUpdateAt;

    @JsonProperty("last_update_started_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date lastUpdateStartedAt;

    @JsonProperty("only_protected_branches")
    private Boolean onlyProtectedBranches;

    @JsonProperty("keep_divergent_refs")
    private Boolean keepDivergentRefs;

    @JsonProperty("update_status")
    private String updateStatus;

    @JsonProperty("url")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public Boolean getOnlyProtectedBranches() {
        return onlyProtectedBranches;
    }

    public void setOnlyProtectedBranches(Boolean onlyProtectedBranches) {
        this.onlyProtectedBranches = onlyProtectedBranches;
    }

    public Boolean getKeepDivergentRefs() {
        return keepDivergentRefs;
    }

    public void setKeepDivergentRefs(Boolean keepDivergentRefs) {
        this.keepDivergentRefs = keepDivergentRefs;
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

    @Override
    public String toString() {
        return JacksonJson.toJsonString(this);
    }
}
