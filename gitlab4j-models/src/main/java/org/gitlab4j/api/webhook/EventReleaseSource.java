package org.gitlab4j.api.webhook;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventReleaseSource {
    @JsonProperty("format")
    private String format;

    @JsonProperty("url")
    private String url;

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
