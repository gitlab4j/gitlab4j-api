package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.Constants.DeploymentStatus;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Deployment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the deployment.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The internal identifier of the deployment.
     */
    @JsonProperty("iid")
    private Long iid;

    /**
     * The reference associated with the deployment (e.g., branch or tag).
     */
    @JsonProperty("ref")
    private String ref;

    /**
     * The SHA associated with the deployment.
     */
    @JsonProperty("sha")
    private String sha;

    /**
     * The creation date of the deployment.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    /**
     * The last updated date of the deployment.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAt;

    /**
     * The status of the deployment (e.g., success, failed).
     */
    @JsonProperty("status")
    private DeploymentStatus status;

    /**
     * The user associated with the deployment.
     */
    @JsonProperty("user")
    private User user;

    /**
     * The environment associated with the deployment.
     */
    @JsonProperty("environment")
    private Environment environment;

    /**
     * The deployable associated with the deployment.
     */
    @JsonProperty("deployable")
    private Deployable deployable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DeploymentStatus getStatus() {
        return status;
    }

    public void setStatus(DeploymentStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Deployable getDeployable() {
        return deployable;
    }

    public void setDeployable(Deployable deployable) {
        this.deployable = deployable;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
