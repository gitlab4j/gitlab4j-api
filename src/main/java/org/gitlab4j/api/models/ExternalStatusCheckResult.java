package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;

public class ExternalStatusCheckResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private MergeRequest mergeRequest;
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
