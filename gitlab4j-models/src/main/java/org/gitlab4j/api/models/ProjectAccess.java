package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

public class ProjectAccess implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccessLevel accessLevel;
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
