package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalStatusCheckResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the status.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The merge request associated with the status.
     */
    @JsonProperty("merge_request")
    private MergeRequest mergeRequest;

    /**
     * The external status check.
     */
    @JsonProperty("external_status_check")
    private ExternalStatusCheck externalStatusCheck;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MergeRequest getMergeRequest() {
        return mergeRequest;
    }

    public void setMergeRequest(MergeRequest mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    public ExternalStatusCheck getExternalStatusCheck() {
        return externalStatusCheck;
    }

    public void setExternalStatusCheck(ExternalStatusCheck externalStatusCheck) {
        this.externalStatusCheck = externalStatusCheck;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
