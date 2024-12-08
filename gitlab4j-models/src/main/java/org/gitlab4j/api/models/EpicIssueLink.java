package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EpicIssueLink implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the epic issue link.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The relative position of the issue in relation to the epic.
     */
    @JsonProperty("relative_position")
    private Integer relativePosition;

    /**
     * The epic associated with the issue.
     */
    @JsonProperty("epic")
    private Epic epic;

    /**
     * The issue associated with the epic.
     */
    @JsonProperty("issue")
    private Issue issue;

    public Long getId() {
        return id;
    }

    public void setId(Long epicIssueId) {
        this.id = epicIssueId;
    }

    public Integer getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(Integer relativePosition) {
        this.relativePosition = relativePosition;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
