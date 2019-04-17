package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

public class Job {

    private Integer id;
    private Commit commit;
    private String coverage;
    private Date createdAt;
    private Date finishedAt;
    private Date artifactsExpireAt;
    private String name;
    private Pipeline pipeline;
    private String ref;
    private Runner runner;
    private User user;
    private Date startedAt;
    private ArtifactsFile artifactsFile;
    private List<Artifact> artifacts;
    private Boolean tag;
    private String webUrl;
    private String stage;
    private JobStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Date getArtifactsExpireAt() {
        return artifactsExpireAt;
    }

    public void setArtifactsExpireAt(Date artifactsExpireAt) {
        this.artifactsExpireAt = artifactsExpireAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public ArtifactsFile getArtifactsFile() {
        return artifactsFile;
    }

    public void setArtifactsFile(ArtifactsFile artifactsFile) {
        this.artifactsFile = artifactsFile;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Job withId(Integer id) {
        this.id = id;
        return this;
    }

    public Job withCommit(Commit commit) {
        this.commit = commit;
        return this;
    }

    public Job withCoverage(String coverage) {
        this.coverage = coverage;
        return this;
    }

    public Job withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Job withFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    public Job withName(String name) {
        this.name = name;
        return this;
    }

    public Job withPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public Job withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public Job withRunner(Runner runner) {
        this.runner = runner;
        return this;
    }

    public Job withUser(User user) {
        this.user = user;
        return this;
    }

    public Job withStartedAt(Date startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public Job withArtifactsFile(ArtifactsFile artifactsFile) {
        this.artifactsFile = artifactsFile;
        return this;
    }

    public Job withTag(Boolean tag) {
        this.tag = tag;
        return this;
    }

    public Job withStage(String stage) {
        this.stage = stage;
        return this;
    }

    public Job withStatus(JobStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
