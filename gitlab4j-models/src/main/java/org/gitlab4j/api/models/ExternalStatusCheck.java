package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalStatusCheck implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the external status check details.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name associated with the external status check details.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The project ID associated with the external status check.
     */
    @JsonProperty("project_id")
    private Long projectId;

    /**
     * The external URL associated with the status check.
     */
    @JsonProperty("external_url")
    private String externalUrl;

    /**
     * The list of protected branches associated with the external status check.
     */
    @JsonProperty("protected_branches")
    private List<ExternalStatusCheckProtectedBranch> protectedBranches;

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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public List<ExternalStatusCheckProtectedBranch> getProtectedBranches() {
        return protectedBranches;
    }

    public void setProtectedBranches(List<ExternalStatusCheckProtectedBranch> protectedBranches) {
        this.protectedBranches = protectedBranches;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
