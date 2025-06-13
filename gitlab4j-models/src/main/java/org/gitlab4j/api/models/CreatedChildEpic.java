package org.gitlab4j.api.models;

import org.gitlab4j.models.utils.JacksonJson;

public class CreatedChildEpic extends AbstractMinimalEpic<CreatedChildEpic> {
    private static final long serialVersionUID = 1L;

    private Boolean hasChildren;
    private Boolean hasIssues;
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
