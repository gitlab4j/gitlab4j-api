
package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;

public class EpicIssueLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer relativePosition;
    private Epic epic;
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
