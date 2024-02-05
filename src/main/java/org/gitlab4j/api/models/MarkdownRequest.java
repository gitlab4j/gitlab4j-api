package org.gitlab4j.api.models;

import java.io.Serializable;

public class MarkdownRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private boolean gfm;
    private String project;

    public MarkdownRequest(String text, boolean gfm) {
        this.text = text;
        this.gfm = gfm;
    }

    public MarkdownRequest(String text, boolean gfm, String project) {
        this.text = text;
        this.gfm = gfm;
        this.project = project;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isGfm() {
        return gfm;
    }

    public void setGfm(boolean gfm) {
        this.gfm = gfm;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
