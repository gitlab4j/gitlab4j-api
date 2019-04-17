
package org.gitlab4j.api.models;

import java.util.Map;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EpicIssue extends Issue {

    private Integer downvotes;
    private Integer upvotes;

    @JsonProperty("_links")
    private Map<String, String> links;

    private Boolean subscribed;
    private Integer epicIssueId;
    private Integer relativePosition;

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    @JsonIgnore
    public String getLinkByName(String name) {
        if (links == null || links.isEmpty()) {
            return (null);
        }

        return (links.get(name));
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Integer getEpicIssueId() {
        return epicIssueId;
    }

    public void setEpicIssueId(Integer epicIssueId) {
        this.epicIssueId = epicIssueId;
    }

    public Integer getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(Integer relativePosition) {
        this.relativePosition = relativePosition;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
