package org.gitlab4j.api.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
