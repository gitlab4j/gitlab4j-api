package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ImpersonationToken implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Enum to specify the scope of an ImpersonationToken. */
    public enum Scope {
        API,
        READ_API,
        READ_USER,
        READ_REPOSITORY,
        WRITE_REPOSITORY,
        READ_REGISTRY,
        WRITE_REGISTRY,
        K8S_PROXY,
        SUDO;

        private static JacksonJsonEnumHelper<Scope> enumHelper = new JacksonJsonEnumHelper<>(Scope.class);

        @JsonCreator
        public static Scope forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /**
     * Indicates if the token is active.
     */
    @JsonProperty("active")
    private Boolean active;

    /**
     * The token string.
     */
    @JsonProperty("token")
    private String token;

    /**
     * List of scopes associated with the token.
     */
    @JsonProperty("scopes")
    private List<Scope> scopes;

    /**
     * The user ID associated with the token.
     */
    @JsonProperty("user_id")
    private Long userId;

    /**
     * Indicates if the token has been revoked.
     */
    @JsonProperty("revoked")
    private Boolean revoked;

    /**
     * The name of the token.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The description of the token.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The unique identifier of the token.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The creation date of the token.
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The last used date of the token.
     */
    @JsonProperty("last_used_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date lastUsedAt;

    /**
     * Indicates if the token is used for impersonation.
     */
    @JsonProperty("impersonation")
    private Boolean impersonation;

    /**
     * The expiration date of the token.
     */
    @JsonProperty("expires_at")
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date expiresAt;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(List<Scope> scopes) {
        this.scopes = scopes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUsedAt() {
        return lastUsedAt;
    }

    public void setLastUsedAt(Date lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }

    public Boolean getImpersonation() {
        return impersonation;
    }

    public void setImpersonation(Boolean impersonation) {
        this.impersonation = impersonation;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
