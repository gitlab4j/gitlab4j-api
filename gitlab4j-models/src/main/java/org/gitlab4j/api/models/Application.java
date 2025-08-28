package org.gitlab4j.api.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.gitlab4j.models.utils.JacksonJson;

public class Application implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the application.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The application ID.
     */
    @JsonProperty("application_id")
    private String applicationId;

    /**
     * The name of the application.
     */
    @JsonProperty("application_name")
    private String applicationName;

    /**
     * The callback URL associated with the application.
     */
    @JsonProperty("callback_url")
    private String callbackUrl;

    /**
     * The application is used where the client secret can be kept confidential.
     * Native mobile apps and Single Page Apps are considered non-confidential.
     * Defaults to true if not supplied.
     */
    @JsonProperty("confidential")
    private Boolean confidential;

    @JsonProperty("secret")
    private String secret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Boolean getConfidential() {
        return confidential;
    }

    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
