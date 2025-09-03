package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryFileResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("file_path")
    private String filePath; // full path to file. Ex. lib/class.rb

    @JsonProperty("branch")
    private String branch;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
