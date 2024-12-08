package org.gitlab4j.api.systemhooks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractSystemHookEvent implements SystemHookEvent {
    private static final long serialVersionUID = 1L;

    @JsonProperty("request_url")
    private String requestUrl;

    @JsonProperty("request_query_string")
    private String requestQueryString;

    @JsonProperty("request_secret_token")
    private String requestSecretToken;

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
    public void setRequestSecretToken(String requestSecretToken) {
        this.requestSecretToken = requestSecretToken;
    }

    @Override
    @JsonIgnore
    public String getRequestSecretToken() {
        return (requestSecretToken);
    }
}
