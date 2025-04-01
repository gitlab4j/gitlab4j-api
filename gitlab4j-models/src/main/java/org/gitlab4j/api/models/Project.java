package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.models.ImportStatus.Status;
import org.gitlab4j.models.Constants.AutoCancelPendingPipelines;
import org.gitlab4j.models.Constants.AutoDevopsDeployStrategy;
import org.gitlab4j.models.Constants.BuildGitStrategy;
import org.gitlab4j.models.Constants.ProjectFeatureVisibilityAccessLevel;
import org.gitlab4j.models.Constants.SquashOption;
import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    // Enum for the merge_method of the Project instance.
    public enum MergeMethod {
        MERGE,
        REBASE_MERGE,
        FF;

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

    private Integer approvalsBeforeMerge;
    private Boolean archived;
    private String avatarUrl;
    private Boolean containerRegistryEnabled;
    private Date createdAt;
    private Long creatorId;
    private String defaultBranch;
    private String description;
    private Integer forksCount;
    private Project forkedFromProject;
    private String httpUrlToRepo;
    private Long id;
    private Boolean isPublic;
    private Boolean issuesEnabled;
    private Boolean jobsEnabled;
    private Date lastActivityAt;
    private Boolean lfsEnabled;
    private MergeMethod mergeMethod;
    private Boolean mergeRequestsEnabled;
    private String name;
    private Namespace namespace;
    private String nameWithNamespace;
    private Boolean onlyAllowMergeIfPipelineSucceeds;
    private Boolean allowMergeOnSkippedPipeline;
    private Boolean onlyAllowMergeIfAllDiscussionsAreResolved;
    private Integer openIssuesCount;
    private Owner owner;
    private String path;
    private String pathWithNamespace;
    private Permissions permissions;
    private Boolean publicJobs;
    private String repositoryStorage;
    private Boolean requestAccessEnabled;
    private String runnersToken;
    private Boolean sharedRunnersEnabled;
    private List<SharedGroup> sharedWithGroups;
    private Boolean snippetsEnabled;
    private String sshUrlToRepo;
    private Integer starCount;

    private List<String> tagList;
    private List<String> topics;
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
    private String buildCoverageRegex;
    private BuildGitStrategy buildGitStrategy;
    private String readmeUrl;
    private Boolean canCreateMergeRequestIn;
    private Status importStatus;
    private Integer ciDefaultGitDepth;
    private Boolean ciForwardDeploymentEnabled;
    private String ciConfigPath;
    private Boolean removeSourceBranchAfterMerge;
    private Boolean autoDevopsEnabled;
    private AutoDevopsDeployStrategy autoDevopsDeployStrategy;
    private Boolean autocloseReferencedIssues;
    private Boolean emailsDisabled;
    private String suggestionCommitMessage;
    private SquashOption squashOption;
    private String mergeCommitTemplate;
    private String squashCommitTemplate;
    private String issueBranchTemplate;
    private String mergeRequestsTemplate;
    private String issuesTemplate;

    private Boolean useCustomTemplate;
    private String externalAuthorizationClassificationLabel;
    private Boolean groupRunnersEnabled;
    private Boolean showDefaultAwardEmojis;
    private Boolean warnAboutPotentiallyUnwantedCharacters;
    private Boolean mirrorTriggerBuilds;
    private AutoCancelPendingPipelines autoCancelPendingPipelines;
    private String repositoryObjectFormat;
    private Boolean onlyAllowMergeIfAllStatusChecksPassed;
    private Integer groupWithProjectTemplatesId;
    private Boolean publicBuilds;
    private Integer buildTimeout;
    private String templateName;
    private Boolean emailsEnabled;
    private Boolean mirror;

    private ProjectFeatureVisibilityAccessLevel analyticsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel buildsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel containerRegistryAccessLevel;
    private ProjectFeatureVisibilityAccessLevel environmentsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel featureFlagsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel forkingAccessLevel;
    private ProjectFeatureVisibilityAccessLevel infrastructureAccessLevel;
    private ProjectFeatureVisibilityAccessLevel issuesAccessLevel;
    private ProjectFeatureVisibilityAccessLevel mergeRequestsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel modelExperimentsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel modelRegistryAccessLevel;
    private ProjectFeatureVisibilityAccessLevel monitorAccessLevel;
    private ProjectFeatureVisibilityAccessLevel pagesAccessLevel;
    private ProjectFeatureVisibilityAccessLevel releasesAccessLevel;
    private ProjectFeatureVisibilityAccessLevel repositoryAccessLevel;
    private ProjectFeatureVisibilityAccessLevel requirementsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel securityAndComplianceAccessLevel;
    private ProjectFeatureVisibilityAccessLevel snippetsAccessLevel;
    private ProjectFeatureVisibilityAccessLevel wikiAccessLevel;

    @JsonProperty("_links")
    private Map<String, String> links;

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
        return this;
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

    public Project withContainerRegistryEnabled(Boolean containerRegistryEnabled) {
        this.containerRegistryEnabled = containerRegistryEnabled;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
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
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project withDescription(String description) {
        this.description = description;
        return this;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project withId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getIssuesEnabled() {
        return issuesEnabled;
    }

    public void setIssuesEnabled(Boolean issuesEnabled) {
        this.issuesEnabled = issuesEnabled;
    }

    public Project withIssuesEnabled(Boolean issuesEnabled) {
        this.issuesEnabled = issuesEnabled;
        return this;
    }

    public Boolean getJobsEnabled() {
        return jobsEnabled;
    }

    public void setJobsEnabled(Boolean jobsEnabled) {
        this.jobsEnabled = jobsEnabled;
    }

    public Project withJobsEnabled(Boolean jobsEnabled) {
        this.jobsEnabled = jobsEnabled;
        return this;
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
        return this;
    }

    public MergeMethod getMergeMethod() {
        return mergeMethod;
    }

    public void setMergeMethod(MergeMethod mergeMethod) {
        this.mergeMethod = mergeMethod;
    }

    public Project withMergeMethod(MergeMethod mergeMethod) {
        this.mergeMethod = mergeMethod;
        return this;
    }

    public Boolean getMergeRequestsEnabled() {
        return mergeRequestsEnabled;
    }

    public void setMergeRequestsEnabled(Boolean mergeRequestsEnabled) {
        this.mergeRequestsEnabled = mergeRequestsEnabled;
    }

    public Project withMergeRequestsEnabled(Boolean mergeRequestsEnabled) {
        this.mergeRequestsEnabled = mergeRequestsEnabled;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    public Project withNamespace(Namespace namespace) {
        this.namespace = namespace;
        return this;
    }

    public Project withNamespaceId(long namespaceId) {
        this.namespace = new Namespace();
        this.namespace.setId(namespaceId);
        return this;
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
        return this;
    }

    public Boolean getAllowMergeOnSkippedPipeline() {
        return allowMergeOnSkippedPipeline;
    }

    public void setAllowMergeOnSkippedPipeline(Boolean allowMergeOnSkippedPipeline) {
        this.allowMergeOnSkippedPipeline = allowMergeOnSkippedPipeline;
    }

    public Project withAllowMergeOnSkippedPipeline(Boolean allowMergeOnSkippedPipeline) {
        this.allowMergeOnSkippedPipeline = allowMergeOnSkippedPipeline;
        return this;
    }

    public Boolean getOnlyAllowMergeIfAllDiscussionsAreResolved() {
        return onlyAllowMergeIfAllDiscussionsAreResolved;
    }

    public void setOnlyAllowMergeIfAllDiscussionsAreResolved(Boolean onlyAllowMergeIfAllDiscussionsAreResolved) {
        this.onlyAllowMergeIfAllDiscussionsAreResolved = onlyAllowMergeIfAllDiscussionsAreResolved;
    }

    public Project withOnlyAllowMergeIfAllDiscussionsAreResolved(Boolean onlyAllowMergeIfAllDiscussionsAreResolved) {
        this.onlyAllowMergeIfAllDiscussionsAreResolved = onlyAllowMergeIfAllDiscussionsAreResolved;
        return this;
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
        return this;
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
        return this;
    }

    public Boolean getPublicJobs() {
        return publicJobs;
    }

    public void setPublicJobs(Boolean publicJobs) {
        this.publicJobs = publicJobs;
    }

    public Project withPublicJobs(Boolean publicJobs) {
        this.publicJobs = publicJobs;
        return this;
    }

    public String getRepositoryStorage() {
        return repositoryStorage;
    }

    public void setRepositoryStorage(String repositoryStorage) {
        this.repositoryStorage = repositoryStorage;
    }

    public Project withRepositoryStorage(String repositoryStorage) {
        this.repositoryStorage = repositoryStorage;
        return this;
    }

    public Boolean getRequestAccessEnabled() {
        return requestAccessEnabled;
    }

    public void setRequestAccessEnabled(Boolean request_access_enabled) {
        this.requestAccessEnabled = request_access_enabled;
    }

    public Project withRequestAccessEnabled(Boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
        return this;
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

    public List<SharedGroup> getSharedWithGroups() {
        return sharedWithGroups;
    }

    public void setSharedWithGroups(List<SharedGroup> sharedWithGroups) {
        this.sharedWithGroups = sharedWithGroups;
    }

    public Project withSharedRunnersEnabled(Boolean sharedRunnersEnabled) {
        this.sharedRunnersEnabled = sharedRunnersEnabled;
        return this;
    }

    public Boolean getSnippetsEnabled() {
        return snippetsEnabled;
    }

    public void setSnippetsEnabled(Boolean snippetsEnabled) {
        this.snippetsEnabled = snippetsEnabled;
    }

    public Project withSnippetsEnabled(Boolean snippetsEnabled) {
        this.snippetsEnabled = snippetsEnabled;
        return this;
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

    /**
     * Tags will be removed in API v5
     */
    @Deprecated
    public List<String> getTagList() {
        return tagList;
    }

    /**
     * Tags will be removed in API v5
     */
    @Deprecated
    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    /**
     * Tags will be removed in API v5
     */
    @Deprecated
    public Project withTagList(List<String> tagList) {
        this.tagList = tagList;
        return this;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Project withTopics(List<String> topics) {
        this.topics = topics;
        return this;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Project withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public Integer getVisibilityLevel() {
        return visibilityLevel;
    }

    public void setVisibilityLevel(Integer visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }

    public Project withVisibilityLevel(Integer visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
        return this;
    }

    public Boolean getWallEnabled() {
        return wallEnabled;
    }

    public void setWallEnabled(Boolean wallEnabled) {
        this.wallEnabled = wallEnabled;
    }

    public Project withWallEnabled(Boolean wallEnabled) {
        this.wallEnabled = wallEnabled;
        return this;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Project withWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return this;
    }

    public Boolean getWikiEnabled() {
        return wikiEnabled;
    }

    public void setWikiEnabled(Boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
    }

    public Project withWikiEnabled(Boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
        return this;
    }

    public Boolean getPrintingMergeRequestLinkEnabled() {
        return printingMergeRequestLinkEnabled;
    }

    public void setPrintingMergeRequestLinkEnabled(Boolean printingMergeRequestLinkEnabled) {
        this.printingMergeRequestLinkEnabled = printingMergeRequestLinkEnabled;
    }

    public Project withPrintingMergeRequestLinkEnabled(Boolean printingMergeRequestLinkEnabled) {
        this.printingMergeRequestLinkEnabled = printingMergeRequestLinkEnabled;
        return this;
    }

    public Boolean getResolveOutdatedDiffDiscussions() {
        return resolveOutdatedDiffDiscussions;
    }

    public void setResolveOutdatedDiffDiscussions(Boolean resolveOutdatedDiffDiscussions) {
        this.resolveOutdatedDiffDiscussions = resolveOutdatedDiffDiscussions;
    }

    public Project withResolveOutdatedDiffDiscussions(Boolean resolveOutdatedDiffDiscussions) {
        this.resolveOutdatedDiffDiscussions = resolveOutdatedDiffDiscussions;
        return this;
    }

    public Boolean getInitializeWithReadme() {
        return initializeWithReadme;
    }

    public void setInitializeWithReadme(Boolean initializeWithReadme) {
        this.initializeWithReadme = initializeWithReadme;
    }

    public Project withInitializeWithReadme(Boolean initializeWithReadme) {
        this.initializeWithReadme = initializeWithReadme;
        return this;
    }

    public Boolean getPackagesEnabled() {
        return packagesEnabled;
    }

    public void setPackagesEnabled(Boolean packagesEnabled) {
        this.packagesEnabled = packagesEnabled;
    }

    public Project withPackagesEnabled(Boolean packagesEnabled) {
        this.packagesEnabled = packagesEnabled;
        return this;
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

    public List<CustomAttribute> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
    }

    public static boolean isValid(Project project) {
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
     * @param path      the project path
     * @return a fully qualified project path based on the provided namespace and project path
     */
    public static final String getPathWithNammespace(String namespace, String path) {
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

    public Boolean getCanCreateMergeRequestIn() {
        return canCreateMergeRequestIn;
    }

    public void setCanCreateMergeRequestIn(Boolean canCreateMergeRequestIn) {
        this.canCreateMergeRequestIn = canCreateMergeRequestIn;
    }

    public Status getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(Status importStatus) {
        this.importStatus = importStatus;
    }

    public Integer getCiDefaultGitDepth() {
        return ciDefaultGitDepth;
    }

    public void setCiDefaultGitDepth(Integer ciDefaultGitDepth) {
        this.ciDefaultGitDepth = ciDefaultGitDepth;
    }

    public Boolean getCiForwardDeploymentEnabled() {
        return ciForwardDeploymentEnabled;
    }

    public void setCiForwardDeploymentEnabled(Boolean ciForwardDeploymentEnabled) {
        this.ciForwardDeploymentEnabled = ciForwardDeploymentEnabled;
    }

    public String getCiConfigPath() {
        return ciConfigPath;
    }

    public void setCiConfigPath(String ciConfigPath) {
        this.ciConfigPath = ciConfigPath;
    }

    public Project withCiConfigPath(String ciConfigPath) {
        this.ciConfigPath = ciConfigPath;
        return this;
    }

    public Boolean getRemoveSourceBranchAfterMerge() {
        return removeSourceBranchAfterMerge;
    }

    public void setRemoveSourceBranchAfterMerge(Boolean removeSourceBranchAfterMerge) {
        this.removeSourceBranchAfterMerge = removeSourceBranchAfterMerge;
    }

    public Project withRemoveSourceBranchAfterMerge(Boolean removeSourceBranchAfterMerge) {
        this.removeSourceBranchAfterMerge = removeSourceBranchAfterMerge;
        return this;
    }

    public Boolean getAutoDevopsEnabled() {
        return autoDevopsEnabled;
    }

    public void setAutoDevopsEnabled(Boolean autoDevopsEnabled) {
        this.autoDevopsEnabled = autoDevopsEnabled;
    }

    public Project withAutoDevopsEnabled(Boolean autoDevopsEnabled) {
        this.autoDevopsEnabled = autoDevopsEnabled;
        return this;
    }

    public AutoDevopsDeployStrategy getAutoDevopsDeployStrategy() {
        return autoDevopsDeployStrategy;
    }

    public void setAutoDevopsDeployStrategy(AutoDevopsDeployStrategy autoDevopsDeployStrategy) {
        this.autoDevopsDeployStrategy = autoDevopsDeployStrategy;
    }

    public Boolean getAutocloseReferencedIssues() {
        return autocloseReferencedIssues;
    }

    public void setAutocloseReferencedIssues(Boolean autocloseReferencedIssues) {
        this.autocloseReferencedIssues = autocloseReferencedIssues;
    }

    public Project withAutocloseReferencedIssues(Boolean autocloseReferencedIssues) {
        this.autocloseReferencedIssues = autocloseReferencedIssues;
        return this;
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

    public String getSuggestionCommitMessage() {
        return this.suggestionCommitMessage;
    }

    public Project withSuggestionCommitMessage(String suggestionCommitMessage) {
        this.suggestionCommitMessage = suggestionCommitMessage;
        return this;
    }

    public void setSuggestionCommitMessage(String suggestionCommitMessage) {
        this.suggestionCommitMessage = suggestionCommitMessage;
    }

    public SquashOption getSquashOption() {
        return squashOption;
    }

    public void setSquashOption(SquashOption squashOption) {
        this.squashOption = squashOption;
    }

    public Project withSquashOption(SquashOption squashOption) {
        this.squashOption = squashOption;
        return this;
    }

    public String getMergeCommitTemplate() {
        return mergeCommitTemplate;
    }

    public void setMergeCommitTemplate(String mergeCommitTemplate) {
        this.mergeCommitTemplate = mergeCommitTemplate;
    }

    public String getSquashCommitTemplate() {
        return squashCommitTemplate;
    }

    public void setSquashCommitTemplate(String squashCommitTemplate) {
        this.squashCommitTemplate = squashCommitTemplate;
    }

    public String getIssueBranchTemplate() {
        return issueBranchTemplate;
    }

    public void setIssueBranchTemplate(String issueBranchTemplate) {
        this.issueBranchTemplate = issueBranchTemplate;
    }

    public String getMergeRequestsTemplate() {
        return mergeRequestsTemplate;
    }

    public void setMergeRequestsTemplate(String mergeRequestsTemplate) {
        this.mergeRequestsTemplate = mergeRequestsTemplate;
    }

    public String getIssuesTemplate() {
        return issuesTemplate;
    }

    public void setIssuesTemplate(String issuesTemplate) {
        this.issuesTemplate = issuesTemplate;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public Integer getBuildTimeout() {
        return buildTimeout;
    }

    public void setBuildTimeout(Integer buildTimeout) {
        this.buildTimeout = buildTimeout;
    }

    public Project withBuildTimeout(Integer buildTimeout) {
        this.buildTimeout = buildTimeout;
        return this;
    }

    public Boolean getMirrorTriggerBuilds() {
        return mirrorTriggerBuilds;
    }

    public void setMirrorTriggerBuilds(Boolean mirrorTriggerBuilds) {
        this.mirrorTriggerBuilds = mirrorTriggerBuilds;
    }

    public Project withMirrorTriggerBuilds(Boolean mirrorTriggerBuilds) {
        this.mirrorTriggerBuilds = mirrorTriggerBuilds;
        return this;
    }

    public Integer getGroupWithProjectTemplatesId() {
        return groupWithProjectTemplatesId;
    }

    public void setGroupWithProjectTemplatesId(Integer groupWithProjectTemplatesId) {
        this.groupWithProjectTemplatesId = groupWithProjectTemplatesId;
    }

    public Project withGroupWithProjectTemplatesId(Integer groupWithProjectTemplatesId) {
        this.groupWithProjectTemplatesId = groupWithProjectTemplatesId;
        return this;
    }

    public Boolean getGroupRunnersEnabled() {
        return groupRunnersEnabled;
    }

    public void setGroupRunnersEnabled(Boolean groupRunnersEnabled) {
        this.groupRunnersEnabled = groupRunnersEnabled;
    }

    public Project withGroupRunnersEnabled(Boolean groupRunnersEnabled) {
        this.groupRunnersEnabled = groupRunnersEnabled;
        return this;
    }

    public Boolean getOnlyAllowMergeIfAllStatusChecksPassed() {
        return onlyAllowMergeIfAllStatusChecksPassed;
    }

    public void setOnlyAllowMergeIfAllStatusChecksPassed(Boolean onlyAllowMergeIfAllStatusChecksPassed) {
        this.onlyAllowMergeIfAllStatusChecksPassed = onlyAllowMergeIfAllStatusChecksPassed;
    }

    public Project withOnlyAllowMergeIfAllStatusChecksPassed(Boolean onlyAllowMergeIfAllStatusChecksPassed) {
        this.onlyAllowMergeIfAllStatusChecksPassed = onlyAllowMergeIfAllStatusChecksPassed;
        return this;
    }

    public Boolean getPublicBuilds() {
        return publicBuilds;
    }

    public void setPublicBuilds(Boolean publicBuilds) {
        this.publicBuilds = publicBuilds;
    }

    public Project withPublicBuilds(Boolean publicBuilds) {
        this.publicBuilds = publicBuilds;
        return this;
    }

    public String getRepositoryObjectFormat() {
        return repositoryObjectFormat;
    }

    public void setRepositoryObjectFormat(String repositoryObjectFormat) {
        this.repositoryObjectFormat = repositoryObjectFormat;
    }

    public Project withRepositoryObjectFormat(String repositoryObjectFormat) {
        this.repositoryObjectFormat = repositoryObjectFormat;
        return this;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Project withTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public String getExternalAuthorizationClassificationLabel() {
        return externalAuthorizationClassificationLabel;
    }

    public void setExternalAuthorizationClassificationLabel(String externalAuthorizationClassificationLabel) {
        this.externalAuthorizationClassificationLabel = externalAuthorizationClassificationLabel;
    }

    public Project withExternalAuthorizationClassificationLabel(String externalAuthorizationClassificationLabel) {
        this.externalAuthorizationClassificationLabel = externalAuthorizationClassificationLabel;
        return this;
    }

    public AutoCancelPendingPipelines getAutoCancelPendingPipelines() {
        return autoCancelPendingPipelines;
    }

    public void setAutoCancelPendingPipelines(AutoCancelPendingPipelines autoCancelPendingPipelines) {
        this.autoCancelPendingPipelines = autoCancelPendingPipelines;
    }

    public Project withAutoCancelPendingPipelines(AutoCancelPendingPipelines autoCancelPendingPipelines) {
        this.autoCancelPendingPipelines = autoCancelPendingPipelines;
        return this;
    }

    public Boolean getUseCustomTemplate() {
        return useCustomTemplate;
    }

    public void setUseCustomTemplate(Boolean useCustomTemplate) {
        this.useCustomTemplate = useCustomTemplate;
    }

    public Project withUseCustomTemplate(Boolean useCustomTemplate) {
        this.useCustomTemplate = useCustomTemplate;
        return this;
    }

    public Boolean getEmailsEnabled() {
        return emailsEnabled;
    }

    public void setEmailsEnabled(Boolean emailsEnabled) {
        this.emailsEnabled = emailsEnabled;
    }

    public Project withEmailsEnabled(Boolean emailsEnabled) {
        this.emailsEnabled = emailsEnabled;
        return this;
    }

    public Boolean getShowDefaultAwardEmojis() {
        return showDefaultAwardEmojis;
    }

    public void setShowDefaultAwardEmojis(Boolean showDefaultAwardEmojis) {
        this.showDefaultAwardEmojis = showDefaultAwardEmojis;
    }

    public Project withShowDefaultAwardEmojis(Boolean showDefaultAwardEmojis) {
        this.showDefaultAwardEmojis = showDefaultAwardEmojis;
        return this;
    }

    public Boolean getWarnAboutPotentiallyUnwantedCharacters() {
        return warnAboutPotentiallyUnwantedCharacters;
    }

    public void setWarnAboutPotentiallyUnwantedCharacters(Boolean warnAboutPotentiallyUnwantedCharacters) {
        this.warnAboutPotentiallyUnwantedCharacters = warnAboutPotentiallyUnwantedCharacters;
    }

    public Project withWarnAboutPotentiallyUnwantedCharacters(Boolean warnAboutPotentiallyUnwantedCharacters) {
        this.warnAboutPotentiallyUnwantedCharacters = warnAboutPotentiallyUnwantedCharacters;
        return this;
    }

    public Boolean getMirror() {
        return mirror;
    }

    public void setMirror(Boolean mirror) {
        this.mirror = mirror;
    }

    public Project withMirror(Boolean mirror) {
        this.mirror = mirror;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getAnalyticsAccessLevel() {
        return analyticsAccessLevel;
    }

    public void setAnalyticsAccessLevel(ProjectFeatureVisibilityAccessLevel analyticsAccessLevel) {
        this.analyticsAccessLevel = analyticsAccessLevel;
    }

    public Project withAnalyticsAccessLevel(ProjectFeatureVisibilityAccessLevel analyticsAccessLevel) {
        this.analyticsAccessLevel = analyticsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getBuildsAccessLevel() {
        return buildsAccessLevel;
    }

    public void setBuildsAccessLevel(ProjectFeatureVisibilityAccessLevel buildsAccessLevel) {
        this.buildsAccessLevel = buildsAccessLevel;
    }

    public Project withBuildsAccessLevel(ProjectFeatureVisibilityAccessLevel buildsAccessLevel) {
        this.buildsAccessLevel = buildsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getContainerRegistryAccessLevel() {
        return containerRegistryAccessLevel;
    }

    public void setContainerRegistryAccessLevel(ProjectFeatureVisibilityAccessLevel containerRegistryAccessLevel) {
        this.containerRegistryAccessLevel = containerRegistryAccessLevel;
    }

    public Project withContainerRegistryAccessLevel(ProjectFeatureVisibilityAccessLevel containerRegistryAccessLevel) {
        this.containerRegistryAccessLevel = containerRegistryAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getEnvironmentsAccessLevel() {
        return environmentsAccessLevel;
    }

    public void setEnvironmentsAccessLevel(ProjectFeatureVisibilityAccessLevel environmentsAccessLevel) {
        this.environmentsAccessLevel = environmentsAccessLevel;
    }

    public Project withEnvironmentsAccessLevel(ProjectFeatureVisibilityAccessLevel environmentsAccessLevel) {
        this.environmentsAccessLevel = environmentsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getFeatureFlagsAccessLevel() {
        return featureFlagsAccessLevel;
    }

    public void setFeatureFlagsAccessLevel(ProjectFeatureVisibilityAccessLevel featureFlagsAccessLevel) {
        this.featureFlagsAccessLevel = featureFlagsAccessLevel;
    }

    public Project withFeatureFlagsAccessLevel(ProjectFeatureVisibilityAccessLevel featureFlagsAccessLevel) {
        this.featureFlagsAccessLevel = featureFlagsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getForkingAccessLevel() {
        return forkingAccessLevel;
    }

    public void setForkingAccessLevel(ProjectFeatureVisibilityAccessLevel forkingAccessLevel) {
        this.forkingAccessLevel = forkingAccessLevel;
    }

    public Project withForkingAccessLevel(ProjectFeatureVisibilityAccessLevel forkingAccessLevel) {
        this.forkingAccessLevel = forkingAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getInfrastructureAccessLevel() {
        return infrastructureAccessLevel;
    }

    public void setInfrastructureAccessLevel(ProjectFeatureVisibilityAccessLevel infrastructureAccessLevel) {
        this.infrastructureAccessLevel = infrastructureAccessLevel;
    }

    public Project withInfrastructureAccessLevel(ProjectFeatureVisibilityAccessLevel infrastructureAccessLevel) {
        this.infrastructureAccessLevel = infrastructureAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getIssuesAccessLevel() {
        return issuesAccessLevel;
    }

    public void setIssuesAccessLevel(ProjectFeatureVisibilityAccessLevel issuesAccessLevel) {
        this.issuesAccessLevel = issuesAccessLevel;
    }

    public Project withIssuesAccessLevel(ProjectFeatureVisibilityAccessLevel issuesAccessLevel) {
        this.issuesAccessLevel = issuesAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getMergeRequestsAccessLevel() {
        return mergeRequestsAccessLevel;
    }

    public void setMergeRequestsAccessLevel(ProjectFeatureVisibilityAccessLevel mergeRequestsAccessLevel) {
        this.mergeRequestsAccessLevel = mergeRequestsAccessLevel;
    }

    public Project withMergeRequestsAccessLevel(ProjectFeatureVisibilityAccessLevel mergeRequestsAccessLevel) {
        this.mergeRequestsAccessLevel = mergeRequestsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getModelExperimentsAccessLevel() {
        return modelExperimentsAccessLevel;
    }

    public void setModelExperimentsAccessLevel(ProjectFeatureVisibilityAccessLevel modelExperimentsAccessLevel) {
        this.modelExperimentsAccessLevel = modelExperimentsAccessLevel;
    }

    public Project withModelExperimentsAccessLevel(ProjectFeatureVisibilityAccessLevel modelExperimentsAccessLevel) {
        this.modelExperimentsAccessLevel = modelExperimentsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getModelRegistryAccessLevel() {
        return modelRegistryAccessLevel;
    }

    public void setModelRegistryAccessLevel(ProjectFeatureVisibilityAccessLevel modelRegistryAccessLevel) {
        this.modelRegistryAccessLevel = modelRegistryAccessLevel;
    }

    public Project withModelRegistryAccessLevel(ProjectFeatureVisibilityAccessLevel modelRegistryAccessLevel) {
        this.modelRegistryAccessLevel = modelRegistryAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getMonitorAccessLevel() {
        return monitorAccessLevel;
    }

    public void setMonitorAccessLevel(ProjectFeatureVisibilityAccessLevel monitorAccessLevel) {
        this.monitorAccessLevel = monitorAccessLevel;
    }

    public Project withMonitorAccessLevel(ProjectFeatureVisibilityAccessLevel monitorAccessLevel) {
        this.monitorAccessLevel = monitorAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getPagesAccessLevel() {
        return pagesAccessLevel;
    }

    public void setPagesAccessLevel(ProjectFeatureVisibilityAccessLevel pagesAccessLevel) {
        this.pagesAccessLevel = pagesAccessLevel;
    }

    public Project withPagesAccessLevel(ProjectFeatureVisibilityAccessLevel pagesAccessLevel) {
        this.pagesAccessLevel = pagesAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getReleasesAccessLevel() {
        return releasesAccessLevel;
    }

    public void setReleasesAccessLevel(ProjectFeatureVisibilityAccessLevel releasesAccessLevel) {
        this.releasesAccessLevel = releasesAccessLevel;
    }

    public Project withReleasesAccessLevel(ProjectFeatureVisibilityAccessLevel releasesAccessLevel) {
        this.releasesAccessLevel = releasesAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getRepositoryAccessLevel() {
        return repositoryAccessLevel;
    }

    public void setRepositoryAccessLevel(ProjectFeatureVisibilityAccessLevel repositoryAccessLevel) {
        this.repositoryAccessLevel = repositoryAccessLevel;
    }

    public Project withRepositoryAccessLevel(ProjectFeatureVisibilityAccessLevel repositoryAccessLevel) {
        this.repositoryAccessLevel = repositoryAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getRequirementsAccessLevel() {
        return requirementsAccessLevel;
    }

    public void setRequirementsAccessLevel(ProjectFeatureVisibilityAccessLevel requirementsAccessLevel) {
        this.requirementsAccessLevel = requirementsAccessLevel;
    }

    public Project withRequirementsAccessLevel(ProjectFeatureVisibilityAccessLevel requirementsAccessLevel) {
        this.requirementsAccessLevel = requirementsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getSecurityAndComplianceAccessLevel() {
        return securityAndComplianceAccessLevel;
    }

    public void setSecurityAndComplianceAccessLevel(
            ProjectFeatureVisibilityAccessLevel securityAndComplianceAccessLevel) {
        this.securityAndComplianceAccessLevel = securityAndComplianceAccessLevel;
    }

    public Project withSecurityAndComplianceAccessLevel(
            ProjectFeatureVisibilityAccessLevel securityAndComplianceAccessLevel) {
        this.securityAndComplianceAccessLevel = securityAndComplianceAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getSnippetsAccessLevel() {
        return snippetsAccessLevel;
    }

    public void setSnippetsAccessLevel(ProjectFeatureVisibilityAccessLevel snippetsAccessLevel) {
        this.snippetsAccessLevel = snippetsAccessLevel;
    }

    public Project withSnippetsAccessLevel(ProjectFeatureVisibilityAccessLevel snippetsAccessLevel) {
        this.snippetsAccessLevel = snippetsAccessLevel;
        return this;
    }

    public ProjectFeatureVisibilityAccessLevel getWikiAccessLevel() {
        return wikiAccessLevel;
    }

    public void setWikiAccessLevel(ProjectFeatureVisibilityAccessLevel wikiAccessLevel) {
        this.wikiAccessLevel = wikiAccessLevel;
    }

    public Project withWikiAccessLevel(ProjectFeatureVisibilityAccessLevel wikiAccessLevel) {
        this.wikiAccessLevel = wikiAccessLevel;
        return this;
    }

    @JsonIgnore
    public String getLinkByName(String name) {
        if (links == null || links.isEmpty()) {
            return (null);
        }

        return (links.get(name));
    }
}
