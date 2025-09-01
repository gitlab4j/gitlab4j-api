package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class IssueEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Enum to use for specifying the state events resource type. */
    public enum ResourceType {
        ISSUE;

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

    /**
     * The unique identifier of the resource.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The user associated with the resource.
     */
    @JsonProperty("user")
    private User user;

    /**
     * The creation date of the resource.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private String createdAt;

    /**
     * The type of the resource.
     */
    @JsonProperty("resource_type")
    private ResourceType resourceType;

    /**
     * The ID of the resource.
     */
    @JsonProperty("resource_id")
    private Long resourceId;

    /**
     * The state of the resource.
     */
    @JsonProperty("state")
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
