package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Changes implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The mode of file A in the change.
     */
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("a_mode")
    private String a_mode;

    /**
     * The mode of file B in the change.
     */
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("b_mode")
    private String b_mode;

    /**
     * Indicates if the file was deleted in the change.
     */
    @JsonProperty("deleted_file")
    private Boolean deletedFile;

    /**
     * The diff content of the file in the change.
     */
    @JsonProperty("diff")
    private String diff;

    /**
     * Indicates if the file is a new file in the change.
     */
    @JsonProperty("new_file")
    private Boolean newFile;

    /**
     * The new path of the file in the change.
     */
    @JsonProperty("new_path")
    private String newPath;

    /**
     * The old path of the file in the change.
     */
    @JsonProperty("old_path")
    private String oldPath;

    /**
     * Indicates if the file was renamed in the change.
     */
    @JsonProperty("renamed_file")
    private Boolean renamedFile;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("a_mode")
    public String getAMode() {
        return a_mode;
    }

    public void setAMode(String a_mode) {
        this.a_mode = a_mode;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("b_mode")
    public String getBMode() {
        return b_mode;
    }

    public void setBMode(String b_mode) {
        this.b_mode = b_mode;
    }

    public Boolean getDeletedFile() {
        return deletedFile;
    }

    public void setDeletedFile(Boolean deletedFile) {
        this.deletedFile = deletedFile;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public Boolean getNewFile() {
        return newFile;
    }

    public void setNewFile(Boolean newFile) {
        this.newFile = newFile;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public Boolean getRenamedFile() {
        return renamedFile;
    }

    public void setRenamedFile(Boolean renamedFile) {
        this.renamedFile = renamedFile;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
