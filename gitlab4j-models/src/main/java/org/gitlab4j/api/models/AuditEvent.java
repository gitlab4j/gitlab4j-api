package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuditEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the audit event.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The ID of the author making the event.
     */
    @JsonProperty("author_id")
    private Long authorId;

    /**
     * The ID of the entity involved in the event.
     */
    @JsonProperty("entity_id")
    private Long entityId;

    /**
     * The type of the entity involved in the event.
     */
    @JsonProperty("entity_type")
    private String entityType;

    /**
     * The details associated with the audit event.
     */
    @JsonProperty("details")
    private AuditEventDetail details;

    /**
     * The creation date of the audit event.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public AuditEventDetail getDetails() {
        return details;
    }

    public void setDetails(AuditEventDetail details) {
        this.details = details;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
