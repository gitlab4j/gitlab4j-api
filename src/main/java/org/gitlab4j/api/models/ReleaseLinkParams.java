package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.util.Date;
import java.util.List;

public class ReleaseLinkParams {

    private String name;
    private String tagName;
    private String url;
    private String filepath;
    private String linkType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReleaseLinkParams withName(String name) {
        this.name = name;
        return (this);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ReleaseLinkParams withTagName(String tagName) {
        this.tagName = tagName;
        return (this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ReleaseLinkParams withUrl(String url) {
        this.url = url;
        return (this);
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public ReleaseLinkParams withFilepath(String filepath) {
        this.filepath = filepath;
        return (this);
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public ReleaseLinkParams withLinkType(String linkType) {
        this.linkType = linkType;
        return (this);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
