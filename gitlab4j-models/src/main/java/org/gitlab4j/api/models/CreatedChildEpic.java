package org.gitlab4j.api.models;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatedChildEpic extends AbstractMinimalEpic<CreatedChildEpic> {
    private static final long serialVersionUID = 1L;

    /**
     * Indicates if the child epic has children.
     */
    @JsonProperty("has_children")
    private Boolean hasChildren;

    /**
     * Indicates if the child epic has issues.
     */
    @JsonProperty("has_issues")
    private Boolean hasIssues;

    /**
     * The relation URL associated with the child epic.
     */
    @JsonProperty("relation_url")
    private String relationUrl;

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Boolean getHasIssues() {
        return hasIssues;
    }

    public void setHasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public String getRelationUrl() {
        return relationUrl;
    }

    public void setRelationUrl(String relationUrl) {
        this.relationUrl = relationUrl;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
