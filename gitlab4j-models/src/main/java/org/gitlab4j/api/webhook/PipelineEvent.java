package org.gitlab4j.api.webhook;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.models.Build;
import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.Variable;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PipelineEvent extends AbstractEvent {
    private static final long serialVersionUID = 1L;

    public static final String X_GITLAB_EVENT = "Pipeline Hook";
    public static final String OBJECT_KIND = "pipeline";

    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;

    @JsonProperty("merge_request")
    private EventMergeRequest mergeRequest;

    @JsonProperty("user")
    private EventUser user;

    @JsonProperty("project")
    private EventProject project;

    @JsonProperty("commit")
    private EventCommit commit;

    @JsonProperty("jobs")
    private List<Job> jobs;

    @JsonProperty("builds")
    private List<Build> builds;

    public String getObjectKind() {
        return (OBJECT_KIND);
    }

    public void setObjectKind(String objectKind) {
        if (!OBJECT_KIND.equals(objectKind))
            throw new RuntimeException("Invalid object_kind (" + objectKind + "), must be '" + OBJECT_KIND + "'");
    }

    public ObjectAttributes getObjectAttributes() {
        return this.objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributes objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    public EventMergeRequest getMergeRequest() {
        return mergeRequest;
    }

    public void setMergeRequest(EventMergeRequest mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    public EventUser getUser() {
        return user;
    }

    public void setUser(EventUser user) {
        this.user = user;
    }

    public EventProject getProject() {
        return project;
    }

    public void setProject(EventProject project) {
        this.project = project;
    }

    public EventCommit getCommit() {
        return commit;
    }

    public void setCommit(EventCommit commit) {
        this.commit = commit;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(List<Build> builds) {
        this.builds = builds;
    }

    public static class ObjectAttributes {

        private Long id;
        private Long iid;
        private String name;
        private String ref;
        private Boolean tag;
        private String sha;
        private String beforeSha;
        private String source;
        private String status;
        private String detailedStatus;
        private List<String> stages;
        private Date createdAt;
        private Date finishedAt;
        private Integer duration;
        private Float queuedDuration;
        private List<Variable> variables;
        private String url;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getIid() {
            return iid;
        }

        public void setIid(Long iid) {
            this.iid = iid;
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

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getBeforeSha() {
            return beforeSha;
        }

        public void setBeforeSha(String beforeSha) {
            this.beforeSha = beforeSha;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDetailedStatus() {
            return detailedStatus;
        }

        public void setDetailedStatus(String detailedStatus) {
            this.detailedStatus = detailedStatus;
        }

        public List<String> getStages() {
            return stages;
        }

        public void setStages(List<String> stages) {
            this.stages = stages;
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

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Float getQueuedDuration() {
            return queuedDuration;
        }

        public void setQueuedDuration(Float queuedDuration) {
            this.queuedDuration = queuedDuration;
        }

        public List<Variable> getVariables() {
            return variables;
        }

        public void setVariables(List<Variable> variables) {
            this.variables = variables;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
