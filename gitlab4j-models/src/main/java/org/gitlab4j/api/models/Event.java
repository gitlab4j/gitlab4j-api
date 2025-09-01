package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.Constants.TargetType;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the event.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the action that triggered the event.
     */
    @JsonProperty("action_name")
    private String actionName;

    /**
     * The author associated with the event.
     */
    @JsonProperty("author")
    private Author author;

    /**
     * The ID of the author associated with the event.
     */
    @JsonProperty("author_id")
    private Long authorId;

    /**
     * The username of the author associated with the event.
     */
    @JsonProperty("author_username")
    private String authorUsername;

    /**
     * The event data associated with the event.
     */
    @JsonProperty("data")
    private EventData data;

    /**
     * The ID of the project associated with the event.
     */
    @JsonProperty("project_id")
    private Long projectId;

    /**
     * The target ID associated with the event.
     */
    @JsonProperty("target_id")
    private Long targetId;

    /**
     * The target IID associated with the event.
     */
    @JsonProperty("target_iid")
    private Long targetIid;

    /**
     * The target title associated with the event.
     */
    @JsonProperty("target_title")
    private String targetTitle;

    /**
     * The target type associated with the event.
     */
    @JsonProperty("target_type")
    private TargetType targetType;

    /**
     * The title of the event.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The creation date of the event.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The note associated with the event.
     */
    @JsonProperty("note")
    private Note note;

    /**
     * The push data associated with the event.
     */
    @JsonProperty("push_data")
    private PushData pushData;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public EventData getData() {
        return data;
    }

    public void setData(EventData data) {
        this.data = data;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getTargetIid() {
        return targetIid;
    }

    public void setTargetIid(Long targetIid) {
        this.targetIid = targetIid;
    }

    public String getTargetTitle() {
        return targetTitle;
    }

    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public PushData getPushData() {
        return pushData;
    }

    public void setPushData(PushData pushData) {
        this.pushData = pushData;
    }

    public Event withActionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    public Event withAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Event withAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Event withAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

    public Event withData(EventData data) {
        this.data = data;
        return this;
    }

    public Event withProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    public Event withTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public Event withTargetIid(Long targetIid) {
        this.targetIid = targetIid;
        return this;
    }

    public Event withTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
        return this;
    }

    public Event withTargetType(TargetType targetType) {
        this.targetType = targetType;
        return this;
    }

    public Event withTitle(String title) {
        this.title = title;
        return this;
    }

    public Event withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
