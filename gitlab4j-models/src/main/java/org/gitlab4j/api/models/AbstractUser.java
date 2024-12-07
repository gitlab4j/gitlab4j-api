package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractUser<U extends AbstractUser<U>> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    @JsonProperty("email")
    private String email;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("state")
    private String state;

    @JsonProperty("username")
    private String username;

    @JsonProperty("web_url")
    private String webUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @SuppressWarnings("unchecked")
    public U withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U withEmail(String email) {
        this.email = email;
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U withId(Long id) {
        this.id = id;
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U withName(String name) {
        this.name = name;
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U withState(String state) {
        this.state = state;
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U withUsername(String username) {
        this.username = username;
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U withWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return (U) this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
