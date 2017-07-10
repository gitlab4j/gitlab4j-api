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
    private Date created_at;
    private Date finished_at;
    private String name;
    private Pipeline pipeline;
    private String ref;
    private Runner runner;
    private User user;
    private Date started_at;
    private ArtifactsFile artifacts_file;
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

    public Date getCreated_at() {
    return created_at;
  }

    public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

    public Date getFinished_at() {
    return finished_at;
  }

    public void setFinished_at(Date finished_at) {
    this.finished_at = finished_at;
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

    public Date getStarted_at() {
    return started_at;
  }

    public void setStarted_at(Date started_at) {
    this.started_at = started_at;
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

    public ArtifactsFile getArtifacts_file() {
        return artifacts_file;
    }

    public void setArtifacts_file(ArtifactsFile artifacts_file) {
        this.artifacts_file = artifacts_file;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }
}
