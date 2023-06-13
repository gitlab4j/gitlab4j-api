
package org.gitlab4j.api.models;

import java.util.Base64;

import org.gitlab4j.api.Constants.Encoding;
import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RepositoryFileResponse {

    private String filePath; // full path to file. Ex. lib/class.rb
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
