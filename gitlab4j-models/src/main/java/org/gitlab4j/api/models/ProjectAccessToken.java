package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.Constants;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectAccessToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("scopes")
    private List<Constants.ProjectAccessTokenScope> scopes;

    @JsonProperty("name")
    private String name;

    @JsonProperty("expires_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date expiresAt;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    @JsonProperty("revoked")
    private Boolean revoked;

    @JsonProperty("access_level")
    private Long accessLevel;

    @JsonProperty("last_used_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date lastUsedAt;

    @JsonProperty("token")
    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Constants.ProjectAccessTokenScope> getScopes() {
        return scopes;
    }

    public void setScopes(List<Constants.ProjectAccessTokenScope> scopes) {
        this.scopes = scopes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiredAt) {
        this.expiresAt = expiredAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public Long getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Long accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Date getLastUsedAt() {
        return lastUsedAt;
    }

    public void setLastUsedAt(Date lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return JacksonJson.toJsonString(this);
    }
}
