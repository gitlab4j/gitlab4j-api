package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.Constants.DeploymentStatus;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Deployable implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the deployable.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The status of the deployable.
     */
    @JsonProperty("status")
    private DeploymentStatus status;

    /**
     * The stage of the deployable.
     */
    @JsonProperty("stage")
    private String stage;

    /**
     * The name of the deployable.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The reference associated with the deployable.
     */
    @JsonProperty("ref")
    private String ref;

    /**
     * Indicates if the deployable is a tag.
     */
    @JsonProperty("tag")
    private Boolean tag;

    /**
     * The coverage of the deployable.
     */
    @JsonProperty("coverage")
    private Float coverage;

    /**
     * The creation date of the deployable.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The date the deployable was started.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("started_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startedAt;

    /**
     * The date the deployable was finished.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("finished_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date finishedAt;

    /**
     * The duration of the deployable process.
     */
    @JsonProperty("duration")
    private Double duration;

    /**
     * The user associated with the deployable.
     */
    @JsonProperty("user")
    private User user;

    /**
     * The commit associated with the deployable.
     */
    @JsonProperty("commit")
    private Commit commit;

    /**
     * The pipeline associated with the deployable.
     */
    @JsonProperty("pipeline")
    private Pipeline pipeline;

    /**
     * The web URL associated with the deployable.
     */
    @JsonProperty("web_url")
    private String webUrl;

    /**
     * The list of artifacts associated with the deployable.
     */
    @JsonProperty("artifacts")
    private List<Artifact> artifacts;

    /**
     * The runner associated with the deployable.
     */
    @JsonProperty("runner")
    private Runner runner;

    /**
     * The expiration date of the artifacts.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("artifacts_expire_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date artifactsExpireAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeploymentStatus getStatus() {
        return status;
    }

    public void setStatus(DeploymentStatus status) {
        this.status = status;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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

    public Date getArtifactsExpireAt() {
        return artifactsExpireAt;
    }

    public void setArtifactsExpireAt(Date artifactsExpireAt) {
        this.artifactsExpireAt = artifactsExpireAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
