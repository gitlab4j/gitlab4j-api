package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CommitStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Indicates if failure is allowed for this commit status.
     */
    @JsonProperty("allow_failure")
    private Boolean allowFailure;

    /**
     * The author of the commit status.
     */
    @JsonProperty("author")
    private Author author;

    /**
     * The coverage percentage associated with the commit status.
     */
    @JsonProperty("coverage")
    private Float coverage;

    /**
     * The creation date of the commit status.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    /**
     * The description of the commit status.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The date the commit status was finished.
     */
    @JsonProperty("finished_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date finishedAt;

    /**
     * The unique identifier of the commit status.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the commit status.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The pipeline ID associated with the commit status.
     */
    @JsonProperty("pipeline_id")
    private Long pipelineId;

    /**
     * The reference of the commit status (e.g., branch name).
     */
    @JsonProperty("ref")
    private String ref;

    /**
     * The SHA associated with the commit status.
     */
    @JsonProperty("sha")
    private String sha;

    /**
     * The date the commit status was started.
     */
    @JsonProperty("started_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date startedAt;

    /**
     * The status of the commit (e.g., success, failed).
     */
    @JsonProperty("status")
    private String status;

    /**
     * The target URL associated with the commit status.
     */
    @JsonProperty("target_url")
    private String targetUrl;

    public Boolean isAllowFailure() {
        return allowFailure;
    }

    public void setAllowFailure(Boolean allowFailure) {
        this.allowFailure = allowFailure;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Float getCoverage() {
        return coverage;
    }

    public void setCoverage(Float coverage) {
        this.coverage = coverage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishedAt() {
        return this.finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
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

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
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

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public CommitStatus withCoverage(Float coverage) {
        this.coverage = coverage;
        return this;
    }

    public CommitStatus withDescription(String description) {
        this.description = description;
        return this;
    }

    public CommitStatus withName(String name) {
        this.name = name;
        return this;
    }

    public CommitStatus withPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
        return this;
    }

    public CommitStatus withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public CommitStatus withTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
