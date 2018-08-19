package org.gitlab4j.api.systemhooks;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractSystemHookEvent implements SystemHookEvent {

    private String requestUrl;
    private String requestQuesryString;

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
    public void setRequestQueryString(String requestQuesryString) {
        this.requestQuesryString = requestQuesryString;
    }

    @Override
    @JsonIgnore
    public String getRequestQueryString() {
        return (requestQuesryString);
    }
}
