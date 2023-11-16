package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class GroupAccessToken extends ImpersonationToken {
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
