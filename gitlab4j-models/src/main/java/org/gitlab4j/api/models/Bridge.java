package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bridge implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The commit associated with the bridge.
     */
    @JsonProperty("commit")
    private Commit commit;

    /**
     * Indicates whether failure is allowed for the bridge.
     */
    @JsonProperty("allow_failure")
    private boolean allowFailure;

    /**
     * The creation date of the bridge.
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The start date of the bridge.
     */
    @JsonProperty("started_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startedAt;

    /**
     * The finish date of the bridge.
     */
    @JsonProperty("finished_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date finishedAt;

    /**
     * The date the bridge was erased.
     */
    @JsonProperty("erased_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date erasedAt;

    /**
     * The duration of the bridge.
     */
    @JsonProperty("duration")
    private Double duration;

    /**
     * The queued duration of the bridge.
     */
    @JsonProperty("queued_duration")
    private Double queuedDuration;

    /**
     * The unique identifier of the bridge.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the bridge.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The coverage associated with the bridge.
     */
    @JsonProperty("coverage")
    private String coverage;

    /**
     * The pipeline associated with the bridge.
     */
    @JsonProperty("pipeline")
    private Pipeline pipeline;

    /**
     * The reference associated with the bridge.
     */
    @JsonProperty("ref")
    private String ref;

    /**
     * The stage associated with the bridge.
     */
    @JsonProperty("stage")
    private String stage;

    /**
     * The status of the bridge.
     */
    @JsonProperty("status")
    private String status;

    /**
     * Indicates whether the bridge is a tag.
     */
    @JsonProperty("tag")
    private boolean tag;

    /**
     * The web URL associated with the bridge.
     */
    @JsonProperty("web_url")
    private String webUrl;

    /**
     * The user associated with the bridge.
     */
    @JsonProperty("user")
    private User user;

    /**
     * The downstream pipeline associated with the bridge.
     */
    @JsonProperty("downstream_pipeline")
    private DownstreamPipeline downstreamPipeline;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public boolean isAllowFailure() {
        return allowFailure;
    }

    public void setAllowFailure(boolean allowFailure) {
        this.allowFailure = allowFailure;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Date getErasedAt() {
        return erasedAt;
    }

    public void setErasedAt(Date erasedAt) {
        this.erasedAt = erasedAt;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getQueuedDuration() {
        return queuedDuration;
    }

    public void setQueuedDuration(Double queuedDuration) {
        this.queuedDuration = queuedDuration;
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

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DownstreamPipeline getDownstreamPipeline() {
        return downstreamPipeline;
    }

    public void setDownstreamPipeline(DownstreamPipeline downstreamPipeline) {
        this.downstreamPipeline = downstreamPipeline;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
