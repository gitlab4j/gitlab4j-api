package org.gitlab4j.api.models;

import java.util.Map;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EpicIssue extends AbstractIssue {
    private static final long serialVersionUID = 1L;

    @JsonProperty("_links")
    private Map<String, String> links;

    private Long epicIssueId;
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
