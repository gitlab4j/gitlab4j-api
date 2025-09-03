package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractGroup<G extends AbstractGroup<G>> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the group.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the group.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The avatar URL associated with the group.
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * The web URL of the group.
     */
    @JsonProperty("web_url")
    private String webUrl;

    /**
     * The full name of the group.
     */
    @JsonProperty("full_name")
    private String fullName;

    /**
     * The full path of the group.
     */
    @JsonProperty("full_path")
    private String fullPath;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    @SuppressWarnings("unchecked")
    public G withId(Long id) {
        this.id = id;
        return (G) this;
    }

    @SuppressWarnings("unchecked")
    public G withName(String name) {
        this.name = name;
        return (G) this;
    }

    @SuppressWarnings("unchecked")
    public G withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return (G) this;
    }

    @SuppressWarnings("unchecked")
    public G withWebUrl(String url) {
        this.webUrl = url;
        return (G) this;
    }

    @SuppressWarnings("unchecked")
    public G withFullName(String fullName) {
        this.fullName = fullName;
        return (G) this;
    }

    @SuppressWarnings("unchecked")
    public G withFullPath(String fullPath) {
        this.fullPath = fullPath;
        return (G) this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
