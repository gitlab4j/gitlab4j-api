package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

public class Markdown implements Serializable {
    private static final long serialVersionUID = 1L;

    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
