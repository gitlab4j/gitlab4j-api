package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

public class Suggestion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fromLine;
    private Long toLine;
    private Boolean appliable;
    private Boolean applied;
    private String fromContent;
    private String toContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromLine() {
        return fromLine;
    }

    public void setFromLine(Long fromLine) {
        this.fromLine = fromLine;
    }

    public Long getToLine() {
        return toLine;
    }

    public void setToLine(Long toLine) {
        this.toLine = toLine;
    }

    public Boolean getAppliable() {
        return appliable;
    }

    public void setAppliable(Boolean appliable) {
        this.appliable = appliable;
    }

    public Boolean getApplied() {
        return applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

    public String getFromContent() {
        return fromContent;
    }

    public void setFromContent(String fromContent) {
        this.fromContent = fromContent;
    }

    public String getToContent() {
        return toContent;
    }

    public void setToContent(String toContent) {
        this.toContent = toContent;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
