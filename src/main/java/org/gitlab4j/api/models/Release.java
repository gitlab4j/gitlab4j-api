package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Release {

    private String tagName;
    private String description;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
