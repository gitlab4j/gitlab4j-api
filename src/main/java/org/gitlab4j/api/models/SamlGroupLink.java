package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.api.utils.JacksonJson;

public class SamlGroupLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private AccessLevel accessLevel;

    private int memberRoleId;

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel aAccessLevel) {
        accessLevel = aAccessLevel;
    }

    public int getMemberRoleId() {
        return memberRoleId;
    }

    public void setMemberRoleId(int aMemberRoleId) {
        memberRoleId = aMemberRoleId;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
