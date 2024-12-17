package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Exists implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("exists")
    private Boolean exists;

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
