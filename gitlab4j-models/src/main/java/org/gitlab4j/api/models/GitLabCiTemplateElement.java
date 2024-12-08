package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitLabCiTemplateElement implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The key string associated with the key details.
     */
    @JsonProperty("key")
    private String key;

    /**
     * The name associated with the key details.
     */
    @JsonProperty("name")
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
