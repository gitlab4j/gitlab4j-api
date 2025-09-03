package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileUpload implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The alternate text for the file.
     */
    @JsonProperty("alt")
    private String alt;

    /**
     * The URL of the uploaded file.
     */
    @JsonProperty("url")
    private String url;

    /**
     * The markdown representation of the file.
     */
    @JsonProperty("markdown")
    private String markdown;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
