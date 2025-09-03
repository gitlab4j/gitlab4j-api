package org.gitlab4j.api.systemhooks;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryChange {

    @JsonProperty("after")
    private String after;

    @JsonProperty("before")
    private String before;

    @JsonProperty("ref")
    private String ref;

    public String getAfter() {
        return this.after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return this.before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
