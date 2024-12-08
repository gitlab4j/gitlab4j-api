package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class LabelEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Enum to use for specifying the label event resource type. */
    public enum ResourceType {
        ISSUE,
        EPIC,
        MERGE_REQUEST;

        private static JacksonJsonEnumHelper<ResourceType> enumHelper =
                new JacksonJsonEnumHelper<>(ResourceType.class, true, true);

        @JsonCreator
        public static ResourceType forValue(String value) {
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

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user")
    private User user;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("resource_type")
    private ResourceType resourceType;

    @JsonProperty("resource_id")
    private Long resourceId;

    @JsonProperty("label")
    private Label label;

    @JsonProperty("action")
    private String action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
