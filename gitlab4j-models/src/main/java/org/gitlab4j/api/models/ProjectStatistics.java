package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains the sizing information from the project. To get this information,
 * ProjectApi.getProject() has to be called with parameter statistics=true
 * which is only allowed for GitLab admins.
 */
public class ProjectStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("commit_count")
    private long commitCount;

    @JsonProperty("storage_size")
    private long storageSize;

    @JsonProperty("repository_size")
    private long repositorySize;

    @JsonProperty("wiki_size")
    private long wikiSize;

    @JsonProperty("lfs_objects_size")
    private long lfsObjectsSize;

    @JsonProperty("job_artifacts_size")
    private long jobArtifactsSize;

    @JsonProperty("packages_size")
    private long packagesSize;

    public long getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(long commitCount) {
        this.commitCount = commitCount;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    public long getRepositorySize() {
        return repositorySize;
    }

    public void setRepositorySize(long repositorySize) {
        this.repositorySize = repositorySize;
    }

    public long getWikiSize() {
        return wikiSize;
    }

    public void setWikiSize(long wikiSize) {
        this.wikiSize = wikiSize;
    }

    public long getLfsObjectsSize() {
        return lfsObjectsSize;
    }

    public void setLfsObjectsSize(long lfsObjectsSize) {
        this.lfsObjectsSize = lfsObjectsSize;
    }

    public long getJobArtifactsSize() {
        return jobArtifactsSize;
    }

    public void setJobArtifactsSize(long jobArtifactsSize) {
        this.jobArtifactsSize = jobArtifactsSize;
    }

    public long getPackagesSize() {
        return packagesSize;
    }

    public void setPackagesSize(long packagesSize) {
        this.packagesSize = packagesSize;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
