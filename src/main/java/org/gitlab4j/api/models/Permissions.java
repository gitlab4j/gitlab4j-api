package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;

public class Permissions implements Serializable {
    private static final long serialVersionUID = 1L;

    private ProjectAccess projectAccess;
    private ProjectAccess groupAccess;

    public ProjectAccess getProjectAccess() {
        return projectAccess;
    }

    public void setProjectAccess(ProjectAccess projectAccess) {
        this.projectAccess = projectAccess;
    }

    public ProjectAccess getGroupAccess() {
        return groupAccess;
    }

    public void setGroupAccess(ProjectAccess groupAccess) {
        this.groupAccess = groupAccess;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
