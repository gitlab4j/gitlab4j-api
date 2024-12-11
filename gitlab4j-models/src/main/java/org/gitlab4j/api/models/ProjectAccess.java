package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectAccess implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    @JsonProperty("notification_level")
    private int notificationLevel;

    public AccessLevel getAccessLevel() {
        return (accessLevel);
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getNotificationLevel() {
        return (notificationLevel);
    }

    public void setNotificationLevel(int notificationLevel) {
        this.notificationLevel = notificationLevel;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
