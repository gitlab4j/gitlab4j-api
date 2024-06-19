package org.gitlab4j.api.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;
import java.util.Date;

public class SharedGroup implements Serializable {
    private static final long serialVersionUID = 1L;

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
