
package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;
import java.util.Date;

public class CommitStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean allowFailure;
    private Author author;
    private Float coverage;
    private Date createdAt;
    private String description;
    private Date finishedAt;
    private Long id;
    private String name;
    private Long pipelineId;
    private String ref;
    private String sha;
    private Date startedAt;
    private String status;
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
