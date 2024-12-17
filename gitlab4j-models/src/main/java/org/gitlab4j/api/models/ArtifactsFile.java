package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtifactsFile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The filename of the artifact file.
     */
    @JsonProperty("filename")
    private String filename;

    /**
     * The size of the artifact file.
     */
    @JsonProperty("size")
    private Long size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
