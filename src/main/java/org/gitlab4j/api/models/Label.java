package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Label {

    private Integer id;
    private String name;
    private String color;
    private String description;
    private Integer openIssuesCount;
    private Integer closedIssuesCount;
    private Integer openMergeRequestsCount;
    private Boolean subscribed;
    private Integer priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public Integer getClosedIssuesCount() {
        return closedIssuesCount;
    }

    public void setClosedIssuesCount(Integer closedIssuesCount) {
        this.closedIssuesCount = closedIssuesCount;
    }

    public Integer getOpenMergeRequestsCount() {
        return openMergeRequestsCount;
    }

    public void setOpenMergeRequestsCount(Integer openMergeRequestsCount) {
        this.openMergeRequestsCount = openMergeRequestsCount;
    }

    public Boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
