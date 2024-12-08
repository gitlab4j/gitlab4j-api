package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuditEventDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The change associated with the audit event.
     */
    @JsonProperty("change")
    private String change;

    /**
     * The previous value of the change.
     */
    @JsonProperty("from")
    private String from;

    /**
     * The new value after the change.
     */
    @JsonProperty("to")
    private String to;

    /**
     * The added value in the audit event.
     */
    @JsonProperty("add")
    private String add;

    /**
     * The custom message associated with the audit event.
     */
    @JsonProperty("custom_message")
    private String customMessage;

    /**
     * The name of the author making the change.
     */
    @JsonProperty("author_name")
    private String authorName;

    /**
     * The ID of the target of the audit event.
     */
    @JsonProperty("target_id")
    private Object targetId;

    /**
     * The type of the target of the audit event.
     */
    @JsonProperty("target_type")
    private String targetType;

    /**
     * The details of the target of the audit event.
     */
    @JsonProperty("target_details")
    private String targetDetails;

    /**
     * The IP address associated with the audit event.
     */
    @JsonProperty("ip_address")
    private String ipAddress;

    /**
     * The path of the entity in the audit event.
     */
    @JsonProperty("entity_path")
    private String entityPath;

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Object getTargetId() {
        return targetId;
    }

    public void setTargetId(Object targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetDetails() {
        return targetDetails;
    }

    public void setTargetDetails(String targetDetails) {
        this.targetDetails = targetDetails;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
