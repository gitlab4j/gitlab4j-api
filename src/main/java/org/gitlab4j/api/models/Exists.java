package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.api.utils.JacksonJson;

public class Exists implements Serializable {
    private static final long serialVersionUID = 1L;

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
