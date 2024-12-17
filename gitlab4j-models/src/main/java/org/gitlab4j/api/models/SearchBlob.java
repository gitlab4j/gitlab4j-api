package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchBlob implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("basename")
    private String basename;

    @JsonProperty("data")
    private String data;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("id")
    private String id;

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("startline")
    private Integer startline;

    @JsonProperty("project_id")
    private Long projectId;

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Integer getStartline() {
        return startline;
    }

    public void setStartline(Integer startline) {
        this.startline = startline;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
