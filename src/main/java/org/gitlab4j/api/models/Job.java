package org.gitlab4j.api.models;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Job {

    private Integer id;
    private Commit commit;
    private String coverage;
    private Date createdAt;
    private Date finishedAt;
    private String name;
    private Pipeline pipeline;
    private String ref;
    private Runner runner;
    private User user;
    private Date startedAt;
    private ArtifactsFile artifactsFile;
    private Boolean tag;
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

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }
}
