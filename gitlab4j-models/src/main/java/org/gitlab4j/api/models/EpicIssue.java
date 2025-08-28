package org.gitlab4j.api.models;

import java.util.Map;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EpicIssue extends AbstractIssue {
    private static final long serialVersionUID = 1L;

    /**
     * The links associated with the epic issue.
     */
    @JsonProperty("_links")
    private Map<String, String> links;

    /**
     * The unique identifier of the epic issue.
     */
    @JsonProperty("epic_issue_id")
    private Long epicIssueId;

    /**
     * The relative position of the epic issue in relation to other issues.
     */
    @JsonProperty("relative_position")
    private Integer relativePosition;

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

    public Long getEpicIssueId() {
        return epicIssueId;
    }

    public void setEpicIssueId(Long epicIssueId) {
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
