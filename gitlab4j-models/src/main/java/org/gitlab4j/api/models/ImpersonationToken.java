package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ImpersonationToken implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Enum to specify the scope of an ImpersonationToken. */
    public enum Scope {
        /** Grants complete read/write access to the API. */
        API,

        /** Grants read access to the API. */
        READ_API,

        /** Grants read-only access to the user's profile. */
        READ_USER,

        /** Grants read-only access to repositories on private projects using Git-over-HTTP. */
        READ_REPOSITORY,

        /** Grants read-write access to repositories on private projects using Git-over-HTTP. */
        WRITE_REPOSITORY,

        /** Grants read (pull) access to a Container Registry. */
        READ_REGISTRY,

        /** Grants write (push) access to a Container Registry. */
        WRITE_REGISTRY,

        /** Grants pull access through the dependency proxy. */
        READ_VIRTUAL_REGISTRY,

        /** Grants push, pull and delete access through the dependency proxy. */
        WRITE_VIRTUAL_REGISTRY,

        /** Grants create access to the runners. */
        CREATE_RUNNER,

        /** Grants access to manage the runners. */
        MANAGE_RUNNER,

        /** Grants access to GitLab Duo related API endpoints. */
        AI_FEATURES,

        /** Grants permission to perform Kubernetes API calls using the agent for Kubernetes. */
        K8S_PROXY,

        /** Grants permission to rotate this token using the personal access token API. */
        SELF_ROTATE,

        /** Grants permission to perform API actions as any user in the system, when authenticated as an admin user. */
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

    private Boolean active;
    private String token;
    private List<Scope> scopes;
    private Long userId;
    private Boolean revoked;
    private String name;
    private String description;
    private Long id;
    private Date createdAt;
    private Date lastUsedAt;
    private Boolean impersonation;

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
