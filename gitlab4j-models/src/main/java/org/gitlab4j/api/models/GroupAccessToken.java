package org.gitlab4j.api.models;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupAccessToken extends ImpersonationToken {
    private static final long serialVersionUID = 1L;

    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
