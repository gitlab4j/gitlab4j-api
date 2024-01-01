package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;

public class GitLabCiTemplateElement implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;
    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
