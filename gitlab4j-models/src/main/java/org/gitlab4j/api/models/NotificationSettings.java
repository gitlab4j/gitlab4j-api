package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class NotificationSettings implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Notification level */
    public static enum Level {
        DISABLED,
        PARTICIPATING,
        WATCH,
        GLOBAL,
        MENTION,
        CUSTOM;

        private static JacksonJsonEnumHelper<Level> enumHelper = new JacksonJsonEnumHelper<>(Level.class);

        @JsonCreator
        public static Level forValue(String value) {
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

    public static class Events implements Serializable {
        private static final long serialVersionUID = 1L;

        @JsonProperty("new_note")
        private Boolean newNote;

        @JsonProperty("new_issue")
        private Boolean newIssue;

        @JsonProperty("reopen_issue")
        private Boolean reopenIssue;

        @JsonProperty("close_issue")
        private Boolean closeIssue;

        @JsonProperty("reassign_issue")
        private Boolean reassignIssue;

        @JsonProperty("new_merge_request")
        private Boolean newMergeRequest;

        @JsonProperty("reopen_merge_request")
        private Boolean reopenMergeRequest;

        @JsonProperty("close_merge_request")
        private Boolean closeMergeRequest;

        @JsonProperty("reassign_merge_request")
        private Boolean reassignMergeRequest;

        @JsonProperty("merge_merge_request")
        private Boolean mergeMergeRequest;

        @JsonProperty("failed_pipeline")
        private Boolean failedPipeline;

        @JsonProperty("success_pipeline")
        private Boolean successPipeline;

        public Boolean getNewNote() {
            return newNote;
        }

        public void setNewNote(Boolean newNote) {
            this.newNote = newNote;
        }

        public Boolean getNewIssue() {
            return newIssue;
        }

        public void setNewIssue(Boolean newIssue) {
            this.newIssue = newIssue;
        }

        public Boolean getReopenIssue() {
            return reopenIssue;
        }

        public void setReopenIssue(Boolean reopenIssue) {
            this.reopenIssue = reopenIssue;
        }

        public Boolean getCloseIssue() {
            return closeIssue;
        }

        public void setCloseIssue(Boolean closeIssue) {
            this.closeIssue = closeIssue;
        }

        public Boolean getReassignIssue() {
            return reassignIssue;
        }

        public void setReassignIssue(Boolean reassignIssue) {
            this.reassignIssue = reassignIssue;
        }

        public Boolean getNewMergeRequest() {
            return newMergeRequest;
        }

        public void setNewMergeRequest(Boolean newMergeRequest) {
            this.newMergeRequest = newMergeRequest;
        }

        public Boolean getReopenMergeRequest() {
            return reopenMergeRequest;
        }

        public void setReopenMergeRequest(Boolean reopenMergeRequest) {
            this.reopenMergeRequest = reopenMergeRequest;
        }

        public Boolean getCloseMergeRequest() {
            return closeMergeRequest;
        }

        public void setCloseMergeRequest(Boolean closeMergeRequest) {
            this.closeMergeRequest = closeMergeRequest;
        }

        public Boolean getReassignMergeRequest() {
            return reassignMergeRequest;
        }

        public void setReassignMergeRequest(Boolean reassignMergeRequest) {
            this.reassignMergeRequest = reassignMergeRequest;
        }

        public Boolean getMergeMergeRequest() {
            return mergeMergeRequest;
        }

        public void setMergeMergeRequest(Boolean mergeMergeRequest) {
            this.mergeMergeRequest = mergeMergeRequest;
        }

        public Boolean getFailedPipeline() {
            return failedPipeline;
        }

        public void setFailedPipeline(Boolean failedPipeline) {
            this.failedPipeline = failedPipeline;
        }

        public Boolean getSuccessPipeline() {
            return successPipeline;
        }

        public void setSuccessPipeline(Boolean successPipeline) {
            this.successPipeline = successPipeline;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }

    @JsonProperty("level")
    private Level level;

    @JsonProperty("email")
    private String email;

    @JsonProperty("events")
    private Events events;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
