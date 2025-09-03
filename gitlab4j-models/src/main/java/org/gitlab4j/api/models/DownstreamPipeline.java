package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class DownstreamPipeline implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the downstream pipeline.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The SHA of the downstream pipeline.
     */
    @JsonProperty("sha")
    private String sha;

    /**
     * The reference for the downstream pipeline (e.g., branch name).
     */
    @JsonProperty("ref")
    private String ref;

    /**
     * The status of the downstream pipeline.
     */
    @JsonProperty("status")
    private String status;

    /**
     * The creation date of the downstream pipeline.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    /**
     * The last updated date of the downstream pipeline.
     */
    @JsonProperty("updated_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date updatedAt;

    /**
     * The web URL of the downstream pipeline.
     */
    @JsonProperty("web_url")
    private String webUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
