package org.gitlab4j.api.systemhooks;

import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

public class GroupSystemHookEvent extends AbstractSystemHookEvent {
    
    public static final String GROUP_CREATE_EVENT = "group_create";
    public static final String GROUP_DESTROY_EVENT = "group_destroy";
    public static final String GROUP_RENAME_EVENT = "group_rename";
    
    private Date createdAt;
    private Date updatedAt;
    private String eventName;
    private String name;
    private String path;
    private String fullPath;
    private Integer groupId;
    private String ownerEmail;
    private String ownerName;
    private String oldPath;
    private String oldFullPath;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public String getOldFullPath() {
        return oldFullPath;
    }

    public void setOldFullPath(String oldFullPath) {
        this.oldFullPath = oldFullPath;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
