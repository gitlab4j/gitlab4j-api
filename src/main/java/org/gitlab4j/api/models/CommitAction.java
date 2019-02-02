package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class CommitAction {

    public enum Action {

        CREATE, DELETE, MOVE, UPDATE;

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

    public enum Encoding {

        BASE64, TEXT;

        private static JacksonJsonEnumHelper<Encoding> enumHelper = new JacksonJsonEnumHelper<>(Encoding.class);

        @JsonCreator
        public static Encoding forValue(String value) {
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

    private Action action;
    private String filePath;
    private String previousPath;
    private String content;
    private Encoding encoding;
    private String lastCommitId;

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

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
