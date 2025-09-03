package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Note implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Enum to use for ordering the results. */
    public static enum OrderBy {
        CREATED_AT,
        UPDATED_AT;
        private static JacksonJsonEnumHelper<OrderBy> enumHelper = new JacksonJsonEnumHelper<>(OrderBy.class);

        @JsonCreator
        public static OrderBy forValue(String value) {
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

    // This is not used because the GitLab example JSON is using a funny string for the MERGE_REQUEST notable_type
    // ("Merge request").
    // Once they fix the bug, the notableType field can be changed from String to NotableType.
    public static enum NoteableType {
        COMMIT,
        EPIC,
        ISSUE,
        MERGE_REQUEST,
        SNIPPET;
        private static JacksonJsonEnumHelper<NoteableType> enumHelper =
                new JacksonJsonEnumHelper<>(NoteableType.class, true, true);

        @JsonCreator
        public static NoteableType forValue(String value) {
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

    public static enum Type {
        DISCUSSION_NOTE,
        DIFF_NOTE;
        private static JacksonJsonEnumHelper<Type> enumHelper = new JacksonJsonEnumHelper<>(Type.class, true, true);

        @JsonCreator
        public static Type forValue(String value) {
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

    @JsonProperty("attachment")
    private String attachment;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("body")
    private String body;

    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    @JsonProperty("downvote")
    private Boolean downvote;

    @JsonProperty("expires_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date expiresAt;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("noteable_id")
    private Long noteableId;

    @JsonProperty("noteable_type")
    private String noteableType; // Use String until the constant is fixed in the GitLab API

    @JsonProperty("noteable_iid")
    private Long noteableIid;

    @JsonProperty("system")
    private Boolean system;

    @JsonProperty("title")
    private String title;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date updatedAt;

    @JsonProperty("upvote")
    private Boolean upvote;

    @JsonProperty("resolved")
    private Boolean resolved;

    @JsonProperty("resolvable")
    private Boolean resolvable;

    @JsonProperty("resolved_by")
    private Participant resolvedBy;

    @JsonProperty("resolved_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date resolvedAt;

    @JsonProperty("internal")
    private Boolean internal;

    @JsonProperty("type")
    private Type type;

    @JsonProperty("position")
    private Position position;

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDownvote() {
        return downvote;
    }

    public void setDownvote(Boolean downvote) {
        this.downvote = downvote;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoteableId() {
        return noteableId;
    }

    public void setNoteableId(Long noteableId) {
        this.noteableId = noteableId;
    }

    public String getNoteableType() {
        return noteableType;
    }

    public void setNoteableType(String noteableType) {
        this.noteableType = noteableType;
    }

    public Long getNoteableIid() {
        return noteableIid;
    }

    public void setNoteableIid(Long noteableIid) {
        this.noteableIid = noteableIid;
    }

    public Boolean getSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(Boolean upvote) {
        this.upvote = upvote;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public Boolean getResolvable() {
        return resolvable;
    }

    public void setResolvable(Boolean resolvable) {
        this.resolvable = resolvable;
    }

    public Participant getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(Participant resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    public Date getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Date resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
