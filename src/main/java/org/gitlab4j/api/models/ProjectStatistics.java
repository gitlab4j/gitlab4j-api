package org.gitlab4j.api.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * contains sizing information from the project.
 * To get this information, the project api has to be called with parameter statistics=true 
 * which is only allowed for gitlab admins.
 *
 * Example json from projects api response:
 * https://<gitlab>/api/v4//projects?statistics=true
 *
 * "statistics": {
 *       "commit_count": 37,
 *       "storage_size": 1038090,
 *       "repository_size": 1038090,
 *       "lfs_objects_size": 0,
 *       "job_artifacts_size": 0
 *     }
 */
@XmlAccessorType(XmlAccessType.FIELD)
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
}
