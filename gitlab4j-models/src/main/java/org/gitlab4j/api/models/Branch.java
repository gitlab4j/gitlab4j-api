package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The commit associated with the branch.
     */
    @JsonProperty("commit")
    private Commit commit;

    /**
     * Indicates whether developers can merge to this branch.
     */
    @JsonProperty("developers_can_merge")
    private Boolean developersCanMerge;

    /**
     * Indicates whether developers can push to this branch.
     */
    @JsonProperty("developers_can_push")
    private Boolean developersCanPush;

    /**
     * Indicates whether the branch is merged.
     */
    @JsonProperty("merged")
    private Boolean merged;

    /**
     * The name of the branch.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Indicates whether the branch is protected.
     */
    @JsonProperty("protected")
    private Boolean isProtected;

    /**
     * Indicates whether the branch is the default branch.
     */
    @JsonProperty("default")
    private Boolean isDefault;

    /**
     * Indicates whether pushing is allowed on this branch.
     */
    @JsonProperty("can_push")
    private Boolean canPush;

    /**
     * The web URL associated with the branch.
     */
    @JsonProperty("web_url")
    private String webUrl;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Boolean getDevelopersCanMerge() {
        return developersCanMerge;
    }

    public void setDevelopersCanMerge(Boolean developersCanMerge) {
        this.developersCanMerge = developersCanMerge;
    }

    public Boolean getDevelopersCanPush() {
        return developersCanPush;
    }

    public void setDevelopersCanPush(Boolean developersCanPush) {
        this.developersCanPush = developersCanPush;
    }

    public Boolean getMerged() {
        return merged;
    }

    public void setMerged(Boolean merged) {
        this.merged = merged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getProtected() {
        return isProtected;
    }

    public void setProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getCanPush() {
        return canPush;
    }

    public void setCanPush(Boolean canPush) {
        this.canPush = canPush;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public static final boolean isValid(Branch branch) {
        return (branch != null && branch.getName() != null);
    }

    public Branch withCommit(Commit commit) {
        this.commit = commit;
        return this;
    }

    public Branch withDevelopersCanMerge(Boolean developersCanMerge) {
        this.developersCanMerge = developersCanMerge;
        return this;
    }

    public Branch withDevelopersCanPush(Boolean developersCanPush) {
        this.developersCanPush = developersCanPush;
        return this;
    }

    public Branch withMerged(Boolean merged) {
        this.merged = merged;
        return this;
    }

    public Branch withName(String name) {
        this.name = name;
        return this;
    }

    public Branch withIsProtected(Boolean isProtected) {
        this.isProtected = isProtected;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
