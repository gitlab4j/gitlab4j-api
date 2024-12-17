package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.Constants;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeployToken implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the deploy token.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the deploy token.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The username associated with the deploy token.
     */
    @JsonProperty("username")
    private String username;

    /**
     * The expiration date of the deploy token.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("expires_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date expiresAt;

    /**
     * The list of scopes associated with the deploy token.
     */
    @JsonProperty("scopes")
    private List<Constants.DeployTokenScope> scopes;

    /**
     * The token string.
     */
    @JsonProperty("token")
    private String token;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public List<Constants.DeployTokenScope> getScopes() {
        return scopes;
    }

    public void setScopes(List<Constants.DeployTokenScope> scopes) {
        this.scopes = scopes;
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
