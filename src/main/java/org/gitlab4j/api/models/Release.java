package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Release implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String tagName;
    private String description;
    private String descriptionHtml;
    private Date createdAt;
    private Date releasedAt;
    private Author author;
    private Commit commit;
    private List<Milestone> milestones;
    private String commitPath;
    private String tagPath;
    private String evidenceSha;
    private Assets assets;
    @JsonProperty("_links")
    private Map<String, String> links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(Date releasedAt) {
        this.releasedAt = releasedAt;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public String getCommitPath() {
        return commitPath;
    }

    public void setCommitPath(String commitPath) {
        this.commitPath = commitPath;
    }

    public String getTagPath() {
        return tagPath;
    }

    public void setTagPath(String tagPath) {
        this.tagPath = tagPath;
    }

    public String getEvidenceSha() {
        return evidenceSha;
    }

    public void setEvidenceSha(String evidenceSha) {
        this.evidenceSha = evidenceSha;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    @JsonIgnore
    public String getLinkByName(String name) {
        if (links == null || links.isEmpty()) {
            return (null);
        }

        return (links.get(name));
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
