package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;
import java.util.Date;

public class Bridge implements Serializable {
    private static final long serialVersionUID = 1L;

    private Commit commit;
    private boolean allowFailure;
    private Date createdAt;
    private Date startedAt;
    private Date finishedAt;
    private Date erasedAt;
    private Double duration;
    private Double queuedDuration;
    private int id;
    private String name;
    private String coverage;
    private Pipeline pipeline;
    private String ref;
    private String stage;
    private String status;
    private boolean tag;
    private String webUrl;
    private User user;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
