package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used by various models to represent the approved_by property,
 * which can contain a User or Group instance.
 *
 * @since 4.19.0
 */
public class ApprovedBy implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The user associated with the approval.
     */
    @JsonProperty("user")
    private User user;

    /**
     * The group associated with the approval.
     */
    @JsonProperty("group")
    private Group group;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (group != null) {
            throw new RuntimeException("ApprovedBy is already set to a group, cannot be set to a user");
        }

        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        if (user != null) {
            throw new RuntimeException("ApprovedBy is already set to a user, cannot be set to a group");
        }

        this.group = group;
    }

    /**
     * Return the user or group that represents this ApprovedBy instance.  Returned
     * object will either be an instance of a User or Group.
     *
     * @return the user or group that represents this ApprovedBy instance
     */
    @JsonIgnore
    public Object getApprovedBy() {
        return (user != null ? user : group);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
