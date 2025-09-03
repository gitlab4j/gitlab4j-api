package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class ExternalStatusCheckStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the status check.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name associated with the status check.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The external URL associated with the status check.
     */
    @JsonProperty("external_url")
    private String externalUrl;

    /**
     * The status of the external status check.
     */
    @JsonProperty("status")
    private Status status;

    public enum Status {
        PASSED,
        FAILED,
        PENDING;

        private static JacksonJsonEnumHelper<Status> enumHelper = new JacksonJsonEnumHelper<>(Status.class);

        @JsonCreator
        public static Status forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
