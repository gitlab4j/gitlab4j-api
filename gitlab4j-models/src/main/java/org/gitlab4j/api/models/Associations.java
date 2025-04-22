package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

public class Associations implements Serializable {
    private static final long serialVersionUID = 1L;

    private int groupsCount;
    private int projectsCount;
    private int issuesCount;
    private int mergeRequestsCount;

    public int getGroupsCount() {
        return groupsCount;
    }

    public void setGroupsCount(int groupsCount) {
        this.groupsCount = groupsCount;
    }

    public int getProjectsCount() {
        return projectsCount;
    }

    public void setProjectsCount(int projectsCount) {
        this.projectsCount = projectsCount;
    }

    public int getIssuesCount() {
        return issuesCount;
    }

    public void setIssuesCount(int issuesCount) {
        this.issuesCount = issuesCount;
    }

    public int getMergeRequestsCount() {
        return mergeRequestsCount;
    }

    public void setMergeRequestsCount(int mergeRequestsCount) {
        this.mergeRequestsCount = mergeRequestsCount;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
