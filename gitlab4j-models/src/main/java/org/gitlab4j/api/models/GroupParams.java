package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.Constants.DefaultBranchProtectionLevel;
import org.gitlab4j.models.Constants.ProjectCreationLevel;
import org.gitlab4j.models.Constants.SubgroupCreationLevel;
import org.gitlab4j.models.GitLabForm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is utilized by the <code>org.gitlab4j.api.GroupApi#createGroup(GroupParams)</code>
 * and <code>org.gitlab4j.api.GroupApi#updateGroup(Object, GroupParams)</code> methods to set
 * the parameters for the call to the GitLab API.
 */
public class GroupParams implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The name of the project.
     */
    @JsonProperty("name")
    private String name;

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
    private String visibility;

    /**
     * Indicates if sharing with group is locked.
     */
    @JsonProperty("share_with_group_lock")
    private Boolean shareWithGroupLock;

    /**
     * Indicates if two-factor authentication is required.
     */
    @JsonProperty("require_two_factor_authentication")
    private Boolean requireTwoFactorAuthentication;

    /**
     * The grace period for two-factor authentication in days.
     */
    @JsonProperty("two_factor_grace_period")
    private Integer twoFactorGracePeriod;

    /**
     * The project creation level for the project.
     */
    @JsonProperty("project_creation_level")
    private ProjectCreationLevel projectCreationLevel;

    /**
     * Indicates if Auto DevOps is enabled.
     */
    @JsonProperty("auto_devops_enabled")
    private Boolean autoDevopsEnabled;

    /**
     * The subgroup creation level for the project.
     */
    @JsonProperty("subgroup_creation_level")
    private SubgroupCreationLevel subgroupCreationLevel;

    /**
     * Indicates if emails are disabled for the project.
     */
    @JsonProperty("emails_disabled")
    private Boolean emailsDisabled;

    /**
     * Indicates if large file storage (LFS) is enabled for the project.
     */
    @JsonProperty("lfs_enabled")
    private Boolean lfsEnabled;

    /**
     * Indicates if access requests are enabled for the project.
     */
    @JsonProperty("request_access_enabled")
    private Boolean requestAccessEnabled;

    /**
     * The parent project ID of the project.
     */
    @JsonProperty("parent_id")
    private Long parentId;

    /**
     * The shared runners minutes limit for the project.
     */
    @JsonProperty("shared_runners_minutes_limit")
    private Integer sharedRunnersMinutesLimit;

    /**
     * The extra shared runners minutes limit for the project.
     */
    @JsonProperty("extra_shared_runners_minutes_limit")
    private Integer extraSharedRunnersMinutesLimit;

    /**
     * The default branch protection level for the project.
     */
    @JsonProperty("default_branch_protection")
    private DefaultBranchProtectionLevel defaultBranchProtection;

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
     * Indicates if membership lock is enabled.
     */
    @JsonProperty("membership_lock")
    private Boolean membershipLock;

    /**
     * The ID of the file template project.
     */
    @JsonProperty("file_template_project_id")
    private Long fileTemplateProjectId;

    /**
     * The parent group ID for creating nested group. For create only.
     *
     * @param parentId the parent group ID for creating nested group
     * @return this GroupParms instance
     */
    public GroupParams withParentId(Long parentId) {
        this.parentId = parentId;
        return (this);
    }

    /**
     * Prevent adding new members to project membership within this group.  For update only.
     *
     * @param membershipLock if true, prevent adding new members to project membership within this group
     * @return this GroupParms instance
     */
    public GroupParams withMembershipLock(Boolean membershipLock) {
        this.membershipLock = membershipLock;
        return (this);
    }

    /**
     * The ID of a project to load custom file templates from.  For update only.
     *
     * @param fileTemplateProjectId the ID of a project to load custom file templates from
     * @return this GroupParms instance
     */
    public GroupParams withFileTemplateProjectId(Long fileTemplateProjectId) {
        this.fileTemplateProjectId = fileTemplateProjectId;
        return (this);
    }

    public GroupParams withName(String name) {
        this.name = name;
        return (this);
    }

    public GroupParams withPath(String path) {
        this.path = path;
        return (this);
    }

    public GroupParams withDescription(String description) {
        this.description = description;
        return (this);
    }

    public GroupParams withVisibility(String visibility) {
        this.visibility = visibility;
        return (this);
    }

    public GroupParams withShareWithGroupLock(Boolean shareWithGroupLock) {
        this.shareWithGroupLock = shareWithGroupLock;
        return (this);
    }

    public GroupParams withRequireTwoFactorAuthentication(Boolean requireTwoFactorAuthentication) {
        this.requireTwoFactorAuthentication = requireTwoFactorAuthentication;
        return (this);
    }

    public GroupParams withTwoFactorGracePeriod(Integer twoFactorGracePeriod) {
        this.twoFactorGracePeriod = twoFactorGracePeriod;
        return (this);
    }

    public GroupParams withProjectCreationLevel(ProjectCreationLevel projectCreationLevel) {
        this.projectCreationLevel = projectCreationLevel;
        return (this);
    }

    public GroupParams withAutoDevopsEnabled(Boolean autoDevopsEnabled) {
        this.autoDevopsEnabled = autoDevopsEnabled;
        return (this);
    }

    public GroupParams withSubgroupCreationLevel(SubgroupCreationLevel subgroupCreationLevel) {
        this.subgroupCreationLevel = subgroupCreationLevel;
        return (this);
    }

    public GroupParams withEmailsDisabled(Boolean emailsDisabled) {
        this.emailsDisabled = emailsDisabled;
        return (this);
    }

    public GroupParams withLfsEnabled(Boolean lfsEnabled) {
        this.lfsEnabled = lfsEnabled;
        return (this);
    }

    public GroupParams withRequestAccessEnabled(Boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
        return (this);
    }

    public GroupParams withSharedRunnersMinutesLimit(Integer sharedRunnersMinutesLimit) {
        this.sharedRunnersMinutesLimit = sharedRunnersMinutesLimit;
        return (this);
    }

    public GroupParams withExtraSharedRunnersMinutesLimit(Integer extraSharedRunnersMinutesLimit) {
        this.extraSharedRunnersMinutesLimit = extraSharedRunnersMinutesLimit;
        return (this);
    }

    public GroupParams withDefaultBranchProtection(DefaultBranchProtectionLevel defaultBranchProtection) {
        this.defaultBranchProtection = defaultBranchProtection;
        return (this);
    }

    public GroupParams withPreventSharingGroupsOutsideHierarchy(Boolean preventSharingGroupsOutsideHierarchy) {
        this.preventSharingGroupsOutsideHierarchy = preventSharingGroupsOutsideHierarchy;
        return (this);
    }

    public GroupParams withPreventForkingOutsideGroup(Boolean preventForkingOutsideGroup) {
        this.preventForkingOutsideGroup = preventForkingOutsideGroup;
        return (this);
    }

    /**
     * Get the form params for a group create oir update call.
     *
     * @param isCreate set to true for a create group call, false for update
     * @return a GitLabApiForm instance holding the parameters for the group create or update operation
     * @throws RuntimeException if required parameters are missing
     */
    public GitLabForm getForm(boolean isCreate) {

        GitLabForm form = new GitLabForm()
                .withParam("name", name, isCreate)
                .withParam("path", path, isCreate)
                .withParam("description", description)
                .withParam("visibility", visibility)
                .withParam("share_with_group_lock", shareWithGroupLock)
                .withParam("require_two_factor_authentication", requireTwoFactorAuthentication)
                .withParam("two_factor_grace_period", twoFactorGracePeriod)
                .withParam("project_creation_level", projectCreationLevel)
                .withParam("auto_devops_enabled", autoDevopsEnabled)
                .withParam("subgroup_creation_level", subgroupCreationLevel)
                .withParam("emails_disabled", emailsDisabled)
                .withParam("lfs_enabled", lfsEnabled)
                .withParam("request_access_enabled", requestAccessEnabled)
                .withParam("shared_runners_minutes_limit", sharedRunnersMinutesLimit)
                .withParam("extra_shared_runners_minutes_limit", extraSharedRunnersMinutesLimit)
                .withParam("default_branch_protection", defaultBranchProtection)
                .withParam("prevent_sharing_groups_outside_hierarchy", preventSharingGroupsOutsideHierarchy)
                .withParam("prevent_forking_outside_group", preventForkingOutsideGroup);

        if (isCreate) {
            form.withParam("parent_id", parentId);
        } else {
            form.withParam("membership_lock", membershipLock)
                    .withParam("file_template_project_id", fileTemplateProjectId);
        }

        return (form);
    }
}
