package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchAccessLevel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the branch access level.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The access level associated with the branch.
     */
    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    /**
     * The description of the access level associated with the branch.
     */
    @JsonProperty("access_level_description")
    private String accessLevelDescription;

    /**
     * The user ID associated with the branch access level.
     */
    @JsonProperty("user_id")
    private Long userId;

    /**
     * The group ID associated with the branch access level.
     */
    @JsonProperty("group_id")
    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccessLevel getAccessLevel() {
        return this.accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevelDescription() {
        return this.accessLevelDescription;
    }

    public void setAccessLevelDescription(String accessLevelDescription) {
        this.accessLevelDescription = accessLevelDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
