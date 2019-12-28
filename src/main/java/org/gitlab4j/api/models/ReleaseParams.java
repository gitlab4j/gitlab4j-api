package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

public class ReleaseParams {

    private String name;
    private String tagName;
    private String description;
    private String ref;
    private List<Milestone> milestones;
    private Assets assets;
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

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public ReleaseParams withMilestones(List<Milestone> milestones) {
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
