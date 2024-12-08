package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Diff implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The mode of file A in the diff.
     */
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("a_mode")
    private String a_mode;

    /**
     * The mode of file B in the diff.
     */
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("b_mode")
    private String b_mode;

    /**
     * Indicates if the file was deleted.
     */
    @JsonProperty("deleted_file")
    private Boolean deletedFile;

    /**
     * The diff content of the file.
     */
    @JsonProperty("diff")
    private String diff;

    /**
     * Indicates if the file is a new file.
     */
    @JsonProperty("new_file")
    private Boolean newFile;

    /**
     * The new path of the file.
     */
    @JsonProperty("new_path")
    private String newPath;

    /**
     * The old path of the file.
     */
    @JsonProperty("old_path")
    private String oldPath;

    /**
     * Indicates if the file was renamed.
     */
    @JsonProperty("renamed_file")
    private Boolean renamedFile;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("a_mode")
    public String getAMode() {
        return this.a_mode;
    }

    public void setAMode(String aMode) {
        this.a_mode = aMode;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("b_mode")
    public String getBMode() {
        return this.b_mode;
    }

    public void setBMode(String bMode) {
        this.b_mode = bMode;
    }

    public Boolean getDeletedFile() {
        return this.deletedFile;
    }

    public void setDeletedFile(Boolean deletedFile) {
        this.deletedFile = deletedFile;
    }

    public String getDiff() {
        return this.diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public Boolean getNewFile() {
        return this.newFile;
    }

    public void setNewFile(Boolean newFile) {
        this.newFile = newFile;
    }

    public String getNewPath() {
        return this.newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getOldPath() {
        return this.oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public Boolean getRenamedFile() {
        return this.renamedFile;
    }

    public void setRenamedFile(Boolean renamedFile) {
        this.renamedFile = renamedFile;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
