package org.gitlab4j.api.models;

import java.io.Serializable;

public class MarkdownRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private boolean gfm;

    public MarkdownRequest(String text, boolean gfm) {
        this.text = text;
        this.gfm = gfm;
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
}
