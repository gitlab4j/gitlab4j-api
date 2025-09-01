package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;

import org.gitlab4j.models.Constants.DefaultBranchProtectionLevel;
import org.gitlab4j.models.Constants.ProjectCreationLevel;
import org.gitlab4j.models.Constants.SubgroupCreationLevel;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Group extends AbstractGroup<Group> {
    private static final long serialVersionUID = 1L;

    public class Statistics {

        /**
         * The total storage size used by the project.
         */
        @JsonProperty("storage_size")
        private Long storageSize;

        /**
         * The repository size for the project.
         */
        @JsonProperty("repository_size")
        private Long repositorySize;

        /**
         * The size used by LFS (Large File Storage) objects.
         */
        @JsonProperty("lfs_objects_size")
        private Long lfsObjectsSize;

        /**
         * The size of job artifacts associated with the project.
         */
        @JsonProperty("job_artifacts_size")
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

    /**
     * The path of the project.
     */
    @JsonProperty("path")
    private String path;

    /**
     * The description of the project.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The visibility of the project.
     */
    @JsonProperty("visibility")
    private Visibility visibility;

    /**
     * Indicates if LFS (Large File Storage) is enabled for the project.
     */
    @JsonProperty("lfs_enabled")
    private Boolean lfsEnabled;

    /**
     * Indicates if request access is enabled for the project.
     */
    @JsonProperty("request_access_enabled")
    private Boolean requestAccessEnabled;

    /**
     * The parent project ID, if any.
     */
    @JsonProperty("parent_id")
    private Long parentId;

    /**
     * The shared runners minutes limit for the project.
     */
    @JsonProperty("shared_runners_minutes_limit")
    private Integer sharedRunnersMinutesLimit;

    /**
     * The statistics related to the project.
     */
    @JsonProperty("statistics")
    private Statistics statistics;

    /**
     * The list of projects associated with the current project.
     */
    @JsonProperty("projects")
    private List<Project> projects;

    /**
     * The list of shared projects.
     */
    @JsonProperty("shared_projects")
    private List<Project> sharedProjects;

    /**
     * The creation date of the project.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    private Date createdAt;

    /**
     * The groups with which the project is shared.
     */
    @JsonProperty("shared_with_groups")
    private List<SharedGroup> sharedWithGroups;

    /**
     * The custom attributes for the project.
     */
    @JsonProperty("custom_attributes")
    private List<CustomAttribute> customAttributes;

    /**
     * The runners token for the project.
     */
    @JsonProperty("runners_token")
    private String runnersToken;

    /**
     * Indicates if sharing groups outside the hierarchy is prevented.
     */
    @JsonProperty("prevent_sharing_groups_outside_hierarchy")
    private Boolean preventSharingGroupsOutsideHierarchy;

    /**
     * Indicates if forking outside the group is prevented.
     */
    @JsonProperty("prevent_forking_outside_group")
    private Boolean preventForkingOutsideGroup;

    /**
     * The project creation level for the project.
     */
    @JsonProperty("project_creation_level")
    private ProjectCreationLevel projectCreationLevel;

    /**
     * The subgroup creation level for the project.
     */
    @JsonProperty("subgroup_creation_level")
    private SubgroupCreationLevel subgroupCreationLevel;

    /**
     * The default branch protection level for the project.
     */
    @JsonProperty("default_branch_protection")
    private DefaultBranchProtectionLevel defaultBranchProtection;

    /**
     * The date when the project is marked for deletion.
     * Expected in the format (yyyy-MM-dd).
     */
    @JsonProperty("marked_for_deletion_on")
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date markedForDeletionOn;

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

    public Boolean getRequestAccessEnabled() {
        return requestAccessEnabled;
    }

    public void setRequestAccessEnabled(Boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
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

    public List<SharedGroup> getSharedWithGroups() {
        return sharedWithGroups;
    }

    public void setSharedWithGroups(List<SharedGroup> sharedWithGroups) {
        this.sharedWithGroups = sharedWithGroups;
    }

    public String getRunnersToken() {
        return runnersToken;
    }

    public void setRunnersToken(String runnersToken) {
        this.runnersToken = runnersToken;
    }

    public Boolean getPreventSharingGroupsOutsideHierarchy() {
        return preventSharingGroupsOutsideHierarchy;
    }

    public void setPreventSharingGroupsOutsideHierarchy(Boolean preventSharingGroupsOutsideHierarchy) {
        this.preventSharingGroupsOutsideHierarchy = preventSharingGroupsOutsideHierarchy;
    }

    public Boolean getPreventForkingOutsideGroup() {
        return preventForkingOutsideGroup;
    }

    public void setPreventForkingOutsideGroup(Boolean preventForkingOutsideGroup) {
        this.preventForkingOutsideGroup = preventForkingOutsideGroup;
    }

    public ProjectCreationLevel getProjectCreationLevel() {
        return this.projectCreationLevel;
    }

    public void setProjectCreationLevel(ProjectCreationLevel projectCreationLevel) {
        this.projectCreationLevel = projectCreationLevel;
    }

    public SubgroupCreationLevel getSubgroupCreationLevel() {
        return this.subgroupCreationLevel;
    }

    public void setSubgroupCreationLevel(SubgroupCreationLevel subgroupCreationLevel) {
        this.subgroupCreationLevel = subgroupCreationLevel;
    }

    public DefaultBranchProtectionLevel getDefaultBranchProtection() {
        return this.defaultBranchProtection;
    }

    public void setDefaultBranchProtection(DefaultBranchProtectionLevel defaultBranchProtection) {
        this.defaultBranchProtection = defaultBranchProtection;
    }

    public List<CustomAttribute> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
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

    public Group withRequestAccessEnabled(boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
        return this;
    }

    public Group withParentId(Long parentId) {
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

    public Group withPreventSharingGroupsOutsideHierarchy(Boolean preventSharingGroupsOutsideHierarchy) {
        this.preventSharingGroupsOutsideHierarchy = preventSharingGroupsOutsideHierarchy;
        return this;
    }

    public Group withPreventForkingOutsideGroup(Boolean preventForkingOutsideGroup) {
        this.preventForkingOutsideGroup = preventForkingOutsideGroup;
        return this;
    }

    public Group withProjectCreationLevel(ProjectCreationLevel projectCreationLevel) {
        this.projectCreationLevel = projectCreationLevel;
        return this;
    }

    public Group withSubgroupCreationLevel(SubgroupCreationLevel subgroupCreationLevel) {
        this.subgroupCreationLevel = subgroupCreationLevel;
        return this;
    }

    public Group withDefaultBranchProtection(DefaultBranchProtectionLevel defaultBranchProtection) {
        this.defaultBranchProtection = defaultBranchProtection;
        return this;
    }

    public Group withCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
