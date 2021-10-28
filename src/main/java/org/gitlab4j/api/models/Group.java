
package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Group {

    public class Statistics {
        private Long storageSize;
        private Long repositorySize;
        private Long lfsObjectsSize;
        private Long jobArtifactsSize;

        public Long getStorageSize() {
            return storageSize;
        }

        public void setStorageSize(Long storageSize) {
            this.storageSize = storageSize;
        }

        public Long getRepositorySize() {
            return repositorySize;
        }

        public void setRepositorySize(Long repositorySize) {
            this.repositorySize = repositorySize;
        }

        public Long getLfsObjectsSize() {
            return lfsObjectsSize;
        }

        public void setLfsObjectsSize(Long lfsObjectsSize) {
            this.lfsObjectsSize = lfsObjectsSize;
        }

        public Long getJobArtifactsSize() {
            return jobArtifactsSize;
        }

        public void setJobArtifactsSize(Long jobArtifactsSize) {
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
    private Boolean emailsDisabled;
    private Integer extraSharedRunnersMinutesLimit;
    private Boolean membershipLock;
    private Boolean requireTwoFactorAuthentication;
    private Integer twoFactorGracePeriod;
    private GroupParams.ProjectCreationLevel projectCreationLevel;
    private GroupParams.SubgroupCreationLevel subgroupCreationLevel;
    private Integer defaultBranchProtection;
    private Boolean mentionsDisabled;
    private Boolean autoDevopsEnabled;
    private Integer fileTemplateProjectId;
    private Boolean shareWithGroupLock;
    private Statistics statistics;
    private List<Project> projects;
    private List<Project> sharedProjects;
    private Date createdAt;
    private List<ProjectSharedGroup> sharedWithGroups;

    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date markedForDeletionOn;

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

    public Date getMarkedForDeletionOn() {
        return markedForDeletionOn;
    }

    public void setMarkedForDeletionOn(Date markedForDeletionOn) {
        this.markedForDeletionOn = markedForDeletionOn;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }


    public Boolean getEmailsDisabled() {
        return emailsDisabled;
    }

    public void setEmailsDisabled(Boolean emailsDisabled) {
        this.emailsDisabled = emailsDisabled;
    }

    public Integer getExtraSharedRunnersMinutesLimit() {
        return extraSharedRunnersMinutesLimit;
    }

    public void setExtraSharedRunnersMinutesLimit(Integer extraSharedRunnersMinutesLimit) {
        this.extraSharedRunnersMinutesLimit = extraSharedRunnersMinutesLimit;
    }

    public Boolean getMembershipLock() {
        return membershipLock;
    }

    public void setMembershipLock(Boolean membershipLock) {
        this.membershipLock = membershipLock;
    }

    public Boolean getRequireTwoFactorAuthentication() {
        return requireTwoFactorAuthentication;
    }

    public void setRequireTwoFactorAuthentication(Boolean requireTwoFactorAuthentication) {
        this.requireTwoFactorAuthentication = requireTwoFactorAuthentication;
    }

    public Integer getTwoFactorGracePeriod() {
        return twoFactorGracePeriod;
    }

    public void setTwoFactorGracePeriod(Integer twoFactorGracePeriod) {
        this.twoFactorGracePeriod = twoFactorGracePeriod;
    }

    public GroupParams.ProjectCreationLevel getProjectCreationLevel() {
        return projectCreationLevel;
    }

    public void setProjectCreationLevel(GroupParams.ProjectCreationLevel projectCreationLevel) {
        this.projectCreationLevel = projectCreationLevel;
    }

    public GroupParams.SubgroupCreationLevel getSubgroupCreationLevel() {
        return subgroupCreationLevel;
    }

    public void setSubgroupCreationLevel(GroupParams.SubgroupCreationLevel subgroupCreationLevel) {
        this.subgroupCreationLevel = subgroupCreationLevel;
    }

    public Integer getDefaultBranchProtection() {
        return defaultBranchProtection;
    }

    public void setDefaultBranchProtection(Integer defaultBranchProtection) {
        this.defaultBranchProtection = defaultBranchProtection;
    }

    public Boolean getMentionsDisabled() {
        return mentionsDisabled;
    }

    public void setMentionsDisabled(Boolean mentionsDisabled) {
        this.mentionsDisabled = mentionsDisabled;
    }

    public Boolean getAutoDevopsEnabled() {
        return autoDevopsEnabled;
    }

    public void setAutoDevopsEnabled(Boolean autoDevopsEnabled) {
        this.autoDevopsEnabled = autoDevopsEnabled;
    }

    public Integer getFileTemplateProjectId() {
        return fileTemplateProjectId;
    }

    public void setFileTemplateProjectId(Integer fileTemplateProjectId) {
        this.fileTemplateProjectId = fileTemplateProjectId;
    }

    public Boolean getShareWithGroupLock() {
        return shareWithGroupLock;
    }

    public void setShareWithGroupLock(Boolean shareWithGroupLock) {
        this.shareWithGroupLock = shareWithGroupLock;
    }

    public List<ProjectSharedGroup> getSharedWithGroups() {
        return sharedWithGroups;
    }

    public void setSharedWithGroups(List<ProjectSharedGroup> sharedWithGroups) {
        this.sharedWithGroups = sharedWithGroups;
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

    public Group withLfsEnabled(boolean lfsEnabled) {
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

    public Group withEmailsDisabled(Boolean emailsDisabled) {
        this.emailsDisabled = emailsDisabled;
        return (this);
    }

    public Group withExtraSharedRunnersMinutesLimit(Integer extraSharedRunnersMinutesLimit) {
        this.extraSharedRunnersMinutesLimit = extraSharedRunnersMinutesLimit;
        return (this);
    }

    public Group withMembershipLock(Boolean membershipLock) {
        this.membershipLock = membershipLock;
        return (this);
    }

    public Group withRequireTwoFactorAuthentication(Boolean requireTwoFactorAuthentication) {
        this.requireTwoFactorAuthentication = requireTwoFactorAuthentication;
        return (this);
    }

    public Group withTwoFactorGracePeriod(Integer twoFactorGracePeriod) {
        this.twoFactorGracePeriod = twoFactorGracePeriod;
        return (this);
    }

    public Group withProjectCreationLevel(GroupParams.ProjectCreationLevel projectCreationLevel) {
        this.projectCreationLevel = projectCreationLevel;
        return (this);
    }

    public Group withSubgroupCreationLevel(GroupParams.SubgroupCreationLevel subgroupCreationLevel) {
        this.subgroupCreationLevel = subgroupCreationLevel;
        return (this);
    }

    public Group withDefaultBranchProtection(Integer defaultBranchProtection) {
        this.defaultBranchProtection = defaultBranchProtection;
        return (this);
    }

    public Group withMentionsDisabled(Boolean mentionsDisabled) {
        this.mentionsDisabled = mentionsDisabled;
        return (this);
    }

    public Group withAutoDevopsEnabled(Boolean autoDevopsEnabled) {
        this.autoDevopsEnabled = autoDevopsEnabled;
        return (this);
    }

    public Group withFileTemplateProjectId(Integer fileTemplateProjectId) {
        this.fileTemplateProjectId = fileTemplateProjectId;
        return (this);
    }

    public Group withShareWithGroupLock(Boolean shareWithGroupLock) {
        this.shareWithGroupLock = shareWithGroupLock;
        return (this);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
