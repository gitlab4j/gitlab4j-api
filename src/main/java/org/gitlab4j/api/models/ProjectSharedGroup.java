package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class ProjectSharedGroup {
 
    private Integer groupId;
    private String groupName;
    private AccessLevel groupAccessLevel;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
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

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
