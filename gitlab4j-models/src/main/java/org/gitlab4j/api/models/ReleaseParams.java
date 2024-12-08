package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReleaseParams implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tag_name")
    private String tagName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("milestones")
    private List<String> milestones;

    @JsonProperty("assets")
    private Assets assets;

    @JsonProperty("released_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date releasedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReleaseParams withName(String name) {
        this.name = name;
        return (this);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ReleaseParams withTagName(String tagName) {
        this.tagName = tagName;
        return (this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReleaseParams withDescription(String description) {
        this.description = description;
        return (this);
    }

    public List<String> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<String> milestones) {
        this.milestones = milestones;
    }

    public ReleaseParams withMilestones(List<String> milestones) {
        this.milestones = milestones;
        return (this);
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public ReleaseParams withRef(String ref) {
        this.ref = ref;
        return (this);
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public ReleaseParams withAssets(Assets assets) {
        this.assets = assets;
        return (this);
    }

    public Date getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(Date releasedAt) {
        this.releasedAt = releasedAt;
    }

    public ReleaseParams withReleasedAt(Date releasedAt) {
        this.releasedAt = releasedAt;
        return (this);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
