package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class LabelEvent {

    /** Enum to use for specifying the label event resource type. */
    public enum ResourceType {

	ISSUE, EPIC, MERGE_REQUEST;

	private static JacksonJsonEnumHelper<ResourceType> enumHelper = new JacksonJsonEnumHelper<>(ResourceType.class,
	        true, true);

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

    private int id;
    private User user;
    private String createdAt;
    private ResourceType resourceType;
    private int resourceId;
    private Label label;
    private String action;

    public int getId() {
	return id;
    }

    public void setId(int id) {
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

    public int getResourceId() {
	return resourceId;
    }

    public void setResourceId(int resourceId) {
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
