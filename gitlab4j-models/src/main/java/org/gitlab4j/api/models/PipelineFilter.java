package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.Constants.PipelineOrderBy;
import org.gitlab4j.models.Constants.PipelineScope;
import org.gitlab4j.models.Constants.PipelineSource;
import org.gitlab4j.models.Constants.SortOrder;
import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  This class is used to filter Pipelines when getting lists of them.
 */
public class PipelineFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    /** {@link org.gitlab4j.models.Constants.PipelineScope} The scope of pipelines, one of: running, pending, finished, branches, tags */
    @JsonProperty("scope")
    private PipelineScope scope;

    /** {@link org.gitlab4j.models.Constants.PipelineScope} The status of pipelines, one of: running, pending, success, failed, canceled, skipped, created */
    @JsonProperty("status")
    private PipelineStatus status;

    /** {@link org.gitlab4j.models.Constants.PipelineSource} The source of pipelines */
    @JsonProperty("source")
    private PipelineSource source;

    /** The ref of pipelines. */
    @JsonProperty("ref")
    private String ref;

    /** The SHA of pipelines. */
    @JsonProperty("sha")
    private String sha;

    /** If true, returns pipelines with invalid configurations. */
    @JsonProperty("yaml_errors")
    private Boolean yamlErrors;

    /** The name of the user who triggered pipelines. */
    @JsonProperty("name")
    private String name;

    /** The username of the user who triggered pipelines */
    @JsonProperty("username")
    private String username;

    /** Return pipelines updated after the specified date. */
    @JsonProperty("updated_after")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAfter;

    /** Return pipelines updated before the specified date. */
    @JsonProperty("updated_before")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedBefore;

    /** {@link org.gitlab4j.models.Constants.PipelineOrderBy} Order pipelines by id, status, ref, updated_at or user_id (default: id). */
    @JsonProperty("order_by")
    private PipelineOrderBy orderBy;

    /** {@link org.gitlab4j.models.Constants.SortOrder} Return issues sorted in asc or desc order. Default is desc. */
    @JsonProperty("sort")
    private SortOrder sort;

    public void setScope(PipelineScope scope) {
        this.scope = scope;
    }

    public void setStatus(PipelineStatus status) {
        this.status = status;
    }

    public void setSource(PipelineSource source) {
        this.source = source;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setYamlErrors(Boolean yamlErrors) {
        this.yamlErrors = yamlErrors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    public void setUpdatedBefore(Date updatedBefore) {
        this.updatedBefore = updatedBefore;
    }

    public void setOrderBy(PipelineOrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public void setSort(SortOrder sort) {
        this.sort = sort;
    }

    public PipelineFilter withScope(PipelineScope scope) {
        this.scope = scope;
        return this;
    }

    public PipelineFilter withStatus(PipelineStatus status) {
        this.status = status;
        return this;
    }

    public PipelineFilter withSource(PipelineSource source) {
        this.source = source;
        return this;
    }

    public PipelineFilter withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public PipelineFilter withSha(String sha) {
        this.sha = sha;
        return this;
    }

    public PipelineFilter withYamlErrors(Boolean yamlErrors) {
        this.yamlErrors = yamlErrors;
        return this;
    }

    public PipelineFilter withName(String name) {
        this.name = name;
        return this;
    }

    public PipelineFilter withUsername(String username) {
        this.username = username;
        return this;
    }

    public PipelineFilter withUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
        return this;
    }

    public PipelineFilter withUpdatedBefore(Date updatedBefore) {
        this.updatedBefore = updatedBefore;
        return this;
    }

    public PipelineFilter withOrderBy(PipelineOrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public PipelineFilter withSort(SortOrder sort) {
        this.sort = sort;
        return this;
    }

    @JsonIgnore
    public GitLabForm getQueryParams() {
        return (new GitLabForm()
                .withParam("scope", scope)
                .withParam("status", status)
                .withParam("source", source)
                .withParam("ref", ref)
                .withParam("sha", sha)
                .withParam("yaml_errors", yamlErrors)
                .withParam("name", name)
                .withParam("username", username)
                .withParam("updated_after", updatedAfter)
                .withParam("updated_before", updatedBefore)
                .withParam("order_by", orderBy)
                .withParam("sort", sort));
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
