package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.util.Objects;

public class BranchAccessLevel {

    private AccessLevel accessLevel;
    private String accessLevelDescription;
    private Integer userId;
    private Integer groupId;

    public AccessLevel getAccessLevel() {
        return this.accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        switch (accessLevel) {
            case MAINTAINER:
                this.accessLevelDescription = "Maintainers";
                break;

            case DEVELOPER:
                this.accessLevelDescription = "Developers + Maintainers";
                break;

            case NONE:
                this.accessLevelDescription = "No one";
                break;

            default:
                break;
        }
    }

    public String getAccessLevelDescription() {
        return this.accessLevelDescription;
    }

    public void setAccessLevelDescription(String accessLevelDescription) {
        this.accessLevelDescription = accessLevelDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchAccessLevel that = (BranchAccessLevel) o;
        // Sometimes access level will not be null even though it's a user-scoped rule, so ignore it
        if(userId != null) return Objects.equals(accessLevelDescription, that.accessLevelDescription) && Objects.equals(userId, that.userId) && Objects.equals(groupId, that.groupId);
        return accessLevel == that.accessLevel && Objects.equals(accessLevelDescription, that.accessLevelDescription) && Objects.equals(userId, that.userId) && Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessLevel, accessLevelDescription, userId, groupId);
    }
}
