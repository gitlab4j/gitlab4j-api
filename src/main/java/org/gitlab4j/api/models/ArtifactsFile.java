package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class ArtifactsFile {

    private String filename;
    private Integer size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
