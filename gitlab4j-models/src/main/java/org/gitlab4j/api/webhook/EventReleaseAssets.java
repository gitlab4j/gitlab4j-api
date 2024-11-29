package org.gitlab4j.api.webhook;

import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

public class EventReleaseAssets {

    private Integer count;
    private List<EventReleaseLink> links;
    private List<EventReleaseSource> sources;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<EventReleaseLink> getLinks() {
        return links;
    }

    public void setLinks(List<EventReleaseLink> links) {
        this.links = links;
    }

    public List<EventReleaseSource> getSources() {
        return sources;
    }

    public void setSources(List<EventReleaseSource> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
