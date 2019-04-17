
package org.gitlab4j.api.models;

import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

public class Group {

    public class Statistics {
        private Integer storageSize;
        private Integer repositorySize;
        private Integer lfsObjectsSize;
        private Integer jobArtifactsSize;

        public Integer getStorageSize() {
            return storageSize;
        }

        public void setStorageSize(Integer storageSize) {
            this.storageSize = storageSize;
        }

        public Integer getRepositorySize() {
            return repositorySize;
        }

        public void setRepositorySize(Integer repositorySize) {
            this.repositorySize = repositorySize;
        }

        public Integer getLfsObjectsSize() {
            return lfsObjectsSize;
        }

        public void setLfsObjectsSize(Integer lfsObjectsSize) {
            this.lfsObjectsSize = lfsObjectsSize;
        }

        public Integer getJobArtifactsSize() {
            return jobArtifactsSize;
        }

        public void setJobArtifactsSize(Integer jobArtifactsSize) {
            this.jobArtifactsSize = jobArtifactsSize;
        }
    }


    private Integer id;
    private String name;
    private String path;
    private String description;
    private Visibility visibility;
    private Boolean lfsEnabled;
    private String avatarUrl;
    private String webUrl;
    private Boolean requestAccessEnabled;
    private String fullName;
    private String fullPath;
    private Integer parentId;
    private Integer sharedRunnersMinutesLimit;
    private Statistics statistics;
    private List<Project> projects;
    private List<Project> sharedProjects;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Boolean getLfsEnabled() {
        return lfsEnabled;
    }

    public void setLfsEnabled(Boolean lfsEnabled) {
        this.lfsEnabled = lfsEnabled;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Boolean getRequestAccessEnabled() {
        return requestAccessEnabled;
    }

    public void setRequestAccessEnabled(Boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSharedRunnersMinutesLimit() {
        return sharedRunnersMinutesLimit;
    }

    public void setSharedRunnersMinutesLimit(Integer sharedRunnersMinutesLimit) {
        this.sharedRunnersMinutesLimit = sharedRunnersMinutesLimit;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public List<Project> getProjects() {
        return (projects);
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getSharedProjects() {
        return (sharedProjects);
    }

    public void setSharedProjects(List<Project> sharedProjects) {
        this.sharedProjects = sharedProjects;
    }

    public Group withId(Integer id) {
        this.id = id;
        return this;
    }

    public Group withName(String name) {
        this.name = name;
        return this;
    }

    public Group withPath(String path) {
        this.path = path;
        return this;
    }

    public Group withDescription(String description) {
        this.description = description;
        return this;
    }

    public Group withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public Group withlfsEnabled(boolean lfsEnabled) {
        this.lfsEnabled = lfsEnabled;
        return this;
    }

    public Group withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public Group withWebUrl(String url) {
        this.webUrl = url;
        return this;
    }

    public Group withRequestAccessEnabled(boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
        return this;
    }

    public Group withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Group withFullPath(String fullPath) {
        this.fullPath = fullPath;
        return this;
    }

    public Group withParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public Group withSharedRunnersMinutesLimit(Integer minutesLimit) {
        this.sharedRunnersMinutesLimit = minutesLimit;
        return this;
    }

    public Group withStatistics(Statistics statistics) {
        this.statistics = statistics;
        return this;
    }

    public Group withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Group withSharedProjects(List<Project> sharedProjects) {
        this.sharedProjects = sharedProjects;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
