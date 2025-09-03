package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Map;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthCheckItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The status of the health check.
     */
    @JsonProperty("status")
    private HealthCheckStatus status;

    /**
     * A map of labels associated with the health check.
     */
    @JsonProperty("labels")
    private Map<String, String> labels;

    /**
     * The message associated with the health check status.
     */
    @JsonProperty("message")
    private String message;

    public HealthCheckStatus getStatus() {
        return this.status;
    }

    public void setStatus(HealthCheckStatus status) {
        this.status = status;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
