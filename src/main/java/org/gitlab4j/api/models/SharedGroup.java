package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SharedGroup {

    private Long groupId;
    private String groupName;
    private String groupFullPath;
    private AccessLevel groupAccessLevel;
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date expiresAt;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public AccessLevel getGroupAccessLevel() {
        return (groupAccessLevel);
    }

    public void setGroupAccessLevel(AccessLevel accessLevel) {
        this.groupAccessLevel = accessLevel;
    }

    public String getGroupFullPath() {
        return groupFullPath;
    }

    public void setGroupFullPath(String groupFullPath) {
        this.groupFullPath = groupFullPath;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
