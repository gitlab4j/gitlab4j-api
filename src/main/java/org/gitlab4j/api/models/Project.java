
package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.Constants.AutoDevopsDeployStrategy;
import org.gitlab4j.api.Constants.BuildGitStrategy;
import org.gitlab4j.api.ProjectLicense;
import org.gitlab4j.api.models.ImportStatus.Status;
import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Project {

    // Enum for the merge_method of the Project instance.
    public enum MergeMethod {

        MERGE, REBASE_MERGE, FF;

        private static JacksonJsonEnumHelper<MergeMethod> enumHelper = new JacksonJsonEnumHelper<>(MergeMethod.class);

        @JsonCreator
        public static MergeMethod forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    public enum FeatureAccessLevel {
        ENABLED, PRIVATE, DISABLED;

        private static JacksonJsonEnumHelper<FeatureAccessLevel> enumHelper = new JacksonJsonEnumHelper<>(FeatureAccessLevel.class);

        @JsonCreator
        public static FeatureAccessLevel forValue(String value) { return enumHelper.forValue(value);}

        @JsonValue
        public String toValue() { return (enumHelper.toString(this).toLowerCase());}

        @Override
        public String toString() { return this.toValue();}
    }

    public static class ContainerExpirationPolicyAttributes {

        public String getCadence() {
            return cadence;
        }

        public void setCadence(String cadence) {
            this.cadence = cadence;
        }

        public Integer getKeepN() {
            return keepN;
        }

        public void setKeepN(Integer keepN) {
            this.keepN = keepN;
        }

        public String getOlderThan() {
            return olderThan;
        }

        public void setOlderThan(String olderThan) {
            this.olderThan = olderThan;
        }

        public String getNameRegex() {
            return nameRegex;
        }

        public void setNameRegex(String nameRegex) {
            this.nameRegex = nameRegex;
        }

        public String getNameRegexDelete() {
            return nameRegexDelete;
        }

        public void setNameRegexDelete(String nameRegexDelete) {
            this.nameRegexDelete = nameRegexDelete;
        }

        public String getNameRegexKeep() {
            return nameRegexKeep;
        }

        public void setNameRegexKeep(String nameRegexKeep) {
            this.nameRegexKeep = nameRegexKeep;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        private String cadence;
        private Integer keepN;
        private String olderThan;
        private String nameRegex;
        private String nameRegexDelete;
        private String nameRegexKeep;
        private Boolean enabled;

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof ContainerExpirationPolicyAttributes)) {
                return false;
            } else {
                ContainerExpirationPolicyAttributes target = (ContainerExpirationPolicyAttributes) obj;
                return cadence.equals(target.getCadence())
                        //&& (keepN != null ? target.getKeepN() != null : keepN.equals(target.getKeepN()))
                        //&& (olderThan != null ? target.getOlderThan() != null : olderThan.equals(target.getOlderThan()))
                        && (nameRegex != null ? target.getNameRegex() != null : nameRegex.equals(target.getNameRegex()))
                        // && (nameRegexDelete != null ? target.getNameRegexDelete() != null : nameRegexDelete.equals(target.getNameRegexDelete()))
                        && (nameRegexKeep != null ? target.getNameRegexKeep() != null : nameRegexKeep.equals(target.getNameRegexKeep()))
                        && (enabled != null ? target.getEnabled() != null : enabled.equals(target.getEnabled()));
            }

        }

        @Override
        public String toString() {
            return "ContainerExpirationPolicyAttributes{" +
                    "cadence='" + cadence + '\'' +
                    ", keepN=" + keepN +
                    ", olderThan='" + olderThan + '\'' +
                    ", nameRegex='" + nameRegex + '\'' +
                    ", nameRegexDelete='" + nameRegexDelete + '\'' +
                    ", nameRegexKeep='" + nameRegexKeep + '\'' +
                    ", enabled=" + enabled +
                    '}';
        }
    }

    private Integer approvalsBeforeMerge;
    private Boolean archived;
    private String autoCancelPendingPipelines;
    private String avatarUrl;
    private String buildCoverageRegex;
    private BuildGitStrategy buildGitStrategy;
    private Integer buildTimeout;
    private String ciConfigPath;
    private Integer ciDefaultGitDepth;
    private Boolean ciForwardDeploymentEnabled;
    private ContainerExpirationPolicyAttributes containerExpirationPolicy;
    private Boolean containerRegistryEnabled;
    private Date createdAt;
    private Integer creatorId;
    private String defaultBranch;
    private String description;
    private String externalAuthorizationClassificationLabel;
    private Integer forksCount;
    private Project forkedFromProject;
    private String httpUrlToRepo;
    private Integer id;
    private Boolean isPublic;
    private Boolean issuesEnabled;
    private Boolean jobsEnabled;
    private Date lastActivityAt;
    private Boolean lfsEnabled;
    private MergeMethod mergeMethod;
    private Boolean mergeRequestsEnabled;
    private Boolean mirror;
    private Boolean mirrorOverwritesDivergedBranches;
    private Boolean mirrorTriggerBuilds;
    private Integer mirrorUserId;
    private String name;
    private Namespace namespace;
    private String nameWithNamespace;
    private Boolean onlyAllowMergeIfPipelineSucceeds;
    private Boolean onlyAllowMergeIfAllDiscussionsAreResolved;
    private Boolean onlyMirrorProtectedBranches;
    private Integer openIssuesCount;
    private Owner owner;
    private String path;
    private String pathWithNamespace;
    private Permissions permissions;
    private Boolean publicJobs;
    private String repositoryStorage;
    private Boolean requestAccessEnabled;
    private String runnersToken;
    private Boolean serviceDeskEnabled;
    private Boolean sharedRunnersEnabled;
    private List<ProjectSharedGroup> sharedWithGroups;
    private Boolean snippetsEnabled;
    private String sshUrlToRepo;
    private Integer starCount;
    private List<String> tagList;
    private Integer visibilityLevel;
    private Visibility visibility;
    private Boolean wallEnabled;
    private String webUrl;
    private Boolean wikiEnabled;
    private Boolean printingMergeRequestLinkEnabled;
    private Boolean resolveOutdatedDiffDiscussions;
    private ProjectStatistics statistics;
    private Boolean initializeWithReadme;
    private Boolean packagesEnabled;
    private Boolean emptyRepo;
    private String licenseUrl;
    private ProjectLicense license;
    private List<CustomAttribute> customAttributes;
    private String readmeUrl;
    private Boolean canCreateMergeRequestIn;
    private Status importStatus;
    private Boolean removeSourceBranchAfterMerge;
    private Boolean autoDevopsEnabled;
    private AutoDevopsDeployStrategy autoDevopsDeployStrategy;
    private Boolean autocloseReferencedIssues;
    private Boolean emailsDisabled;
    private FeatureAccessLevel issuesAccessLevel;
    private FeatureAccessLevel repositoryAccessLevel;
    private FeatureAccessLevel mergeRequestsAccessLevel;
    private FeatureAccessLevel forkingAccessLevel;
    private FeatureAccessLevel buildsAccessLevel;
    private FeatureAccessLevel wikiAccessLevel;
    private FeatureAccessLevel snippetsAccessLevel;
    private FeatureAccessLevel pagesAccessLevel;


    public Boolean getCiForwardDeploymentEnabled() {
        return ciForwardDeploymentEnabled;
    }

    public void setCiForwardDeploymentEnabled(Boolean ciForwardDeploymentEnabled) {
        this.ciForwardDeploymentEnabled = ciForwardDeploymentEnabled;
    }

    public Project withCiForwardDeploymentEnabled(Boolean ciForwardDeploymentEnabled) {
        this.ciForwardDeploymentEnabled = ciForwardDeploymentEnabled;
        return (this);
    }



    public String getSuggestionCommitMessage() {
        return suggestionCommitMessage;
    }

    public void setSuggestionCommitMessage(String suggestionCommitMessage) {
        this.suggestionCommitMessage = suggestionCommitMessage;
    }

    public Project withSuggestionCommitMessage(String suggestionCommitMessage) {
        this.suggestionCommitMessage = suggestionCommitMessage;
        return (this);
    }

    private String suggestionCommitMessage;

    public FeatureAccessLevel getIssuesAccessLevel() {
        return issuesAccessLevel;
    }

    public void setIssuesAccessLevel(FeatureAccessLevel issuesAccessLevel) {
        this.issuesAccessLevel = issuesAccessLevel;
    }

    public Project withIssuesAccessLevel(FeatureAccessLevel issuesAccessLevel) {
        this.issuesAccessLevel = issuesAccessLevel;
        return (this);
    }

    public FeatureAccessLevel getRepositoryAccessLevel() {
        return repositoryAccessLevel;
    }

    public void setRepositoryAccessLevel(FeatureAccessLevel repositoryAccessLevel) {
        this.repositoryAccessLevel = repositoryAccessLevel;
    }

    public Project withRepositoryAccessLevel(FeatureAccessLevel repositoryAccessLevel) {
        this.repositoryAccessLevel = repositoryAccessLevel;
        return (this);
    }

    public FeatureAccessLevel getMergeRequestsAccessLevel() {
        return mergeRequestsAccessLevel;
    }

    public void setMergeRequestsAccessLevel(FeatureAccessLevel mergeRequestsAccessLevel) {
        this.mergeRequestsAccessLevel = mergeRequestsAccessLevel;
    }

    public Project withMergeRequestsAccessLevel(FeatureAccessLevel mergeRequestsAccessLevel) {
        this.mergeRequestsAccessLevel = mergeRequestsAccessLevel;
        return (this);
    }


    public FeatureAccessLevel getForkingAccessLevel() {
        return forkingAccessLevel;
    }

    public void setForkingAccessLevel(FeatureAccessLevel forkingAccessLevel) {
        this.forkingAccessLevel = forkingAccessLevel;
    }

    public Project withForkingAccessLevel(FeatureAccessLevel forkingAccessLevel) {
        this.forkingAccessLevel = forkingAccessLevel;
        return (this);
    }

    public FeatureAccessLevel getBuildsAccessLevel() {
        return buildsAccessLevel;
    }

    public void setBuildsAccessLevel(FeatureAccessLevel buildsAccessLevel) {
        this.buildsAccessLevel = buildsAccessLevel;
    }

    public Project withBuildsAccessLevel(FeatureAccessLevel buildsAccessLevel) {
        this.buildsAccessLevel = buildsAccessLevel;
        return (this);
    }

    public FeatureAccessLevel getWikiAccessLevel() {
        return wikiAccessLevel;
    }

    public void setWikiAccessLevel(FeatureAccessLevel wikiAccessLevel) {
        this.wikiAccessLevel = wikiAccessLevel;
    }

    public Project withWikiAccessLevel(FeatureAccessLevel wikiAccessLevel) {
        this.wikiAccessLevel = wikiAccessLevel;
        return (this);
    }

    public FeatureAccessLevel getSnippetsAccessLevel() {
        return snippetsAccessLevel;
    }

    public void setSnippetsAccessLevel(FeatureAccessLevel snippetsAccessLevel) {
        this.snippetsAccessLevel = snippetsAccessLevel;
    }

    public Project withSnippetsAccessLevel(FeatureAccessLevel snippetsAccessLevel) {
        this.snippetsAccessLevel = snippetsAccessLevel;
        return (this);
    }

    public FeatureAccessLevel getPagesAccessLevel() {
        return pagesAccessLevel;
    }

    public void setPagesAccessLevel(FeatureAccessLevel pagesAccessLevel) {
        this.pagesAccessLevel = pagesAccessLevel;
    }

    public Project withPagesAccessLevel(FeatureAccessLevel pagesAccessLevel) {
        this.pagesAccessLevel = pagesAccessLevel;
        return (this);
    }

    

    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date markedForDeletionOn;

    public Integer getApprovalsBeforeMerge() {
        return approvalsBeforeMerge;
    }

    public void setApprovalsBeforeMerge(Integer approvalsBeforeMerge) {
        this.approvalsBeforeMerge = approvalsBeforeMerge;
    }

    public Project withApprovalsBeforeMerge(Integer approvalsBeforeMerge) {
        this.approvalsBeforeMerge = approvalsBeforeMerge;
        return (this);
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getContainerRegistryEnabled() {
        return containerRegistryEnabled;
    }

    public void setContainerRegistryEnabled(Boolean containerRegistryEnabled) {
        this.containerRegistryEnabled = containerRegistryEnabled;
    }

    public Project withContainerRegistryEnabled(boolean containerRegistryEnabled) {
        this.containerRegistryEnabled = containerRegistryEnabled;
        return (this);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public Project withDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
        return (this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project withDescription(String description) {
        this.description = description;
        return (this);
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    public Project getForkedFromProject() {
        return forkedFromProject;
    }

    public void setForkedFromProject(Project forkedFromProject) {
        this.forkedFromProject = forkedFromProject;
    }

    public String getHttpUrlToRepo() {
        return httpUrlToRepo;
    }

    public void setHttpUrlToRepo(String httpUrlToRepo) {
        this.httpUrlToRepo = httpUrlToRepo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project withId(Integer id) {
        this.id = id;
        return (this);
    }

    public Boolean getIssuesEnabled() {
        return issuesEnabled;
    }

    public void setIssuesEnabled(Boolean issuesEnabled) {
        this.issuesEnabled = issuesEnabled;
    }

    public Project withIssuesEnabled(boolean issuesEnabled) {
        this.issuesEnabled = issuesEnabled;
        return (this);
    }

    public Boolean getJobsEnabled() {
        return jobsEnabled;
    }

    public void setJobsEnabled(Boolean jobsEnabled) {
        this.jobsEnabled = jobsEnabled;
    }

    public Project withJobsEnabled(boolean jobsEnabled) {
        this.jobsEnabled = jobsEnabled;
        return (this);
    }

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(Date lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public Boolean getLfsEnabled() {
        return lfsEnabled;
    }

    public void setLfsEnabled(Boolean lfsEnabled) {
        this.lfsEnabled = lfsEnabled;
    }

    public Project withLfsEnabled(Boolean lfsEnabled) {
        this.lfsEnabled = lfsEnabled;
        return (this);
    }

    public MergeMethod getMergeMethod() {
        return mergeMethod;
    }

    public void setMergeMethod(MergeMethod mergeMethod) {
        this.mergeMethod = mergeMethod;
    }

    public Project withMergeMethod(MergeMethod mergeMethod) {
        this.mergeMethod = mergeMethod;
        return (this);
    }

    public Boolean getMergeRequestsEnabled() {
        return mergeRequestsEnabled;
    }

    public void setMergeRequestsEnabled(Boolean mergeRequestsEnabled) {
        this.mergeRequestsEnabled = mergeRequestsEnabled;
    }

    public Project withMergeRequestsEnabled(boolean mergeRequestsEnabled) {
        this.mergeRequestsEnabled = mergeRequestsEnabled;
        return (this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project withName(String name) {
        this.name = name;
        return (this);
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    public Project withNamespace(Namespace namespace) {
        this.namespace = namespace;
        return (this);
    }

    public Project withNamespaceId(int namespaceId) {
        this.namespace = new Namespace();
        this.namespace.setId(namespaceId);
        return (this);
    }

    public String getNameWithNamespace() {
        return nameWithNamespace;
    }

    public void setNameWithNamespace(String nameWithNamespace) {
        this.nameWithNamespace = nameWithNamespace;
    }

    public Boolean getOnlyAllowMergeIfPipelineSucceeds() {
        return onlyAllowMergeIfPipelineSucceeds;
    }

    public void setOnlyAllowMergeIfPipelineSucceeds(Boolean onlyAllowMergeIfPipelineSucceeds) {
        this.onlyAllowMergeIfPipelineSucceeds = onlyAllowMergeIfPipelineSucceeds;
    }

    public Project withOnlyAllowMergeIfPipelineSucceeds(Boolean onlyAllowMergeIfPipelineSucceeds) {
        this.onlyAllowMergeIfPipelineSucceeds = onlyAllowMergeIfPipelineSucceeds;
        return (this);
    }

    public Boolean getOnlyAllowMergeIfAllDiscussionsAreResolved() {
        return onlyAllowMergeIfAllDiscussionsAreResolved;
    }

    public void setOnlyAllowMergeIfAllDiscussionsAreResolved(Boolean onlyAllowMergeIfAllDiscussionsAreResolved) {
        this.onlyAllowMergeIfAllDiscussionsAreResolved = onlyAllowMergeIfAllDiscussionsAreResolved;
    }

    public Project withOnlyAllowMergeIfAllDiscussionsAreResolved(Boolean onlyAllowMergeIfAllDiscussionsAreResolved) {
        this.onlyAllowMergeIfAllDiscussionsAreResolved = onlyAllowMergeIfAllDiscussionsAreResolved;
        return (this);
    }

    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Project withPath(String path) {
        this.path = path;
        return (this);
    }

    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Project withPublic(Boolean isPublic) {
        this.isPublic = isPublic;
        return (this);
    }

    public Boolean getPublicJobs() {
        return publicJobs;
    }

    public void setPublicJobs(Boolean publicJobs) {
        this.publicJobs = publicJobs;
    }

    public Project withPublicJobs(boolean publicJobs) {
        this.publicJobs = publicJobs;
        return (this);
    }

    public String getRepositoryStorage() {
        return repositoryStorage;
    }

    public void setRepositoryStorage(String repositoryStorage) {
        this.repositoryStorage = repositoryStorage;
    }

    public Project withRepositoryStorage(String repositoryStorage) {
        this.repositoryStorage = repositoryStorage;
        return (this);
    }

    public Boolean getRequestAccessEnabled() {
        return requestAccessEnabled;
    }

    public void setRequestAccessEnabled(Boolean request_access_enabled) {
        this.requestAccessEnabled = request_access_enabled;
    }

    public Project withRequestAccessEnabled(boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
        return (this);
    }

    public String getRunnersToken() {
        return runnersToken;
    }

    public void setRunnersToken(String runnersToken) {
        this.runnersToken = runnersToken;
    }

    public Boolean getSharedRunnersEnabled() {
        return sharedRunnersEnabled;
    }

    public void setSharedRunnersEnabled(Boolean sharedRunnersEnabled) {
        this.sharedRunnersEnabled = sharedRunnersEnabled;
    }

    public List<ProjectSharedGroup> getSharedWithGroups() {
        return sharedWithGroups;
    }

    public void setSharedWithGroups(List<ProjectSharedGroup> sharedWithGroups) {
        this.sharedWithGroups = sharedWithGroups;
    }

    public Project withSharedRunnersEnabled(boolean sharedRunnersEnabled) {
        this.sharedRunnersEnabled = sharedRunnersEnabled;
        return (this);
    }

    public Boolean getSnippetsEnabled() {
        return snippetsEnabled;
    }

    public void setSnippetsEnabled(Boolean snippetsEnabled) {
        this.snippetsEnabled = snippetsEnabled;
    }

    public Project withSnippetsEnabled(boolean snippetsEnabled) {
        this.snippetsEnabled = snippetsEnabled;
        return (this);
    }

    public String getSshUrlToRepo() {
        return sshUrlToRepo;
    }

    public void setSshUrlToRepo(String sshUrlToRepo) {
        this.sshUrlToRepo = sshUrlToRepo;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Project withTagList(List<String> tagList) {
        this.tagList = tagList;
        return (this);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Project withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return (this);
    }

    public Integer getVisibilityLevel() {
        return visibilityLevel;
    }

    public void setVisibilityLevel(Integer visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }

    public Project withVisibilityLevel(Integer visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
        return (this);
    }

    public Boolean getWallEnabled() {
        return wallEnabled;
    }

    public void setWallEnabled(Boolean wallEnabled) {
        this.wallEnabled = wallEnabled;
    }

    public Project withWallEnabled(Boolean wallEnabled) {
        this.wallEnabled = wallEnabled;
        return (this);
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Project withWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return (this);
    }

    public Boolean getWikiEnabled() {
        return wikiEnabled;
    }

    public void setWikiEnabled(Boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
    }

    public Project withWikiEnabled(boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
        return (this);
    }

    public Boolean getPrintingMergeRequestLinkEnabled() {
        return printingMergeRequestLinkEnabled;
    }

    public void setPrintingMergeRequestLinkEnabled(Boolean printingMergeRequestLinkEnabled) {
        this.printingMergeRequestLinkEnabled = printingMergeRequestLinkEnabled;
    }

    public Project withPrintingMergeRequestLinkEnabled(Boolean printingMergeRequestLinkEnabled) {
        this.printingMergeRequestLinkEnabled = printingMergeRequestLinkEnabled;
        return (this);
    }

    public Boolean getResolveOutdatedDiffDiscussions() {
        return resolveOutdatedDiffDiscussions;
    }

    public void setResolveOutdatedDiffDiscussions(Boolean resolveOutdatedDiffDiscussions) {
        this.resolveOutdatedDiffDiscussions = resolveOutdatedDiffDiscussions;
    }

    public Project withResolveOutdatedDiffDiscussions(boolean resolveOutdatedDiffDiscussions) {
        this.resolveOutdatedDiffDiscussions = resolveOutdatedDiffDiscussions;
        return (this);
    }

    public Boolean getInitializeWithReadme() {
        return initializeWithReadme;
    }

    public void setInitializeWithReadme(Boolean initializeWithReadme) {
        this.initializeWithReadme = initializeWithReadme;
    }

    public Project withInitializeWithReadme(boolean initializeWithReadme) {
        this.initializeWithReadme = initializeWithReadme;
        return (this);
    }

    public Boolean getPackagesEnabled() {
        return packagesEnabled;
    }

    public void setPackagesEnabled(Boolean packagesEnabled) {
        this.packagesEnabled = packagesEnabled;
    }

    public Project withPackagesEnabled(Boolean packagesEnabled) {
        this.packagesEnabled = packagesEnabled;
        return (this);
    }

    public ProjectStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(ProjectStatistics statistics) {
        this.statistics = statistics;
    }

    public Boolean getEmptyRepo() {
        return emptyRepo;
    }

    public void setEmptyRepo(Boolean emptyRepo) {
        this.emptyRepo = emptyRepo;
    }

    public Date getMarkedForDeletionOn() {
        return markedForDeletionOn;
    }

    public void setMarkedForDeletionOn(Date markedForDeletionOn) {
        this.markedForDeletionOn = markedForDeletionOn;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public ProjectLicense getLicense() {
        return license;
    }

    public void setLicense(ProjectLicense license) {
        this.license = license;
    }

    public String getAutoCancelPendingPipelines() {
        return autoCancelPendingPipelines;
    }

    public void setAutoCancelPendingPipelines(String autoCancelPendingPipelines) {
        this.autoCancelPendingPipelines = autoCancelPendingPipelines;
    }

    public Project withAutoCancelPendingPipelines(String autoCancelPendingPipelines) {
        this.autoCancelPendingPipelines = autoCancelPendingPipelines;
        return (this);
    }

    public Integer getBuildTimeout() {
        return buildTimeout;
    }

    public void setBuildTimeout(Integer buildTimeout) {
        this.buildTimeout = buildTimeout;
    }

    public Project withBuildTimeout(Integer buildTimeout) {
        this.buildTimeout = buildTimeout;
        return (this);
    }

    public String getCiConfigPath() {
        return ciConfigPath;
    }

    public void setCiConfigPath(String ciConfigPath) {
        this.ciConfigPath = ciConfigPath;
    }

    public Project withCiConfigPath(String ciConfigPath) {
        this.ciConfigPath = ciConfigPath;
        return (this);
    }

    public ContainerExpirationPolicyAttributes getContainerExpirationPolicy() {
        return containerExpirationPolicy;
    }

    public void setContainerExpirationPolicy(ContainerExpirationPolicyAttributes containerExpirationPolicy) {
        this.containerExpirationPolicy = containerExpirationPolicy;
    }

    public Project withContainerExpirationPolicy(ContainerExpirationPolicyAttributes containerExpirationPolicyAttributes) {
        this.containerExpirationPolicy = containerExpirationPolicyAttributes;
        return (this);
    }

    public String getExternalAuthorizationClassificationLabel() {
        return externalAuthorizationClassificationLabel;
    }

    public void setExternalAuthorizationClassificationLabel(String externalAuthorizationClassificationLabel) {
        this.externalAuthorizationClassificationLabel = externalAuthorizationClassificationLabel;
    }

    public Project withExternalAuthorizationClassificationLabel(String externalAuthorizationClassificationLabel) {
        this.externalAuthorizationClassificationLabel = externalAuthorizationClassificationLabel;
        return (this);
    }

    public Boolean getMirror() {
        return mirror;
    }

    public void setMirror(Boolean mirror) {
        this.mirror = mirror;
    }

    public Project withMirror(Boolean mirror) {
        this.mirror = mirror;
        return (this);
    }

    public Boolean getMirrorOverwritesDivergedBranches() {
        return mirrorOverwritesDivergedBranches;
    }

    public void setMirrorOverwritesDivergedBranches(Boolean mirrorOverwritesDivergedBranches) {
        this.mirrorOverwritesDivergedBranches = mirrorOverwritesDivergedBranches;
    }

    public Project withMirrorOverwritesDivergedBranches(Boolean mirrorOverwritesDivergedBranches) {
        this.mirrorOverwritesDivergedBranches = mirrorOverwritesDivergedBranches;
        return (this);
    }

    public Boolean getMirrorTriggerBuilds() {
        return mirrorTriggerBuilds;
    }

    public void setMirrorTriggerBuilds(Boolean mirrorTriggerBuilds) {
        this.mirrorTriggerBuilds = mirrorTriggerBuilds;
    }

    public Project withMirrorTriggerBuilds(Boolean mirrorTriggerBuilds) {
        this.mirrorTriggerBuilds = mirrorTriggerBuilds;
        return (this);
    }

    public Integer getMirrorUserId() {
        return mirrorUserId;
    }

    public void setMirrorUserId(Integer mirrorUserId) {
        this.mirrorUserId = mirrorUserId;
    }

    public Project withMirrorUserId(Integer mirrorUserId) {
        this.mirrorUserId = mirrorUserId;
        return(this);
    }

    public Boolean getOnlyMirrorProtectedBranches() {
        return onlyMirrorProtectedBranches;
    }

    public void setOnlyMirrorProtectedBranches(Boolean onlyMirrorProtectedBranches) {
        this.onlyMirrorProtectedBranches = onlyMirrorProtectedBranches;
    }

    public Project withOnlyMirrorProtectedBranches(Boolean onlyMirrorProtectedBranches) {
        this.onlyMirrorProtectedBranches = onlyMirrorProtectedBranches;
        return (this);
    }

    public Boolean getServiceDeskEnabled() {
        return serviceDeskEnabled;
    }

    public void setServiceDeskEnabled(Boolean serviceDeskEnabled) {
        this.serviceDeskEnabled = serviceDeskEnabled;
    }

    public Project withServiceDeskEnabled(Boolean serviceDeskEnabled) {
        this.serviceDeskEnabled = serviceDeskEnabled;
        return (this);
    }



    public List<CustomAttribute> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
    }

    public static final boolean isValid(Project project) {
        return (project != null && project.getId() != null);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }

    /**
     * Formats a fully qualified project path based on the provided namespace and project path.
     *
     * @param namespace the namespace, either a user name or group name
     * @param path the project path
     * @return a fully qualified project path based on the provided namespace and project path
     */
    public static final String getPathWithNamespace(String namespace, String path) {
        return (namespace.trim() + "/" + path.trim());
    }

    public String getBuildCoverageRegex() {
        return buildCoverageRegex;
    }

    public void setBuildCoverageRegex(String buildCoverageRegex) {
        this.buildCoverageRegex = buildCoverageRegex;
    }

    public Project withBuildCoverageRegex(String buildCoverageRegex) {
        this.buildCoverageRegex = buildCoverageRegex;
        return this;
    }

    public BuildGitStrategy getBuildGitStrategy() {
        return buildGitStrategy;
    }

    public void setBuildGitStrategy(BuildGitStrategy buildGitStrategy) {
        this.buildGitStrategy = buildGitStrategy;
    }

    public Project withBuildGitStrategy(BuildGitStrategy buildGitStrategy) {
        this.buildGitStrategy = buildGitStrategy;
        return this;
    }

    public String getReadmeUrl() {
        return readmeUrl;
    }

    public void setReadmeUrl(String readmeUrl) {
        this.readmeUrl = readmeUrl;
    }

    public Project withReadmeUrl(String readmeUrl) {
        this.readmeUrl = readmeUrl;
        return (this);
    }

    public Boolean getCanCreateMergeRequestIn() {
        return canCreateMergeRequestIn;
    }

    public void setCanCreateMergeRequestIn(Boolean canCreateMergeRequestIn) {
        this.canCreateMergeRequestIn = canCreateMergeRequestIn;
    }

    public Project withCanCreateMergeRequestIn(Boolean canCreateMergeRequestIn) {
        this.canCreateMergeRequestIn = canCreateMergeRequestIn;
        return (this);
    }

    public Status getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(Status importStatus) {
        this.importStatus = importStatus;
    }

    public Project withImportStatus(Status importStatus) {
        this.importStatus = importStatus;
        return (this);
    }

    public Integer getCiDefaultGitDepth() {
        return ciDefaultGitDepth;
    }

    public void setCiDefaultGitDepth(Integer ciDefaultGitDepth) {
        this.ciDefaultGitDepth = ciDefaultGitDepth;
    }

    public Project withCiDefaultGitDepth(Integer ciDefaultGitDepth) {
        this.ciDefaultGitDepth = ciDefaultGitDepth;
        return (this);
    }

    public Boolean getRemoveSourceBranchAfterMerge() {
        return removeSourceBranchAfterMerge;
    }

    public void setRemoveSourceBranchAfterMerge(Boolean removeSourceBranchAfterMerge) {
        this.removeSourceBranchAfterMerge = removeSourceBranchAfterMerge;
    }


    public Project withRemoveSourceBranchAfterMerge(Boolean removeSourceBranchAfterMerge) {
        this.removeSourceBranchAfterMerge = removeSourceBranchAfterMerge;
        return(this);
    }

    public Boolean getAutoDevopsEnabled() {
        return autoDevopsEnabled;
    }

    public void setAutoDevopsEnabled(Boolean autoDevopsEnabled) {
        this.autoDevopsEnabled = autoDevopsEnabled;
    }

    public Project withAutoDevopsEnabled(Boolean autoDevopsEnabled) {
        this.autoDevopsEnabled = autoDevopsEnabled;
        return (this);
    }

    public AutoDevopsDeployStrategy getAutoDevopsDeployStrategy() {
        return autoDevopsDeployStrategy;
    }

    public void setAutoDevopsDeployStrategy(AutoDevopsDeployStrategy autoDevopsDeployStrategy) {
        this.autoDevopsDeployStrategy = autoDevopsDeployStrategy;
    }

    public Project withAutoDevopsDeployStrategy(AutoDevopsDeployStrategy autoDevopsDeployStrategy) {
        this.autoDevopsDeployStrategy = autoDevopsDeployStrategy;
        return (this);
    }

    public Boolean getAutocloseReferencedIssues() {
        return autocloseReferencedIssues;
    }

    public void setAutocloseReferencedIssues(Boolean autocloseReferencedIssues) {
        this.autocloseReferencedIssues = autocloseReferencedIssues;
    }

    public Project withAutocloseReferencedIssues(Boolean autocloseReferencedIssues) {
        this.autocloseReferencedIssues = autocloseReferencedIssues;
        return (this);
    }

    public Boolean getEmailsDisabled() {
        return emailsDisabled;
    }

    public void setEmailsDisabled(Boolean emailsDisabled) {
        this.emailsDisabled = emailsDisabled;
    }

    public Project withEmailsDisabled(Boolean emailsDisabled) {
        this.emailsDisabled = emailsDisabled;
        return this;
    }


}
