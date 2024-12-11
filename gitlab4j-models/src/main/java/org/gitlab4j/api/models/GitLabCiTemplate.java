package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitLabCiTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The name associated with the content.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The content of the object.
     */
    @JsonProperty("content")
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
