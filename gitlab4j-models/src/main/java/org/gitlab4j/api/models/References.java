package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class References implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("short")
    private String _short;

    @JsonProperty("relative")
    private String relative;

    @JsonProperty("full")
    private String full;

    public String getShort() {
        return _short;
    }

    public void setShort(String _short) {
        this._short = _short;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
