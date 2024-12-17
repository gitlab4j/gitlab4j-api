package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueLink implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The source issue in the link.
     */
    @JsonProperty("source_issue")
    private Issue sourceIssue;

    /**
     * The target issue in the link.
     */
    @JsonProperty("target_issue")
    private Issue targetIssue;

    /**
     * The type of the link between the issues.
     */
    @JsonProperty("link_type")
    private LinkType linkType;

    public Issue getSourceIssue() {
        return sourceIssue;
    }

    public void setSourceIssue(Issue sourceIssue) {
        this.sourceIssue = sourceIssue;
    }

    public Issue getTargetIssue() {
        return targetIssue;
    }

    public void setTargetIssue(Issue targetIssue) {
        this.targetIssue = targetIssue;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
