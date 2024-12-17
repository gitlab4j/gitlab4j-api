package org.gitlab4j.api.models;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.gitlab4j.models.Constants.Encoding;
import org.gitlab4j.models.utils.FileUtils;
import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class CommitAction implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Action {
        CREATE,
        DELETE,
        MOVE,
        UPDATE,
        CHMOD;

        private static JacksonJsonEnumHelper<Action> enumHelper = new JacksonJsonEnumHelper<>(Action.class);

        @JsonCreator
        public static Action forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /**
     * The action associated with the commit (e.g., add, modify, delete).
     */
    @JsonProperty("action")
    private Action action;

    /**
     * The file path associated with the commit action.
     */
    @JsonProperty("file_path")
    private String filePath;

    /**
     * The previous file path before the commit action.
     */
    @JsonProperty("previous_path")
    private String previousPath;

    /**
     * The content of the file associated with the commit action.
     */
    @JsonProperty("content")
    private String content;

    /**
     * The encoding of the file associated with the commit action.
     */
    @JsonProperty("encoding")
    private Encoding encoding;

    /**
     * The last commit ID associated with the commit action.
     */
    @JsonProperty("last_commit_id")
    private String lastCommitId;

    /**
     * Indicates if the file mode should be executed in the commit action.
     */
    @JsonProperty("execute_filemode")
    private Boolean executeFilemode;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public CommitAction withAction(Action action) {
        this.action = action;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public CommitAction withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getPreviousPath() {
        return previousPath;
    }

    public void setPreviousPath(String previousPath) {
        this.previousPath = previousPath;
    }

    public CommitAction withPreviousPath(String previousPath) {
        this.previousPath = previousPath;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommitAction withContent(String content) {
        this.content = content;
        return this;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public CommitAction withEncoding(Encoding encoding) {
        this.encoding = encoding;
        return this;
    }

    public String getLastCommitId() {
        return lastCommitId;
    }

    public void setLastCommitId(String lastCommitId) {
        this.lastCommitId = lastCommitId;
    }

    public CommitAction withLastCommitId(String lastCommitId) {
        this.lastCommitId = lastCommitId;
        return this;
    }

    public Boolean getExecuteFilemode() {
        return executeFilemode;
    }

    public void setExecuteFilemode(Boolean executeFilemode) {
        this.executeFilemode = executeFilemode;
    }

    public CommitAction withExecuteFilemode(Boolean executeFilemode) {
        this.executeFilemode = executeFilemode;
        return this;
    }

    public CommitAction withFileContent(String filePath, Encoding encoding) {
        File file = new File(filePath);
        return (withFileContent(file, filePath, encoding));
    }

    public CommitAction withFileContent(File file, String filePath, Encoding encoding) {

        this.encoding = (encoding != null ? encoding : Encoding.TEXT);
        this.filePath = filePath;

        try {
            content = FileUtils.getFileContentAsString(file, this.encoding);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return (this);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
