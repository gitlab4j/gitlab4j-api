package org.gitlab4j.api.webhook;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.models.ArtifactsFile;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.utils.JacksonJson;

public class PipelineEvent extends AbstractEvent {

    public static final String X_GITLAB_EVENT = "Pipeline Hook";
    public static final String OBJECT_KIND = "pipeline";

    private ObjectAttributes objectAttributes;
    private User user;
    private EventProject project;
    private EventCommit commit;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public List<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(List<Build> builds) {
        this.builds = builds;
    }

    public static class Build {

        private Integer id;
        private String stage;
        private String name;
        private String status;
        private Date createdAt;
        private Date startedAt;
        private Date finishedAt;
        private String when;
        private Boolean manual;
        private User user;
        private ArtifactsFile artifactsFile;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public ArtifactsFile getArtifactsFile() {
            return artifactsFile;
        }

        public void setArtifactsFile(ArtifactsFile artifactsFile) {
            this.artifactsFile = artifactsFile;
        }
    }

    public static class ObjectAttributes {

        private Integer id;
        private String ref;
        private Boolean tag;
        private String sha;
        private String beforeSha;
        private String status;
        private List<String> stages;
        private Date createdAt;
        private Date finishedAt;
        private Integer duration;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
