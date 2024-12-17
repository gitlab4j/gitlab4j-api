package org.gitlab4j.api.webhook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractEvent implements Event {
    private static final long serialVersionUID = 1L;

    @JsonProperty("event_type")
    private String eventType;

    @JsonProperty("request_url")
    private String requestUrl;

    @JsonProperty("request_query_string")
    private String requestQueryString;

    @JsonProperty("secret_token")
    private String secretToken;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    @JsonIgnore
    public String getRequestUrl() {
        return (requestUrl);
    }

    @Override
    public void setRequestQueryString(String requestQueryString) {
        this.requestQueryString = requestQueryString;
    }

    @Override
    @JsonIgnore
    public String getRequestQueryString() {
        return (requestQueryString);
    }

    @Override
    public void setRequestSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    @JsonIgnore
    public String getRequestSecretToken() {
        return (secretToken);
    }
}
