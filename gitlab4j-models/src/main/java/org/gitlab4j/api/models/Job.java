package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Job implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("commit")
    private Commit commit;

    @JsonProperty("coverage")
    private String coverage;

    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    @JsonProperty("finished_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date finishedAt;

    @JsonProperty("erased_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date erasedAt;

    @JsonProperty("artifacts_expire_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date artifactsExpireAt;

    @JsonProperty("name")
    private String name;

    @JsonProperty("pipeline")
    private Pipeline pipeline;

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("runner")
    private Runner runner;

    @JsonProperty("user")
    private User user;

    @JsonProperty("started_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startedAt;

    @JsonProperty("artifacts_file")
    private ArtifactsFile artifactsFile;

    @JsonProperty("artifacts")
    private List<Artifact> artifacts;

    @JsonProperty("tag")
    private Boolean tag;

    @JsonProperty("tag_list")
    private List<String> tagList;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("stage")
    private String stage;

    @JsonProperty("status")
    private JobStatus status;

    @JsonProperty("failure_reason")
    private String failureReason;

    @JsonProperty("when")
    private String when;

    @JsonProperty("manual")
    private Boolean manual;

    @JsonProperty("allow_failure")
    private Boolean allowFailure;

    @JsonProperty("duration")
    private Float duration;

    @JsonProperty("queued_duration")
    private Float queuedDuration;

    @JsonProperty("project")
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    /**
     * When someone deletes job using
     * <a href="https://docs.gitlab.com/ee/api/jobs.html#erase-a-job">job erase api</a>, you can
     * detect it using this field. Normally erasing job does mean only that job artifacts and
     * a job logs gets removed. Job metadata (started_at, duration, ....) stays in place.
     * <p>
     * You can use this attribute to filter out such jobs, that have erased at non-null if you need
     * to.
     */
    public Date getErasedAt() {
        return erasedAt;
    }

    public void setErasedAt(Date erasedAt) {
        this.erasedAt = erasedAt;
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

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
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

    public String getFailureReason() {
        return this.failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
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

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Boolean getAllowFailure() {
        return allowFailure;
    }

    public void setAllowFailure(Boolean allowFailure) {
        this.allowFailure = allowFailure;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getQueuedDuration() {
        return queuedDuration;
    }

    public void setQueuedDuration(Float queuedDuration) {
        this.queuedDuration = queuedDuration;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Job withId(Long id) {
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

    public Job withErasedAt(Date erasedAt) {
        this.erasedAt = erasedAt;
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

    public Job withWhen(String when) {
        this.when = when;
        return this;
    }

    public Job withManual(Boolean manual) {
        this.manual = manual;
        return this;
    }

    public Job withAllowFailure(Boolean allowFailure) {
        this.allowFailure = allowFailure;
        return this;
    }

    public Job withDuration(Float duration) {
        this.duration = duration;
        return this;
    }

    public Job withQueuedDuration(Float queuedDuration) {
        this.queuedDuration = queuedDuration;
        return this;
    }

    public Job withProject(Project project) {
        this.project = project;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
