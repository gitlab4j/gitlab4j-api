package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.gitlab4j.api.utils.JacksonJson;

@XmlAccessorType (XmlAccessType.FIELD)
public class ProjectAccess {

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
