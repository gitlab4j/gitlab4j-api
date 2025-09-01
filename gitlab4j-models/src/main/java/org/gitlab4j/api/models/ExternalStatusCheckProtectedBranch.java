package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalStatusCheckProtectedBranch implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the external status check protected branch.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The project ID associated with the external status check.
     */
    @JsonProperty("project_id")
    private Long projectId;

    /**
     * The name of the external status check protected branch.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The creation date of the external status check protected branch.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The last updated date of the external status check protected branch.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updatedAt;

    /**
     * Indicates if code owner approval is required for the external status check protected branch.
     */
    @JsonProperty("code_owner_approval_required")
    private Boolean codeOwnerApprovalRequired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getCodeOwnerApprovalRequired() {
        return codeOwnerApprovalRequired;
    }

    public void setCodeOwnerApprovalRequired(Boolean codeOwnerApprovalRequired) {
        this.codeOwnerApprovalRequired = codeOwnerApprovalRequired;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
