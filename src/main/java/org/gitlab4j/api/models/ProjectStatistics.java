package org.gitlab4j.api.models;


import org.gitlab4j.api.utils.JacksonJson;

/**
 * This class contains the sizing information from the project. To get this information,
 * ProjectApi.getProject() has to be called with parameter statistics=true 
 * which is only allowed for GitLab admins.
 */
public class ProjectStatistics {

    long commitCount;
    long storageSize;
    long lfsObjectSize;
    long jobArtifactsSize;

    public long getCommitCount() {
        return commitCount;
    }

    public long getJobArtifactsSize() {
        return jobArtifactsSize;
    }

    public long getLfsObjectSize() {
        return lfsObjectSize;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setCommitCount(long commitCount) {
        this.commitCount = commitCount;
    }

    public void setJobArtifactsSize(long jobArtifactsSize) {
        this.jobArtifactsSize = jobArtifactsSize;
    }

    public void setLfsObjectSize(long lfsObjectSize) {
        this.lfsObjectSize = lfsObjectSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
